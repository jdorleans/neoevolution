package org.neoevolution.sample.soundfilter.factory;

import org.neoevolution.core.error.FitnessCalculator;
import org.neoevolution.factory.algorithm.AbstractNEStopAlgorithmFactory;
import org.neoevolution.sample.soundfilter.core.SFAlgorithm;
import org.neoevolution.sample.soundfilter.core.SFEvaluation;
import org.neoevolution.sample.soundfilter.mvc.model.SFConfiguration;
import org.neoevolution.sample.soundfilter.mvc.service.SFValidationService;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since 1.0
 */
public class SFAlgorithmFactory extends AbstractNEStopAlgorithmFactory
        <SFAlgorithm, SFEvaluation, SFConfiguration> {

    private SFValidationService validationService;

    public SFAlgorithmFactory(FitnessCalculator fitnessCalculator, SFValidationService validationService) {
        super();
        evaluationFactory = new SFEvaluationFactory(fitnessCalculator);
        this.validationService = validationService;
    }

    @Override
    protected SFAlgorithm creation() {
        return new SFAlgorithm(validationService);
    }

}
