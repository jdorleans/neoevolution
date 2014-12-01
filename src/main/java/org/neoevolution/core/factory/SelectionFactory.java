package org.neoevolution.core.factory;

import org.neoevolution.core.GAConfiguration;
import org.neoevolution.core.operator.selection.Selection;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since Nov 30 2014
 */
public interface SelectionFactory<T extends Selection, C extends GAConfiguration>
        extends ConfigurableFactory<T, C> {

}
