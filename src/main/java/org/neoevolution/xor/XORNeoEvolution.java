package org.neoevolution.xor;

import org.neoevolution.configuration.NeoEvolutionConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.config.EnableNeo4jRepositories;

@Configuration
@EnableNeo4jRepositories("org.neoevolution.xor")
@NeoEvolutionConfiguration
public class XORNeoEvolution {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(XORNeoEvolution.class, args);
    }

}