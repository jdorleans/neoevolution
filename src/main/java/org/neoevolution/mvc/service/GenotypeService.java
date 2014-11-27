package org.neoevolution.mvc.service;

import org.neoevolution.core.model.Genotype;
import org.neoevolution.mvc.repository.GenotypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
