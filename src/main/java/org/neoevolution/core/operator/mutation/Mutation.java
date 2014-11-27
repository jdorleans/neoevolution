package org.neoevolution.core.operator.mutation;

import org.neoevolution.core.model.Genotype;

public interface Mutation {

    void mutate(Genotype genotype);

    void mutate(Genotype genotype, boolean ignoreRate);
}
