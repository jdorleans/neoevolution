package org.neoevolution.core.operator.evaluation;

import org.neoevolution.core.Population;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since 01/11/14.
 */
public interface Evaluation {

    String getName();

    void evaluate(Population population);

}
