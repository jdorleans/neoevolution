package org.neoevolution.sample.xor.mvc.controller;

import org.neoevolution.mvc.controller.EvolutionController;
import org.neoevolution.sample.xor.mvc.model.XORConfiguration;
import org.neoevolution.sample.xor.mvc.model.XOREvolution;
import org.neoevolution.sample.xor.mvc.service.XOREvolutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since 1.0
 */
@RestController
@RequestMapping("/xor")
public class XOREvolutionController extends EvolutionController
        <XOREvolution, XOREvolutionService, XORConfiguration> {

    @Autowired
    public XOREvolutionController(XOREvolutionService service) {
        super(service);
    }

}
