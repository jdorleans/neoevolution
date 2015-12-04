package org.neoevolution.configuration;

import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.SessionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.data.neo4j.config.Neo4jConfiguration;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.server.Neo4jServer;
import org.springframework.data.neo4j.server.RemoteServer;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.List;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since 1.0
 */
@Configuration
@PropertySource("classpath:neoevolution.properties")
@EnableNeo4jRepositories("org.neoevolution.mvc.repository")
@EnableTransactionManagement
public class NENeo4jConfiguration extends Neo4jConfiguration {

    private static final String MODEL_PACKAGE = "org.neoevolution.mvc.model";

    @Value("${neoevolution.neo4j.login}")
    private String login;

    @Value("${neoevolution.neo4j.password}")
    private String password;

    @Value("${neoevolution.neo4j.url}")
    private String url;

    @Value("#{'${neoevolution.neo4j.packages}'.replaceAll('\\s*', '').split(',')}")
    private List<String> packages;


    @PostConstruct
    private void init() throws IOException {
        packages.add(MODEL_PACKAGE);
    }


    @Override
    public Neo4jServer neo4jServer() {
        return new RemoteServer(url, login, password);
    }

    @Override
    public SessionFactory getSessionFactory() {
        return new SessionFactory(packages.toArray(new String[packages.size()]));
    }

    @Override
    @Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
    public Session getSession() throws Exception {
        return super.getSession();
    }

}