package org.neoevolution;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.neo4j.kernel.impl.util.FileUtils;
import org.neoevolution.core.GeneticAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.config.Neo4jConfiguration;

import java.io.File;
import java.util.Arrays;

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
    public void run(String... args) throws Exception {
        geneticAlgorithm.evolve();
    }

    public static void main(String[] args) throws Exception
    {
        FileUtils.deleteRecursively(new File("data"));
        ConfigurableApplicationContext ctx = SpringApplication.run(Application.class, args);

        System.out.println("Let's inspect the beans provided by Spring Boot:");
        String[] beanNames = ctx.getBeanDefinitionNames();
        Arrays.sort(beanNames);
        for (String beanName : beanNames) {
            System.out.println(beanName);
        }
    }

}