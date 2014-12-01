package org.neoevolution.core;

import org.neoevolution.core.model.Genotype;
import org.neoevolution.core.model.Population;
import org.neoevolution.core.operator.evaluation.Evaluation;
import org.neoevolution.core.operator.selection.Selection;
import org.neoevolution.core.operator.speciation.Speciation;
import org.neoevolution.core.stop.StopCondition;

import java.util.Set;

public class GeneticAlgorithm {

    private Population population;

    private Evaluation evaluation;

    private Selection selection;

    private Speciation speciation;

    private StopCondition stopCondition;


    public GeneticAlgorithm() { }

    public GeneticAlgorithm(Population population) {
        this.population = population;
    }

    // 1. Evaluation
    // 2. Selection
    // 3. Reproduction
    // 4. Mutation
    // 5. Speciation
    public void evolve()
    {
        while (!stopCondition.isStop(population)) {
            evolution();
        }
        evaluation.evaluate(population);
    }
    
    private void evolution() {
        evaluation.evaluate(population);
        Set<Genotype> offsprings = selection.select(population);
        speciation.speciate(population, offsprings);
    }


    public Population getPopulation() {
        return population;
    }
    public void setPopulation(Population population) {
        this.population = population;
    }

    public Evaluation getEvaluation() {
        return evaluation;
    }
    public void setEvaluation(Evaluation evaluation) {
        this.evaluation = evaluation;
    }

    public Selection getSelection() {
        return selection;
    }
    public void setSelection(Selection selection) {
        this.selection = selection;
    }

    public Speciation getSpeciation() {
        return speciation;
    }
    public void setSpeciation(Speciation speciation) {
        this.speciation = speciation;
    }

    public StopCondition getStopCondition() {
        return stopCondition;
    }
    public void setStopCondition(StopCondition stopCondition) {
        this.stopCondition = stopCondition;
    }

}
