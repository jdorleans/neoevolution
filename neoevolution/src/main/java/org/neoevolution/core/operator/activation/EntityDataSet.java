package org.neoevolution.core.operator.activation;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since Jan 02 2015
 */
@JsonPropertyOrder({"id", "data"})
public class EntityDataSet extends DataSet {

    private Long id;


    public EntityDataSet() {
        super();
    }

    public EntityDataSet(Long id) {
        super();
        this.id = id;
    }

    public EntityDataSet(int size) {
        super(size);
    }

    public EntityDataSet(List<List<Double>> data) {
        this(null, data);
    }

    public EntityDataSet(Long id, List<List<Double>> data) {
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
