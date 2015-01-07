package org.neoevolution.xor.mvc.model;

import org.neoevolution.mvc.model.Evolution;
import org.springframework.data.neo4j.annotation.NodeEntity;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since 1.0
 */
@NodeEntity
public class XOREvolution extends Evolution<XORConfiguration> {

    private static final long serialVersionUID = 3405043242077820767L;

}
