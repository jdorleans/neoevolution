package org.neoevolution.core.factory;

import org.neoevolution.core.GAConfiguration;
import org.neoevolution.core.operator.speciation.Speciation;
import org.neoevolution.util.ClassUtils;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since Nov 30 2014
 */
public class SpeciationFactory {

    private GAConfiguration configuration;


    public SpeciationFactory(GAConfiguration configuration) {
        this.configuration = configuration;
    }


    public Speciation create() {
        Speciation speciation = ClassUtils.create(configuration.getSpeciationFunction());
        speciation.configure(configuration);
        return speciation;
    }

}
