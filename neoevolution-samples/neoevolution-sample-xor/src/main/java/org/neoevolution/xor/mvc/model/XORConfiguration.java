package org.neoevolution.xor.mvc.model;

import org.neoevolution.core.error.ErrorFunctionType;
import org.neoevolution.mvc.model.configuration.AbstractNEErrorStopConfiguration;
import org.springframework.data.neo4j.annotation.NodeEntity;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since Dez 25 2014
 */
@NodeEntity
public class XORConfiguration extends AbstractNEErrorStopConfiguration<ErrorFunctionType> {

    private static final long serialVersionUID = -5120062947316203681L;

}
