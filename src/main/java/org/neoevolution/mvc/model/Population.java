package org.neoevolution.mvc.model;

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

    public void addSpecies(Species specie) {
        updateBestSpecies(specie);
        species.add(specie);
    }

    public void updateBestSpecies(Species species)
    {
        Genotype speciesGenotype = species.getBestGenotype();

        if (bestGenotype == null || speciesGenotype.getFitness() > bestGenotype.getFitness()) {
            bestGenotype = speciesGenotype;
        }
        if (bestSpecies == null || species.getFitness() > bestSpecies.getFitness()) {
            bestSpecies = species;
        }
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
