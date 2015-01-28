package org.neoevolution.mvc.model.configuration;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since 1.0
 */
public abstract class NEConfiguration extends NNConfiguration {

    private static final long serialVersionUID = -8178118236584126267L;

    protected Double survivalRate;

    protected Double elitismRate;

    protected Double enableSynapseRate;

    protected Double enableSynapsePenalty;

    protected Double excessFactor;

    protected Double weightFactor;

    protected Double compatibilityRate;

    protected Double compatibilityThreshold;


    public Double getSurvivalRate() {
        return survivalRate;
    }
    public void setSurvivalRate(Double survivalRate) {
        this.survivalRate = survivalRate;
    }

    public Double getElitismRate() {
        return elitismRate;
    }
    public void setElitismRate(Double elitismRate) {
        this.elitismRate = elitismRate;
    }

    public Double getEnableSynapseRate() {
        return enableSynapseRate;
    }
    public void setEnableSynapseRate(Double enableSynapseRate) {
        this.enableSynapseRate = enableSynapseRate;
    }

    public Double getEnableSynapsePenalty() {
        return enableSynapsePenalty;
    }
    public void setEnableSynapsePenalty(Double enableSynapsePenalty) {
        this.enableSynapsePenalty = enableSynapsePenalty;
    }

    public Double getExcessFactor() {
        return excessFactor;
    }
    public void setExcessFactor(Double excessFactor) {
        this.excessFactor = excessFactor;
    }

    public Double getWeightFactor() {
        return weightFactor;
    }
    public void setWeightFactor(Double weightFactor) {
        this.weightFactor = weightFactor;
    }

    public Double getCompatibilityRate() {
        return compatibilityRate;
    }
    public void setCompatibilityRate(Double compatibilityRate) {
        this.compatibilityRate = compatibilityRate;
    }

    public Double getCompatibilityThreshold() {
        return compatibilityThreshold;
    }
    public void setCompatibilityThreshold(Double compatibilityThreshold) {
        this.compatibilityThreshold = compatibilityThreshold;
    }

}
