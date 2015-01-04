package org.neoevolution.mvc.service;

import org.neoevolution.mvc.model.Genotype;
import org.neoevolution.mvc.model.Neuron;
import org.neoevolution.mvc.model.NeuronType;
import org.neoevolution.mvc.model.Synapse;
import org.neoevolution.mvc.repository.GenotypeRepository;
import org.neoevolution.util.InnovationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since 24/10/14.
 */
@Service
public class GenotypeService extends AbstractFitnessEntityService<Genotype, GenotypeRepository> {

    @Autowired
    private NeuronService neuronService;

    @Autowired
    private SynapseService synapseService;


    @Autowired
    public GenotypeService(GenotypeRepository repository) {
        super(repository);
    }


    @Override
    protected void beforeCreate(Genotype entity, boolean updateReference)
    {
        Set<Neuron> neurons = entity.getNeurons();

        if (neurons != null)
        {
            neuronService.create(neurons, updateReference);

            if (updateReference) {
                updateNeurons(entity, neurons);
                updateSynapses(entity.getSynapses(), neurons);
            }
        }
        synapseService.create(entity.getSynapses(), updateReference);
    }

    private void updateNeurons(Genotype entity, Set<Neuron> neurons) {
        entity.setInputs(getNeurons(entity.getInputs(), neurons));
        entity.setOutputs(getNeurons(entity.getOutputs(), neurons));
    }

    private SortedSet<Neuron> getNeurons(Set<Neuron> notSavedNeurons, Set<Neuron> savedNeurons)
    {
        SortedSet<Neuron> neurons = new TreeSet<>();

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


    @Override
    protected void beforeUpdate(Genotype entity, Genotype dbEntity) {
        entity.setInputs(dbEntity.getInputs());
        entity.setOutputs(dbEntity.getOutputs());
        entity.setNeurons(dbEntity.getNeurons());
        entity.setSynapses(dbEntity.getSynapses());
    }


    @Transactional
    public Genotype addNeuron(Long id, Long neuronId)
    {
        Genotype genotype = find(id);
        Neuron neuron = neuronService.find(neuronId);
        genotype.addNeuron(neuron);
        NeuronType type = neuron.getType();

        if (NeuronType.isInputOrBias(type)) {
            genotype.addInput(neuron);
        }
        else if (NeuronType.isOutput(type)) {
            genotype.addOutput(neuron);
        }
        return repository.save(genotype);
    }


}
