package org.neoevolution.core.activation;

import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since 1.0
 */
@RunWith(MockitoJUnitRunner.class)
public class SigmoidFunctionTest extends AbstractActivationFunctionTest {

    @Override
    protected ActivationFunction initFunction() {
        return new SigmoidFunction();
    }

    @Override
    protected ActivationFunctionType getType() {
        return ActivationFunctionType.SIGMOID;
    }

    @Override
    protected double getMinimum() {
        return 0;
    }

    @Override
    protected double getMaximum() {
        return 1;
    }

    @Override
    protected double getHalf() {
        return 0.5;
    }

    @Override
    protected List<Double> getMinimumValues() {
        List<Double> values = new ArrayList<>();
        values.add(-1000d);
        return values;
    }

    @Override
    protected List<Double> getMaximumValues() {
        List<Double> values = new ArrayList<>();
        values.add(1000d);
        return values;
    }

    @Override
    protected List<Double> getHalfValues() {
        List<Double> values = new ArrayList<>(1);
        values.add(0d);
        return values;
    }

}