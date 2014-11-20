package org.neoevolution.core.operator.mutation;

import org.neoevolution.core.Genotype;
import org.neoevolution.core.operator.AbstractOperation;

public abstract class AbstractMutation extends AbstractOperation implements Mutation {

    protected abstract void mutation(Genotype genotype);

    @Override
    public void mutate(Genotype genotype) {
        mutate(genotype, false);
    }

    @Override
    public void mutate(Genotype genotype, boolean ignoreRate) {
        if (ignoreRate || operate()) {
            mutation(genotype);
        }
    }

}
