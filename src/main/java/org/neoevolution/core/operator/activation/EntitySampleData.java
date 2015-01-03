package org.neoevolution.core.operator.activation;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since Jan 02 2015
 */
@JsonPropertyOrder({"id", "data"})
public class EntitySampleData extends SampleData {

    private Long id;


    public EntitySampleData() { }

    public EntitySampleData(Long id) {
        this(id, null);
    }

    public EntitySampleData(List<List<Double>> data) {
        this(null, data);
    }

    public EntitySampleData(Long id, List<List<Double>> data) {
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
