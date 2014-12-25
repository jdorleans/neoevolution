package org.neoevolution.core.stop;

import org.neoevolution.mvc.model.Population;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since Nov 27 2014
 */
public interface StopCondition {

    boolean isStop(Population population);

}
