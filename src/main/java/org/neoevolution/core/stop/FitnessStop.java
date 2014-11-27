package org.neoevolution.core.stop;

import org.neoevolution.core.model.Population;
import org.springframework.stereotype.Component;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since Nov 27 2014
 */
@Component
public class FitnessStop implements StopCondition {

    private static final Double DEFAULT = 0.9;

    private Double goal;


    public FitnessStop() {
        this(DEFAULT);
    }

    public FitnessStop(double goal) {
        this.goal = goal;
    }


    @Override
    public boolean isStop(Population population) {
        return population.getBestGenotype().getFitness() >= goal;
    }


    public Double getGoal() {
        return goal;
    }
    public void setGoal(Double goal) {
        this.goal = goal;
    }

}
