package org.neoevolution.util;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since 1.0
 */
public final class FutureUtils {

    private FutureUtils() { }


    public static <T> List<T> getResults(List<Future<T>> futures)
    {
        List<T> results = new ArrayList<>(futures.size());

        for (Future<T> future : futures)
        {
            try {
                results.add(future.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        return results;
    }

}
