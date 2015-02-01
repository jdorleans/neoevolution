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
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import org.neoevolution.core.activation.GenotypeActivation;
import org.neoevolution.mvc.model.Genotype;
import org.neoevolution.util.Randomizer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since 1.0
 */
public class AutoPilotApplication extends ApplicationAdapter {

    public static final int MAX_ROCKS = 5;
    private static final float MAX_SCORES = MAX_ROCKS * 10;
    private static final float GRAVITY_FORCE = -10f;
    private static final float PLANE_START_X = 100;
    private static final float PLANE_START_Y = 300;
    private static final float PLANE_VELOCITY_X = 2.5f;
    private static final float PLANE_JUMP_IMPULSE = 1f;
    private static final float METER_IN_PIXELS = 100f;

    private static final float MAX_WIDTH = 800;
    private static final float MAX_HEIGHT = 480;
    private static final float HALF_WIDTH = MAX_WIDTH / 2;
    private static final float HALF_HEIGHT = MAX_HEIGHT / 2;
    private static final float ROCK_SPACE = 250;
    private static final float CAMERA_STEP = 300;

    private static final short BIT_ROCK = 2;
    private static final short BIT_PLANE = 4;

    private static final String ROCK = "ROCK";
    private static final String PLANE = "PLANE";


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
    private Rock closestRock;
    private Texture background;
    private float groundOffsetX;

    private Music music;
    private Sound explode;
    private BitmapFont font;
    private TextureRegion readyTR;
    private TextureRegion gameOverTR;

    private int jump;
    private int speed;
    private State state;
    private double scores;
    private double maxScores;
    private int countRockUp;
    private int countRockDown;
    private boolean isDebug;

    private Genotype genotype;
    private GenotypeActivation activation;


    public AutoPilotApplication() {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        LwjglApplication lwjglApplication = new LwjglApplication(this, config);
    }


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

        jump = 0;
        speed = 0;
        maxScores = MAX_SCORES;
        plane = new Plane();
        rocks = new Array<>();
        ground = new Ground(false);
        ceiling = new Ground(true);
        background = new Texture("assets/background.png");
        createRocks();

        createText();
//        createMusic();
//        explode = Gdx.audio.newSound(Gdx.files.internal("assets/explode.wav"));
        activation = new GenotypeActivation();

