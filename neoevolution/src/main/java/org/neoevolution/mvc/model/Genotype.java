package org.neoevolution.mvc.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.neoevolution.mvc.json.InnovationArraySerializer;
import org.neoevolution.util.MapUtils;
import org.springframework.data.neo4j.annotation.*;

import java.util.HashSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

@NodeEntity
public class Genotype extends AbstractFitnessEntity {

    private static final long serialVersionUID = -849596329356657600L;

    @Indexed
    private Boolean evaluated;

    private Double adjustedFitness;

    @Fetch
    @RelatedTo(type="INPUT")
    @JsonSerialize(using = InnovationArraySerializer.class)
    private SortedSet<Neuron> inputs;

    @Fetch
    @RelatedTo(type="OUTPUT")
    @JsonSerialize(using = InnovationArraySerializer.class)
    private SortedSet<Neuron> outputs;

    @Fetch
    @RelatedTo(type="NEURON")
    private Set<Neuron> neurons;

    @Query("MATCH (g:Genotype)-->(n1:Neuron)-[s]-(n2:Neuron) " +
           "WHERE id(g)={self} RETURN DISTINCT s")
    private Set<Synapse> synapses;


    public Genotype() {
        this(1l, 1l, new TreeSet<Neuron>(), new TreeSet<Neuron>());
    }

    public Genotype(Long innovation, Long generation, SortedSet<Neuron> inputs, SortedSet<Neuron> outputs)
    {
        super(innovation, generation);
        this.evaluated = false;
        this.adjustedFitness = 0d;
        this.inputs = inputs;
        this.outputs = outputs;
        int size = MapUtils.getSize(getInputsSize() + getOutputsSize());
        this.synapses = new HashSet<>(size*size);
        initNeurons(size);
    }

    private void initNeurons(int size) {
        this.neurons = new HashSet<>(size);
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

    public SortedSet<Neuron> getInputs() {
        return inputs;
    }
    public void setInputs(SortedSet<Neuron> inputs) {
        this.inputs = inputs;
    }

    public SortedSet<Neuron> getOutputs() {
        return outputs;
    }
    public void setOutputs(SortedSet<Neuron> outputs) {
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
