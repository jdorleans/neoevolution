package org.neoevolution.sample.soundfilter.mvc.controller;

import org.neoevolution.mvc.controller.EvolutionController;
import org.neoevolution.sample.soundfilter.mvc.model.SFConfiguration;
import org.neoevolution.sample.soundfilter.mvc.model.SFEvolution;
import org.neoevolution.sample.soundfilter.mvc.service.SFEvolutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since 1.0
 */
@RestController
@RequestMapping("/soundfilter")
public class SFEvolutionController extends EvolutionController
        <SFEvolution, SFEvolutionService, SFConfiguration> {

    @Autowired
    public SFEvolutionController(SFEvolutionService service) {
        super(service);
    }

}
