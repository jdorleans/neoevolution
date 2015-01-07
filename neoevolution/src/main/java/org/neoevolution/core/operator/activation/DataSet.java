package org.neoevolution.core.operator.activation;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since Jan 02 2015
 */
public class DataSet extends AbstractDataSet<List<Double>> {

    public DataSet() {
        super(SIZE);
    }

    public DataSet(int size) {
        super(new ArrayList<List<Double>>(size));
    }

    public DataSet(List<List<Double>> data) {
        super(data);
    }

}
