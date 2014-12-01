package org.neoevolution.core;

import org.neoevolution.mvc.AbstractEntity;
import org.springframework.data.neo4j.annotation.NodeEntity;

import java.io.Serializable;
import java.util.List;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since Oct 22 2014
 */
@NodeEntity
public class GAConfiguration extends AbstractEntity implements Serializable {

    private static final long serialVersionUID = -2126831555723317376L;

    private int populationSize;

    private int inputSize;

    private int outputSize;

    private int hiddenMinSize;

    private int hiddenMaxSize;

    private boolean fullyConnected;

    private int weightRange;

    private String activationBias;

    private String activationInput;

    private String activationHidden;

    private String activationOutput;

    private String errorFactory;

    private List<String> stopFunctions;

    private String evaluationFactory;

    private String selectionFactory;

    private String reproductionFactory;

    private double survivalRate;

    private double elitismRate;

    private double enableSynapseRate;

    private double enableSynapsePenalty;

    private String mutationFactory;

    private String addNeuronMutationFactory;

    private double addNeuronRate;

    private String addSynapseMutationFactory;

    private double addSynapseRate;

    private String weightSynapseMutationFactory;

    private double weightSynapseRate;

    private boolean weightSynapseReset;

    private String speciationFactory;

    private double speciesSizeRate;

    private double excessFactor;

    private double weightFactor;

    private double compatibilityRate;

    private double compatibilityThreshold;


    public int getPopulationSize() {
        return populationSize;
    }

    public void setPopulationSize(int populationSize) {
        this.populationSize = populationSize;
    }

    public int getMaxSpeciesSize() {
        return Math.max(1, (int) (populationSize * speciesSizeRate));
    }

    public int getInputSize() {
        return inputSize;
    }

    public void setInputSize(int inputSize) {
        this.inputSize = inputSize;
    }

    public int getOutputSize() {
        return outputSize;
    }

    public void setOutputSize(int outputSize) {
        this.outputSize = outputSize;
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

    public String getActivationBias() {
        return activationBias;
    }

    public void setActivationBias(String activationBias) {
        this.activationBias = activationBias;
    }

    public String getActivationInput() {
        return activationInput;
    }

    public void setActivationInput(String activationInput) {
        this.activationInput = activationInput;
    }

    public String getActivationHidden() {
        return activationHidden;
    }

    public void setActivationHidden(String activationHidden) {
        this.activationHidden = activationHidden;
    }

    public String getActivationOutput() {
        return activationOutput;
    }

    public void setActivationOutput(String activationOutput) {
        this.activationOutput = activationOutput;
    }

    public String getErrorFactory() {
        return errorFactory;
    }

    public void setErrorFactory(String errorFactory) {
        this.errorFactory = errorFactory;
    }

    public List<String> getStopFunctions() {
        return stopFunctions;
    }

    public void setStopFunctions(List<String> stopFunctions) {
        this.stopFunctions = stopFunctions;
    }

    public String getEvaluationFactory() {
        return evaluationFactory;
    }

    public void setEvaluationFactory(String evaluationFactory) {
        this.evaluationFactory = evaluationFactory;
    }

    public String getSelectionFactory() {
        return selectionFactory;
    }

    public void setSelectionFactory(String selectionFactory) {
        this.selectionFactory = selectionFactory;
    }

    public String getReproductionFactory() {
        return reproductionFactory;
    }

    public void setReproductionFactory(String reproductionFactory) {
        this.reproductionFactory = reproductionFactory;
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

    public String getMutationFactory() {
        return mutationFactory;
    }

    public void setMutationFactory(String mutationFactory) {
        this.mutationFactory = mutationFactory;
    }

    public String getAddNeuronMutationFactory() {
        return addNeuronMutationFactory;
    }

    public void setAddNeuronMutationFactory(String addNeuronMutationFactory) {
        this.addNeuronMutationFactory = addNeuronMutationFactory;
    }

    public double getAddNeuronRate() {
        return addNeuronRate;
    }

    public void setAddNeuronRate(double addNeuronRate) {
        this.addNeuronRate = addNeuronRate;
    }

    public String getAddSynapseMutationFactory() {
        return addSynapseMutationFactory;
    }

    public void setAddSynapseMutationFactory(String addSynapseMutationFactory) {
        this.addSynapseMutationFactory = addSynapseMutationFactory;
    }

    public double getAddSynapseRate() {
        return addSynapseRate;
    }

    public void setAddSynapseRate(double addSynapseRate) {
        this.addSynapseRate = addSynapseRate;
    }

    public String getWeightSynapseMutationFactory() {
        return weightSynapseMutationFactory;
    }

    public void setWeightSynapseMutationFactory(String weightSynapseMutationFactory) {
        this.weightSynapseMutationFactory = weightSynapseMutationFactory;
    }

    public double getWeightSynapseRate() {
        return weightSynapseRate;
    }

    public void setWeightSynapseRate(double weightSynapseRate) {
        this.weightSynapseRate = weightSynapseRate;
    }

    public boolean isWeightSynapseReset() {
        return weightSynapseReset;
    }

    public void setWeightSynapseReset(boolean weightSynapseReset) {
        this.weightSynapseReset = weightSynapseReset;
    }

    public String getSpeciationFactory() {
        return speciationFactory;
    }

    public void setSpeciationFactory(String speciationFactory) {
        this.speciationFactory = speciationFactory;
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

}
