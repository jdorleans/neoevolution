package org.neoevolution.mvc.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.neoevolution.mvc.json.InnovationArraySerializer;
import org.neoevolution.util.MapUtils;
import org.springframework.data.neo4j.annotation.Fetch;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.annotation.RelatedTo;

import java.util.LinkedHashSet;
import java.util.Set;

@NodeEntity
public class Genotype extends AbstractFitnessEntity {

    private static final long serialVersionUID = -849596329356657600L;

    private Boolean evaluated;

    private Double adjustedFitness;

    @Fetch
    @RelatedTo(type="INPUT")
    @JsonSerialize(using = InnovationArraySerializer.class)
    private Set<Neuron> inputs;

    @Fetch
    @RelatedTo(type="OUTPUT")
    @JsonSerialize(using = InnovationArraySerializer.class)
    private Set<Neuron> outputs;

    @Fetch
    @RelatedTo(type="NEURON")
    private Set<Neuron> neurons;

    // FIXME - Ordering does not work!
    @Query("MATCH (g:Genotype)-->(n1:Neuron)-[s]-(n2:Neuron) " +
           "WHERE id(g)={self} RETURN DISTINCT s ORDER BY id(s)")
    private Set<Synapse> synapses;


    public Genotype() {
        this(1l, 1l, new LinkedHashSet<Neuron>(), new LinkedHashSet<Neuron>());
    }

    public Genotype(Long innovation, Long generation, Set<Neuron> inputs, Set<Neuron> outputs)
    {
        super(innovation, generation);
        this.evaluated = false;
        this.adjustedFitness = 0d;
        this.inputs = inputs;
        this.outputs = outputs;
        int size = MapUtils.getSize(getInputsSize() + getOutputsSize());
        this.synapses = new LinkedHashSet<>(size*size);
        initNeurons(size);
    }

    private void initNeurons(int size) {
        this.neurons = new LinkedHashSet<>(size);
        addNeurons(inputs);
        addNeurons(outputs);
    }


    public void addInput(Neuron neuron) {
        inputs.add(neuron);
        neurons.add(neuron);
    }

    public void addNeuron(Neuron neuron) {
        neurons.add(neuron);
    }

    public void addNeurons(Set<Neuron> neurons) {
        for (Neuron neuron : neurons) {
            this.neurons.add(neuron);
        }
    }

    public void addOutput(Neuron neuron) {
        outputs.add(neuron);
        neurons.add(neuron);
    }

    public void addSynapse(Synapse synapse) {
        synapses.add(synapse);
    }

    @JsonIgnore
    public int getInputsSize() {
        return inputs.size();
    }

    @JsonIgnore
    public int getOutputsSize() {
        return outputs.size();
    }

    @JsonIgnore
    public int getHiddenSize() {
        return getNeuronsSize() - getInputsSize() - getOutputsSize();
    }

    @JsonIgnore
    public int getNeuronsSize() {
        return neurons.size();
    }

    @JsonIgnore
    public int getSynapsesSize() {
        return synapses.size();
    }

    @JsonIgnore
    public int getGenesSize() {
        return getNeuronsSize() + getSynapsesSize();
    }


    @Override
    public boolean equals(Object obj) {
        return (obj instanceof Genotype && super.equals(obj));
    }

    @Override
    public String toString() {
        return "GENOTYPE(i:"+ innovation +", g:"+ generation +", f:"+ fitness +")";
    }

    public Boolean isEvaluated() {
        return evaluated;
    }
    public void setEvaluated(Boolean evaluated) {
        this.evaluated = evaluated;
    }

    public Double getAdjustedFitness() {
        return adjustedFitness;
    }
    public void setAdjustedFitness(Double adjustedFitness) {
        this.adjustedFitness = adjustedFitness;
    }

    public Set<Neuron> getInputs() {
        return inputs;
    }
    public void setInputs(Set<Neuron> inputs) {
        this.inputs = inputs;
    }

    public Set<Neuron> getOutputs() {
        return outputs;
    }
    public void setOutputs(Set<Neuron> outputs) {
        this.outputs = outputs;
    }

    public Set<Neuron> getNeurons() {
        return neurons;
    }
    public void setNeurons(Set<Neuron> neurons) {
        this.neurons = neurons;
    }

    public Set<Synapse> getSynapses() {
        return synapses;
    }
    public void setSynapses(Set<Synapse> synapses) {
        this.synapses = synapses;
    }

}
