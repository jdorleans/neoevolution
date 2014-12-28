package org.neoevolution.mvc.service;

import org.neoevolution.mvc.model.Genotype;
import org.neoevolution.mvc.model.Neuron;
import org.neoevolution.mvc.model.Synapse;
import org.neoevolution.mvc.repository.GenotypeRepository;
import org.neoevolution.util.InnovationUtils;
import org.neoevolution.util.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since 24/10/14.
 */
@Service
public class GenotypeService extends AbstractService<Genotype, GenotypeRepository> {

    @Autowired
    private NeuronService neuronService;

    @Autowired
    private SynapseService synapseService;


    @Autowired
    public GenotypeService(GenotypeRepository repository) {
        super(repository);
    }


    @Override
    protected void beforeSave(Genotype entity, boolean updateReference)
    {
        Set<Neuron> neurons = entity.getNeurons();
        neuronService.save(neurons, updateReference);

        if (updateReference) {
            updateNeurons(entity, neurons);
            updateSynapses(entity.getSynapses(), neurons);
        }
        synapseService.save(entity.getSynapses(), updateReference);
    }

    private void updateNeurons(Genotype entity, Set<Neuron> neurons) {
        entity.setInputs(getNeurons(entity.getInputs(), neurons));
        entity.setOutputs(getNeurons(entity.getOutputs(), neurons));
    }

    private Set<Neuron> getNeurons(Set<Neuron> notSavedNeurons, Set<Neuron> savedNeurons)
    {
        Set<Neuron> neurons = MapUtils.createHashSet(notSavedNeurons.size());

        for (Neuron notSavedNeuron : notSavedNeurons) {
            neurons.add(InnovationUtils.find(notSavedNeuron.getInnovation(), savedNeurons));
        }
        return neurons;
    }

    private void updateSynapses(Set<Synapse> synapses, Set<Neuron> neurons) {
        for (Synapse synapse : synapses) {
            synapse.setStart(InnovationUtils.find(synapse.getStart().getInnovation(), neurons));
            synapse.setEnd(InnovationUtils.find(synapse.getEnd().getInnovation(), neurons));
        }
    }

}
