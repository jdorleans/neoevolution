package org.neoevolution.mvc.service;

import org.neoevolution.mvc.model.Population;
import org.neoevolution.mvc.repository.PopulationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since 24/10/14.
 */
@Service
public class PopulationService extends AbstractService<Population, PopulationRepository> {

    @Autowired
    private SpecieService specieService;


    @Autowired
    public PopulationService(PopulationRepository repository) {
        super(repository);
    }


    @Override
    protected void beforeSave(Population entity) {
        specieService.save(entity.getSpecies());
    }

}
