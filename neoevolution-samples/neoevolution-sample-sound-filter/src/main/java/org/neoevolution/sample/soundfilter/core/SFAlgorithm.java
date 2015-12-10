package org.neoevolution.sample.soundfilter.core;

import org.neoevolution.core.algorithm.AbstractNEStopAlgorithm;
import org.neoevolution.mvc.model.Genotype;
import org.neoevolution.sample.soundfilter.mvc.service.SFValidationService;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since 1.0
 */
public class SFAlgorithm extends AbstractNEStopAlgorithm<SFEvaluation> {

    private SFValidationService validationService;

    public SFAlgorithm(SFValidationService validationService) {
        super();
        this.validationService = validationService;
    }


    @Override
    protected void evolution()
    {
        super.evolution();

        if (population.getGeneration() % 500 == 0) {
            Genotype bestGenotype = population.getBestGenotype();
            validationService.validate(bestGenotype);
        }
    }

}
