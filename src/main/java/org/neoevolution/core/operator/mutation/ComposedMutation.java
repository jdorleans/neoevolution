package org.neoevolution.core.operator.mutation;

import org.neoevolution.mvc.model.Genotype;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since 25/10/14.
 */
public class ComposedMutation implements Mutation {

    private List<Mutation> mutations;


    public ComposedMutation() {
        mutations = new ArrayList<>();
    }

    public ComposedMutation(List<Mutation> mutations) {
        this.mutations = mutations;
    }


    public void mutate(Genotype genotype) {
        for (Mutation mutation : mutations) {
            mutation.mutate(genotype);
        }
    }


    public void add(Mutation mutation) {
        if (mutations != null) {
            mutations.add(mutation);
        }
    }


    public List<Mutation> getMutations() {
        return mutations;
    }
    public void setMutations(List<Mutation> mutations) {
        this.mutations = mutations;
    }

}
