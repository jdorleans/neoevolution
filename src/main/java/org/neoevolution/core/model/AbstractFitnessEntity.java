package org.neoevolution.core.model;

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
        } else {
            String key = getKey();

            if (key != null && obj instanceof AbstractFitnessEntity) {
                AbstractFitnessEntity entity = (AbstractFitnessEntity) obj;
                equals = key.equals(entity.getKey());
            }
        }
        return equals;
    }

    @Override
    public int hashCode() {
        String key = getKey();
        return (key == null ? System.identityHashCode(this) : key.hashCode());
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
