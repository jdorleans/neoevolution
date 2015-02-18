package org.neoevolution.sample.autopilot.core;

import org.neoevolution.core.operator.evaluation.AbstractEvaluation;
import org.neoevolution.mvc.model.Genotype;
import org.neoevolution.mvc.model.Population;
import org.neoevolution.sample.autopilot.AutoPilotNeoEvolution;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.scheduling.annotation.AsyncResult;

import java.util.concurrent.Future;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since 1.0
 */
@Configurable
public class AutoPilotEvaluation extends AbstractEvaluation {

    @Override
    public void evaluate(Population population) {
        super.evaluate(population);
    }

    @Override
    protected Future<Genotype> evaluate(Genotype genotype)
    {
        AutoPilotNeoEvolution.application.run(genotype);

        while (AutoPilotNeoEvolution.application.isRunning()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        genotype.setEvaluated(true);
        return new AsyncResult<>(genotype);
    }

}
