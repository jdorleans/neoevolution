package org.neoevolution.sample.autopilot.mvc.model;

import org.neoevolution.mvc.model.Evolution;
import org.springframework.data.neo4j.annotation.NodeEntity;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since 1.0
 */
@NodeEntity
public class AutoPilotEvolution extends Evolution<AutoPilotConfiguration> {

    private static final long serialVersionUID = -3578573617352491814L;

}
