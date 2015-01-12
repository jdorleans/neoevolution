package org.neoevolution.sample.autopilot.core;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import org.neoevolution.core.activation.GenotypeActivation;
import org.neoevolution.mvc.model.Genotype;

import java.util.ArrayList;
import java.util.List;

public class AutoPilotApplication extends ApplicationAdapter {

    private static final float PLANE_JUMP_IMPULSE = 350;
    private static final float GRAVITY = -150;
    private static final float PLANE_VELOCITY_X = 200;
    private static final float PLANE_START_Y = 240;
    private static final float PLANE_START_X = 50;

    ShapeRenderer shapeRenderer;
    SpriteBatch batch;
    OrthographicCamera camera;
    OrthographicCamera uiCamera;
    Texture background;
    TextureRegion ground;
    float groundOffsetX = 0;
    TextureRegion ceiling;
    TextureRegion rock;
    TextureRegion rockDown;
    Animation plane;
    TextureRegion ready;
    TextureRegion gameOver;
    BitmapFont font;

    Vector2 planePosition = new Vector2();
    Vector2 planeVelocity = new Vector2();
    float planeStateTime = 0;
    Vector2 gravity = new Vector2();
    Array<Rock> rocks = new Array<Rock>();

    GameState gameState = GameState.START;
    int score = 0;
    Rectangle planeBox = new Rectangle();
    Rectangle rockBox = new Rectangle();

    Music music;
    Sound explode;

    boolean isDebug = true;
    int planeCW, planeCH;
    Vector2 planeCenterPos = new Vector2();
    Vector2 groundCenterPos = new Vector2();
    Vector2 ceilingCenterPos = new Vector2();

    Genotype genotype;
    GenotypeActivation activation;

    public AutoPilotApplication(GenotypeActivation activation) {
        this.activation = activation;
    }

    @Override
    public void create() {
        shapeRenderer = new ShapeRenderer();
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
        uiCamera = new OrthographicCamera();
        uiCamera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        uiCamera.update();

        font = new BitmapFont(Gdx.files.internal("assets/arial.fnt"));

        background = new Texture("assets/background.png");
        ground = new TextureRegion(new Texture("assets/ground.png"));
        ceiling = new TextureRegion(ground);
        ceiling.flip(true, true);

        rock = new TextureRegion(new Texture("assets/rock.png"));
        rockDown = new TextureRegion(rock);
        rockDown.flip(false, true);

        Texture frame1 = new Texture("assets/plane1.png");
        frame1.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        Texture frame2 = new Texture("assets/plane2.png");
        Texture frame3 = new Texture("assets/plane3.png");

        ready = new TextureRegion(new Texture("assets/ready.png"));
        gameOver = new TextureRegion(new Texture("assets/gameover.png"));

        plane = new Animation(0.05f, new TextureRegion(frame1), new TextureRegion(frame2), new TextureRegion(frame3), new TextureRegion(frame2));
        plane.setPlayMode(Animation.PlayMode.LOOP);

//        music = Gdx.audio.newMusic(Gdx.files.internal("assets/music.mp3"));
//        music.setLooping(true);
//        music.play();

        explode = Gdx.audio.newSound(Gdx.files.internal("assets/explode.wav"));
        planeCW = plane.getKeyFrames()[0].getRegionWidth() / 2;
        planeCH = plane.getKeyFrames()[0].getRegionHeight() / 2;

        resetWorld();
    }

    private void updateCenterPosition() {
        planeCenterPos.set(planePosition.x + planeCW, planePosition.y + planeCH);
        ceilingCenterPos.set(planeCenterPos.x, 480 - ceiling.getRegionHeight());
        groundCenterPos.set(planeCenterPos.x, ground.getRegionY() + ground.getRegionHeight());
    }

    public void resetWorld() {
        groundOffsetX = 0;
        planePosition.set(PLANE_START_X, PLANE_START_Y);
        planeVelocity.set(0, 0);
        gravity.set(0, GRAVITY);
        camera.position.x = 400;

        rocks.clear();
        for (int i = 0; i < 5; i++) {
            boolean isDown = MathUtils.randomBoolean();
            rocks.add(new Rock(700 + i * 200, isDown ? 480 - rock.getRegionHeight() : 0, isDown ? rockDown : rock, isDown));
        }
    }

    private void updateWorld()
    {
        float deltaTime = Gdx.graphics.getDeltaTime();
        planeStateTime += deltaTime;
        updateOnTouch();

        if (gameState != GameState.START) {
            planeVelocity.add(gravity);
        }
        planePosition.mulAdd(planeVelocity, deltaTime);
        updateCenterPosition();

        camera.position.x = planePosition.x + 350;
        if (camera.position.x - groundOffsetX > ground.getRegionWidth() + 400) {
            groundOffsetX += ground.getRegionWidth();
        }

        if (planePosition.y < ground.getRegionHeight() - 20 ||
                planePosition.y + plane.getKeyFrames()[0].getRegionHeight() > 480 - ground.getRegionHeight() + 20) {
            gameOver();
        }
        planeBox.set(planePosition.x + 20, planePosition.y, plane.getKeyFrames()[0].getRegionWidth() - 20, plane.getKeyFrames()[0].getRegionHeight());

        for (Rock r : rocks)
        {
            if (camera.position.x - r.position.x > 400 + r.image.getRegionWidth()) {
                r.isDown = MathUtils.randomBoolean();
                r.position.x += 5 * 200;
                r.position.y = r.isDown ? 480 - this.rock.getRegionHeight() : 0;
                r.image = r.isDown ? rockDown : this.rock;
                r.counted = false;
                r.updatePickCenter();
            }
            rockBox.set(r.position.x + (r.image.getRegionWidth() - 30) / 2 + 20,
                    r.position.y, 20, r.image.getRegionHeight() - 10);

            if (planeBox.overlaps(rockBox)) {
                gameOver();
            }
            if (r.position.x < planePosition.x && !r.counted) {
                score++;
                r.counted = true;
            }
        }
    }

