package org.neoevolution.mvc.service;

import org.neoevolution.core.error.FitnessCalculator;
import org.neoevolution.mvc.dataset.EntityDataSet;
import org.neoevolution.mvc.dataset.FitnessDataSet;
import org.neoevolution.mvc.model.Genotype;
import org.neoevolution.util.FutureUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since Jan 02 2015
 */
@Service
public class GenotypeFitnessService {

    @Autowired
    private GenotypeService service;

    @Autowired
    private FitnessCalculator activation;


    public List<Double> calculate(Long id, FitnessDataSet dataSet) {
        return activation.calculate(service.find(id), dataSet);
    }

    public EntityDataSet calculateEntity(Long id, FitnessDataSet dataSet) {
        return activation.calculateEntity(service.find(id), dataSet);
    }


    public List<EntityDataSet> calculate(List<Long> ids, FitnessDataSet dataSet)
    {
        List<Future<EntityDataSet>> futures = new ArrayList<>(ids.size());

        for (Long id : ids) {
            futures.add(activation.calculateEntityAsync(service.find(id), dataSet));
        }
        return FutureUtils.getResults(futures);
    }

    public List<EntityDataSet> calculate(List<FitnessDataSet> dataSets)
    {
        List<Future<EntityDataSet>> futures = new ArrayList<>(dataSets.size());

        for (FitnessDataSet data : dataSets) {
            futures.add(activation.calculateEntityAsync(service.find(data.getId()), data));
        }
        return FutureUtils.getResults(futures);
    }

    public List<EntityDataSet> calculateAll(FitnessDataSet dataSet)
    {
        List<Genotype> genotypes = service.findAll();
        List<Future<EntityDataSet>> futures = new ArrayList<>(genotypes.size());

        for (Genotype genotype : genotypes) {
            futures.add(activation.calculateEntityAsync(genotype, dataSet));
        }
        return FutureUtils.getResults(futures);
    }

}
