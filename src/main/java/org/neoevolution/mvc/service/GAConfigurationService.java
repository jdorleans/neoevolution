package org.neoevolution.mvc.service;

import org.neoevolution.core.GAConfiguration;
import org.neoevolution.mvc.repository.GAConfigurationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since Dec 14 2014
 */
@Service
public class GAConfigurationService extends AbstractService<GAConfiguration, GAConfigurationRepository> {

    private Map<Long, GAConfiguration> configurations;


    @Autowired
    public GAConfigurationService(GAConfigurationRepository repository) {
        super(repository);
    }


}
