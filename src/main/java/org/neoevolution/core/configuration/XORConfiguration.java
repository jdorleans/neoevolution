package org.neoevolution.core.configuration;

import org.neoevolution.core.error.ErrorFunctionType;
import org.springframework.data.neo4j.annotation.NodeEntity;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since Oct 22 2014
 */
@NodeEntity
public class XORConfiguration extends ErrorConfiguration<ErrorFunctionType> {

    private static final long serialVersionUID = -2954650514798624341L;

    private Double fitness;


    public Double getFitness() {
        return fitness;
    }
    public void setFitness(Double fitness) {
        this.fitness = fitness;
    }

}
