package org.neoevolution.factory.algorithm;

import org.neoevolution.core.algorithm.AbstractNNAlgorithm;
import org.neoevolution.core.operator.evaluation.Evaluation;
import org.neoevolution.core.operator.selection.Selection;
import org.neoevolution.core.operator.speciation.Speciation;
import org.neoevolution.core.stop.StopCondition;
import org.neoevolution.factory.model.PopulationFactory;
import org.neoevolution.factory.model.configuration.AbstractConfigurableFactory;
import org.neoevolution.factory.operator.evaluation.EvaluationFactory;
import org.neoevolution.factory.operator.selection.SelectionFactory;
import org.neoevolution.factory.operator.speciation.SpeciationFactory;
import org.neoevolution.factory.stop.StopConditionFactory;
import org.neoevolution.mvc.model.configuration.NNConfiguration;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since 1.0
 */
public abstract class AbstractNNAlgorithmFactory<T extends AbstractNNAlgorithm<Eva, Sel, Spe, Sto>,
        Eva extends Evaluation, Sel extends Selection,
        Spe extends Speciation, Sto extends StopCondition, C extends NNConfiguration>
        extends AbstractConfigurableFactory<T, C>
        implements NNAlgorithmFactory<T, C> {

    protected PopulationFactory<C> populationFactory;

    protected EvaluationFactory<Eva, C> evaluationFactory;

    protected SelectionFactory<Sel, C> selectionFactory;

    protected SpeciationFactory<Spe, C> speciationFactory;

    protected StopConditionFactory<Sto, C> stopConditionFactory;


    protected AbstractNNAlgorithmFactory() {
        populationFactory = new PopulationFactory<>();
    }


    @Override
    public void configure(C configuration)
    {
        super.configure(configuration);
        evaluationFactory.configure(configuration);
        selectionFactory.configure(configuration);
        speciationFactory.configure(configuration);
        stopConditionFactory.configure(configuration);
        populationFactory.configure(configuration);
        populationFactory.setSpeciation(speciationFactory.create());
    }

    @Override
    public T create()
    {
        T algorithm = creation();
        algorithm.setPopulation(populationFactory.create());
        algorithm.setEvaluation(evaluationFactory.create());
        algorithm.setSelection(selectionFactory.create());
        algorithm.setSpeciation(speciationFactory.create());
        algorithm.setStopCondition(stopConditionFactory.create());
        return algorithm;
    }

    protected abstract T creation();


    public PopulationFactory<C> getPopulationFactory() {
        return populationFactory;
    }
    public void setPopulationFactory(PopulationFactory<C> populationFactory) {
        this.populationFactory = populationFactory;
    }

    public EvaluationFactory<Eva, C> getEvaluationFactory() {
        return evaluationFactory;
    }
    public void setEvaluationFactory(EvaluationFactory<Eva, C> evaluationFactory) {
        this.evaluationFactory = evaluationFactory;
    }

    public SelectionFactory<Sel, C> getSelectionFactory() {
        return selectionFactory;
    }
    public void setSelectionFactory(SelectionFactory<Sel, C> selectionFactory) {
        this.selectionFactory = selectionFactory;
    }

    public SpeciationFactory<Spe, C> getSpeciationFactory() {
        return speciationFactory;
    }
    public void setSpeciationFactory(SpeciationFactory<Spe, C> speciationFactory) {
        this.speciationFactory = speciationFactory;
    }

    public StopConditionFactory<Sto, C> getStopConditionFactory() {
        return stopConditionFactory;
    }
    public void setStopConditionFactory(StopConditionFactory<Sto, C> stopConditionFactory) {
        this.stopConditionFactory = stopConditionFactory;
    }

}
