package org.neoevolution.configuration;

import org.neoevolution.core.activation.ActivationFunction;
import org.neoevolution.core.error.ErrorFunction;
import org.neoevolution.util.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.aspectj.EnableSpringConfigured;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since 1.0
 */
@EnableAsync
@EnableSpringConfigured
@EnableAspectJAutoProxy
@Import(NENeo4jConfiguration.class)
@SpringBootApplication(scanBasePackages = "org.neoevolution")
public class NeoEvolutionConfiguration {

    @Bean
    @Autowired
    public Map<String, ActivationFunction> activationFunctions(List<ActivationFunction> activations)
    {
        Map<String, ActivationFunction> functions = new HashMap<>(activations.size());
        for (ActivationFunction activation : activations) {
            functions.put(activation.getType().getName(), activation);
        }
        return functions;
    }

    @Bean
    public Map<String, ErrorFunction> errorFunctions(List<ErrorFunction> errors)
    {
        Map<String, ErrorFunction> functions = MapUtils.createHashMap(errors.size());
        for (ErrorFunction error : errors) {
            functions.put(error.getType().getName(), error);
        }
        return functions;
    }

}