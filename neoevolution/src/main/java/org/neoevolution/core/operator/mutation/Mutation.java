package org.neoevolution.core.operator.mutation;

import org.neoevolution.mvc.model.Genotype;

public interface Mutation {

    void mutate(Genotype genotype);

}
