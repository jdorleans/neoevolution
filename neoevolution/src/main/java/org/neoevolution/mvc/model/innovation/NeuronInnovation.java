package org.neoevolution.mvc.model.innovation;


import org.neo4j.ogm.annotation.NodeEntity;
import org.neoevolution.mvc.model.Neuron;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since 1.0
 */
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
        return Integer.toString(idx);
    }

    public void innovate(int idx, Neuron neuron) {
        neuron.setInnovation(next(key(idx)));
    }


    @Override
    public boolean equals(Object obj) {
        return (obj instanceof NeuronInnovation && super.equals(obj));
    }

}
