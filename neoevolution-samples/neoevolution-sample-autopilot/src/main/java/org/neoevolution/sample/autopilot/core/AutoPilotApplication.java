package org.neoevolution.sample.autopilot.core;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import org.neoevolution.core.activation.GenotypeActivation;
import org.neoevolution.mvc.model.Genotype;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since Jan 11 2015
 */
public class AutoPilotApplication extends ApplicationAdapter {

    private static final int MAX_SCORES = 20;
    private static final float GRAVITY_FORCE = -6f;
    private static final float PLANE_START_X = 100;
    private static final float PLANE_START_Y = 300;
    private static final float PLANE_VELOCITY_X = 2.5f;
    private static final float PLANE_JUMP_IMPULSE = 1f;
    private static final float METER_IN_PIXELS = 100f;

    private static final float MAX_WIDTH = 800;
    private static final float MAX_HEIGHT = 480;
    private static final float WIDTH_CENTER = MAX_WIDTH / 2;
    private static final float HEIGHT_CENTER = MAX_HEIGHT / 2;
    private static final float ROCK_SPACE = 250;
    private static final float CAMERA_STEP = 300;

    private static final short BIT_ROCK = 2;
    private static final short BIT_PLANE = 4;
    private static final short BIT_SENSOR = 8;

    private static final String ROCK = "ROCK";
    private static final String PLANE = "PLANE";
    private static final String SENSOR = "SENSOR";


    private Vector2 p1;
    private Vector2 p2;
    private Vector2 collision;
    private Vector2 collisionNormal;

    private World world;
    private OrthographicCamera camera;

    private SpriteBatch spriteBatch;
    private ShapeRenderer shapeRenderer;
    private Box2DDebugRenderer debugRenderer;

    private Plane plane;
    private Ground ground;
    private Ground ceiling;
    private Array<Rock> rocks;
    private Texture background;
    private float groundOffsetX;

    private Music music;
    private Sound explode;
    private BitmapFont font;
    private TextureRegion readyTR;
    private TextureRegion gameOverTR;
    private List<Fixture> contacts;

    private int scores;
    private State state;
    private boolean isDebug = true;

    private Genotype genotype;
    private GenotypeActivation activation;


    @Override
    public void create()
    {
        Gdx.input.setInputProcessor(new AutoPilotInputs());

        p1 = new Vector2();
        p2 = new Vector2();
        collision = new Vector2();
        collisionNormal = new Vector2();

        spriteBatch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
        debugRenderer = new Box2DDebugRenderer();

        world = new World(new Vector2(0, 0), true);
        world.setContactListener(new AutoPilotCollision());

        camera = new OrthographicCamera();
        camera.setToOrtho(false, MAX_WIDTH, MAX_HEIGHT);

        plane = new Plane();
        rocks = new Array<>();
        ground = new Ground(false);
        ceiling = new Ground(true);
        background = new Texture("assets/background.png");
        createRocks();

        createText();
//        createMusic();
        explode = Gdx.audio.newSound(Gdx.files.internal("assets/explode.wav"));
        contacts = new ArrayList<>();
        activation = new GenotypeActivation();

        reset();
    }

    private void createRocks() {
        for (int i = 0; i < 5; i++) {
            rocks.add(new Rock(0));
        }
    }

    private void createText() {
        font = new BitmapFont(Gdx.files.internal("assets/arial.fnt"));
        readyTR = new TextureRegion(new Texture("assets/ready.png"));
        gameOverTR = new TextureRegion(new Texture("assets/gameover.png"));
    }

    private void createMusic() {
        music = Gdx.audio.newMusic(Gdx.files.internal("assets/music.mp3"));
        music.setLooping(true);
        music.play();
    }


    public void run(Genotype genotype) {
        this.genotype = genotype;
        run();
    }

    private void run() {
        state = State.RUNNING;
        world.setGravity(new Vector2(0, GRAVITY_FORCE));
        plane.body.setLinearVelocity(PLANE_VELOCITY_X, 0);
    }


    public void reset() {
        scores = 0;
        genotype = null;
        state = State.START;
        groundOffsetX = 0;
        plane.reset();
        resetRocks();
        contacts.clear();
        camera.position.x = WIDTH_CENTER;
    }

    private void resetRocks() {
        for (int i = 0; i < 5; i++) {
            rocks.get(i).update(700 + i * ROCK_SPACE);
        }
    }

    private void gameOver()
    {
//        if (!state.isGameOver()) {
//            explode.play();
//        }
        state = State.GAME_OVER;
        world.clearForces();
        world.setGravity(new Vector2(0, 0));
        plane.body.setLinearVelocity(0, 0);
    }

