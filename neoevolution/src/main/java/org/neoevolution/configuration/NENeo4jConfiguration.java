package org.neoevolution.configuration;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.factory.GraphDatabaseBuilder;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.neoevolution.mvc.converter.ActivationToStringConverter;
import org.neoevolution.mvc.converter.StringToActivationConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.ConverterRegistry;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.neo4j.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.config.Neo4jConfiguration;
import org.springframework.data.neo4j.rest.SpringRestGraphDatabase;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.PostConstruct;
import java.io.File;
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

    @Value("${neoevolution.neo4j.rest}")
    private Boolean isRest;

    @Value("${neoevolution.neo4j.address}")
    private String address;

    @Value("#{'${neoevolution.neo4j.packages}'.replaceAll('\\s*', '').split(',')}")
    private List<String> packages;

    @Autowired
    private StringToActivationConverter stringToActivationConverter;

    @Autowired
    private ActivationToStringConverter activationToStringConverter;


    @PostConstruct
    private void init() throws IOException {
        configurePackages();
        configureDatabase();
    }

    private void configurePackages() {
        packages.add(MODEL_PACKAGE);
        setBasePackage(packages.toArray(new String[packages.size()]));
    }

    private void configureDatabase() throws IOException
    {
        if (isRest) {
            setGraphDatabaseService(new SpringRestGraphDatabase(address));
        }
        else {
            File file = new ClassPathResource("neo4j.properties").getFile();
            GraphDatabaseBuilder dbBuilder = new GraphDatabaseFactory().newEmbeddedDatabaseBuilder(address);

            if (file.exists()) {
                dbBuilder = dbBuilder.loadPropertiesFromFile(file.getAbsolutePath());
            }
            setGraphDatabaseService(dbBuilder.newGraphDatabase());
        }
    }

    @Bean
    public GraphDatabaseService graphDatabaseService() {
        return super.getGraphDatabaseService();
    }

    @Override
    protected ConversionService neo4jConversionService() throws Exception {
        ConverterRegistry converterRegistry = (ConverterRegistry) super.neo4jConversionService();
        converterRegistry.addConverter(stringToActivationConverter);
        converterRegistry.addConverter(activationToStringConverter);
        return (ConversionService) converterRegistry;
    }

}