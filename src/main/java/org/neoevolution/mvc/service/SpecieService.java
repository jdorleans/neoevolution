package org.neoevolution.mvc.service;

import org.neoevolution.mvc.model.Species;
import org.neoevolution.mvc.repository.SpecieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since 24/10/14.
 */
@Service
public class SpecieService extends AbstractService<Species, SpecieRepository> {

    @Autowired
    private GenotypeService genotypeService;


    @Autowired
    public SpecieService(SpecieRepository repository) {
        super(repository);
    }


    @Override
    protected void beforeSave(Species entity) {
        genotypeService.save(entity.getGenotypes());
    }

}
