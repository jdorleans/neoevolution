package org.neoevolution.core.stop;

import org.neoevolution.core.model.Population;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since Nov 27 2014
 */
public class ComposedStop implements StopCondition {

    private List<StopCondition> conditions;


    public ComposedStop() {
        this(new ArrayList<StopCondition>());
    }

    public ComposedStop(List<StopCondition> conditions) {
        this.conditions = conditions;
    }


    @Override
    public boolean isStop(Population population)
    {
        for (StopCondition condition : conditions) {
            if (!condition.isStop(population)) {
                return false;
            }
        }
        return true;
    }


    public void addStopCondition(StopCondition condition) {
        if (conditions != null) {
            conditions.add(condition);
        }
    }


    public List<StopCondition> getConditions() {
        return conditions;
    }
    public void setConditions(List<StopCondition> conditions) {
        this.conditions = conditions;
    }

}
