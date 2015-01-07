package org.neoevolution.sample.robot.mvc.controller;

import org.neoevolution.mvc.controller.NEConfigurationController;
import org.neoevolution.sample.robot.mvc.model.RobotConfiguration;
import org.neoevolution.sample.robot.mvc.service.RobotConfigurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since 1.0
 */
@RestController
@RequestMapping("/robot/config")
public class RobotConfigurationController extends NEConfigurationController<RobotConfiguration, RobotConfigurationService> {

    @Autowired
    protected RobotConfigurationController(RobotConfigurationService service) {
        super(service);
    }

}
