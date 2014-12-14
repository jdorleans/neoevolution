package org.neoevolution.mvc;

import org.neoevolution.core.GAConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since Nov 27 2014
 */
@RestController
@RequestMapping("/evolution")
public class EvolutionController {

    @Autowired
    private EvolutionService service;

    @RequestMapping(method = RequestMethod.POST)
    public void evolve(@RequestBody GAConfiguration configuration) {
        service.evolve(configuration);
    }

    @RequestMapping(value = "/test/{runs}", method = RequestMethod.POST)
    public void test(@PathVariable int runs, @RequestBody GAConfiguration configuration)
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
