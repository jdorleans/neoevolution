package org.neoevolution.core.algorithm;

import org.neoevolution.core.operator.evaluation.Evaluation;
import org.neoevolution.core.operator.selection.NaturalSelection;
import org.neoevolution.core.operator.speciation.NESpeciation;
import org.neoevolution.core.stop.StopCondition;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since Dec 20 2014
 */
public abstract class AbstractNEAlgorithm<Eva extends Evaluation, Sto extends StopCondition>
        extends AbstractNNAlgorithm<Eva, NaturalSelection, NESpeciation, Sto> {

}
