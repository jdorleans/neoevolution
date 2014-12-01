package org.neoevolution.factory;

import org.neoevolution.core.GAConfiguration;
import org.neoevolution.core.GeneticAlgorithm;
import org.neoevolution.core.operator.evaluation.Evaluation;
import org.neoevolution.core.operator.selection.Selection;
import org.neoevolution.core.stop.FitnessStop;
import org.neoevolution.core.stop.StopCondition;
import org.neoevolution.util.ClassUtils;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since Nov 28 2014
 */
public class GeneticAlgorithmFactory extends AbstractConfigurableFactory<GeneticAlgorithm, GAConfiguration> {

    private EvaluationFactory<Evaluation, GAConfiguration> evaluationFactory;

    private SelectionFactory<Selection, GAConfiguration> selectionFactory;

    private SpeciationFactory<GAConfiguration> speciationFactory;

    private PopulationFactory<GAConfiguration> populationFactory;

    // FIXME
    private StopCondition stopCondition;


    @Override
    public void configure(GAConfiguration configuration) {
        super.configure(configuration);
        evaluationFactory = ClassUtils.create(configuration.getEvaluationFactory());
        evaluationFactory.configure(configuration);
        selectionFactory = ClassUtils.create(configuration.getSelectionFactory());
        selectionFactory.configure(configuration);
        speciationFactory = ClassUtils.create(configuration.getSpeciationFactory());
        speciationFactory.configure(configuration);
        populationFactory = new PopulationFactory<>(); // FIXME - GENERIC
        populationFactory.configure(configuration);
    }

    @Override
    public GeneticAlgorithm create()
    {
        GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm();
        geneticAlgorithm.setEvaluation(evaluationFactory.create());
        geneticAlgorithm.setSelection(selectionFactory.create());
        geneticAlgorithm.setSpeciation(speciationFactory.create());
        geneticAlgorithm.setPopulation(populationFactory.create());
        geneticAlgorithm.setStopCondition(new FitnessStop(0.9)); // FIXME
        return geneticAlgorithm;
    }

}
