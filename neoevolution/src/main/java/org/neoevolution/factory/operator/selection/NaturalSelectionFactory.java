package org.neoevolution.factory.operator.selection;

import org.neoevolution.core.operator.mutation.ComposedMutation;
import org.neoevolution.core.operator.reproduction.Crossover;
import org.neoevolution.core.operator.selection.NaturalSelection;
import org.neoevolution.factory.operator.mutation.NEMutationFactory;
import org.neoevolution.factory.operator.reproduction.CrossoverFactory;
import org.neoevolution.mvc.model.configuration.NEConfiguration;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since Nov 30 2014
 */
public class NaturalSelectionFactory<C extends NEConfiguration>
        extends AbstractSelectionFactory<NaturalSelection, Crossover, ComposedMutation, C> {

    public NaturalSelectionFactory() {
        reproductionFactory = new CrossoverFactory<>();
        mutationFactory = new NEMutationFactory<>();
    }

    @Override
    public NaturalSelection create()
    {
        NaturalSelection selection = new NaturalSelection();
        selection.setPopulationSize(configuration.getPopulationSize());
        selection.setMaxSpeciesSize(configuration.getMaxSpeciesSize());
        selection.setSurvivalRate(configuration.getSurvivalRate());
        selection.setElitismRate(configuration.getElitismRate());
        selection.setReproduction(reproductionFactory.create());
        selection.setMutation(mutationFactory.create());
        return selection;
    }

}
