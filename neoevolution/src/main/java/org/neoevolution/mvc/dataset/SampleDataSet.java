package org.neoevolution.mvc.dataset;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since 1.0
 */
public class SampleDataSet extends EntityDataSet<SampleData> {

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
