package org.neoevolution.mvc.model;

import org.neoevolution.util.MapUtils;
import org.springframework.data.neo4j.annotation.Fetch;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;

import java.util.Set;

@NodeEntity
public class Species extends AbstractFitnessEntity {

    private static final long serialVersionUID = -2774570149581847246L;

    @Fetch
    @RelatedTo(type="BEST")
    private Genotype bestGenotype;

    @Fetch
    @RelatedTo(type="GENOTYPE")
    private Set<Genotype> genotypes;


    public Species() {
        this(1l, 1l, 100);
    }

    public Species(Long innovation, Long generation, Integer size) {
        super(innovation, generation);
        this.genotypes = MapUtils.createLinkedHashSet(size);
    }


    public void addGenotype(Genotype genotype) {
        updateBestGenotype(genotype);
        genotypes.add(genotype);
    }

    public void updateBestGenotype(Genotype genotype) {
        if (bestGenotype == null || genotype.getFitness() > bestGenotype.getFitness()) {
            bestGenotype = genotype;
        }
    }


    @Override
    public boolean equals(Object obj) {
        return (obj instanceof Species && super.equals(obj));
    }

    @Override
    public String toString() {
        return "SPECIES(i:"+ innovation +", g:"+ generation +", f:"+ fitness +")";
    }


    public Genotype getBestGenotype() {
        return bestGenotype;
    }
    public void setBestGenotype(Genotype bestGenotype) {
        this.bestGenotype = bestGenotype;
    }

    public Set<Genotype> getGenotypes() {
        return genotypes;
    }
    public void setGenotypes(Set<Genotype> genotypes) {
        this.genotypes = genotypes;
    }

}
