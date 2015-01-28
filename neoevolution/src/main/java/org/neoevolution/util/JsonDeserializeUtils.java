package org.neoevolution.util;

import com.fasterxml.jackson.databind.JsonNode;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since 1.0
 */
public final class JsonDeserializeUtils {

    private JsonDeserializeUtils() { }


    public static boolean isValid(JsonNode value) {
        return value != null && !value.isNull();
    }

    public static Boolean asBoolean(JsonNode node, String fieldName)
    {
        JsonNode value = node.get(fieldName);

        if (isValid(value)) {
            return value.asBoolean();
        }
        return null;
    }

    public static Integer asInteger(JsonNode node, String fieldName)
    {
        JsonNode value = node.get(fieldName);

        if (isValid(value)) {
            return value.asInt();
        }
        return null;
    }

    public static Long asLong(JsonNode node, String fieldName)
    {
        JsonNode value = node.get(fieldName);

        if (isValid(value)) {
            return value.asLong();
        }
        return null;
    }

    public static Double asDouble(JsonNode node, String fieldName)
    {
        JsonNode value = node.get(fieldName);

        if (isValid(value)) {
            return value.asDouble();
        }
        return null;
    }

    public static String asString(JsonNode node, String fieldName)
    {
        JsonNode value = node.get(fieldName);

        if (isValid(value)) {
            return value.asText();
        }
        return null;
    }

}
