package org.neoevolution.sample.robot.mvc.service;

import org.neoevolution.mvc.service.NEConfigurationService;
import org.neoevolution.sample.robot.mvc.model.RobotConfiguration;
import org.neoevolution.sample.robot.mvc.repository.RobotConfigurationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since 1.0
 */
@Service
public class RobotConfigurationService extends NEConfigurationService<RobotConfiguration, RobotConfigurationRepository> {

    @Autowired
    public RobotConfigurationService(RobotConfigurationRepository repository) {
        super(repository);
    }

}
