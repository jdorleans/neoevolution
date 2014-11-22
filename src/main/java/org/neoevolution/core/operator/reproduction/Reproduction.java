package org.neoevolution.core.operator.reproduction;

import org.neoevolution.core.Genotype;

public interface Reproduction {

    Genotype reproduce(Parents parents, int generation);

}
