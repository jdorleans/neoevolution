package org.neoevolution.xor;

import org.neoevolution.configuration.NeoEvolutionConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.config.EnableNeo4jRepositories;
import org.springframework.scheduling.annotation.AsyncConfigurerSupport;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
@EnableNeo4jRepositories("org.neoevolution.xor")
@NeoEvolutionConfiguration
public class XORNeoEvolution extends AsyncConfigurerSupport {

    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(3);
        executor.setMaxPoolSize(3);
        executor.setQueueCapacity(300);
        executor.setThreadNamePrefix("XORNeoEvolution-");
        executor.initialize();
        return executor;
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(XORNeoEvolution.class, args);
    }

}