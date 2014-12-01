package org.neoevolution.factory;

import org.neoevolution.core.GAConfiguration;
import org.neoevolution.core.activation.ActivationFunctionManager;
import org.neoevolution.core.innovation.NeuronInnovation;
import org.neoevolution.core.model.Neuron;
import org.neoevolution.core.model.NeuronType;
import org.neoevolution.mvc.service.NeuronInnovationService;
import org.neoevolution.util.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since Oct 22 2014
 */
@Configurable(preConstruction = true)
public class NeuronFactory<C extends GAConfiguration> implements ConfigurableFactory<Neuron, C> {

    private List<Neuron> inputs;

    private List<Neuron> outputs;

    private NeuronInnovation innovation;

    @Autowired
    private NeuronInnovationService innovationService;

    private ActivationFunctionManager functionManager;


    @Override
    public void configure(C configuration) {
        this.innovation = innovationService.findByConfigIdOrCreate(configuration.getId());
        this.functionManager = new ActivationFunctionManager(configuration);
        initInputs(configuration.getInputSize());
        initOutputs(configuration.getOutputSize());
    }



    // FIXME - FACTORY IS CREATED MANY TIMES, THUS WE MUST REUSE INNOVATION!!!
    private void initInputs(int inputs) {
        this.inputs = createList(inputs, NeuronType.INPUT);
        this.inputs.add(create(NeuronType.BIAS));
    }

    private void initOutputs(int outputs) {
        this.outputs = createList(outputs, NeuronType.OUTPUT);
    }


    private List<Neuron> createList(int size, NeuronType type)
    {
        List<Neuron> neurons = new ArrayList<>(size + 1);

        for (int i = 0; i < size; i++) {
            neurons.add(create(type));
        }
        return neurons;
    }


    @Override
    public Neuron create() {
        return new Neuron();
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
        Set<Neuron> list = MapUtils.createLinkedHashSet(neurons.size(), false);

        for (Neuron neuron : neurons) {
            list.add(new Neuron(neuron));
        }
        return list;
    }

}