    private void updateOnTouch()
    {
        if (gameState == GameState.RUNNING)
        {
            List<Double> inputs = new ArrayList<>(12);
            float distCeil = planeCenterPos.dst(ceilingCenterPos);
            float distGround = planeCenterPos.dst(groundCenterPos);
            inputs.add((double) distCeil);
            inputs.add((double) distGround);

            for (Rock r : rocks) {
                float distRock = planeCenterPos.dst(r.pickCenter);
                inputs.add(r.isDown ? 1d : 0d);
                inputs.add((double) distRock);
            }
            List<Double> outputs = activation.activate(genotype, inputs);

            if (outputs.get(0) >= 0.5)
            {
                if (score >= 20) {
                    gameOver();
                }
                planeVelocity.set(PLANE_VELOCITY_X, PLANE_JUMP_IMPULSE);
//            } else {
//                planeVelocity.set(PLANE_VELOCITY_X, 0);
            }
        }

        if (gameState == GameState.GAME_OVER) {
            gameState = GameState.START;
            resetWorld();
        }
    }

    private void gameOver()
    {
        if (gameState != GameState.GAME_OVER) {
            explode.play();
        }
        planeVelocity.x = 0;
        gameState = GameState.GAME_OVER;
    }

    private void drawWorld()
    {
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(background, camera.position.x - background.getWidth() / 2, 0);

        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        drawLinesToLimits();

        for (Rock r : rocks) {
            drawLinesToRock(r);
            batch.draw(r.image, r.position.x, r.position.y);
        }
        batch.draw(ground, groundOffsetX, 0);
        batch.draw(ground, groundOffsetX + ground.getRegionWidth(), 0);
        batch.draw(ceiling, groundOffsetX, 480 - ceiling.getRegionHeight());
        batch.draw(ceiling, groundOffsetX + ceiling.getRegionWidth(), 480 - ceiling.getRegionHeight());
        batch.draw(plane.getKeyFrame(planeStateTime), planePosition.x, planePosition.y);
        batch.end();
        shapeRenderer.end();

        batch.setProjectionMatrix(uiCamera.combined);
        batch.begin();
        if (gameState == GameState.START) {
            batch.draw(ready, Gdx.graphics.getWidth() / 2 - ready.getRegionWidth() / 2, Gdx.graphics.getHeight() / 2 - ready.getRegionHeight() / 2);
        }
        if (gameState == GameState.GAME_OVER) {
            batch.draw(gameOver, Gdx.graphics.getWidth() / 2 - gameOver.getRegionWidth() / 2, Gdx.graphics.getHeight() / 2 - gameOver.getRegionHeight() / 2);
        }
        if (gameState == GameState.GAME_OVER || gameState == GameState.RUNNING) {
            font.draw(batch, "" + score, Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() - 60);
        }
        batch.end();
    }

    private void drawLinesToLimits()
    {
        if (isDebug) {
            shapeRenderer.setColor(0, 1, 0, 1);
            shapeRenderer.line(planeCenterPos.x, planeCenterPos.y, ceilingCenterPos.x, ceilingCenterPos.y);
            shapeRenderer.setColor(0, 0, 0, 1);
            shapeRenderer.line(planeCenterPos.x, planeCenterPos.y, groundCenterPos.x, groundCenterPos.y);
        }
    }

    private void drawLinesToRock(Rock r)
    {
        if (isDebug)
        {
            if (r.isDown) {
                shapeRenderer.setColor(1, 0, 0, 1);
            } else {
                shapeRenderer.setColor(0, 0, 1, 1);
            }
            shapeRenderer.line(planeCenterPos.x, planeCenterPos.y, r.pickCenter.x, r.pickCenter.y);
        }
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        updateWorld();
        drawWorld();
    }

    @Override
    public void dispose() {
        shapeRenderer.dispose();
        batch.dispose();
        background.dispose();
        font.dispose();
    }

    public void start(Genotype genotype) {
        score = 0;
        this.genotype = genotype;
        gameState = GameState.RUNNING;
    }

    public int getScore() {
        return score;
    }

    public boolean isRunning() {
        return GameState.RUNNING == gameState;
    }

    static class Rock {
        Vector2 position = new Vector2();
        Vector2 pickCenter = new Vector2();
        TextureRegion image;
        boolean counted;
        boolean isDown;

        public Rock(float x, float y, TextureRegion image, boolean isDown) {
            this.position.x = x;
            this.position.y = y;
            this.image = image;
            this.isDown = isDown;
            updatePickCenter();
        }

        public void updatePickCenter() {
            if (isDown) {
                pickCenter.set(position.x + image.getRegionWidth()/2, position.y);
            } else {
                pickCenter.set(position.x + image.getRegionWidth()/2, position.y + image.getRegionHeight());
            }
        }
    }

    static enum GameState {
        START, RUNNING, GAME_OVER
    }
}
