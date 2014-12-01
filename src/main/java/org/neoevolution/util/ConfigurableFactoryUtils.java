package org.neoevolution.util;

import org.neoevolution.core.GAConfiguration;
import org.neoevolution.factory.ConfigurableFactory;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since Nov 30 2014
 */
public class ConfigurableFactoryUtils {

    public static <T extends ConfigurableFactory> T create(String name, GAConfiguration configuration) {
        T factory = ClassUtils.create(name);
        factory.configure(configuration);
        return factory;
    }

}
