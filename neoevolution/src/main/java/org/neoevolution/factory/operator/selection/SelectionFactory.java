package org.neoevolution.factory.operator.selection;

import org.neoevolution.mvc.model.configuration.NNConfiguration;
import org.neoevolution.core.operator.selection.Selection;
import org.neoevolution.factory.model.configuration.ConfigurableFactory;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since Nov 30 2014
 */
public interface SelectionFactory<T extends Selection, C extends NNConfiguration>
        extends ConfigurableFactory<T, C> {

}
