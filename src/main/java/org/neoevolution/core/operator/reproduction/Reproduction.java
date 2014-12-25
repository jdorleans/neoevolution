package org.neoevolution.core.operator.reproduction;

import org.neoevolution.mvc.model.Genotype;

public interface Reproduction {

    Genotype reproduce(Parents parents, Long generation);

}
