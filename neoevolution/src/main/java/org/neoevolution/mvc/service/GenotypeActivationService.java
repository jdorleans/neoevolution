package org.neoevolution.mvc.service;

import org.neoevolution.core.operator.activation.DataSet;
import org.neoevolution.core.operator.activation.EntityDataSet;
import org.neoevolution.core.operator.activation.GenotypeActivation;
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


    public List<Double> activate(Long id, List<Double> inputs) {
        return activation.activate(service.find(id), inputs);
    }

    public DataSet activate(Long id, DataSet inputSet) throws ExecutionException, InterruptedException {
        return activation.activate(service.find(id), inputSet).get();
    }

    public List<EntityDataSet> activate(List<EntityDataSet> inputSet)
    {
        List<Future<EntityDataSet>> futures = new ArrayList<>(inputSet.size());

        for (EntityDataSet input : inputSet) {
            futures.add(activation.activateEntity(service.find(input.getId()), input));
        }
        return FutureUtils.getResults(futures);
    }

    public List<EntityDataSet> activateAll(DataSet inputSet)
    {
        List<Genotype> genotypes = service.findAll();
        List<Future<EntityDataSet>> futures = new ArrayList<>(genotypes.size());

        for (Genotype genotype : genotypes) {
            futures.add(activation.activateEntity(genotype, inputSet));
        }
        return FutureUtils.getResults(futures);
    }

}
