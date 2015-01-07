package org.neoevolution.xor.core;

import org.neoevolution.core.operator.evaluation.TrainingEvaluation;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since 1.0
 */
public class XOREvaluation extends TrainingEvaluation {

    public XOREvaluation() {
        initInputs();
        initOutputs();
    }

    private void initOutputs() {
        List<Double> output1 = new ArrayList<>(1);
        output1.add(0d);
        List<Double> output2 = new ArrayList<>(1);
        output2.add(1d);
        List<Double> output3 = new ArrayList<>(1);
        output3.add(1d);
        List<Double> output4 = new ArrayList<>(1);
        output4.add(0d);
        outputSet = new ArrayList<>(4);
        outputSet.add(output1);
        outputSet.add(output2);
        outputSet.add(output3);
        outputSet.add(output4);
    }

    private void initInputs() {
        List<Double> input1 = new ArrayList<>(2);
        input1.add(0d);
        input1.add(0d);
        List<Double> input2 = new ArrayList<>(2);
        input2.add(0d);
        input2.add(1d);
        List<Double> input3 = new ArrayList<>(2);
        input3.add(1d);
        input3.add(0d);
        List<Double> input4 = new ArrayList<>(2);
        input4.add(1d);
        input4.add(1d);
        inputSet = new ArrayList<>(4);
        inputSet.add(input1);
        inputSet.add(input2);
        inputSet.add(input3);
        inputSet.add(input4);
    }

}
