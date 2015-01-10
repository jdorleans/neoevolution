package org.neoevolution.sample.autopilot.mvc.controller;

import org.neoevolution.mvc.controller.EvolutionController;
import org.neoevolution.sample.autopilot.mvc.model.AutoPilotConfiguration;
import org.neoevolution.sample.autopilot.mvc.model.AutoPilotEvolution;
import org.neoevolution.sample.autopilot.mvc.service.AutoPilotEvolutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since 1.0
 */
@RestController
@RequestMapping("/autopilot")
public class AutoPilotEvolutionController extends EvolutionController
        <AutoPilotEvolution, AutoPilotEvolutionService, AutoPilotConfiguration> {

    @Autowired
    public AutoPilotEvolutionController(AutoPilotEvolutionService service) {
        super(service);
    }

}
