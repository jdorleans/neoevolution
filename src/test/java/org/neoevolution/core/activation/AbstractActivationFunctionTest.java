package org.neoevolution.core.activation;

import org.fest.assertions.api.Assertions;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public abstract class AbstractActivationFunctionTest {

    protected ActivationFunction function;

    protected abstract ActivationFunction initFunction();

    protected abstract ActivationFunctionType getType();

    protected abstract double getMinimum();

    protected abstract double getMaximum();

    protected abstract double getHalf();

    protected abstract List<Double> getMinimumValues();

    protected abstract List<Double> getMaximumValues();

    protected abstract List<Double> getHalfValues();


    @Before
    public void setup() {
        if (function == null) {
            function = initFunction();
        }
    }

    @Test
    public void testGetType() throws Exception {
        Assertions.assertThat(function.getType()).isEqualTo(getType());
    }

    @Test
    public void testGetMinimum() throws Exception {
        Assertions.assertThat(function.getMinimum()).isEqualTo(getMinimum());
    }

    @Test
    public void testGetMaximum() throws Exception {
        Assertions.assertThat(function.getMaximum()).isEqualTo(getMaximum());
    }

    @Test
    public void testCalculateWithMinimum() throws Exception {
        for (Double value : getMinimumValues()) {
            Assertions.assertThat(function.calculate(value)).isEqualTo(getMinimum());
        }
    }

    @Test
    public void testCalculateWithMaximum() throws Exception {
        for (Double value : getMaximumValues()) {
            Assertions.assertThat(function.calculate(value)).isEqualTo(getMaximum());
        }
    }

    @Test
    public void testCalculateWithHalf() throws Exception {
        for (Double value : getHalfValues()) {
            Assertions.assertThat(function.calculate(value)).isEqualTo(getHalf());
        }
    }

}