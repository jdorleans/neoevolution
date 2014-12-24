package org.neoevolution.core.model;

import org.neoevolution.mvc.AbstractEntity;

public abstract class AbstractFitnessEntity extends AbstractInnovationEntity {

    private static final long serialVersionUID = 3287349315995923916L;

    protected Long generation;

    protected Double fitness;


    protected AbstractFitnessEntity() {
        this(1l, 1l);
    }

    protected AbstractFitnessEntity(Long innovation, Long generation) {
        this(innovation, generation, 0d);
    }

    protected AbstractFitnessEntity(Long innovation, Long generation, Double fitness) {
        super(innovation);
        this.generation = generation;
        this.fitness = fitness;
    }


    @Override
    public boolean equals(Object obj)
    {
        boolean equals = false;

        if (this == obj) {
            equals = true;
        }
        else if (innovation != null && generation != null)
        {
            if (obj instanceof AbstractFitnessEntity) {
                AbstractFitnessEntity entity = (AbstractFitnessEntity) obj;
                equals = getKey().equals(entity.getKey());
            }
        }
        return equals;
    }

    @Override
    public int hashCode()
    {
        if (innovation == null || generation == null) {
            return System.identityHashCode(this);
        } else {
            return getKey().hashCode();
        }
    }

    @Override
    public int compareTo(AbstractEntity entity)
    {
        int compare = 1;

        if (innovation == null || generation == null) {
            compare = -1;
        }
        else if (entity instanceof AbstractFitnessEntity)
        {
            AbstractFitnessEntity gene = (AbstractFitnessEntity) entity;

            if (gene.getKey() != null) {
                compare = getKey().compareTo(gene.getKey());
            }
        }
        return compare;
    }


    public Long getGeneration() {
        return generation;
    }
    public void setGeneration(Long generation) {
        this.generation = generation;
    }

    public Double getFitness() {
        return fitness;
    }
    public void setFitness(Double fitness) {
        this.fitness = fitness;
    }

    public String getKey() {
        return generation + ";" + innovation;
    }
}
