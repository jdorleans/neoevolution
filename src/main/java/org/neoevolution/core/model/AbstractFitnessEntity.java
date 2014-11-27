package org.neoevolution.core.model;

public abstract class AbstractFitnessEntity extends AbstractInnovationEntity {

    private static final long serialVersionUID = 3287349315995923916L;

    protected Integer generation;

    protected Double fitness;


    protected AbstractFitnessEntity(Long innovation) {
        this(innovation, 0);
    }

    protected AbstractFitnessEntity(Long innovation, Integer generation) {
        this(innovation, generation, 0d);
    }

    protected AbstractFitnessEntity(Long innovation, Integer generation, Double fitness) {
        super(innovation);
        this.generation = generation;
        this.fitness = fitness;
    }


    public Integer getGeneration() {
        return generation;
    }
    public void setGeneration(Integer generation) {
        this.generation = generation;
    }

    public Double getFitness() {
        return fitness;
    }
    public void setFitness(Double fitness) {
        this.fitness = fitness;
    }

}
