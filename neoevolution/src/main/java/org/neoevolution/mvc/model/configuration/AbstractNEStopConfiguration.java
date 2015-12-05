package org.neoevolution.mvc.model.configuration;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since 1.0
 */
public abstract class AbstractNEStopConfiguration
        extends NEConfiguration implements StopConfiguration {

    private static final long serialVersionUID = -4476670834461032225L;

    protected Double bestFitness;

    protected Integer maxGeneration;

    protected Boolean assertAll;


    protected AbstractNEStopConfiguration() {
        super();
        assertAll = false;
        bestFitness = 0.9;
        maxGeneration = 100;
    }


    @Override
    public Double getBestFitness() {
        return bestFitness;
    }
    public void setBestFitness(Double bestFitness) {
        this.bestFitness = bestFitness;
    }

    @Override
    public Integer getMaxGeneration() {
        return maxGeneration;
    }
    public void setMaxGeneration(Integer maxGeneration) {
        this.maxGeneration = maxGeneration;
    }

    @Override
    public Boolean isAssertAll() {
        return assertAll;
    }
    public void setAssertAll(Boolean assertAll) {
        this.assertAll = assertAll;
    }

}
