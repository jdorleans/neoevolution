package org.neoevolution.core;

import org.neoevolution.core.factory.GenotypeFactory;
import org.neoevolution.core.model.Genotype;
import org.neoevolution.core.model.Population;
import org.neoevolution.core.operator.evaluation.Evaluation;
import org.neoevolution.core.operator.evaluation.EvaluationManager;
import org.neoevolution.core.operator.selection.Selection;
import org.neoevolution.core.operator.speciation.Speciation;
import org.neoevolution.core.stop.FitnessStop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Set;

@Component
public class GeneticAlgorithm {

    private Population population;

    @Autowired
    private GenotypeFactory genotypeFactory;

    private Evaluation evaluation;

    @Autowired
    private EvaluationManager evaluationManager;

    @Autowired
    private Selection selection;

    @Autowired
    private Speciation speciation;

    @Autowired
    private FitnessStop stopCondition;

    @Autowired
    private GAConfiguration configuration;


    @PostConstruct
    private void init() {
        evaluation = evaluationManager.getCached();
        population = new Population(configuration.getMaxSpeciesSize());
        Set<Genotype> offsprings = genotypeFactory.createList(configuration.getPopulationSize(), population.getGeneration());
        speciation.speciate(population, offsprings);
    }


    // 1. Evaluation
    // 2. Selection
    // 3. Reproduction
    // 4. Mutation
    // 5. Speciation
    public void evolve() {
        evolve(true);
    }

    public void evolve(boolean reset)
    {
        if (reset) {
            init();
        }
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

}
