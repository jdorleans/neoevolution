package org.neoevolution.core;

import org.neoevolution.util.MapUtils;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;
import org.springframework.data.neo4j.annotation.RelatedToVia;

import java.util.LinkedHashSet;
import java.util.Set;

@NodeEntity
public class Genotype extends AbstractInnovationEntity {

    private static final long serialVersionUID = -849596329356657600L;

    private transient static long count;

    private Integer generation;

    private Boolean evaluated;

    private Double fitness;

    private Double adjustedFitness;

    @RelatedTo(type="INPUT")
    private Set<Neuron> inputs;

    @RelatedTo(type="OUTPUT")
    private Set<Neuron> outputs;

    @RelatedTo(type="NEURON")
    private Set<Neuron> neurons;

    @RelatedToVia(type="SYNAPSE")
    private Set<Synapse> synapses;


    public Genotype() {
        this(0, new LinkedHashSet<Neuron>(0), new LinkedHashSet<Neuron>(0));
    }

    public Genotype(int generation, Set<Neuron> inputs, Set<Neuron> outputs)
    {
        super(count++);
        this.generation = generation;
        this.evaluated = false;
        this.fitness = 0d;
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

    public int getInputsSize() {
        return inputs.size();
    }

    public int getOutputsSize() {
        return outputs.size();
    }

    public int getHiddensSize() {
        return getNeuronsSize() - getInputsSize() - getOutputsSize();
    }

    public int getNeuronsSize() {
        return neurons.size();
    }

    public int getSynapsesSize() {
        return synapses.size();
    }

    public int getGenesSize() {
        return getNeuronsSize() + getSynapsesSize();
    }


    @Override
    public boolean equals(Object obj) {
        return (obj instanceof Genotype && super.equals(obj));
    }

    @Override
    public String toString() {
        return "GENOTYPE(g:"+ generation +", f:"+ fitness +")";
    }

    public Integer getGeneration() {
        return generation;
    }
    public void setGeneration(Integer generation) {
        this.generation = generation;
    }

    public Boolean isEvaluated() {
        return evaluated;
    }
    public void setEvaluated(Boolean evaluated) {
        this.evaluated = evaluated;
    }

    public Double getFitness() {
        return fitness;
    }
    public void setFitness(Double fitness) {
        this.fitness = fitness;
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
