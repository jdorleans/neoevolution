package org.neoevolution.mvc.dataset;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since Jan 02 2015
 */
public class DataSet<T> implements Iterable<T> {

    public static final int SIZE = 10;

    protected List<T> data;


    public DataSet() {
        this(SIZE);
    }

    public DataSet(int size) {
        this(new ArrayList<T>(size));
    }

    public DataSet(List<T> data) {
        this.data = data;
    }


    public boolean hasData() {
        return data != null;
    }

    public T get(int index) {
        return data.get(index);
    }

    public void add(T values) {
        data.add(values);
    }

    public int size() {
        return data.size();
    }

    @JsonIgnore
    public boolean isEmpty() {
        return data.isEmpty();
    }

    @Override
    public Iterator<T> iterator() {
        return data.iterator();
    }


    public List<T> getData() {
        return data;
    }
    public void setData(List<T> data) {
        this.data = data;
    }

}
