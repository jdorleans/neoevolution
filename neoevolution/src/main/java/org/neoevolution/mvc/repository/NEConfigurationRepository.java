package org.neoevolution.mvc.repository;

import org.neoevolution.mvc.model.configuration.NEConfiguration;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since 1.0
 */
@NoRepositoryBean
public interface NEConfigurationRepository<T extends NEConfiguration> extends NNConfigurationRepository<T> {

}
