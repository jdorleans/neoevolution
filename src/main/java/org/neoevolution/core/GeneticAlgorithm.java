package org.neoevolution.core;

import org.neoevolution.core.factory.GenotypeFactory;
import org.neoevolution.core.operator.Speciation;
import org.neoevolution.core.operator.evaluation.Evaluation;
import org.neoevolution.core.operator.selection.Selection;
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

    @Autowired
    private Evaluation evaluation;

    @Autowired
    private Selection selection;

    @Autowired
    private Speciation speciation;

    @Autowired
    private GAConfiguration configuration;

//    @PostConstruct
    private void init() {
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
