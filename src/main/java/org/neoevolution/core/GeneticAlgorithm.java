package org.neoevolution.core;

import org.neoevolution.core.factory.GenotypeFactory;
import org.neoevolution.core.model.Genotype;
import org.neoevolution.core.model.Population;
import org.neoevolution.core.operator.evaluation.Evaluation;
import org.neoevolution.core.operator.evaluation.EvaluationManager;
import org.neoevolution.core.operator.selection.Selection;
import org.neoevolution.core.operator.speciation.Speciation;
import org.neoevolution.mvc.service.PopulationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class GeneticAlgorithm {

    private Population population;

    private Set<Genotype> offsprings;

    @Autowired
    private GenotypeFactory genotypeFactory;

    @Autowired
    private PopulationService populationService;

    private Evaluation evaluation;

    @Autowired
    private EvaluationManager evaluationManager;

    @Autowired
    private Selection selection;

    @Autowired
    private Speciation speciation;

    @Autowired
    private GAConfiguration configuration;


    private void init() {
        evaluation = evaluationManager.get();
        population = new Population(configuration.getMaxSpeciesSize());
        offsprings = genotypeFactory.createList(configuration.getPopulationSize(), population.getGeneration());
        speciation.speciate(population, offsprings);
    }


    // 1. Evaluation
    // 2. Selection
    // 3. Reproduction
    // 4. Mutation
    // 5. Speciation
    public void evolve()
    {
        init();
        while (population.getBestGenotype().getFitness() < 0.99) {
            evolution();
        }
        evaluation.evaluate(population);
//        populationService.save(population);
    }
    
    private void evolution() {
        evaluation.evaluate(population);
        offsprings = selection.select(population);
        speciation.speciate(population, offsprings);
    }

}
