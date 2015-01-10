package org.neoevolution.sample.autopilot.mvc.model;

import org.neoevolution.mvc.model.configuration.AbstractNEStopConfiguration;
import org.springframework.data.neo4j.annotation.NodeEntity;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since 1.0
 */
@NodeEntity
public class AutoPilotConfiguration extends AbstractNEStopConfiguration {

    private static final long serialVersionUID = -708962913514236355L;

}
