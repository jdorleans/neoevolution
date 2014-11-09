package org.neoevolution.core.innovation;

import org.neoevolution.core.Gene;
import org.neoevolution.core.Neuron;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class InnovationManager<T extends Gene> {

    public static final int CAPACITY = 5000;

    protected String code;

    protected Long current;

    protected Map<String, Long> innovations;


    protected InnovationManager(String code) {
        this(code, CAPACITY);
    }

    protected InnovationManager(String code, int capacity) {
        this.code = code;
        this.current = 0l;
        this.innovations = new ConcurrentHashMap<>(capacity);
    }


    public Long innovate(Gene gene, Neuron from, Neuron to)
    {
        Long innovation = gene.getInnovation();

        if (innovation == null)
        {
            String key = key(from, to);
            innovation = innovations.get(key);

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
        Long innovation = innovations.get(key);

        if (innovation == null) {
            innovation = next();
            innovations.put(key, innovation);
        }
        return innovation;
    }

    protected synchronized Long next() {
        return ++current;
    }


    public Long getCurrent() {
        return current;
    }
    public void setCurrent(Long current) {
        this.current = current;
    }

    public Map<String, Long> getInnovations() {
        return innovations;
    }
    public void setInnovations(Map<String, Long> innovations) {
        this.innovations = innovations;
    }

}
