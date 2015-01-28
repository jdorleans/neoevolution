package org.neoevolution.core.stop;

import org.neoevolution.mvc.model.Population;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since 1.0
 */
public class GenerationStop implements StopCondition {

    private static final Integer DEFAULT = 100;

    private Integer goal;


    public GenerationStop() {
        this(DEFAULT);
    }

    public GenerationStop(Integer goal) {
        this.goal = goal;
    }


    @Override
    public boolean isStop(Population population) {
        return population.getGeneration() >= goal;
    }


    public Integer getGoal() {
        return goal;
    }
    public void setGoal(Integer goal) {
        this.goal = goal;
    }

}
