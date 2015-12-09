package org.neoevolution.mvc.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;
import org.neo4j.ogm.annotation.Transient;
import org.neoevolution.mvc.json.InnovationArraySerializer;
import org.neoevolution.util.MapUtils;

import java.util.HashSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since 1.0
 */
@NodeEntity
public class Genotype extends AbstractFitnessEntity {

    private static final long serialVersionUID = -849596329356657600L;

    private Boolean evaluated;

    private Double adjustedFitness;

    @Relationship(type="IN")
    @JsonSerialize(using = InnovationArraySerializer.class)
    private SortedSet<Neuron> inputs;

    @Relationship(type="OUT")
    @JsonSerialize(using = InnovationArraySerializer.class)
    private SortedSet<Neuron> outputs;

    @Relationship(type="NEURON")
    private Set<Neuron> neurons;

    @Transient
    private Set<Synapse> synapses;


    public Genotype() {
        this(1L, 1L, new TreeSet<>(), new TreeSet<>());
    }

    public Genotype(Long innovation, Long generation, SortedSet<Neuron> inputs, SortedSet<Neuron> outputs)
    {
        super(innovation, generation);
        this.evaluated = false;
        this.adjustedFitness = 0d;
        this.inputs = inputs;
        this.outputs = outputs;
        int size = (int) (MapUtils.getSize(getInputsSize() + getOutputsSize()) * 1.2);
        this.synapses = new HashSet<>(size);
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
