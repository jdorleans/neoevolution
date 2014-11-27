package org.neoevolution.core.operator.mutation;

import org.neoevolution.core.model.Genotype;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since 25/10/14.
 */
@Component
public class MutationManager {

    @Autowired
    private List<Mutation> mutations;


    public void mutate(Genotype genotype) {
        for (Mutation mutation : mutations) {
            mutation.mutate(genotype);
        }
    }

}
