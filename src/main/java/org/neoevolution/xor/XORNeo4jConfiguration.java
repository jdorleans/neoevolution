package org.neoevolution.xor;

import org.neoevolution.configuration.NENeo4jConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.config.EnableNeo4jRepositories;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since Dec 30 2014
 */

@Configuration
@EnableNeo4jRepositories({"org.neoevolution.xor"})
public class XORNeo4jConfiguration extends NENeo4jConfiguration {

    public XORNeo4jConfiguration() {
        configurePackages("org.neoevolution.xor");
        configureDatabase("data");
//        configureDatabase("http://localhost:7474/db/data/", true); // FIXME - Why Rest Database is VERY SLOW!?
    }

}
