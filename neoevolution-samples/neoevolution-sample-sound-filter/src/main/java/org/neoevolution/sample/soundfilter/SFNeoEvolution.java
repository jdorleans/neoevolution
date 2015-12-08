package org.neoevolution.sample.soundfilter;

import org.neoevolution.configuration.NeoEvolutionConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since 1.0
 */
@Configuration
@EnableNeo4jRepositories("org.neoevolution.sample.soundfilter.mvc.repository")
@Import(NeoEvolutionConfiguration.class)
public class SFNeoEvolution {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(SFNeoEvolution.class, args);
    }

}