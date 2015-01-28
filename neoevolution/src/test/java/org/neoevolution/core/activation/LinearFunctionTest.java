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
public class LinearFunctionTest extends AbstractActivationFunctionTest {

    @Override
    protected ActivationFunction initFunction() {
        return new LinearFunction();
    }

    @Override
    protected ActivationFunctionType getType() {
        return ActivationFunctionType.LINEAR;
    }

    @Override
    protected double getMinimum() {
        return Double.MIN_VALUE;
    }

    @Override
    protected double getMaximum() {
        return Double.MAX_VALUE;
    }

    @Override
    protected double getHalf() {
        return 0;
    }

    @Override
    protected List<Double> getMinimumValues() {
        List<Double> values = new ArrayList<>();
        values.add(Double.MIN_VALUE);
        return values;
    }

    @Override
    protected List<Double> getMaximumValues() {
        List<Double> values = new ArrayList<>();
        values.add(Double.MAX_VALUE);
        return values;
    }

    @Override
    protected List<Double> getHalfValues() {
        List<Double> values = new ArrayList<>(1);
        values.add(0d);
        return values;
    }

}