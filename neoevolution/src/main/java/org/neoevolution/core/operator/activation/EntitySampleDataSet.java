package org.neoevolution.core.operator.activation;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since Jan 02 2015
 */
@JsonPropertyOrder({"id", "data"})
public class EntitySampleDataSet extends SampleDataSet {

    private Long id;


    public EntitySampleDataSet() {
        super();
    }

    public EntitySampleDataSet(Long id) {
        super();
        this.id = id;
    }

    public EntitySampleDataSet(int size) {
        super(size);
    }

    public EntitySampleDataSet(List<SampleData> samples) {
        this(null, samples);
    }

    public EntitySampleDataSet(Long id, List<SampleData> samples) {
        super(samples);
        this.id = id;
    }


    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

}
