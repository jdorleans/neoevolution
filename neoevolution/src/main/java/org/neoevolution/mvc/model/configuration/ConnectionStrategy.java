package org.neoevolution.mvc.model.configuration;

/**
 * @author Jonathan D'Orleans <jdorleans@sagaranatech.com>
 * @since Feb 14 2015
 */
public enum ConnectionStrategy {

    RANDOM_ONE, RANDOM_ALL, INDEX_RELATED, FULLY;

    public static boolean isRandomOne(ConnectionStrategy connectionStrategy) {
        return RANDOM_ONE.equals(connectionStrategy);
    }

    public static boolean isRandomAll(ConnectionStrategy connectionStrategy) {
        return RANDOM_ALL.equals(connectionStrategy);
    }

    public static boolean isIndexRelated(ConnectionStrategy connectionStrategy) {
        return INDEX_RELATED.equals(connectionStrategy);
    }

    public static boolean isFully(ConnectionStrategy connectionStrategy) {
        return FULLY.equals(connectionStrategy);
    }

}
