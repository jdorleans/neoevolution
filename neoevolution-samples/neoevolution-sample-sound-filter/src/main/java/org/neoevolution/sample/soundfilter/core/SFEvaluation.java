package org.neoevolution.sample.soundfilter.core;

import org.neoevolution.core.operator.evaluation.TrainingEvaluation;
import org.neoevolution.mvc.dataset.SampleData;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since 1.0
 */
public class SFEvaluation extends TrainingEvaluation {

    public SFEvaluation() {
        super();
        initSamples();
    }

    private void initSamples() {
        addSample(0d, 0d, 0d);
        addSample(0d, 1d, 1d);
        addSample(1d, 0d, 1d);
        addSample(1d, 1d, 0d);
    }

    private void addSample(double in1, double in2, double out1) {
        SampleData sample = new SampleData(2, 1);
        sample.addInput(in1);
        sample.addInput(in2);
        sample.addOutput(out1);
        data.add(sample);
    }

}
