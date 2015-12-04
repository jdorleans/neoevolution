package org.neoevolution.sample.xor;

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
@Import(NeoEvolutionConfiguration.class)
@EnableNeo4jRepositories("org.neoevolution.sample.xor.mvc.repository")
public class XORNeoEvolution {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(XORNeoEvolution.class, args);
    }

}