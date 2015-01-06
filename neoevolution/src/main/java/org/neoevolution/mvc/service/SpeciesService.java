package org.neoevolution.mvc.service;

import org.neoevolution.mvc.model.Genotype;
import org.neoevolution.mvc.model.Species;
import org.neoevolution.mvc.repository.SpeciesRepository;
import org.neoevolution.util.InnovationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since 24/10/14.
 */
@Service
public class SpeciesService extends AbstractFitnessEntityService<Species, SpeciesRepository> {

    @Autowired
    private GenotypeService genotypeService;


    @Autowired
    public SpeciesService(SpeciesRepository repository) {
        super(repository);
    }


    @Override
    protected void beforeCreate(Species entity, boolean updateReference)
    {
        Set<Genotype> genotypes = entity.getGenotypes();

        if (genotypes != null)
        {
            genotypeService.create(genotypes, updateReference);

            if (updateReference) {
                updateBestGenotype(entity, genotypes);
            }
        }
    }

    private void updateBestGenotype(Species entity, Set<Genotype> genotypes) {
        Long bestInnovation = entity.getBestGenotype().getInnovation();
        entity.setBestGenotype(InnovationUtils.find(bestInnovation, genotypes));
    }


    @Override
    protected void beforeUpdate(Species entity, Species dbEntity) {
        entity.setGenotypes(dbEntity.getGenotypes());
        entity.setBestGenotype(dbEntity.getBestGenotype());
    }

}
