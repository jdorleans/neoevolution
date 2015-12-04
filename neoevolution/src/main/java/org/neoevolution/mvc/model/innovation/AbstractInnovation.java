package org.neoevolution.mvc.model.innovation;

import org.neo4j.ogm.annotation.typeconversion.Convert;
import org.neoevolution.mvc.converter.InnovationMapToStringArray;
import org.neoevolution.mvc.model.AbstractEntity;
import org.neoevolution.mvc.model.Gene;
import org.neoevolution.mvc.model.Neuron;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since 1.0
 */
public abstract class AbstractInnovation extends AbstractEntity {

    private static final long serialVersionUID = -6763412711915520799L;

    protected String code;

    protected AtomicLong current;

    @Convert(InnovationMapToStringArray.class)
    protected Map<String, Long> innovations;


    protected AbstractInnovation(String code) {
        this.code = code;
        this.current = new AtomicLong(0);
        this.innovations = new HashMap<>();
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

    protected Long next() {
        return current.incrementAndGet();
    }


    protected Long get(String key) {
        return innovations.get(key);
    }

    protected void put(String key, Long value) {
        innovations.put(key, value);
    }


    @Override
    public boolean equals(Object obj) {
        return (obj instanceof AbstractInnovation && super.equals(obj));
    }


    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }

    public AtomicLong getCurrent() {
        return current;
    }
    public void setCurrent(AtomicLong current) {
        this.current = current;
    }

    public Map<String, Long> getInnovations() {
        return innovations;
    }

    public void setInnovations(Map<String, Long> innovations) {
        this.innovations = innovations;
    }

}
