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
 * @since 1.0
 */
@Service
public class PopulationService extends AbstractFitnessEntityService<Population, PopulationRepository> {

    @Autowired
    private SpeciesService speciesService;


    @Autowired
    public PopulationService(PopulationRepository repository) {
        super(repository);
    }


    @Override
    protected void beforeCreate(Population entity, boolean updateReference)
    {
        Set<Species> species = entity.getSpecies();

        if (species != null)
        {
            speciesService.create(species, updateReference);

            if (updateReference) {
                updateBestSpecies(entity, species);
                updateBestGenotype(entity, species);
            }
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


    @Override
    protected void beforeUpdate(Population entity, Population dbEntity) {
        entity.setSpecies(dbEntity.getSpecies());
        entity.setBestSpecies(dbEntity.getBestSpecies());
        entity.setBestGenotype(dbEntity.getBestGenotype());
    }

}
