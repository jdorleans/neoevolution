package org.neoevolution.util;

import org.neoevolution.mvc.model.AbstractInnovationEntity;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since Dez 28 2014
 */
public final class InnovationUtils {

    private InnovationUtils() { }


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

    public static <T extends AbstractInnovationEntity> Map<Long, T> createHashMap(Set<T> entities) {
        Map<Long, T> map = MapUtils.createHashMap(entities.size());
        putMap(entities, map);
        return map;
    }

    public static <T extends AbstractInnovationEntity> void putMap(Set<T> entities, Map<Long, T> map) {
        for (T entity : entities) {
            map.put(entity.getInnovation(), entity);
        }
    }

}
