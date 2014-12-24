package org.neoevolution.xor;

import org.neoevolution.core.configuration.ErrorConfiguration;
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

    private Integer maxGeneration;

    private Boolean assertAll;


    public XORConfiguration() {
        assertAll = false;
        fitness = 0.9;
        maxGeneration = 100;
    }


    public Double getFitness() {
        return fitness;
    }
    public void setFitness(Double fitness) {
        this.fitness = fitness;
    }

    public Integer getMaxGeneration() {
        return maxGeneration;
    }
    public void setMaxGeneration(Integer maxGeneration) {
        this.maxGeneration = maxGeneration;
    }

    public Boolean isAssertAll() {
        return assertAll;
    }
    public void setAssertAll(Boolean assertAll) {
        this.assertAll = assertAll;
    }

}
