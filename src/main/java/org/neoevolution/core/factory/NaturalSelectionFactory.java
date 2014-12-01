package org.neoevolution.core.factory;

import org.neoevolution.core.GAConfiguration;
import org.neoevolution.core.operator.selection.NaturalSelection;
import org.neoevolution.util.ClassUtils;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since Nov 30 2014
 */
public class NaturalSelectionFactory<C extends GAConfiguration>
        extends AbstractConfigurableFactory<NaturalSelection, C>
        implements SelectionFactory<NaturalSelection, C> {

    private ReproductionFactory reproductionFactory;

    private MutationFactory mutationFactory;


    @Override
    public void configure(C configuration) {
        super.configure(configuration);
        reproductionFactory = ClassUtils.create(configuration.getReproductionFunction());
        reproductionFactory.configure(configuration);
    }


    public NaturalSelection create() {
        return new NaturalSelection();
    }

}
