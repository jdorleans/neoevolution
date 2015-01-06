package org.neoevolution.mvc.service;

import org.neoevolution.core.operator.activation.EntitySampleData;
import org.neoevolution.core.operator.activation.GenotypeActivation;
import org.neoevolution.core.operator.activation.SampleData;
import org.neoevolution.mvc.model.Genotype;
import org.neoevolution.util.FutureUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since Jan 02 2015
 */
@Service
public class GenotypeActivationService {

    @Autowired
    private GenotypeService service;

    @Autowired
    private GenotypeActivation activation;


    public EntitySampleData activate(Long id, SampleData sample) throws ExecutionException, InterruptedException {
        return activation.activate(service.find(id), sample).get();
    }

    public List<EntitySampleData> activate(List<EntitySampleData> samples)
    {
        List<Future<EntitySampleData>> futures = new ArrayList<>(samples.size());

        for (EntitySampleData sample : samples) {
            futures.add(activation.activate(service.find(sample.getId()), sample));
        }
        return FutureUtils.getResults(futures);
    }

    public List<EntitySampleData> activateAll(SampleData sample)
    {
        List<Genotype> genotypes = service.findAll();
        List<Future<EntitySampleData>> futures = new ArrayList<>(genotypes.size());

        for (Genotype genotype : genotypes) {
            futures.add(activation.activate(genotype, sample));
        }
        return FutureUtils.getResults(futures);
    }

}
