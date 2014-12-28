package org.neoevolution.mvc.model;

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
        if (generation != null && obj instanceof AbstractFitnessEntity) {
            AbstractFitnessEntity entity = (AbstractFitnessEntity) obj;
            return super.equals(obj) && generation.equals(entity.getGeneration());
        }
        return false;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (generation != null ? generation.hashCode() : 0);
        result = 31 * result + (fitness != null ? fitness.hashCode() : 0);
        return result;
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
            AbstractFitnessEntity e = (AbstractFitnessEntity) entity;

            if (e.getGeneration() != null)
            {
                compare = generation.compareTo(e.getGeneration());

                if (compare == 0) {
                    compare = super.compareTo(entity);
                }
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

}
