package org.neoevolution;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.aspectj.EnableSpringConfigured;

@Configuration
@ComponentScan(basePackages = "org.neoevolution")
@EnableSpringConfigured
@EnableAutoConfiguration
public class NeoEvolutionConfiguration {

}