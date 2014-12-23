package org.neoevolution.mvc;

import org.neoevolution.core.configuration.NNConfiguration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since Nov 27 2014
 */
public abstract class EvolutionController<S extends NNEvolutionService<?, C, ?>, C extends NNConfiguration> {

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
        long startTotal = System.currentTimeMillis();

        for (int i = 0; i < runs; i++) {
            long start = System.currentTimeMillis();
            System.out.println("Running: "+ (i+1));
            service.evolve(configuration);
            configuration.setId(null);
            System.out.println("Finished in: " + (System.currentTimeMillis() - start));
        }
        long total = System.currentTimeMillis() - startTotal;
        System.out.println("TOTAL TIME: " + total);
        System.out.println("AVERAGE TIME: " + total/runs);

    }

}
