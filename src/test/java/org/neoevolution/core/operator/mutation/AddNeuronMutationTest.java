package org.neoevolution.core.operator.mutation;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.neoevolution.core.factory.NeuronFactory;
import org.neoevolution.core.factory.SynapseFactory;

@RunWith(MockitoJUnitRunner.class)
public class AddNeuronMutationTest {

    @InjectMocks
    private AddNeuronMutation mutation;

    @Mock
    private NeuronFactory factory;

    @Mock
    private SynapseFactory synapseFactory;

    @Test
    public void testMutation() throws Exception {

    }

}