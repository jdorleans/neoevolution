package org.neoevolution.factory.operator.selection;

import org.neoevolution.core.operator.selection.Selection;
import org.neoevolution.factory.model.configuration.ConfigurableFactory;
import org.neoevolution.mvc.model.configuration.NNConfiguration;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since 1.0
 */
public interface SelectionFactory<T extends Selection, C extends NNConfiguration>
        extends ConfigurableFactory<T, C> {

}
