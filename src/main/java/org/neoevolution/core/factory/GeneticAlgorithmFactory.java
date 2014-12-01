package org.neoevolution.core.factory;

import org.neoevolution.core.GAConfiguration;
import org.neoevolution.core.GeneticAlgorithm;
import org.neoevolution.core.operator.evaluation.Evaluation;
import org.neoevolution.core.operator.selection.Selection;
import org.neoevolution.core.stop.StopCondition;
import org.neoevolution.util.ClassUtils;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since Nov 28 2014
 */
public class GeneticAlgorithmFactory extends AbstractConfigurableFactory<GeneticAlgorithm, GAConfiguration> {

    private EvaluationFactory<Evaluation, GAConfiguration> evaluationFactory;

    private SelectionFactory<Selection, GAConfiguration> selectionFactory;

    private StopCondition stopCondition;

    private SpeciationFactory speciationFactory;

    private PopulationFactory populationFactory;


    @Override
    public void configure(GAConfiguration configuration) {
        super.configure(configuration);
        evaluationFactory = ClassUtils.create(configuration.getEvaluationFunction());
        evaluationFactory.configure(configuration);
        selectionFactory = ClassUtils.create(configuration.getSelectionFunction());
        selectionFactory.configure(configuration);
        speciationFactory = new SpeciationFactory(configuration);
        populationFactory = new PopulationFactory(configuration);
    }

    public GeneticAlgorithm create()
    {
        GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm();
        geneticAlgorithm.setEvaluation(evaluationFactory.create());
        geneticAlgorithm.setSpeciation(speciationFactory.create());
        geneticAlgorithm.setPopulation(populationFactory.create());
        return geneticAlgorithm;
    }

}
