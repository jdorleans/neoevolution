package org.neoevolution.core.stop;

import org.neoevolution.mvc.model.Population;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since Nov 27 2014
 */
public class ComposedStop implements StopCondition {

    private Boolean assertAll;

    private List<StopCondition> conditions;


    public ComposedStop() {
        this(true, new ArrayList<StopCondition>());
    }

    public ComposedStop(Boolean assertAll, List<StopCondition> conditions) {
        this.assertAll = assertAll;
        this.conditions = conditions;
    }


    @Override
    public boolean isStop(Population population)
    {
        boolean isStop = true;

        for (StopCondition condition : conditions)
        {
            boolean stop = condition.isStop(population);

            if (assertAll) {
                if (!stop) {
                    return false;
                }
            } else {
                if (stop) {
                    return true;
                } else {
                    isStop = false;
                }
            }
        }
        return isStop;
    }


    public void add(StopCondition condition) {
        if (conditions != null) {
            conditions.add(condition);
        }
    }


    public Boolean isAssertAll() {
        return assertAll;
    }
    public void setAssertAll(Boolean assertAll) {
        this.assertAll = assertAll;
    }

    public List<StopCondition> getConditions() {
        return conditions;
    }
    public void setConditions(List<StopCondition> conditions) {
        this.conditions = conditions;
    }

}
