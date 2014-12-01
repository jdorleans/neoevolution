package org.neoevolution.util;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since Nov 30 2014
 */
public class ClassUtils {

    public static <T> T create(String name)
    {
        T obj = null;

        try {
            obj = (T) Class.forName(name).newInstance();
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return obj;
    }

}
