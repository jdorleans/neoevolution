package org.neoevolution.sample.autopilot;

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
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since Jan 11 2015
 */
public class AutoPilotTest extends ApplicationAdapter {

    private static final float GRAVITY_FORCE = 150;
    private static final float PLANE_START_X = 50;
    private static final float PLANE_START_Y = 240;
    private static final float PLANE_VELOCITY_X = 200;
    private static final float PLANE_JUMP_IMPULSE = 350;
    private static final float METER_IN_PIXELS = 100f;

    private static final float MAX_WIDTH = 800;
    private static final float MAX_HEIGHT = 480;
    private static final float WIDTH_CENTER = MAX_WIDTH / 2;
    private static final float HEIGHT_CENTER = MAX_HEIGHT / 2;

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
    private Vector2 groundCenterPos;
    private Vector2 ceilingCenterPos;

    private Music music;
    private Sound explode;
    private BitmapFont font;
    private TextureRegion readyTR;
    private TextureRegion gameOverTR;

    private int scores;
    private State state;
    private boolean isDebug;


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
        camera = new OrthographicCamera();
        camera.setToOrtho(false, MAX_WIDTH, MAX_HEIGHT);

        plane = new Plane();
        rocks = new Array<>();
        ground = new Ground(false);
        ceiling = new Ground(true);
        background = new Texture("assets/background.png");
        groundCenterPos = new Vector2();
        ceilingCenterPos = new Vector2();

        createText();
//        createMusic();
        explode = Gdx.audio.newSound(Gdx.files.internal("assets/explode.wav"));

        reset();
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


    public void reset()
    {
        state = State.RUNNING;
        groundOffsetX = 0;
        camera.position.x = WIDTH_CENTER;
        plane.reset();
        plane.body.setLinearVelocity(100, 0);
        createRocks();
    }

    private void createRocks()
    {
        rocks.clear();

        for (int i = 0; i < 5; i++) {
            rocks.add(new Rock(700 + i * 200));
        }
    }

    private void gameOver()
    {
//        if (!state.isGameOver()) {
//            explode.play();
//        }
        state = State.GAME_OVER;
        plane.body.setLinearVelocity(0, 0);
    }

    @Override
    public void render()
    {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        world.step(1 / 60f, 6, 2);
        update();
        draw();
    }

    private void update()
    {
        plane.update();

        camera.position.x = plane.center.x + 350;
        groundCenterPos.set(plane.center.x, ground.getY() + ground.getHeight());
        ceilingCenterPos.set(plane.center.x, MAX_HEIGHT - ceiling.getHeight());

        if (camera.position.x - groundOffsetX > ground.getWidth() + WIDTH_CENTER) {
            groundOffsetX += ground.getWidth();
        }

        if (plane.center.y < groundCenterPos.y || plane.center.y > ceilingCenterPos.y) {
            gameOver();
        }
        updateRocks();
//        updateOnTouch();
    }


    private void updateRocks()
    {
        for (Rock rock : rocks)
        {
            if (camera.position.x - rock.getX() > WIDTH_CENTER + rock.getWidth()) {
                rock.update(rock.getX() + 5 * 200);
            }

//            if (planeBox.overlaps(rockBox)) {
//                gameOver();
//            }
            if (rock.getX() < plane.center.x && !rock.counted) {
                scores++;
                rock.counted = true;
            }
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
        spriteBatch.draw(plane.getFrame(), plane.center.x - plane.size.x/2, plane.center.y - plane.size.y/2);

        drawMouseLines();
        drawLinesToBoundary();

        spriteBatch.end();
        shapeRenderer.end();
        debugRenderer.render(world, camera.combined);
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
        else if (state.isRunning()) {
            font.draw(spriteBatch, "" + scores, camera.position.x, MAX_HEIGHT - 10);
        }
    }

    private void drawLinesToBoundary()
    {
        if (isDebug) {
            shapeRenderer.setColor(0, 0, 0, 1);
            shapeRenderer.line(plane.center, groundCenterPos);
            shapeRenderer.setColor(1, 0, 0, 1);
            shapeRenderer.line(plane.center, ceilingCenterPos);
        }
    }


    private void drawMouseLines() {
        shapeRenderer.setColor(1, 0, 0, 1);
        shapeRenderer.line(p1, p2);
        shapeRenderer.setColor(0, 0, 1, 1);
        shapeRenderer.line(collision, collisionNormal);
    }

    @Override
    public void dispose() {
        font.dispose();
        background.dispose();
        spriteBatch.dispose();
        shapeRenderer.dispose();
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


    private class AutoPilotInputs extends InputAdapter {

        private Vector3 screenPos;

        private RayCastCollision rayCastCollision;

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
                p1.set(screenPos.x, screenPos.y);
            } else if (Gdx.input.isButtonPressed(Input.Buttons.RIGHT)) {
                p2.set(screenPos.x, screenPos.y);
            }
            world.rayCast(rayCastCollision, p1, p2);
            return true;
        }

        @Override
        public boolean keyUp(int keycode)
        {
            if (keycode == Input.Keys.SPACE) {
                isDebug = !isDebug;
            }
            return true;
        }
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
            center = body.getWorldCenter();
        }

