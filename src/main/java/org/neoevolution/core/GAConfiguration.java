package org.neoevolution.core;

import org.neoevolution.core.activation.ActivationFunctionType;
import org.neoevolution.core.error.ErrorFunctionType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.annotation.PostConstruct;
import java.io.Serializable;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since 22/10/14.
 */
@Configuration
@PropertySource("classpath:ga-config.properties")
public class GAConfiguration implements Serializable {

    private static final long serialVersionUID = 8173675508141965147L;

    @Value("${neuron.inputs}")
    private int inputSize;

    @Value("${neuron.hidden.min}")
    private int hiddenMinSize;

    @Value("${neuron.hidden.max}")
    private int hiddenMaxSize;

    @Value("${neuron.outputs}")
    private int outputSize;

    @Value("${population.size}")
    private int populationSize;

    @Value("${genotype.connected.fully}")
    private boolean fullyConnected;

    @Value("${synapse.weight.range}")
    private int weightRange;

    @Value("${mutation.weight.change.rate}")
    private double changeWeightRate;

    @Value("${mutation.neuron.add.rate}")
    private double addNeuronRate;

    @Value("${mutation.synapse.add.rate}")
    private double addSynapseRate;

    private int maxSpeciesSize;

    @Value("${speciation.size.rate}")
    private double speciesSizeRate;

    @Value("${speciation.excess.factor}")
    private double excessFactor;

    @Value("${speciation.weight.factor}")
    private double weightFactor;

    @Value("${speciation.compatibility.rate}")
    private double compatibilityRate;

    @Value("${speciation.compatibility.threshold}")
    private double compatibilityThreshold;

    @Value("${selection.survival.rate}")
    private double survivalRate;

    @Value("${reproduction.elitism.rate}")
    private double elitismRate;

    @Value("${reproduction.synapse.enable.rate}")
    private double enableSynapseRate;

    @Value("${reproduction.synapse.enable.penalty}")
    private double enableSynapsePenalty;

    @Value("${activation.bias}")
    private ActivationFunctionType activationBias;

    @Value("${activation.input}")
    private ActivationFunctionType activationInput;

    @Value("${activation.hidden}")
    private ActivationFunctionType activationHidden;

    @Value("${activation.output}")
    private ActivationFunctionType activationOutput;

    @Value("${error.function}")
    private ErrorFunctionType errorFunction;


    @PostConstruct
    private void init() {
        maxSpeciesSize = Math.max(1, (int) (populationSize * speciesSizeRate));
    }


    public int getInputSize() {
        return inputSize;
    }

    public void setInputSize(int inputSize) {
        this.inputSize = inputSize;
    }

    public int getHiddenMinSize() {
        return hiddenMinSize;
    }

    public void setHiddenMinSize(int hiddenMinSize) {
        this.hiddenMinSize = hiddenMinSize;
    }

    public int getHiddenMaxSize() {
        return hiddenMaxSize;
    }

    public void setHiddenMaxSize(int hiddenMaxSize) {
        this.hiddenMaxSize = hiddenMaxSize;
    }

    public int getOutputSize() {
        return outputSize;
    }

    public void setOutputSize(int outputSize) {
        this.outputSize = outputSize;
    }

    public boolean isFullyConnected() {
        return fullyConnected;
    }

    public void setFullyConnected(boolean fullyConnected) {
        this.fullyConnected = fullyConnected;
    }

    public int getWeightRange() {
        return weightRange;
    }

    public void setWeightRange(int weightRange) {
        this.weightRange = weightRange;
    }

    public int getPopulationSize() {
        return populationSize;
    }

    public void setPopulationSize(int populationSize) {
        this.populationSize = populationSize;
    }

    public double getChangeWeightRate() {
        return changeWeightRate;
    }

    public void setChangeWeightRate(double changeWeightRate) {
        this.changeWeightRate = changeWeightRate;
    }

    public double getAddNeuronRate() {
        return addNeuronRate;
    }

    public void setAddNeuronRate(double addNeuronRate) {
        this.addNeuronRate = addNeuronRate;
    }

    public double getAddSynapseRate() {
        return addSynapseRate;
    }

    public void setAddSynapseRate(double addSynapseRate) {
        this.addSynapseRate = addSynapseRate;
    }

    public double getEnableSynapseRate() {
        return enableSynapseRate;
    }

    public void setEnableSynapseRate(double enableSynapseRate) {
        this.enableSynapseRate = enableSynapseRate;
    }

    public double getEnableSynapsePenalty() {
        return enableSynapsePenalty;
    }

    public void setEnableSynapsePenalty(double enableSynapsePenalty) {
        this.enableSynapsePenalty = enableSynapsePenalty;
    }

    public int getMaxSpeciesSize() {
        return maxSpeciesSize;
    }

    public double getSpeciesSizeRate() {
        return speciesSizeRate;
    }

    public void setSpeciesSizeRate(double speciesSizeRate) {
        this.speciesSizeRate = speciesSizeRate;
    }

    public double getExcessFactor() {
        return excessFactor;
    }

    public void setExcessFactor(double excessFactor) {
        this.excessFactor = excessFactor;
    }

    public double getWeightFactor() {
        return weightFactor;
    }

    public void setWeightFactor(double weightFactor) {
        this.weightFactor = weightFactor;
    }

    public double getCompatibilityRate() {
        return compatibilityRate;
    }

    public void setCompatibilityRate(double compatibilityRate) {
        this.compatibilityRate = compatibilityRate;
    }

    public double getCompatibilityThreshold() {
        return compatibilityThreshold;
    }

    public void setCompatibilityThreshold(double compatibilityThreshold) {
        this.compatibilityThreshold = compatibilityThreshold;
    }

    public double getSurvivalRate() {
        return survivalRate;
    }

    public void setSurvivalRate(double survivalRate) {
        this.survivalRate = survivalRate;
    }

    public double getElitismRate() {
        return elitismRate;
    }

    public void setElitismRate(double elitismRate) {
        this.elitismRate = elitismRate;
    }

    public ActivationFunctionType getActivationBias() {
        return activationBias;
    }

    public void setActivationBias(ActivationFunctionType activationBias) {
        this.activationBias = activationBias;
    }

    public ActivationFunctionType getActivationInput() {
        return activationInput;
    }

    public void setActivationInput(ActivationFunctionType activationInput) {
        this.activationInput = activationInput;
    }

    public ActivationFunctionType getActivationHidden() {
        return activationHidden;
    }

    public void setActivationHidden(ActivationFunctionType activationHidden) {
        this.activationHidden = activationHidden;
    }

    public ActivationFunctionType getActivationOutput() {
        return activationOutput;
    }

    public void setActivationOutput(ActivationFunctionType activationOutput) {
        this.activationOutput = activationOutput;
    }

    public ErrorFunctionType getErrorFunction() {
        return errorFunction;
    }

    public void setErrorFunction(ErrorFunctionType errorFunction) {
        this.errorFunction = errorFunction;
    }
}
