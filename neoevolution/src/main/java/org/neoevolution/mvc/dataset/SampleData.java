package org.neoevolution.mvc.dataset;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since 1.0
 */
public class SampleData {

    public static final int SIZE = 10;

    private List<Double> inputs;

    private List<Double> outputs;


    public SampleData() {
        this(SIZE, SIZE);
    }

    public SampleData(int inputs, int outputs) {
        this(new ArrayList<Double>(inputs), new ArrayList<Double>(outputs));
    }

    public SampleData(List<Double> inputs, List<Double> outputs) {
        this.inputs = inputs;
        this.outputs = outputs;
    }


    public Double getInput(int index) {
        return inputs.get(index);
    }
    public Double getOutput(int index) {
        return outputs.get(index);
    }

    public void addInput(Double value) {
        inputs.add(value);
    }
    public void addOutput(Double value) {
        outputs.add(value);
    }

    public int inputSize() {
        return inputs.size();
    }
    public int outputSize() {
        return outputs.size();
    }

    @JsonIgnore
    public boolean isInputEmpty() {
        return inputs.isEmpty();
    }
    @JsonIgnore
    public boolean isOutputEmpty() {
        return outputs.isEmpty();
    }


    public List<Double> getInputs() {
        return inputs;
    }
    public void setInputs(List<Double> inputs) {
        this.inputs = inputs;
    }

    public List<Double> getOutputs() {
        return outputs;
    }
    public void setOutputs(List<Double> outputs) {
        this.outputs = outputs;
    }

}
