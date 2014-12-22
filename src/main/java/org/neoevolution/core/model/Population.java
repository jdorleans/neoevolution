package org.neoevolution.core.model;

import org.neoevolution.util.MapUtils;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;

import java.util.Set;

@NodeEntity
public class Population extends AbstractFitnessEntity {

    private static final long serialVersionUID = 6557027803394718011L;

    @RelatedTo(type="BEST_GENOTYPE")
    private Genotype bestGenotype;

    @RelatedTo(type="BEST_SPECIES")
    private Species bestSpecies;

    @RelatedTo(type="SPECIES")
    private Set<Species> species;


    public Population() {
        this(1l, 10);
    }

    public Population(Long innovation, Integer maxSpecies) {
        super(innovation, 1l);
        this.species = MapUtils.createLinkedHashSet(maxSpecies*2);
    }


    public Long nextGeneration() {
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
