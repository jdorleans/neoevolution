package org.neoevolution.mvc.service;

import org.neoevolution.mvc.model.Genotype;
import org.neoevolution.mvc.model.Population;
import org.neoevolution.mvc.model.Species;
import org.neoevolution.mvc.repository.PopulationRepository;
import org.neoevolution.util.InnovationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

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
    protected void beforeSave(Population entity, boolean updateReference)
    {
        Set<Species> species = entity.getSpecies();
        specieService.save(species, updateReference);

        if (updateReference) {
            updateBestSpecies(entity, species);
            updateBestGenotype(entity, species);
        }
    }

    private void updateBestSpecies(Population entity, Set<Species> species) {
        Long bestSpeciesInnovation = entity.getBestSpecies().getInnovation();
        entity.setBestSpecies(InnovationUtils.find(bestSpeciesInnovation, species));
    }

    private void updateBestGenotype(Population entity, Set<Species> species)
    {
        Long bestGenotypeInnovation = entity.getBestGenotype().getInnovation();

        for (Species specie : species)
        {
            Genotype bestGenotype = specie.getBestGenotype();

            if (bestGenotypeInnovation.equals(bestGenotype.getInnovation())) {
                entity.setBestGenotype(bestGenotype);
                break;
            }
        }
    }

}
