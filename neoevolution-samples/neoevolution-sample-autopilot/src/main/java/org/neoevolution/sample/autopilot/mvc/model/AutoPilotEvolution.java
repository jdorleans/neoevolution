package org.neoevolution.sample.autopilot.mvc.model;

import org.neo4j.ogm.annotation.NodeEntity;
import org.neoevolution.mvc.model.Evolution;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since 1.0
 */
@NodeEntity
public class AutoPilotEvolution extends Evolution<AutoPilotConfiguration> {

    private static final long serialVersionUID = 2355905888385514119L;

}
