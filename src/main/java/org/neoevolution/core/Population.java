package org.neoevolution.core;

import org.neoevolution.util.MapUtils;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;

import java.util.Set;

@NodeEntity
public class Population extends AbstractInnovationEntity {

    private static final long serialVersionUID = 6557027803394718011L;

    private transient static long count;

    private Double fitness;

    private Integer generation;

    @RelatedTo(type="BEST_GENOTYPE")
    private Genotype bestGenotype;

    @RelatedTo(type="BEST_SPECIES")
    private Species bestSpecies;

    @RelatedTo(type="SPECIES")
    private Set<Species> species;


    public Population() {
        this(0);
    }

    public Population(int maxSpecies) {
        super(count++);
        this.generation = 0;
        this.species = MapUtils.createLinkedHashSet(maxSpecies*2);
    }


    public int nextGeneration() {
        return ++generation;
    }

    public void addSpecie(Species specie)
    {
        Genotype specieBestGenotype = specie.getBestGenotype();

        if (bestGenotype == null || specieBestGenotype.getFitness() > bestGenotype.getFitness()) {
            bestGenotype = specieBestGenotype;
        }
        if (bestSpecies == null || specie.getFitness() > bestSpecies.getFitness()) {
            bestSpecies = specie;
        }
        species.add(specie);
    }


    @Override
    public boolean equals(Object obj) {
        return (obj instanceof Population && super.equals(obj));
    }


    public Double getFitness() {
        return fitness;
    }
    public void setFitness(Double fitness) {
        this.fitness = fitness;
    }

    public Integer getGeneration() {
        return generation;
    }
    public void setGeneration(Integer generation) {
        this.generation = generation;
    }

    public Genotype getBestGenotype() {
        return bestGenotype;
    }
    public void setBestGenotype(Genotype bestGenotype) {
        this.bestGenotype = bestGenotype;
    }

    public Species getBestSpecies() {
        return bestSpecies;
    }
    public void setBestSpecies(Species bestSpecies) {
        this.bestSpecies = bestSpecies;
    }

    public Set<Species> getSpecies() {
        return species;
    }
    public void setSpecies(Set<Species> species) {
        this.species = species;
    }
}
