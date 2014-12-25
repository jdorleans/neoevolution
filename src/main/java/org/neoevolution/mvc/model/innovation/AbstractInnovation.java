package org.neoevolution.mvc.model.innovation;

import org.neoevolution.mvc.model.AbstractEntity;
import org.neoevolution.mvc.model.Gene;
import org.neoevolution.mvc.model.Neuron;
import org.springframework.data.neo4j.fieldaccess.DynamicProperties;
import org.springframework.data.neo4j.fieldaccess.DynamicPropertiesContainer;

public abstract class AbstractInnovation extends AbstractEntity {

    private static final long serialVersionUID = -6763412711915520799L;

    protected String code;

    protected Long current;

    protected Long configId;

    protected DynamicProperties innovations;


    protected AbstractInnovation(String code) {
        this.code = code;
        this.current = 0l;
        this.innovations = new DynamicPropertiesContainer();
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
        return (Long) innovations.getProperty(key);
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

}
