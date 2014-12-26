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

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since Nov 27 2014
 */
public abstract class EvolutionController
        <T extends Evolution<C>, S extends EvolutionService<T, C, ?>, C extends NNConfiguration>
        extends AbstractController<T, S> {


    protected EvolutionController(S service) {
        super(service);
    }


    @RequestMapping(value = "/evolve", method = RequestMethod.POST)
    public T evolve(@RequestBody C configuration, @RequestParam(required = false) Boolean save) {
        return service.evolve(configuration, save);
    }

    @RequestMapping(value = "/evolve/list", method = RequestMethod.POST)
    public List<T> evolve(@RequestBody List<C> configurations, @RequestParam(required = false) Boolean save)
    {
        List<T> evolutions = new ArrayList<>(configurations.size());

        for (C configuration : configurations) {
            evolutions.add(service.evolve(configuration, save));
        }
        return evolutions;
    }

    @RequestMapping(value = "/evolve/{runs}", method = RequestMethod.POST)
    public void evolve(@RequestBody C configuration, @PathVariable int runs, @RequestParam(required = false) Boolean save)
    {
        long total = 0;
        configuration.setNeuronInnovation(null);
        configuration.setSynapseInnovation(null);

        for (int i = 0; i < runs; i++)
        {
            C config = SerializationUtils.clone(configuration);
            config.setNeuronInnovation(new NeuronInnovation());
            config.setSynapseInnovation(new SynapseInnovation());
            long start = System.currentTimeMillis();
            System.out.println("Running: " + (i + 1));
            service.evolve(config, save);
            long end = System.currentTimeMillis() - start;
            total += end;
            System.out.println("Finished in: " + end);
        }
        System.out.println("TOTAL TIME: " + total);
        System.out.println("AVERAGE TIME: " + total/runs);

    }

}
