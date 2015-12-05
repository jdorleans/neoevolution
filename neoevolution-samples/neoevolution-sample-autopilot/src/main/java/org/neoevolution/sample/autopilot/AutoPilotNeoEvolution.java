package org.neoevolution.sample.autopilot;

import org.neoevolution.configuration.NeoEvolutionConfiguration;
import org.neoevolution.sample.autopilot.core.AutoPilotApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since 1.0
 */
@Configuration
@EnableNeo4jRepositories("org.neoevolution.sample.autopilot.mvc.repository")
@Import(NeoEvolutionConfiguration.class)
public class AutoPilotNeoEvolution {

    public static final AutoPilotApplication application = new AutoPilotApplication();

    public static void main(String[] args) {
        SpringApplication.run(AutoPilotNeoEvolution.class, args);
    }

}