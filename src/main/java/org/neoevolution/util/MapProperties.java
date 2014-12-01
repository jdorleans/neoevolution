package org.neoevolution.util;

import org.springframework.data.neo4j.fieldaccess.DynamicPropertiesContainer;

public class MapProperties extends DynamicPropertiesContainer {

    public Boolean getBoolean(String key) {
        return (Boolean) getProperty(key);
    }

    public String getString(String key) {
        return (String) getProperty(key);
    }

    public Integer getInteger(String key) {
        return (Integer) getProperty(key);
    }

    public Long getLong(String key) {
        return (Long) getProperty(key);
    }

    public Float getFloat(String key) {
        return (Float) getProperty(key);
    }

    public Double getDouble(String key) {
        return (Double) getProperty(key);
    }

}
