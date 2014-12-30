package org.neoevolution;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.neoevolution.util.ObjectUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.config.Neo4jConfiguration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableNeo4jRepositories(basePackages = {"org.neoevolution.xor"})
@EnableTransactionManagement
public class NENeo4jConfiguration extends Neo4jConfiguration {

    private static final String URL = ObjectUtils.getNotNull(System.getenv("NEO4J_URL"), "http://localhost:7474/db/data/");

    public NENeo4jConfiguration() {
        setBasePackage("org.neoevolution.mvc.model", "org.neoevolution.xor");
    }

    @Bean
    public GraphDatabaseService graphDatabaseService() {
//        return new SpringRestGraphDatabase(URL); // Neo4j Rest
        return new GraphDatabaseFactory().newEmbeddedDatabase("data");
    }

}