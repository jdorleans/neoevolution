package org.neoevolution;

import org.neo4j.kernel.impl.util.FileUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.neo4j.config.EnableNeo4jRepositories;

import java.io.File;

@Configuration
@EnableNeo4jRepositories(basePackages = {"org.neoevolution.mvc.repository"})
@Import({NeoEvolutionConfiguration.class, NENeo4jConfiguration.class})
public class NeoEvolution {

    public static void main(String[] args) throws Exception {
        FileUtils.deleteRecursively(new File("data"));
        SpringApplication.run(NeoEvolution.class, args);
    }

}