package org.neoevolution;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.neo4j.kernel.impl.util.FileUtils;
import org.neoevolution.core.GeneticAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.config.Neo4jConfiguration;

import java.io.File;

@Configuration
@ComponentScan(basePackages = "org.neoevolution")
@EnableAutoConfiguration
@EnableNeo4jRepositories(basePackages = "org.neoevolution.mvc.repository")
public class Application extends Neo4jConfiguration implements CommandLineRunner {

    @Autowired
    private GeneticAlgorithm geneticAlgorithm;

    public Application() {
        setBasePackage("org.neoevolution");
    }

    @Bean
    public GraphDatabaseService graphDatabaseService() {
        return new GraphDatabaseFactory().newEmbeddedDatabase("data");
    }


    @Override
    public void run(String... args) throws Exception
    {
        int runs = 50;
        long startTotal = System.currentTimeMillis();

        for (int i = 0; i < runs; i++) {
            long start = System.currentTimeMillis();
            System.out.println("Running: "+ (i+1));
            geneticAlgorithm.evolve();
            System.out.println("Finished in: " + (System.currentTimeMillis() - start));
        }
        long total = System.currentTimeMillis() - startTotal;
        System.out.println("TOTAL TIME: " + total);
        System.out.println("AVERAGE TIME: " + total/runs);
    }

    public static void main(String[] args) throws Exception {
        FileUtils.deleteRecursively(new File("data"));
        SpringApplication.run(Application.class, args);
    }

}