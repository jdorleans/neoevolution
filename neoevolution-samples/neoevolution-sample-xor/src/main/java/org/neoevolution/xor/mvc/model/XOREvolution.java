package org.neoevolution.xor.mvc.model;

import org.neoevolution.mvc.model.Evolution;
import org.springframework.data.neo4j.annotation.NodeEntity;

/**
 * @author Jonathan D'Orleans <jdorleans@sagaranatech.com>
 * @since Dec 23 2014
 */
@NodeEntity
public class XOREvolution extends Evolution<XORConfiguration> {

    private static final long serialVersionUID = 3405043242077820767L;

}
