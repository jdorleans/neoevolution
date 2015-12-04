package org.neoevolution.sample.xor.mvc.model;

import org.neo4j.ogm.annotation.NodeEntity;
import org.neoevolution.core.error.NEErrorFunctionType;
import org.neoevolution.mvc.model.configuration.AbstractNEErrorStopConfiguration;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since 1.0
 */
@NodeEntity
public class XORConfiguration extends AbstractNEErrorStopConfiguration<NEErrorFunctionType> {

    private static final long serialVersionUID = -5120062947316203681L;

}
