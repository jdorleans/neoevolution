package org.neoevolution.core.algorithm;

import org.neoevolution.core.operator.evaluation.Evaluation;
import org.neoevolution.core.operator.selection.Selection;
import org.neoevolution.core.operator.speciation.Speciation;
import org.neoevolution.core.stop.StopCondition;
import org.neoevolution.mvc.model.Genotype;
import org.neoevolution.mvc.model.Population;

import java.util.List;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since 1.0
 */
public abstract class AbstractNNAlgorithm
        <Eva extends Evaluation, Sel extends Selection, Spe extends Speciation, Sto extends StopCondition>
        implements NNAlgorithm {

    protected Population population;

    protected Eva evaluation;

    protected Sel selection;

    protected Spe speciation;

    protected Sto stopCondition;


    // 1. Evaluation
    // 2. Selection
    // 3. Reproduction
    // 4. Mutation
    // 5. Speciation
    @Override
    public void evolve()
    {
        while (!stopCondition.isStop(population)) {
            evolution();
        }
        evaluation.evaluate(population);
    }
    
    protected void evolution() {
        evaluation.evaluate(population);
        population.nextGeneration();
        List<Genotype> offsprings = selection.select(population);
        speciation.speciate(population, offsprings);
    }


    public Population getPopulation() {
        return population;
    }
    public void setPopulation(Population population) {
        this.population = population;
    }

    public Eva getEvaluation() {
        return evaluation;
    }
    public void setEvaluation(Eva evaluation) {
        this.evaluation = evaluation;
    }

    public Sel getSelection() {
        return selection;
    }
    public void setSelection(Sel selection) {
        this.selection = selection;
    }

    public Spe getSpeciation() {
        return speciation;
    }
    public void setSpeciation(Spe speciation) {
        this.speciation = speciation;
    }

    public Sto getStopCondition() {
        return stopCondition;
    }
    public void setStopCondition(Sto stopCondition) {
        this.stopCondition = stopCondition;
    }

}
