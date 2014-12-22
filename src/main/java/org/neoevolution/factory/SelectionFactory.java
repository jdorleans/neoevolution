package org.neoevolution.factory;

import org.neoevolution.core.configuration.NNConfiguration;
import org.neoevolution.core.operator.selection.Selection;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since Nov 30 2014
 */
public interface SelectionFactory<T extends Selection, C extends NNConfiguration>
        extends ConfigurableFactory<T, C> {

}
