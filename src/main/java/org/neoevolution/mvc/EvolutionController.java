package org.neoevolution.mvc;

import org.apache.commons.lang3.SerializationUtils;
import org.neoevolution.core.configuration.NNConfiguration;
import org.neoevolution.core.innovation.NeuronInnovation;
import org.neoevolution.core.innovation.SynapseInnovation;
import org.neoevolution.mvc.service.EvolutionService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since Nov 27 2014
 */
public abstract class EvolutionController<S extends EvolutionService<?, C, ?>, C extends NNConfiguration> {

    private S service;

    public EvolutionController(S service) {
        this.service = service;
    }

    @RequestMapping(value = "/evolve", method = RequestMethod.POST)
    public Evolution<C> evolve(@RequestBody C configuration) {
        return service.evolve(configuration);
    }

    @RequestMapping(value = "/evolve/{runs}", method = RequestMethod.POST)
    public void evolve(@RequestBody C configuration, @PathVariable int runs)
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
            service.evolve(config);
            long end = System.currentTimeMillis() - start;
            total += end;
            System.out.println("Finished in: " + end);
        }
        System.out.println("TOTAL TIME: " + total);
        System.out.println("AVERAGE TIME: " + total/runs);

    }

}
