package org.neoevolution.core.operator.evaluation;

import org.neoevolution.mvc.model.Genotype;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.Future;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since 1.0
 */
@Component
public class AsyncTrainingEvaluation {

    @Async
    public Future<Genotype> evaluate(Genotype genotype, TrainingEvaluation evaluation) {
        evaluation.evaluate(genotype);
        return new AsyncResult<>(genotype);
    }

}
