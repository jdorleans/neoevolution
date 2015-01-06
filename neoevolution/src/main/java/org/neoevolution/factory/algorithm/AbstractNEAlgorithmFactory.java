package org.neoevolution.factory.algorithm;

import org.neoevolution.core.algorithm.AbstractNEAlgorithm;
import org.neoevolution.mvc.model.configuration.NEConfiguration;
import org.neoevolution.core.operator.evaluation.Evaluation;
import org.neoevolution.core.operator.selection.NaturalSelection;
import org.neoevolution.core.operator.speciation.NESpeciation;
import org.neoevolution.core.stop.StopCondition;
import org.neoevolution.factory.operator.selection.NaturalSelectionFactory;
import org.neoevolution.factory.operator.speciation.NESpeciationFactory;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since Dec 20 2014
 */
public abstract class AbstractNEAlgorithmFactory<T extends AbstractNEAlgorithm<Eva, Sto>,
        Eva extends Evaluation, Sto extends StopCondition, C extends NEConfiguration>
        extends AbstractNNAlgorithmFactory<T, Eva, NaturalSelection, NESpeciation, Sto, C> {

    protected AbstractNEAlgorithmFactory() {
        super();
        selectionFactory = new NaturalSelectionFactory<>();
        speciationFactory = new NESpeciationFactory<>();
    }

}
