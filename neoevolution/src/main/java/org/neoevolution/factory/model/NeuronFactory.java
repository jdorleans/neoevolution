package org.neoevolution.factory.model;

import org.neoevolution.core.activation.ActivationFunction;
import org.neoevolution.factory.model.configuration.ConfigurableFactory;
import org.neoevolution.mvc.model.Neuron;
import org.neoevolution.mvc.model.NeuronType;
import org.neoevolution.mvc.model.configuration.NNConfiguration;
import org.neoevolution.mvc.model.innovation.NeuronInnovation;
import org.neoevolution.util.MapUtils;

import java.util.*;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since 1.0
 */
public class NeuronFactory<C extends NNConfiguration> implements ConfigurableFactory<Neuron, C> {

    private List<Neuron> inputs;

    private List<Neuron> outputs;

    private NeuronInnovation innovation;

    private Map<NeuronType, ActivationFunction> functions;


    public NeuronFactory() {
        functions = MapUtils.createHashMap(4);
    }


    @Override
    public void configure(C configuration) {
        initFunctions(configuration);
        innovation = configuration.getNeuronInnovation();
        initNeurons(configuration.getInputSize(), configuration.getOutputSize());
    }

    private void initFunctions(C configuration) {
        functions.put(NeuronType.BIAS, configuration.getActivationBias());
        functions.put(NeuronType.INPUT, configuration.getActivationInput());
        functions.put(NeuronType.HIDDEN, configuration.getActivationHidden());
        functions.put(NeuronType.OUTPUT, configuration.getActivationOutput());
    }

    private void initNeurons(int inputSize, int outputSize)
    {
        inputs = new ArrayList<>(inputSize + 1);
        outputs = new ArrayList<>(outputSize);
        createNeurons(inputSize, inputs, NeuronType.INPUT);
        inputs.add(create(NeuronType.BIAS));
        createNeurons(outputSize, outputs, NeuronType.OUTPUT);
    }


    private void createNeurons(int size, List<Neuron> neurons, NeuronType type) {
        for (int i = 0; i < size; i++) {
            neurons.add(create(type));
        }
    }

    private Neuron create(NeuronType type) {
        int idx = inputs.size() + outputs.size() + 1;
        Neuron neuron = new Neuron(type, functions.get(type));
        innovation.innovate(idx, neuron);
        return neuron;
    }


    @Override
    public Neuron create() {
        return new Neuron();
    }

    public Neuron createHidden(Neuron from, Neuron to) {
        NeuronType type = NeuronType.HIDDEN;
        Neuron neuron = new Neuron(type, functions.get(type));
        innovation.innovate(neuron, from, to);
        return neuron;
    }

    public SortedSet<Neuron> createInputs() {
        return copySet(inputs);
    }

    public SortedSet<Neuron> createOutputs() {
        return copySet(outputs);
    }

    private SortedSet<Neuron> copySet(List<Neuron> neurons)
    {
        SortedSet<Neuron> list = new TreeSet<>();

        for (Neuron neuron : neurons) {
            list.add(new Neuron(neuron));
        }
        return list;
    }

}
