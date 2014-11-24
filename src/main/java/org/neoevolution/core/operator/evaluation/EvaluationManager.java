package org.neoevolution.core.operator.evaluation;

import org.neoevolution.core.GAConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since 24/11/14.
 */
@Component
public class EvaluationManager {

    private Evaluation evaluation;

    @Autowired
    private List<Evaluation> evaluations;

    @Autowired
    private GAConfiguration configuration;


    @PostConstruct
    private void init() {
        evaluation = get(configuration.getEvaluationFunction());
    }


    public Evaluation get() {
        return evaluation;
    }

    public Evaluation get(String name)
    {
        for (Evaluation function : evaluations) {
            if (function.getName().equals(name)) {
                return function;
            }
        }
        return null;
    }


}
