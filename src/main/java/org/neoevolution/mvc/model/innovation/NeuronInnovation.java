package org.neoevolution.mvc.model.innovation;

import org.neoevolution.mvc.model.Neuron;
import org.springframework.data.neo4j.annotation.NodeEntity;

@NodeEntity
public class NeuronInnovation extends AbstractInnovation {

    private static final long serialVersionUID = -366999622471119752L;

    public static final String SEPARATOR = "*";


    public NeuronInnovation() {
        this(SEPARATOR);
    }

    public NeuronInnovation(String code) {
        super(code);
    }


    private String key(int idx) {
        return idx+"";
    }

    public void innovate(int idx, Neuron neuron) {
        neuron.setInnovation(next(key(idx)));
    }

}
