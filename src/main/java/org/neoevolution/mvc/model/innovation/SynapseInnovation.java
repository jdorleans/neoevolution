package org.neoevolution.mvc.model.innovation;

import org.springframework.data.neo4j.annotation.NodeEntity;

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
