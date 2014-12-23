package org.neoevolution.mvc.repository;

import org.neoevolution.core.configuration.NEConfiguration;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since Dec 01 2014
 */
@NoRepositoryBean
public interface NEConfigurationRepository<T extends NEConfiguration> extends NNConfigurationRepository<T> {

}
