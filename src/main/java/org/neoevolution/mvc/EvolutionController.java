package org.neoevolution.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since Nov 27 2014
 */
@RestController
@RequestMapping("/evolution")
public class EvolutionController {

    @Autowired
    private EvolutionService service;

    @RequestMapping
    public void evolve() {
        service.evolve();
    }

    @RequestMapping(value = "/test/{runs}")
    public void test(@PathVariable int runs)
    {
        long startTotal = System.currentTimeMillis();

        for (int i = 0; i < runs; i++) {
            long start = System.currentTimeMillis();
            System.out.println("Running: "+ (i+1));
            service.evolve();
            System.out.println("Finished in: " + (System.currentTimeMillis() - start));
        }
        long total = System.currentTimeMillis() - startTotal;
        System.out.println("TOTAL TIME: " + total);
        System.out.println("AVERAGE TIME: " + total/runs);

    }

}
