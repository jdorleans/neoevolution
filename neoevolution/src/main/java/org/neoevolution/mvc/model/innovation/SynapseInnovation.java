package org.neoevolution.mvc.model.innovation;


import org.neo4j.ogm.annotation.NodeEntity;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since 1.0
 */
@NodeEntity
public class SynapseInnovation extends AbstractInnovation {

    private static final long serialVersionUID = -8113077369569030102L;

    public static final String SEPARATOR = "-";


    public SynapseInnovation() {
        this(SEPARATOR);
    }

    public SynapseInnovation(String code) {
        super(code);
    }


    @Override
    public boolean equals(Object obj) {
        return (obj instanceof SynapseInnovation && super.equals(obj));
    }

}
