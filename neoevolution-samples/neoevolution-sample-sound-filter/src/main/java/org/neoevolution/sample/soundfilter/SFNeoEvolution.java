package org.neoevolution.sample.soundfilter;

import org.neoevolution.configuration.NeoEvolutionConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.neo4j.config.EnableNeo4jRepositories;
import org.springframework.scheduling.annotation.AsyncConfigurerSupport;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since 1.0
 */
@Configuration
@EnableNeo4jRepositories("org.neoevolution.sample.soundfilter.mvc.repository")
@Import(NeoEvolutionConfiguration.class)
public class SFNeoEvolution extends AsyncConfigurerSupport {

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
        executor.setThreadNamePrefix("SFNeoEvolution-");
        executor.initialize();
        return executor;
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(SFNeoEvolution.class, args);
    }

}