        reset();
    }

    private void createRocks() {
        for (int i = 0; i < MAX_ROCKS; i++) {
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
        scores = genotype.getFitness();
        run();
    }

    private void run() {
        updatePhysics();
        state = State.RUNNING;
    }

    private void updatePhysics() {
        world.setGravity(new Vector2(0, GRAVITY_FORCE));
        plane.body.setLinearVelocity(PLANE_VELOCITY_X, 0);
    }


    private void reset()
    {
        scores = 0;
        genotype = null;
        groundOffsetX = 0;
        plane.reset();
        resetRocks();
        font.setScale(0.5f);
        camera.position.x = HALF_WIDTH;
        state = State.START;
    }

    private void resetRocks() {
        for (int i = 0; i < MAX_ROCKS; i++) {
            rocks.get(i).update(700 + i * ROCK_SPACE);
        }
    }

    private void gameOver()
    {
//        if (!state.isGameOver()) {
//            explode.play();
//        }
        world.clearForces();
        world.setGravity(new Vector2(0, 0));
        plane.body.setLinearVelocity(0, 0);
        state = State.GAME_OVER;
    }

    @Override
    public void render()
    {
        if (!state.isPause())
        {
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            world.step(1 / (60f - (10 * speed)), 6, 2);
            update();
            draw();
        }
    }

    private void update()
    {
        if (!state.isGameOver())
        {
            plane.update();
            ground.update();
            ceiling.update();
            updateCamera();
            updateRocks();

            if (genotype != null && state.isRunning()) {
                activate();
            }
            updateGameOver();
        }
        else if (genotype != null) {
            reset();
        }
    }

    private void updateGameOver()
    {
        float hh = plane.size.y / 2;

        if (plane.center.y - hh < ground.position.y || plane.center.y + hh > ceiling.position.y) {
            gameOver();
        }

        if (scores >= maxScores) {
            gameOver();
        }
    }

    private void updateCamera()
    {
        camera.position.x = plane.center.x + CAMERA_STEP;

        if (camera.position.x - groundOffsetX > ground.getWidth() + HALF_WIDTH) {
            groundOffsetX += ground.getWidth();
        }
    }

    private void updateRocks()
    {
        closestRock = null;
        double dist = Double.MAX_VALUE;

        for (Rock rock : rocks)
        {
            if (camera.position.x - rock.getX() > HALF_WIDTH + rock.getWidth()) {
                rock.update(rock.getX() + rocks.size * ROCK_SPACE);
            }
            else if (!rock.counted && plane.center.x > rock.pickCenter.x) {
                scores++;
                rock.counted = true;
            }

            if (plane.center.x < rock.pickCenter.x)
            {
                float d = rock.pickCenter.x - plane.center.x;

                if (d < dist) {
                    dist = d;
                    closestRock = rock;
                }
            }
        }
    }

    private void activate()
    {
        List<Double> inputs = new ArrayList<>(5);
        calculateHeight(inputs);
        calculateVelocity(inputs);
        inputs.add(closestRock.invert ? 1d : 0d);
        jump(inputs);
        genotype.setFitness(scores);
    }

    private void jump(List<Double> inputs)
    {
        Double jump = activation.activate(genotype, inputs).get(0);

        if (jump >= 0.5) {
            plane.jump();
        }
    }

    private void calculateHeight(List<Double> inputs)
    {
        double planeHalfY = plane.size.y / 2;
        double maxHeight = ceiling.position.y - ground.position.y - plane.size.y;
        double distGround = Math.abs(plane.center.y - planeHalfY - ground.position.y) / maxHeight;
        double distCeiling = Math.abs(plane.center.y + planeHalfY - ceiling.position.y) / maxHeight;
        inputs.add(distGround);
        inputs.add(distCeiling);
    }

    private void calculateVelocity(List<Double> inputs)
    {
        double verticalVelocity = plane.body.getLinearVelocity().y / -GRAVITY_FORCE;

        if (verticalVelocity >= 0) {
            inputs.add(verticalVelocity);
            inputs.add(0d);
        } else {
            inputs.add(0d);
            inputs.add(-verticalVelocity);
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
                    HALF_HEIGHT - readyTR.getRegionHeight() / 2);
        }
        else if (state.isGameOver()) {
            spriteBatch.draw(gameOverTR, camera.position.x - gameOverTR.getRegionWidth() / 2,
                    HALF_HEIGHT - gameOverTR.getRegionHeight() / 2);
        }
        else if (state.isRunning())
        {
            if (genotype != null) {
                font.draw(spriteBatch, genotype.toString(), plane.center.x, 30);
            } else {
                font.draw(spriteBatch, "Scores: "+ scores, plane.center.x, 30);
            }
            font.draw(spriteBatch, "Speed: "+ (speed+1), camera.position.x + 60, 30);
            font.draw(spriteBatch, "Jump: "+ (jump+1), camera.position.x + 180, 30);
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

    public boolean isRunning() {
        return state.isRunning();
    }

    public void setMaxScores(Double fitness) {
        this.maxScores = fitness;
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
            else if (keycode == Input.Keys.P)
            {
                if (state.isPause()) {
                    state = State.RUNNING;
                } else if (state.isRunning()) {
                    state = State.PAUSE;
                }
                updatePhysics();
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
            else if (keycode == Input.Keys.LEFT) {
                speed -= decrease(speed, 0);
                updatePhysics();
            } else if (keycode == Input.Keys.RIGHT) {
                speed += increase(speed, 5);
                updatePhysics();
            }
            else if (keycode == Input.Keys.UP) {
                jump -= decrease(jump, 0);
            } else if (keycode == Input.Keys.DOWN) {
                jump += increase(jump, 5);
            }
            return true;
        }

        private int decrease(int value, int min) {
            return value > min ? 1 : 0;
        }

        private int increase(int value, int max) {
            return value < max ? 1 : 0;
        }
    }


    private class AutoPilotCollision implements ContactListener {

        @Override
        public void beginContact(Contact contact)
        {
            Fixture fixtureA = contact.getFixtureA();
            Fixture fixtureB = contact.getFixtureB();

            if (fixtureA.getUserData() == PLANE|| fixtureB.getUserData() == PLANE) {
                gameOver();
            }
        }

        @Override
        public void endContact(Contact contact) { }

        @Override
        public void preSolve(Contact contact, Manifold oldManifold) { }

        @Override
        public void postSolve(Contact contact, ContactImpulse impulse) { }

    }


    private class Plane {

        float time;
        Body body;
        Vector2 size;
        Vector2 center;
        Animation animation;

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
            fixture.setFilterData(createFilter(BIT_PLANE, BIT_ROCK));

            shape.dispose();
            return body;
        }

        private void reset() {
            body.setTransform(toMeters(PLANE_START_X, PLANE_START_Y), 0);
        }

        private void jump() {
            body.applyLinearImpulse(new Vector2(0, PLANE_JUMP_IMPULSE + (jump*0.1f)), body.getWorldCenter(), true);
        }

        private TextureRegion getFrame() {
            return animation.getKeyFrame(time);
        }

    }

    private Filter createFilter(short category, short mask) {
        Filter filter = new Filter();
        filter.categoryBits = category;
        filter.maskBits = mask;
        return filter;
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
            updateInvert();
            counted = false;
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

        private void updateInvert()
        {
            invert = Randomizer.randomBoolean();

            if (invert)
            {
                countRockDown++;

                if (countRockDown > 2) {
                    invert = !invert;
                    countRockDown = 0;
                }
            }
            else
            {
                countRockUp++;

                if (countRockUp > 2) {
                    invert = !invert;
                    countRockUp = 0;
                }
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
            fixture.setFilterData(createFilter(BIT_ROCK, BIT_PLANE));

            shape.dispose();
            return body;
        }

    }

    private enum State
    {
        START, RUNNING, PAUSE, GAME_OVER;

        boolean isStart() {
            return START.equals(this);
        }

        boolean isRunning() {
            return RUNNING.equals(this);
        }

        boolean isPause() {
            return PAUSE.equals(this);
        }

        boolean isGameOver() {
            return GAME_OVER.equals(this);
        }
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
        new AutoPilotApplication();
    }

}
