package org.neoevolution.core.innovation;

import org.springframework.data.neo4j.annotation.NodeEntity;

@NodeEntity
public class SynapseInnovation extends Innovation {

    private static final long serialVersionUID = -8113077369569030102L;

    public static final String SEPARATOR = "-";


    public SynapseInnovation() {
        this(SEPARATOR);
    }

    public SynapseInnovation(String code) {
        super(code);
    }

}
