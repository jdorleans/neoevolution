package org.neoevolution.mvc.service;

import org.neoevolution.core.configuration.XORConfiguration;
import org.neoevolution.mvc.repository.XORConfigurationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since Dec 14 2014
 */
@Service
public class XORConfigurationService extends AbstractService<XORConfiguration, XORConfigurationRepository> {

    private Map<Long, XORConfiguration> configurations;


    @Autowired
    public XORConfigurationService(XORConfigurationRepository repository) {
        super(repository);
    }


}
