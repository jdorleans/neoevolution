package org.neoevolution.core.activation;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since 1.0
 */
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
        Assert.assertEquals(function.getType(), getType());
    }

    @Test
    public void testGetMinimum() throws Exception {
        Assert.assertEquals(function.getMinimum(), getMinimum(), 0);
    }

    @Test
    public void testGetMaximum() throws Exception {
        Assert.assertEquals(function.getMaximum(), getMaximum(), 0);
    }

    @Test
    public void testCalculateWithMinimum() throws Exception {
        for (Double value : getMinimumValues()) {
            Assert.assertEquals(function.calculate(value), getMinimum(), 0);
        }
    }

    @Test
    public void testCalculateWithMaximum() throws Exception {
        for (Double value : getMaximumValues()) {
            Assert.assertEquals(function.calculate(value), getMaximum(), 0);
        }
    }

    @Test
    public void testCalculateWithHalf() throws Exception {
        for (Double value : getHalfValues()) {
            Assert.assertEquals(function.calculate(value), getHalf(), 0);
        }
    }

}