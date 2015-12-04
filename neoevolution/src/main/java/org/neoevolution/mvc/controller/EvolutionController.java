package org.neoevolution.mvc.controller;

import org.apache.commons.lang3.SerializationUtils;
import org.neoevolution.mvc.model.Evolution;
import org.neoevolution.mvc.model.configuration.NNConfiguration;
import org.neoevolution.mvc.model.innovation.NeuronInnovation;
import org.neoevolution.mvc.model.innovation.SynapseInnovation;
import org.neoevolution.mvc.service.EvolutionService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since 1.0
 */
public abstract class EvolutionController
        <T extends Evolution<C>, S extends EvolutionService<T, C, ?>, C extends NNConfiguration>
        extends AbstractController<T, S> {


    protected EvolutionController(S service) {
        super(service);
    }


    @RequestMapping(value = "/evolve", method = RequestMethod.POST)
    public T evolve(@RequestBody C configuration,
                    @RequestParam(required = false) Boolean create,
                    @RequestParam(required = false) boolean project)
            throws ExecutionException, InterruptedException {
        return projection(service.evolve(configuration, create), project);
    }

    @RequestMapping(value = "/evolve/batch", method = RequestMethod.POST)
    public List<T> evolve(@RequestBody List<C> configurations,
                          @RequestParam(required = false) Boolean create,
                          @RequestParam(required = false) boolean project)
            throws ExecutionException, InterruptedException
    {
        return projection(service.evolve(configurations, create), project);
    }

    @RequestMapping(value = "/evolve/{runs}", method = RequestMethod.POST)
    public List<T> evolve(@RequestBody C configuration, @PathVariable int runs,
                          @RequestParam(required = false) Boolean create,
                          @RequestParam(required = false) boolean project)
            throws ExecutionException, InterruptedException
    {
        long total = 0;
        List<T> evolutions = new ArrayList<>(runs);
        configuration.setNeuronInnovation(null);
        configuration.setSynapseInnovation(null);

        for (int i = 0; i < runs; i++)
        {
            C config = SerializationUtils.clone(configuration);
            config.setNeuronInnovation(new NeuronInnovation());
            config.setSynapseInnovation(new SynapseInnovation());
            long start = System.currentTimeMillis();
            System.out.println("Running: " + (i + 1));
            evolutions.add(service.evolve(config, create));
            long end = System.currentTimeMillis() - start;
            total += end;
            System.out.println("Finished in: " + end);
        }
        System.out.println("TOTAL TIME: " + total);
        System.out.println("AVERAGE TIME: " + total/runs);
        return projection(evolutions, project);
    }

}
