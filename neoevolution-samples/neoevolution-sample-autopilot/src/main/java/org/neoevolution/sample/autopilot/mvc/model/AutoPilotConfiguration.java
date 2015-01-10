package org.neoevolution.sample.autopilot.mvc.model;

import org.neoevolution.core.error.ErrorFunctionType;
import org.neoevolution.mvc.model.configuration.AbstractNEErrorStopConfiguration;
import org.springframework.data.neo4j.annotation.NodeEntity;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since 1.0
 */
@NodeEntity
public class AutoPilotConfiguration extends AbstractNEErrorStopConfiguration<ErrorFunctionType> {

    private static final long serialVersionUID = 546029848233749645L;

}
