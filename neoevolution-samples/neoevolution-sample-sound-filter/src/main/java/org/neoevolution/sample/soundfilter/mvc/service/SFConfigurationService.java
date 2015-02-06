package org.neoevolution.sample.soundfilter.mvc.service;

import org.neoevolution.mvc.service.NEConfigurationService;
import org.neoevolution.sample.soundfilter.mvc.model.SFConfiguration;
import org.neoevolution.sample.soundfilter.mvc.repository.SFConfigurationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since 1.0
 */
@Service
public class SFConfigurationService extends NEConfigurationService<SFConfiguration, SFConfigurationRepository> {

    @Autowired
    public SFConfigurationService(SFConfigurationRepository repository) {
        super(repository);
    }

}
