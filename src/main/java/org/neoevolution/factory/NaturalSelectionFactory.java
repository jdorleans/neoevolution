package org.neoevolution.factory;

import org.neoevolution.core.GAConfiguration;
import org.neoevolution.core.operator.mutation.Mutation;
import org.neoevolution.core.operator.reproduction.Reproduction;
import org.neoevolution.core.operator.selection.NaturalSelection;
import org.neoevolution.util.ClassUtils;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since Nov 30 2014
 */
public class NaturalSelectionFactory<C extends GAConfiguration>
        extends AbstractConfigurableFactory<NaturalSelection, C>
        implements SelectionFactory<NaturalSelection, C> {

    private ReproductionFactory<Reproduction, C> reproductionFactory;

    private MutationFactory<Mutation, C> mutationFactory;


    @Override
    public void configure(C configuration) {
        super.configure(configuration);
        reproductionFactory = ClassUtils.create(configuration.getReproductionFactory());
        reproductionFactory.configure(configuration);
        mutationFactory = ClassUtils.create(configuration.getMutationFactory());
        mutationFactory.configure(configuration);
    }


    @Override
    public NaturalSelection create()
    {
        NaturalSelection naturalSelection = new NaturalSelection();
        naturalSelection.setPopulationSize(configuration.getPopulationSize());
        naturalSelection.setMaxSpeciesSize(configuration.getMaxSpeciesSize());
        naturalSelection.setSurvivalRate(configuration.getSurvivalRate());
        naturalSelection.setElitismRate(configuration.getElitismRate());
        naturalSelection.setReproduction(reproductionFactory.create());
        naturalSelection.setMutation(mutationFactory.create());
        return naturalSelection;
    }

}
