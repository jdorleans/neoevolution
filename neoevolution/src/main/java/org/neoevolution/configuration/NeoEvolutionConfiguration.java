package org.neoevolution.configuration;

import org.neoevolution.core.activation.*;
import org.neoevolution.util.MapUtils;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.aspectj.EnableSpringConfigured;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.Map;

@Configuration
@ComponentScan("org.neoevolution")
@EnableAsync
@EnableSpringConfigured
@EnableAutoConfiguration
@Import(NENeo4jConfiguration.class)
public class NeoEvolutionConfiguration {

    @Bean
    public Map<ActivationFunctionType, ActivationFunction> activationFunctions() {
        Map<ActivationFunctionType, ActivationFunction> functions = MapUtils.createHashMap(4);
        functions.put(ActivationFunctionType.BINARY, new BinaryFunction());
        functions.put(ActivationFunctionType.LINEAR, new LinearFunction());
        functions.put(ActivationFunctionType.SIGMOID, new SigmoidFunction());
        functions.put(ActivationFunctionType.TANH, new TanhFunction());
        return functions;
    }

}