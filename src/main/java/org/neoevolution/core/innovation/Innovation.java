package org.neoevolution.core.innovation;

import org.neoevolution.core.model.Gene;
import org.neoevolution.core.model.Neuron;
import org.neoevolution.mvc.AbstractEntity;
import org.neoevolution.util.MapProperties;

public abstract class Innovation extends AbstractEntity {

    private static final long serialVersionUID = -6763412711915520799L;

    protected String code;

    protected Long current;

    protected Long configId;

    protected MapProperties innovations;


    protected Innovation(String code) {
        this.code = code;
        this.current = 0l;
        this.innovations = new MapProperties();
    }


    public Long innovate(Gene gene, Neuron from, Neuron to)
    {
        Long innovation = gene.getInnovation();

        if (innovation == null)
        {
            String key = key(from, to);
            innovation = get(key);

            if (innovation == null) {
                innovation = next(key);
            }
            gene.setInnovation(innovation);
        }
        return innovation;
    }

    protected String key(Neuron start, Neuron end) {
        return (start.getInnovation() + code + end.getInnovation());
    }

    protected synchronized Long next(String key)
    {
        Long innovation = get(key);

        if (innovation == null) {
            innovation = next();
            put(key, innovation);
        }
        return innovation;
    }

    protected synchronized Long next() {
        return ++current;
    }


    protected Long get(String key) {
        return innovations.getLong(key);
    }

    protected void put(String key, Long value) {
        innovations.setProperty(key, value);
    }


    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }

    public Long getCurrent() {
        return current;
    }
    public void setCurrent(Long current) {
        this.current = current;
    }

    public Long getConfigId() {
        return configId;
    }
    public void setConfigId(Long configId) {
        this.configId = configId;
    }

    public MapProperties getInnovations() {
        return innovations;
    }
    public void setInnovations(MapProperties innovations) {
        this.innovations = innovations;
    }

}
