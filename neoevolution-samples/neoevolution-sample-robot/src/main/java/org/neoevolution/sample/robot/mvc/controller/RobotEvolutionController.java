package org.neoevolution.sample.robot.mvc.controller;

import org.neoevolution.mvc.controller.EvolutionController;
import org.neoevolution.sample.robot.mvc.model.RobotConfiguration;
import org.neoevolution.sample.robot.mvc.model.RobotEvolution;
import org.neoevolution.sample.robot.mvc.service.RobotEvolutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since 1.0
 */
@RestController
@RequestMapping("/robot")
public class RobotEvolutionController extends EvolutionController
        <RobotEvolution, RobotEvolutionService, RobotConfiguration> {

    @Autowired
    public RobotEvolutionController(RobotEvolutionService service) {
        super(service);
    }

}
