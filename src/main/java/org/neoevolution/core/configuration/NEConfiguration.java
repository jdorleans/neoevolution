package org.neoevolution.core.configuration;

import org.neoevolution.core.activation.ActivationFunctionType;
import org.neoevolution.mvc.AbstractEntity;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since Oct 22 2014
 */
public abstract class NEConfiguration extends AbstractEntity implements NNConfiguration {

    private static final long serialVersionUID = 7311833932458102732L;

    private Integer populationSize;

    private Integer inputSize;

    private Integer outputSize;

    private Boolean fullyConnected;

    private Double weightRange;

    private Double survivalRate;

    private Double elitismRate;

    private Double enableSynapseRate;

    private Double enableSynapsePenalty;

    private Double addNeuronRate;

    private Integer hiddenMaxSize;

    private Double addSynapseRate;

    private Double weightSynapseRate;

    private Boolean weightSynapseReset;

    private Double speciesSizeRate;

    private Double excessFactor;

    private Double weightFactor;

    private Double compatibilityRate;

    private Double compatibilityThreshold;

    private ActivationFunctionType activationBias;

    private ActivationFunctionType activationInput;

    private ActivationFunctionType activationHidden;

    private ActivationFunctionType activationOutput;

    private Long generation;


    protected NEConfiguration() {
        super();
        generation = 0l;
    }


    @Override
    public Long getGeneration() {
        return generation;
    }

    public void setGeneration(Long generation) {
        this.generation = generation;
    }

    @Override
    public Integer getPopulationSize() {
        return populationSize;
    }

    public void setPopulationSize(Integer populationSize) {
        this.populationSize = populationSize;
    }

    @Override
    public Integer getMaxSpeciesSize() {
        return Math.max(1, (int) (populationSize * speciesSizeRate));
    }

    @Override
    public Integer getInputSize() {
        return inputSize;
    }

    public void setInputSize(Integer inputSize) {
        this.inputSize = inputSize;
    }

    @Override
    public Integer getOutputSize() {
        return outputSize;
    }

    public void setOutputSize(Integer outputSize) {
        this.outputSize = outputSize;
    }

    @Override
    public Boolean isFullyConnected() {
        return fullyConnected;
    }

    public void setFullyConnected(Boolean fullyConnected) {
        this.fullyConnected = fullyConnected;
    }

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

    @Override
    public Double getAddNeuronRate() {
        return addNeuronRate;
    }

    public void setAddNeuronRate(Double addNeuronRate) {
        this.addNeuronRate = addNeuronRate;
    }

    @Override
    public Integer getHiddenMaxSize() {
        return hiddenMaxSize;
    }

    public void setHiddenMaxSize(Integer hiddenMaxSize) {
        this.hiddenMaxSize = hiddenMaxSize;
    }

    @Override
    public Double getAddSynapseRate() {
        return addSynapseRate;
    }

    public void setAddSynapseRate(Double addSynapseRate) {
        this.addSynapseRate = addSynapseRate;
    }

    @Override
    public Double getWeightSynapseRate() {
        return weightSynapseRate;
    }

    public void setWeightSynapseRate(Double weightSynapseRate) {
        this.weightSynapseRate = weightSynapseRate;
    }

    @Override
    public Double getWeightRange() {
        return weightRange;
    }

    public void setWeightRange(Double weightRange) {
        this.weightRange = weightRange;
    }

    @Override
    public Boolean isWeightSynapseReset() {
        return weightSynapseReset;
    }

    public void setWeightSynapseReset(Boolean weightSynapseReset) {
        this.weightSynapseReset = weightSynapseReset;
    }

    public Double getSpeciesSizeRate() {
        return speciesSizeRate;
    }

    public void setSpeciesSizeRate(Double speciesSizeRate) {
        this.speciesSizeRate = speciesSizeRate;
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

    @Override
    public ActivationFunctionType getActivationBias() {
        return activationBias;
    }

    public void setActivationBias(ActivationFunctionType activationBias) {
        this.activationBias = activationBias;
    }

    @Override
    public ActivationFunctionType getActivationInput() {
        return activationInput;
    }

    public void setActivationInput(ActivationFunctionType activationInput) {
        this.activationInput = activationInput;
    }

    @Override
    public ActivationFunctionType getActivationHidden() {
        return activationHidden;
    }

    public void setActivationHidden(ActivationFunctionType activationHidden) {
        this.activationHidden = activationHidden;
    }

    @Override
    public ActivationFunctionType getActivationOutput() {
        return activationOutput;
    }

    public void setActivationOutput(ActivationFunctionType activationOutput) {
        this.activationOutput = activationOutput;
    }

}
