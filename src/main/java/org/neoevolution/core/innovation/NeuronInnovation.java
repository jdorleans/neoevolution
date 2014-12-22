package org.neoevolution.core.innovation;

import org.neoevolution.core.model.Neuron;
import org.springframework.data.neo4j.annotation.NodeEntity;

@NodeEntity
public class NeuronInnovation extends AbstractInnovation {

    private static final long serialVersionUID = -366999622471119752L;

    public static final String SEPARATOR = ":";


    public NeuronInnovation() {
        this(SEPARATOR);
    }

    public NeuronInnovation(String code) {
        super(code);
    }


    private String key(Neuron neuron) {
        return (code + neuron.getInnovation());
    }

    public synchronized void innovate(Neuron neuron)
    {
        String key = key(neuron);

        if (!innovations.hasProperty(key)) {
            put(key, neuron.getInnovation());
        }
    }

}