        private void update() {
            time += Gdx.graphics.getDeltaTime();
            center = body.getWorldCenter();
        }

        private Body createBody()
        {
            PolygonShape shape = new PolygonShape();
            shape.setAsBox(size.x, size.y);

            BodyDef bodyDef = new BodyDef();
            bodyDef.type = BodyDef.BodyType.DynamicBody;
            bodyDef.position.set(PLANE_START_X, PLANE_START_Y);
            Body body = world.createBody(bodyDef);

            FixtureDef fixtureDef = new FixtureDef();
            fixtureDef.shape = shape;
            fixtureDef.density = 1;
            fixtureDef.friction = 0;
            fixtureDef.restitution = 0;
            body.createFixture(fixtureDef);

            shape.dispose();
            return body;
        }

        private void reset() {
//            sprite.setPosition(0f,0f);
            body.setTransform(PLANE_START_X, PLANE_START_Y, 0);
            body.setLinearVelocity(0, 0);
            body.setAngularVelocity(0);
        }

        private TextureRegion getFrame() {
            return animation.getKeyFrame(time);
        }

    }

    private class Ground extends Sprite {

        public Ground(boolean invert)
        {
            super(new Texture("assets/ground.png"));

            if (invert) {
                flip(true, true);
            }
        }

    }


    private class Rock extends Sprite {

        Body body;
        boolean isDown;
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
            isDown = MathUtils.randomBoolean();
            float y = isDown ? MAX_HEIGHT - getHeight() : 0;
            float pickX = x + 12 + getWidth() / 2;
            setY(y);
            setX(x);

            if (body != null) {
                world.destroyBody(body);
            }
            if (isDown) {
                flip(false, true);
                pickCenter.set(pickX, y);
                body = createBody(new Vector2(x, MAX_HEIGHT), pickCenter, getWidth());
            } else {
                pickCenter.set(pickX, y + getHeight());
                body = createBody(new Vector2(x, y), pickCenter, getWidth());
            }
        }

        private Body createBody(Vector2 v1, Vector2 v2, float width)
        {
            ChainShape shape = new ChainShape();
            shape.createChain(new Vector2[]{v1, v2, new Vector2(v1.x+width, v1.y)});

            BodyDef bodyDef = new BodyDef();
            bodyDef.type = BodyDef.BodyType.KinematicBody;
            Body body = world.createBody(bodyDef);

            FixtureDef fixtureDef = new FixtureDef();
            fixtureDef.shape = shape;
            fixtureDef.density = 1f;
            fixtureDef.friction = 0f;
            fixtureDef.restitution = 0f;
            body.createFixture(fixtureDef);

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

    private static float toMeters(float pixels) {
        return pixels / METER_IN_PIXELS;
    }

    private static float toPixels(float meters) {
        return meters * METER_IN_PIXELS;
    }


    public static void main(String[] args) {
        AutoPilotTest application = new AutoPilotTest();
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        LwjglApplication lwjglApplication = new LwjglApplication(application, config);
    }

}
