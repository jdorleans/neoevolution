package org.neoevolution.core.operator.activation;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since Jan 02 2015
 */
public class SampleData implements Iterable<List<Double>> {

    private List<List<Double>> data;


    public SampleData() {
        this(new ArrayList<List<Double>>());
    }

    public SampleData(List<List<Double>> data) {
        this.data = data;
    }


    public boolean hasData() {
        return data != null;
    }

    public void add(List<Double> values)
    {
        if (!hasData()) {
            data = new ArrayList<>();
        }
        data.add(values);
    }

    public int size() {
        return (hasData() ? data.size() : 0);
    }

    @JsonIgnore
    public boolean isEmpty() {
        return (size() == 0);
    }

    @Override
    public Iterator<List<Double>> iterator() {
        return (hasData() ? data.iterator() : new SampleData().iterator());
    }


    public List<List<Double>> getData() {
        return data;
    }
    public void setData(List<List<Double>> data) {
        this.data = data;
    }

}
