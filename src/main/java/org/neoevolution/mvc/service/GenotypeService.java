package org.neoevolution.mvc.service;

import org.neoevolution.core.Genotype;
import org.neoevolution.core.Species;
import org.neoevolution.mvc.repository.GenotypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since 24/10/14.
 */
@Service
public class GenotypeService extends AbstractService<Genotype, GenotypeRepository> {

    @Autowired
    private SynapseService synapseService;


    @Autowired
    public GenotypeService(GenotypeRepository repository) {
        super(repository);
    }


    @Override
    protected void beforeSave(Genotype entity) {
        synapseService.save(entity.getSynapses());
    }

    @Override
    @Transactional
    public void save(Genotype entity) {
        Species species = entity.getSpecies();
        entity.setSpecies(null);
        super.save(entity);
        entity.setSpecies(species);
    }

}
