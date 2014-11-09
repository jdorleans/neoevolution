package org.neoevolution.core.operator.reproduction;

import org.neoevolution.core.Population;

public interface Reproduction {

    java.util.List<org.neoevolution.core.Genotype> reproduce(Population population);

}
