package org.neoevolution.mvc.model.configuration;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.neo4j.ogm.annotation.Relationship;
import org.neoevolution.core.ConnectionStrategy;
import org.neoevolution.core.activation.ActivationFunction;
import org.neoevolution.mvc.json.ActivationDeserializer;
import org.neoevolution.mvc.model.AbstractEntity;
import org.neoevolution.mvc.model.innovation.NeuronInnovation;
import org.neoevolution.mvc.model.innovation.SynapseInnovation;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since 1.0
 */
public abstract class NNConfiguration extends AbstractEntity {

    private static final long serialVersionUID = -2459309402975620977L;

    protected Long populationInnovation;

    protected Long speciesInnovation;

    protected Long genotypeInnovation;

    @Relationship(type="NI")
    protected NeuronInnovation neuronInnovation;

    @Relationship(type="SI")
    protected SynapseInnovation synapseInnovation;

    protected Integer populationSize;

    protected Integer inputSize;

    protected Integer outputSize;

    protected Integer hiddenMinSize;

    protected Integer hiddenMaxSize;

    protected ConnectionStrategy connectionStrategy;

    protected Double weightRange;

    protected Double addNeuronRate;

    protected Double addSynapseRate;

    protected Double weightSynapseRate;

    protected Boolean weightSynapseReset;

    protected Double speciesSizeRate;

    @JsonDeserialize(using = ActivationDeserializer.class)
    protected ActivationFunction activationBias;

    @JsonDeserialize(using = ActivationDeserializer.class)
    protected ActivationFunction activationInput;

    @JsonDeserialize(using = ActivationDeserializer.class)
    protected ActivationFunction activationHidden;

    @JsonDeserialize(using = ActivationDeserializer.class)
    protected ActivationFunction activationOutput;


    protected NNConfiguration() {
        super();
        populationInnovation = 0L;
        speciesInnovation = 0L;
        genotypeInnovation = 0L;
        neuronInnovation = new NeuronInnovation();
        synapseInnovation = new SynapseInnovation();
    }

    @JsonIgnore
    public Integer getMaxSpeciesSize() {
        if (populationSize != null && speciesSizeRate != null) {
            return Math.max(1, (int) (populationSize * speciesSizeRate));
        }
        return null;
    }


    @Override
    public boolean equals(Object obj) {
        return (obj instanceof NNConfiguration && super.equals(obj));
    }


    public Long nextPopulationInnovation() {
        return ++populationInnovation;
    }

    public Long nextSpeciesInnovation() {
        return ++speciesInnovation;
    }

    public Long nextGenotypeInnovation() {
        return ++genotypeInnovation;
    }

    public Long getPopulationInnovation() {
        return populationInnovation;
    }
    public void setPopulationInnovation(Long populationInnovation) {
        this.populationInnovation = populationInnovation;
    }

    public Long getSpeciesInnovation() {
        return speciesInnovation;
    }
    public void setSpeciesInnovation(Long speciesInnovation) {
        this.speciesInnovation = speciesInnovation;
    }

    public Long getGenotypeInnovation() {
        return genotypeInnovation;
    }
    public void setGenotypeInnovation(Long genotypeInnovation) {
        this.genotypeInnovation = genotypeInnovation;
    }

    public NeuronInnovation getNeuronInnovation() {
        return neuronInnovation;
    }
    public void setNeuronInnovation(NeuronInnovation neuronInnovation) {
        this.neuronInnovation = neuronInnovation;
    }

    public SynapseInnovation getSynapseInnovation() {
        return synapseInnovation;
    }
    public void setSynapseInnovation(SynapseInnovation synapseInnovation) {
        this.synapseInnovation = synapseInnovation;
    }

    public Integer getPopulationSize() {
        return populationSize;
    }
    public void setPopulationSize(Integer populationSize) {
        this.populationSize = populationSize;
    }

    public Integer getInputSize() {
        return inputSize;
    }
    public void setInputSize(Integer inputSize) {
        this.inputSize = inputSize;
    }

    public Integer getOutputSize() {
        return outputSize;
    }
    public void setOutputSize(Integer outputSize) {
        this.outputSize = outputSize;
    }

    public Integer getHiddenMinSize() {
        return hiddenMinSize;
    }
    public void setHiddenMinSize(Integer hiddenMinSize) {
        this.hiddenMinSize = hiddenMinSize;
    }

    public Integer getHiddenMaxSize() {
        return hiddenMaxSize;
    }
    public void setHiddenMaxSize(Integer hiddenMaxSize) {
        this.hiddenMaxSize = hiddenMaxSize;
    }

    public ConnectionStrategy getConnectionStrategy() {
        return connectionStrategy;
    }
    public void setConnectionStrategy(ConnectionStrategy connectionStrategy) {
        this.connectionStrategy = connectionStrategy;
    }

    public Double getAddNeuronRate() {
        return addNeuronRate;
    }
    public void setAddNeuronRate(Double addNeuronRate) {
        this.addNeuronRate = addNeuronRate;
    }

    public Double getAddSynapseRate() {
        return addSynapseRate;
    }
    public void setAddSynapseRate(Double addSynapseRate) {
        this.addSynapseRate = addSynapseRate;
    }

    public Double getWeightSynapseRate() {
        return weightSynapseRate;
    }
    public void setWeightSynapseRate(Double weightSynapseRate) {
        this.weightSynapseRate = weightSynapseRate;
    }

    public Double getWeightRange() {
        return weightRange;
    }
    public void setWeightRange(Double weightRange) {
        this.weightRange = weightRange;
    }

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

    public ActivationFunction getActivationBias() {
        return activationBias;
    }
    public void setActivationBias(ActivationFunction activationBias) {
        this.activationBias = activationBias;
    }

    public ActivationFunction getActivationInput() {
        return activationInput;
    }
    public void setActivationInput(ActivationFunction activationInput) {
        this.activationInput = activationInput;
    }

    public ActivationFunction getActivationHidden() {
        return activationHidden;
    }
    public void setActivationHidden(ActivationFunction activationHidden) {
        this.activationHidden = activationHidden;
    }

    public ActivationFunction getActivationOutput() {
        return activationOutput;
    }
    public void setActivationOutput(ActivationFunction activationOutput) {
        this.activationOutput = activationOutput;
    }

}
