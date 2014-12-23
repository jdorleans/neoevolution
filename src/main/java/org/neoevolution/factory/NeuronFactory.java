package org.neoevolution.factory;

import org.neoevolution.core.activation.ActivationFunctionManager;
import org.neoevolution.core.configuration.NNConfiguration;
import org.neoevolution.core.innovation.NeuronInnovation;
import org.neoevolution.core.model.Neuron;
import org.neoevolution.core.model.NeuronType;
import org.neoevolution.util.MapUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since Oct 22 2014
 */
public class NeuronFactory<C extends NNConfiguration> implements ConfigurableFactory<Neuron, C> {

    private List<Neuron> inputs;

    private List<Neuron> outputs;

    private NeuronInnovation innovation;

    private ActivationFunctionManager functionManager;


    public NeuronFactory() {
        this.functionManager = new ActivationFunctionManager();
    }


    @Override
    public void configure(C configuration) {
        innovation = configuration.getNeuronInnovation();
        functionManager.configure(configuration);
        initNeurons(configuration.getInputSize(), configuration.getOutputSize());
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
        Neuron neuron = new Neuron(type, functionManager.get(type));
        innovation.innovate(idx, neuron);
        return neuron;
    }


    @Override
    public Neuron create() {
        return new Neuron();
    }

    public Neuron createHidden(Neuron from, Neuron to) {
        NeuronType type = NeuronType.HIDDEN;
        Neuron neuron = new Neuron(type, functionManager.get(type));
        innovation.innovate(neuron, from, to);
        return neuron;
    }

    public Set<Neuron> createInputs() {
        return copySet(inputs);
    }

    public Set<Neuron> createOutputs() {
        return copySet(outputs);
    }

    private Set<Neuron> copySet(List<Neuron> neurons)
    {
        Set<Neuron> list = MapUtils.createLinkedHashSet(neurons.size(), false);

        for (Neuron neuron : neurons) {
            list.add(new Neuron(neuron));
        }
        return list;
    }

}
