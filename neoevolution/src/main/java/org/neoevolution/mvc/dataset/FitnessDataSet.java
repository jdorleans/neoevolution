package org.neoevolution.mvc.dataset;

import org.neoevolution.core.error.ErrorFunctionType;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since Jan 02 2015
 */
public class FitnessDataSet extends SampleDataSet {

    private Double maxFitness;

    private ErrorFunctionType type;


    public FitnessDataSet() {
        this(SIZE);
    }

    public FitnessDataSet(int size) {
        this(new ArrayList<SampleData>(size));
    }

    public FitnessDataSet(List<SampleData> data) {
        this(data, 1d, ErrorFunctionType.MSE);
    }

    public FitnessDataSet(List<SampleData> data, Double maxFitness, ErrorFunctionType type) {
        super(data);
        this.maxFitness = maxFitness;
        this.type = type;
    }


    public Double getMaxFitness() {
        return maxFitness;
    }
    public void setMaxFitness(Double maxFitness) {
        this.maxFitness = maxFitness;
    }

    public ErrorFunctionType getType() {
        return type;
    }
    public void setType(ErrorFunctionType type) {
        this.type = type;
    }

}
