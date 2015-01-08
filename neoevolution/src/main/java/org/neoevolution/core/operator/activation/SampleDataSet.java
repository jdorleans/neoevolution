package org.neoevolution.core.operator.activation;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since Jan 02 2015
 */
public class SampleDataSet extends AbstractDataSet<SampleData> {

    public SampleDataSet() {
        this(SIZE);
    }

    public SampleDataSet(int size) {
        this(new ArrayList<SampleData>(size));
    }

    public SampleDataSet(List<SampleData> data) {
        super(data);
    }

}
