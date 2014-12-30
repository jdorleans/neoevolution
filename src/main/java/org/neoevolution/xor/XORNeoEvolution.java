package org.neoevolution.xor;

import org.neoevolution.configuration.NeoEvolutionConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({NeoEvolutionConfiguration.class, XORNeo4jConfiguration.class})
public class XORNeoEvolution {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(XORNeoEvolution.class, args);
    }

}