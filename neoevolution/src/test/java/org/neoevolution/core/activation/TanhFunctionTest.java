package org.neoevolution.core.activation;

import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class TanhFunctionTest extends AbstractActivationFunctionTest {

    @Override
    protected ActivationFunction initFunction() {
        return new TanhFunction();
    }

    @Override
    protected ActivationFunctionType getType() {
        return ActivationFunctionType.TANH;
    }

    @Override
    protected double getMinimum() {
        return -1;
    }

    @Override
    protected double getMaximum() {
        return 1;
    }

    @Override
    protected double getHalf() {
        return 0;
    }

    @Override
    protected List<Double> getMinimumValues() {
        List<Double> values = new ArrayList<>();
        values.add(-100d);
        return values;
    }

    @Override
    protected List<Double> getMaximumValues() {
        List<Double> values = new ArrayList<>();
        values.add(100d);
        return values;
    }

    @Override
    protected List<Double> getHalfValues() {
        List<Double> values = new ArrayList<>(1);
        values.add(0d);
        return values;
    }

}