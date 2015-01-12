package org.neoevolution.sample.autopilot;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import org.neoevolution.core.activation.GenotypeActivation;
import org.neoevolution.mvc.model.Genotype;
import org.neoevolution.sample.autopilot.core.AutoPilotApplication;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since Jan 10 2015
 */
public class Application {

    private AutoPilotApplication application;

    private LwjglApplicationConfiguration config;

    private LwjglApplication lwjglApplication;

    public Application()
    {
        if (application == null) {
            application = new AutoPilotApplication(new GenotypeActivation());
            config = new LwjglApplicationConfiguration();
            lwjglApplication = new LwjglApplication(application, config);
        }
    }

    public boolean isRunning() {
        return application.isRunning();
    }

    public void start(Genotype genotype) {
        application.start(genotype);
    }

    public void resetWorld() {
        application.resetWorld();
    }

    public int getScore() {
        return application.getScore();
    }

}
