package org.neoevolution.configuration;

import org.neoevolution.core.activation.*;
import org.neoevolution.core.error.*;
import org.neoevolution.util.MapUtils;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.*;
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
    @Scope("prototype")
    public Map<ActivationFunctionType, ActivationFunction> activationFunctions() {
        Map<ActivationFunctionType, ActivationFunction> functions = MapUtils.createHashMap(4);
        functions.put(ActivationFunctionType.BINARY, new BinaryFunction());
        functions.put(ActivationFunctionType.LINEAR, new LinearFunction());
        functions.put(ActivationFunctionType.SIGMOID, new SigmoidFunction());
        functions.put(ActivationFunctionType.TANH, new TanhFunction());
        return functions;
    }

    @Bean
    @Scope("prototype")
    public Map<ErrorFunctionType, ErrorFunction> errorFunctions() {
        Map<ErrorFunctionType, ErrorFunction> functions = MapUtils.createHashMap(3);
        functions.put(ErrorFunctionType.DE, new DEFunction());
        functions.put(ErrorFunctionType.MSE, new MSEFunction());
        functions.put(ErrorFunctionType.RMSE, new RMSEFunction());
        return functions;
    }

}