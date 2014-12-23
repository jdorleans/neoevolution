package org.neoevolution.mvc;

import org.neoevolution.core.configuration.XORConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since Nov 27 2014
 */
@RestController
@RequestMapping("/xor")
public class XOREvolutionController extends EvolutionController<XOREvolutionService, XORConfiguration> {

    @Autowired
    public XOREvolutionController(XOREvolutionService service) {
        super(service);
    }

}
