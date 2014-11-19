package org.neoevolution.core;

import org.neoevolution.core.factory.GenotypeFactory;
import org.neoevolution.core.operator.Speciation;
import org.neoevolution.core.operator.evaluation.Evaluation;
import org.neoevolution.core.operator.mutation.MutationManager;
import org.neoevolution.core.operator.reproduction.Reproduction;
import org.neoevolution.core.operator.selection.Selection;
import org.neoevolution.mvc.service.PopulationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
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
    private Reproduction reproduction;

    @Autowired
    private Speciation speciation;

    @Autowired
    private MutationManager mutation;

    @Autowired
    private GAConfiguration configuration;

    @PostConstruct
    private void init() {
        population = new Population(configuration.getMaxSpeciesSize());
        offsprings = genotypeFactory.createList(configuration.getPopulationSize());
        speciation.speciate(population, offsprings);
    }


    // 1. Evaluation
    // 2. Selection
    // 3. Reproduction
    // 4. Mutation
    // 5. Speciation
    public void evolve()
    {
        while (population.getBestGenotype().getFitness() < 0.99) {
            evolution();
        }
        evaluation.evaluate(population);
        populationService.save(population);
    }
    
    private void evolution() 
    {
        evaluation.evaluate(population);
        selection.select(population);
        configuration.setGeneration(population.nextGeneration());
        offsprings = reproduction.reproduce(population);
        mutation.mutate(offsprings);
        speciation.speciate(population, offsprings);
    }

}
