package org.neoevolution.configuration;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.config.Neo4jConfiguration;
import org.springframework.data.neo4j.rest.SpringRestGraphDatabase;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Arrays;

@Configuration
@EnableNeo4jRepositories({"org.neoevolution.mvc.repository"})
@EnableTransactionManagement
public abstract class NENeo4jConfiguration extends Neo4jConfiguration {

    protected static final String MODEL_PACKAGE = "org.neoevolution.mvc.model";

    protected void configurePackages(String... basePackages) {
        String[] packages = Arrays.copyOf(basePackages, basePackages.length+1);
        packages[packages.length-1] = MODEL_PACKAGE;
        setBasePackage(packages);
    }

    protected void configureDatabase(String address) {
        configureDatabase(address, false);
    }

    protected void configureDatabase(String address, boolean isRest)
    {
        if (isRest) {
            setGraphDatabaseService(new SpringRestGraphDatabase(address));
        } else {
            setGraphDatabaseService(new GraphDatabaseFactory().newEmbeddedDatabase(address));
        }
    }

    @Bean
    public GraphDatabaseService graphDatabaseService() {
        return super.getGraphDatabaseService();
    }

}