    @Override
    public void render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        world.step(1 / 60f, 6, 2);
        update();
        draw();
    }

    private void update()
    {
        plane.update();
        ground.update();
        ceiling.update();
        updateGameOver();
        updateCamera();
        updateRocks();

        if (genotype != null) {
            activate();
        }
    }

    private void updateGameOver()
    {
        float hh = plane.size.y / 2;

        if (plane.center.y - hh < ground.position.y || plane.center.y + hh > ceiling.position.y) {
            gameOver();
        }
    }

    private void updateCamera()
    {
        camera.position.x = plane.center.x + CAMERA_STEP;

        if (camera.position.x - groundOffsetX > ground.getWidth() + WIDTH_CENTER) {
            groundOffsetX += ground.getWidth();
        }
    }


    private void updateRocks()
    {
        for (Rock rock : rocks)
        {
            if (camera.position.x - rock.getX() > WIDTH_CENTER + rock.getWidth()) {
                rock.update(rock.getX() + rocks.size * ROCK_SPACE);
            }

            if (!rock.counted && plane.center.x > rock.pickCenter.x) {
                rock.counted = true;
                scores++;
            }
        }
    }

    private void activate()
    {
        if (state.isRunning())
        {
            if (scores >= MAX_SCORES) {
                gameOver();
            }
            List<Double> inputs = new ArrayList<>(12);
            double totalHeight = ceiling.position.y - ground.position.y - plane.size.y;
            double distGround = Math.abs(plane.center.y - (plane.size.y/2) - ground.position.y) / totalHeight;
            double distCeiling = Math.abs(plane.center.y + (plane.size.y/2) - ceiling.position.y) / totalHeight;
            inputs.add(distGround);
            inputs.add(distCeiling);

            for (Fixture sensor : plane.sensors)
            {
                double contain = 0;

                if (contacts.contains(sensor)) {
                    contain = 1;
                }
                inputs.add(contain);
            }
            List<Double> outputs = activation.activate(genotype, inputs);

            if (outputs.get(0) >= 0.5) {
                plane.jump();
            }
        }

        if (state.isGameOver()) {
            reset();
        }
    }

    private void draw()
    {
        camera.update();

        spriteBatch.setProjectionMatrix(camera.combined);
        spriteBatch.begin();

        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);

        spriteBatch.draw(background, camera.position.x - background.getWidth() / 2, 0);
        drawRocks();
        drawBoundary();
        drawText();
        spriteBatch.draw(plane.getFrame(), plane.center.x - plane.size.x / 2, plane.center.y - plane.size.y / 2);

        if (isDebug) {
            drawMouseLines();
            drawLinesToBoundary();
        }

        spriteBatch.end();
        shapeRenderer.end();

        if (isDebug) {
            Matrix4 scaleMatrix = camera.combined.cpy().scale(METER_IN_PIXELS, METER_IN_PIXELS, 0);
            debugRenderer.render(world, scaleMatrix);
        }
    }

    private void drawRocks() {
        for (Rock rock : rocks) {
            spriteBatch.draw(rock, rock.getX(), rock.getY());
        }
    }

    private void drawBoundary()
    {
        spriteBatch.draw(ground, groundOffsetX, 0);
        spriteBatch.draw(ground, groundOffsetX + ground.getWidth(), 0);
        spriteBatch.draw(ceiling, groundOffsetX, MAX_HEIGHT - ceiling.getHeight());
        spriteBatch.draw(ceiling, groundOffsetX + ceiling.getWidth(), MAX_HEIGHT - ceiling.getHeight());
    }

    private void drawText()
    {
        if (state.isStart()) {
            spriteBatch.draw(readyTR, camera.position.x - readyTR.getRegionWidth() / 2,
                    HEIGHT_CENTER - readyTR.getRegionHeight() / 2);
        }
        else if (state.isGameOver()) {
            spriteBatch.draw(gameOverTR, camera.position.x - gameOverTR.getRegionWidth() / 2,
                    HEIGHT_CENTER - gameOverTR.getRegionHeight() / 2);
        }
        else if (state.isRunning())
        {
            font.setScale(1);
            font.draw(spriteBatch, "" + scores, camera.position.x, MAX_HEIGHT - 10);

            if (genotype != null) {
                font.setScale(0.5f);
                font.draw(spriteBatch, "" + genotype.toString(), plane.center.x, 30);
            }
        }
    }


    private void drawMouseLines() {
        shapeRenderer.setColor(1, 0, 0, 1);
        shapeRenderer.line(toPixels(p1), toPixels(p2));
        shapeRenderer.setColor(0, 0, 1, 1);
        shapeRenderer.line(collision, collisionNormal);
    }

    private void drawLinesToBoundary() {
        shapeRenderer.setColor(0, 0, 0, 1);
        shapeRenderer.line(plane.center, ground.position);
        shapeRenderer.setColor(1, 0, 0, 1);
        shapeRenderer.line(plane.center, ceiling.position);
    }

    @Override
    public void dispose() {
        font.dispose();
        background.dispose();
        spriteBatch.dispose();
        shapeRenderer.dispose();
    }

    public int getScores() {
        return scores;
    }

    public boolean isRunning() {
        return state.isRunning();
    }


    private class RayCastCollision implements RayCastCallback {

        @Override
        public float reportRayFixture(Fixture fixture, Vector2 point, Vector2 normal, float fraction) {
            collision.set(point);
            collisionNormal.set(normal).add(point);
            System.out.println("Collision Point: " + collision);
            System.out.println("Collision Normal: "+ collisionNormal);
            return 0;
        }

    }


    private RayCastCollision rayCastCollision;

    private class AutoPilotInputs extends InputAdapter {

        private Vector3 screenPos;


        public AutoPilotInputs() {
            screenPos = new Vector3();
            rayCastCollision = new RayCastCollision();
        }

        @Override
        public boolean touchDragged(int screenX, int screenY, int pointer)
        {
            screenPos.set(screenX, screenY, 0);
            camera.unproject(screenPos);

            if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
                p1 = toMeters(screenPos.x, screenPos.y);
            } else if (Gdx.input.isButtonPressed(Input.Buttons.RIGHT)) {
                p2 = toMeters(screenPos.x, screenPos.y);
            }
            world.rayCast(rayCastCollision, p1, p2);
            return true;
        }

        @Override
        public boolean keyUp(int keycode)
        {
            if (keycode == Input.Keys.D) {
                isDebug = !isDebug;
            }
            else if (keycode == Input.Keys.SPACE)
            {
                if (state.isGameOver()) {
                    reset();
                } else if (state.isStart()) {
                    run();
                } else {
                    plane.jump();
                }
            }
            return true;
        }
    }


    private class AutoPilotCollision implements ContactListener {

        private Fixture getSensor(Fixture fixtureA, Fixture fixtureB)
        {
            if (SENSOR.equals(fixtureA.getUserData())) {
                return fixtureA;
            } else if (SENSOR.equals(fixtureB.getUserData())) {
                return fixtureB;
            }
            return null;
        }

        @Override
        public void beginContact(Contact contact)
        {
            Fixture fixtureA = contact.getFixtureA();
            Fixture fixtureB = contact.getFixtureB();

            if (PLANE.equals(fixtureA.getUserData()) || PLANE.equals(fixtureB.getUserData())) {
                gameOver();
            }
            else {
                Fixture sensor = getSensor(fixtureA, fixtureB);

                if (sensor != null && !contacts.contains(sensor)) {
                    contacts.add(sensor);
                }
            }
        }

        @Override
        public void endContact(Contact contact)
        {
            Fixture fixtureA = contact.getFixtureA();
            Fixture fixtureB = contact.getFixtureB();

            if (PLANE.equals(fixtureA.getUserData()) || PLANE.equals(fixtureB.getUserData())) {
                gameOver();
            }
            else {
                Fixture sensor = getSensor(fixtureA, fixtureB);

                if (sensor != null) {
                    contacts.remove(sensor);
                }
            }
        }

        @Override
        public void preSolve(Contact contact, Manifold oldManifold)
        {
            Fixture fixtureA = contact.getFixtureA();
            Fixture fixtureB = contact.getFixtureB();

            if (SENSOR.equals(fixtureA.getUserData()) || SENSOR.equals(fixtureB.getUserData())) {
                contact.setEnabled(false); // avoid sensor knock
            }
        }

        @Override
        public void postSolve(Contact contact, ContactImpulse impulse) { }

    }


    private class Plane {

        float time;
        Body body;
        Vector2 size;
        Vector2 center;
        Animation animation;
        List<Fixture> sensors;

        public Plane()
        {
            TextureRegion frame1 = new TextureRegion(new Texture("assets/plane1.png"));
            TextureRegion frame2 = new TextureRegion(new Texture("assets/plane2.png"));
            TextureRegion frame3 = new TextureRegion(new Texture("assets/plane3.png"));
            animation = new Animation(0.05f, frame1, frame2, frame3, frame2);
            animation.setPlayMode(Animation.PlayMode.LOOP);
            size = new Vector2(frame1.getRegionWidth(), frame1.getRegionHeight());
            body = createBody();
            center = toPixels(body.getWorldCenter());
            createSensors();
        }


        private void update() {
            time += Gdx.graphics.getDeltaTime();
            center = toPixels(body.getWorldCenter());
        }

        private Body createBody()
        {
            CircleShape shape = new CircleShape();
            shape.setRadius(toMeters(size.y)/2);

            BodyDef bodyDef = new BodyDef();
            bodyDef.type = BodyDef.BodyType.DynamicBody;
            bodyDef.fixedRotation = true;
            Body body = world.createBody(bodyDef);
            Fixture fixture = body.createFixture(shape, 1);
            fixture.setUserData(PLANE);
            createFilter(fixture, BIT_PLANE, BIT_ROCK);

            shape.dispose();
            return body;
        }

        // NOTE: LINES AND CHAINS CANNOT COLLIDE!!!
        private void createSensors()
        {
            float maxX = toMeters(size.x) / 2;
            float maxY = toMeters(size.y);
            sensors = new ArrayList<>();

            for (int i = -2; i <= 2; i++)
            {
                PolygonShape shape = new PolygonShape();
                shape.setAsBox(maxX, 0.00001f, new Vector2(maxX * 4, maxY * i), 0);

                Fixture fixture = body.createFixture(shape, 0);
                fixture.setUserData(SENSOR);
                createFilter(fixture, BIT_SENSOR, BIT_ROCK);
                sensors.add(fixture);
                shape.dispose();
            }
        }

        private void reset() {
            body.setAngularVelocity(0);
            body.setTransform(toMeters(PLANE_START_X, PLANE_START_Y), 0);
        }

        private void jump() {
            body.applyLinearImpulse(new Vector2(0, PLANE_JUMP_IMPULSE), body.getWorldCenter(), true);
        }

        private TextureRegion getFrame() {
            return animation.getKeyFrame(time);
        }

    }

    private class Ground extends Sprite {

        boolean invert;
        Vector2 position;

        public Ground(boolean invert)
        {
            super(new Texture("assets/ground.png"));
            this.invert = invert;
            float height = getHeight() - 20;

            if (invert) {
                flip(true, true);
                position = new Vector2(plane.center.x, MAX_HEIGHT - height);
            } else {
                position = new Vector2(plane.center.x, height);
            }
        }

        private void update() {
            position.x = plane.center.x;
        }

    }


    private class Rock extends Sprite {

        Body body;
        boolean invert;
        boolean counted;
        Vector2 pickCenter;

        public Rock(float x) {
            super(new Texture("assets/rock.png"));
            this.pickCenter = new Vector2();
            update(x);
        }

        public void update(float x)
        {
            counted = false;
            invert = MathUtils.randomBoolean();
            float y = invert ? MAX_HEIGHT - getHeight() : 0;
            float pickX = x + 12 + getWidth() / 2;
            setPosition(x, y);
            setFlip(false, invert);

            if (body != null) {
                world.destroyBody(body);
            }
            if (invert) {
                pickCenter.set(pickX, y);
                body = createBody(toMeters(x, MAX_HEIGHT), toMeters(pickCenter), toMeters(getWidth()));
            } else {
                pickCenter.set(pickX, y + getHeight());
                body = createBody(toMeters(x, y), toMeters(pickCenter), toMeters(getWidth()));
            }
        }

        private Body createBody(Vector2 v1, Vector2 v2, float width)
        {
            ChainShape shape = new ChainShape();
            shape.createChain(new Vector2[]{v1, v2, new Vector2(v1.x+width, v1.y)});

            BodyDef bodyDef = new BodyDef();
            bodyDef.type = BodyDef.BodyType.StaticBody;
            Body body = world.createBody(bodyDef);
            Fixture fixture = body.createFixture(shape, 0);
            fixture.setUserData(ROCK);
            createFilter(fixture, BIT_ROCK, (short) (BIT_PLANE | BIT_SENSOR));

            shape.dispose();
            return body;
        }

    }

    private enum State
    {
        START, RUNNING, GAME_OVER;

        boolean isStart() {
            return START.equals(this);
        }

        boolean isRunning() {
            return RUNNING.equals(this);
        }

        boolean isGameOver() {
            return GAME_OVER.equals(this);
        }
    }


    private void createFilter(Fixture fixture, short category, short mask) {
        Filter filter = new Filter();
        filter.categoryBits = category;
        filter.maskBits =  mask;
        fixture.setFilterData(filter);
    }

    private static float toMeters(float pixels) {
        return pixels / METER_IN_PIXELS;
    }

    private static Vector2 toMeters(float x, float y) {
        return new Vector2(toMeters(x), toMeters(y));
    }

    private static Vector2 toMeters(Vector2 v) {
        return toMeters(v.x, v.y);
    }


    private static float toPixels(float meters) {
        return meters * METER_IN_PIXELS;
    }

    private static Vector2 toPixels(float x, float y) {
        return new Vector2(toPixels(x), toPixels(y));
    }

    private static Vector2 toPixels(Vector2 v) {
        return toPixels(v.x, v.y);
    }


    public static void main(String[] args) {
        AutoPilotApplication application = new AutoPilotApplication();
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        LwjglApplication lwjglApplication = new LwjglApplication(application, config);
    }

}
