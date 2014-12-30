package org.neoevolution.configuration;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.neo4j.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.config.Neo4jConfiguration;
import org.springframework.data.neo4j.rest.SpringRestGraphDatabase;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.PostConstruct;
import java.util.List;

@Configuration
@PropertySource("classpath:neoevolution.properties")
@EnableNeo4jRepositories("org.neoevolution.mvc.repository")
@EnableTransactionManagement
public class NENeo4jConfiguration extends Neo4jConfiguration {

    private static final String MODEL_PACKAGE = "org.neoevolution.mvc.model";

    @Value("${neoevolution.neo4j.rest}")
    private Boolean isRest;

    @Value("${neoevolution.neo4j.address}")
    private String address;

    @Value("#{'${neoevolution.neo4j.packages}'.replaceAll('\\s*', '').split(',')}")
    private List<String> packages;


    @PostConstruct
    private void init() {
        configurePackages();
        configureDatabase();
    }

    private void configurePackages() {
        packages.add(MODEL_PACKAGE);
        setBasePackage(packages.toArray(new String[packages.size()]));
    }

    private void configureDatabase()
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