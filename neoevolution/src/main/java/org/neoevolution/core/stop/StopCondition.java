package org.neoevolution.core.stop;

import org.neoevolution.mvc.model.Population;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since 1.0
 */
public interface StopCondition {

    boolean isStop(Population population);

}
