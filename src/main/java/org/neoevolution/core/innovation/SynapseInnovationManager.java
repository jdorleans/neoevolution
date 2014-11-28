package org.neoevolution.core.innovation;

import org.springframework.stereotype.Component;

@Component
public class SynapseInnovationManager extends InnovationManager {

    public static final String SEPARATOR = "-";


    public SynapseInnovationManager() {
        this(SEPARATOR);
    }

    public SynapseInnovationManager(String code) {
        this(code, SIZE *100);
    }

    public SynapseInnovationManager(String code, int capacity) {
        super(code, capacity);
    }

}
