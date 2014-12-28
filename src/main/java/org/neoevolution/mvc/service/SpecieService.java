package org.neoevolution.mvc.service;

import org.neoevolution.mvc.model.Genotype;
import org.neoevolution.mvc.model.Species;
import org.neoevolution.mvc.repository.SpecieRepository;
import org.neoevolution.util.InnovationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

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
    protected void beforeSave(Species entity, boolean updateReference)
    {
        Set<Genotype> genotypes = entity.getGenotypes();
        genotypeService.save(genotypes, updateReference);

        if (updateReference) {
            updateBestGenotype(entity, genotypes);
        }
    }

    private void updateBestGenotype(Species entity, Set<Genotype> genotypes) {
        Long bestInnovation = entity.getBestGenotype().getInnovation();
        entity.setBestGenotype(InnovationUtils.find(bestInnovation, genotypes));
    }

}
