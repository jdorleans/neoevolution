package org.neoevolution.mvc.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.neo4j.graphdb.Direction;
import org.neoevolution.core.activation.ActivationFunction;
import org.neoevolution.mvc.json.ActivationDeserializer;
import org.neoevolution.mvc.json.InnovationArraySerializer;
import org.springframework.data.neo4j.annotation.Fetch;
import org.springframework.data.neo4j.annotation.GraphProperty;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedToVia;

import java.util.LinkedHashSet;
import java.util.Set;

@NodeEntity
public class Neuron extends Gene {

    private static final long serialVersionUID = 3943751427193605529L;

    private NeuronType type;

    @JsonIgnore
    private transient Double impulses;

    @GraphProperty(propertyType = String.class)
    @JsonDeserialize(using = ActivationDeserializer.class)
    private ActivationFunction function;

    @Fetch
    @RelatedToVia(direction = Direction.INCOMING)
    @JsonSerialize(using = InnovationArraySerializer.class)
    private Set<Synapse> inputs;

    @Fetch
    @RelatedToVia(direction = Direction.OUTGOING)
    @JsonSerialize(using = InnovationArraySerializer.class)
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
        return function.calculate(impulses);
    }

    public void reset() {
        impulses = 0d;
    }


    @Override
    public boolean equals(Object obj) {
        return (obj instanceof Neuron && super.equals(obj));
    }

    @Override
    public String toString() {
        return type +"(i:"+ innovation +")";
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
