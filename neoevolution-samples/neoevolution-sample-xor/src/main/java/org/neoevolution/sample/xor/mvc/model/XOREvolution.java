package org.neoevolution.sample.xor.mvc.model;

import org.neo4j.ogm.annotation.NodeEntity;
import org.neoevolution.mvc.model.Evolution;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since 1.0
 */
@NodeEntity
public class XOREvolution extends Evolution<XORConfiguration> {

    private static final long serialVersionUID = 3405043242077820767L;

}
