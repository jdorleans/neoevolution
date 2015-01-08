package org.neoevolution.mvc.dataset;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since Jan 02 2015
 */
public class ListDataSet extends EntityDataSet<List<Double>> {

    public ListDataSet() {
        super(SIZE);
    }

    public ListDataSet(int size) {
        super(new ArrayList<List<Double>>(size));
    }

    public ListDataSet(List<List<Double>> data) {
        super(data);
    }

}
