package org.neoevolution.core.operator.reproduction;

import org.neoevolution.core.Genotype;
import org.neoevolution.core.Population;

import java.util.Set;

public interface Reproduction {

    Set<Genotype> reproduce(Population population);

}
