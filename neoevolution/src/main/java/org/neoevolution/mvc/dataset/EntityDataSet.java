package org.neoevolution.mvc.dataset;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since Jan 02 2015
 */
@JsonPropertyOrder({"id", "data"})
public class EntityDataSet<T> extends DataSet<T> {

    protected Long id;


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

    public EntityDataSet(Long id, int size) {
        super(size);
        this.id = id;
    }

    public EntityDataSet(List<T> data) {
        this(null, data);
    }

    public EntityDataSet(Long id, List<T> data) {
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
