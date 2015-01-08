package org.neoevolution.core.operator.activation;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since Jan 02 2015
 */
@JsonPropertyOrder({"id", "data"})
public class EntityFitnessDataSet extends AbstractDataSet<Double> {

    private Long id;


    public EntityFitnessDataSet() {
        super();
    }

    public EntityFitnessDataSet(Long id) {
        super();
        this.id = id;
    }

    public EntityFitnessDataSet(int size) {
        super(size);
    }

    public EntityFitnessDataSet(Long id, int size) {
        super(size);
        this.id = id;
    }

    public EntityFitnessDataSet(List<Double> data) {
        this(null, data);
    }

    public EntityFitnessDataSet(Long id, List<Double> data) {
        super(data);
        this.id = id;
    }


    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

}
