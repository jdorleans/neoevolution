package org.neoevolution.util;

import org.neoevolution.mvc.model.AbstractInnovationEntity;

import java.util.Iterator;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since Dez 28 2014
 */
public class InnovationUtils {

    public static <T extends AbstractInnovationEntity> T find(Long innovation, Iterable<T> entities)
    {
        if (innovation != null)
        {
            for (T entity : entities) {
                if (innovation.equals(entity.getInnovation())) {
                    return entity;
                }
            }
        }
        return null;
    }

    public static <T extends AbstractInnovationEntity> void remove(Long innovation, Iterable<T> entities)
    {
        if (innovation != null)
        {
            Iterator<T> iterator = entities.iterator();

            while (iterator.hasNext()) {
                if (innovation.equals(iterator.next().getInnovation())) {
                    iterator.remove();
                    break;
                }
            }
        }
    }

}
