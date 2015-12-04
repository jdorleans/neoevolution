package org.neoevolution.mvc.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;
import org.neoevolution.mvc.json.InnovationSerializer;
import org.neoevolution.util.MapUtils;

import java.util.Set;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since 1.0
 */
@NodeEntity
public class Species extends AbstractFitnessEntity {

    private static final long serialVersionUID = -2774570149581847246L;

    @Relationship(type="BEST")
    @JsonSerialize(using = InnovationSerializer.class)
    private Genotype bestGenotype;

    @Relationship(type="GENOTYPE")
    private Set<Genotype> genotypes;


    public Species() {
        this(1L, 1L, 100);
    }

    public Species(Long innovation, Long generation, Integer size) {
        super(innovation, generation);
        this.genotypes = MapUtils.createHashSet(size);
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
