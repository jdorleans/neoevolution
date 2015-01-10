package org.neoevolution.sample.autopilot.mvc.service;

import org.neoevolution.mvc.service.NEConfigurationService;
import org.neoevolution.sample.autopilot.mvc.model.AutoPilotConfiguration;
import org.neoevolution.sample.autopilot.mvc.repository.AutoPilotConfigurationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since 1.0
 */
@Service
public class AutoPilotConfigurationService extends NEConfigurationService<AutoPilotConfiguration, AutoPilotConfigurationRepository> {

    @Autowired
    public AutoPilotConfigurationService(AutoPilotConfigurationRepository repository) {
        super(repository);
    }

}
