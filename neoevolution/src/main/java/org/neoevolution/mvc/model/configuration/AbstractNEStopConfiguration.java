package org.neoevolution.mvc.model.configuration;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since 1.0
 */
public abstract class AbstractNEStopConfiguration
        extends NEConfiguration implements StopConfiguration {

    private static final long serialVersionUID = -4476670834461032225L;

    protected Double fitness;

    protected Integer maxGeneration;

    protected Boolean assertAll;


    protected AbstractNEStopConfiguration() {
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
