package org.neoevolution.core;

import org.neo4j.graphdb.Direction;
import org.neoevolution.core.activation.ActivationFunction;
import org.springframework.data.neo4j.annotation.Fetch;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedToVia;

import java.util.LinkedHashSet;
import java.util.Set;

@NodeEntity
public class Neuron extends Gene {

    private static final long serialVersionUID = 3943751427193605529L;

    private NeuronType type;

    private Double impulses;

    private Double activation;

    private ActivationFunction function;

    @Fetch
    @RelatedToVia(direction = Direction.INCOMING)
    private Set<Synapse> inputs;

    @Fetch
    @RelatedToVia(direction = Direction.OUTGOING)
    private Set<Synapse> outputs;


    public Neuron() {
        this(null, null);
    }

    public Neuron(NeuronType type, ActivationFunction function) {
        this(null, type, function);
    }

    public Neuron(Long innovation, NeuronType type, ActivationFunction function) {
        super(innovation);
        this.type = type;
        this.impulses = 0d;
        this.activation = null;
        this.function = function;
        this.inputs = new LinkedHashSet<>();
        this.outputs = new LinkedHashSet<>();
    }

    public Neuron(Neuron neuron) {
        this(neuron.getInnovation(), neuron.getType(), neuron.getFunction());
    }


    public void addInput(Synapse synapse) {
        inputs.add(synapse);
    }

    public void addOutput(Synapse synapse) {
        outputs.add(synapse);
    }

    public void impulse(double value) {
        impulses += value;
    }

    public double activate() {
        activation = function.calculate(impulses);
        return activation;
    }

    @Override
    public String toString() {
        return type +"(i:"+ innovation +", a:"+ activation +")";
    }

    public NeuronType getType() {
        return type;
    }
    public void setType(NeuronType type) {
        this.type = type;
    }

    public Double getImpulses() {
        return impulses;
    }
    public void setImpulses(Double impulses) {
        this.impulses = impulses;
    }

    public Double getActivation() {
        return activation;
    }
    public void setActivation(Double activation) {
        this.activation = activation;
    }

    public ActivationFunction getFunction() {
        return function;
    }
    public void setFunction(ActivationFunction function) {
        this.function = function;
    }

    public Set<Synapse> getInputs() {
        return inputs;
    }
    public void setInputs(Set<Synapse> inputs) {
        this.inputs = inputs;
    }

    public Set<Synapse> getOutputs() {
        return outputs;
    }
    public void setOutputs(Set<Synapse> outputs) {
        this.outputs = outputs;
    }

}
