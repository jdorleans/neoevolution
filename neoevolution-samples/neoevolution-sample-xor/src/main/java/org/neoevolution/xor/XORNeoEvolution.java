package org.neoevolution.xor;

import org.neoevolution.configuration.NeoEvolutionConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.neo4j.config.EnableNeo4jRepositories;
import org.springframework.scheduling.annotation.AsyncConfigurerSupport;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
@EnableNeo4jRepositories("org.neoevolution.xor.mvc.repository")
@Import(NeoEvolutionConfiguration.class)
public class XORNeoEvolution extends AsyncConfigurerSupport {

    public static final int QUEUE_FACTOR = 10000;

    @Override
    public Executor getAsyncExecutor()
    {
        int processors = Runtime.getRuntime().availableProcessors();
        int threads = Math.max(1, processors-1);
        int queueCapacity = threads * QUEUE_FACTOR;
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(threads);
        executor.setMaxPoolSize(threads);
        executor.setQueueCapacity(queueCapacity);
        executor.setThreadNamePrefix("XORNeoEvolution-");
        executor.initialize();
        return executor;
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(XORNeoEvolution.class, args);
    }

}