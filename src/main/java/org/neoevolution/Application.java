package org.neoevolution;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.neo4j.kernel.impl.util.FileUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.aspectj.EnableSpringConfigured;
import org.springframework.data.neo4j.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.config.Neo4jConfiguration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.io.File;

@Configuration
@ComponentScan(basePackages = "org.neoevolution")
@EnableSpringConfigured
@EnableAutoConfiguration
@EnableNeo4jRepositories(basePackages = "org.neoevolution.mvc.repository")
@EnableTransactionManagement
public class Application extends Neo4jConfiguration {

    public Application() {
        setBasePackage("org.neoevolution");
    }

    @Bean
    public GraphDatabaseService graphDatabaseService() {
        return new GraphDatabaseFactory().newEmbeddedDatabase("data");
    }


    public static void main(String[] args) throws Exception {
        FileUtils.deleteRecursively(new File("data"));
        SpringApplication.run(Application.class, args);
    }

}