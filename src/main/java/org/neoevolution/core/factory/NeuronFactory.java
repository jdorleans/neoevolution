package org.neoevolution.core.factory;

import org.neoevolution.core.GAConfiguration;
import org.neoevolution.core.Neuron;
import org.neoevolution.core.NeuronType;
import org.neoevolution.core.activation.ActivationFunctionManager;
import org.neoevolution.core.innovation.NeuronInnovationManager;
import org.neoevolution.util.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since 22/10/14.
 */
@Component
public class NeuronFactory {

    @Autowired
    private NeuronInnovationManager innovation;

    @Autowired
    private ActivationFunctionManager functionManager;

    @Autowired
    private GAConfiguration configuration;

    private List<Neuron> inputs;

    private List<Neuron> outputs;


    @PostConstruct
    private void init() {
        initInputs();
        initOutputs();
    }


    private void initInputs() {
        inputs = createList(configuration.getInputSize(), NeuronType.INPUT);
        inputs.add(create(NeuronType.BIAS));
    }

    private void initOutputs() {
        outputs = createList(configuration.getOutputSize(), NeuronType.OUTPUT);
    }


    private List<Neuron> createList(int size, NeuronType type)
    {
        List<Neuron> neurons = new ArrayList<>(size+1);

        for (int i = 0; i < size; i++) {
            neurons.add(create(type));
        }
        return neurons;
    }

    private Neuron create(NeuronType type) {
        Neuron neuron = new Neuron(type, functionManager.get(type));
        innovation.innovate(neuron);
        return neuron;
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
        Set<Neuron> list = new LinkedHashSet<>(MapUtils.getSize(neurons.size(), false));

        for (Neuron neuron : neurons) {
            list.add(new Neuron(neuron));
        }
        return list;
    }

}
