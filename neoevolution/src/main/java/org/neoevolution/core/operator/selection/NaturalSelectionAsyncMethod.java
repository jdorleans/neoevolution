package org.neoevolution.core.operator.selection;

import org.neoevolution.mvc.model.Genotype;
import org.neoevolution.mvc.model.Species;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.Future;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since 1.0
 */
@Component
public class NaturalSelectionAsyncMethod {

    @Async
    public Future<Species> select(Species specie, Long generation, Double totalFitness, List<Genotype> offsprings, NaturalSelection selection) {
        return new AsyncResult<>(selection.selection(specie, generation, totalFitness, offsprings));
    }

}
