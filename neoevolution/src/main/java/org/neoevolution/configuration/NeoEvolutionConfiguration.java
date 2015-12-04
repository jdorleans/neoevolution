package org.neoevolution.configuration;

import org.neoevolution.core.activation.*;
import org.neoevolution.core.error.*;
import org.neoevolution.util.MapUtils;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.aspectj.EnableSpringConfigured;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.Map;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since 1.0
 */
@EnableAsync
@EnableSpringConfigured
//@EnableAspectJAutoProxy
@Import(NENeo4jConfiguration.class)
@SpringBootApplication(scanBasePackages = "org.neoevolution")
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