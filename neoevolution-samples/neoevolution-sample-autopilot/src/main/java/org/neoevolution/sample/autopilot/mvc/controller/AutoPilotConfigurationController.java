package org.neoevolution.sample.autopilot.mvc.controller;

import org.neoevolution.mvc.controller.NEConfigurationController;
import org.neoevolution.sample.autopilot.mvc.model.AutoPilotConfiguration;
import org.neoevolution.sample.autopilot.mvc.service.AutoPilotConfigurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since 1.0
 */
@RestController
@RequestMapping("/autopilot/config")
public class AutoPilotConfigurationController extends NEConfigurationController<AutoPilotConfiguration, AutoPilotConfigurationService> {

    @Autowired
    protected AutoPilotConfigurationController(AutoPilotConfigurationService service) {
        super(service);
    }

}
