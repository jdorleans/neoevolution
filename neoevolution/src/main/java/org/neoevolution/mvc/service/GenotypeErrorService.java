package org.neoevolution.mvc.service;

import org.neoevolution.core.operator.activation.EntityErrorDataSet;
import org.neoevolution.core.operator.activation.EntityFitnessDataSet;
import org.neoevolution.core.operator.activation.ErrorCalculator;
import org.neoevolution.core.operator.activation.ErrorDataSet;
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
public class GenotypeErrorService {

    @Autowired
    private GenotypeService service;

    @Autowired
    private ErrorCalculator activation;


    public List<Double> calculate(Long id, ErrorDataSet dataSet) {
        return activation.calculate(service.find(id), dataSet);
    }

    public List<EntityFitnessDataSet> calculate(List<EntityErrorDataSet> dataSet)
    {
        List<Future<EntityFitnessDataSet>> futures = new ArrayList<>(dataSet.size());

        for (EntityErrorDataSet data : dataSet) {
            futures.add(activation.calculateEntityAsync(service.find(data.getId()), data));
        }
        return FutureUtils.getResults(futures);
    }

    public List<EntityFitnessDataSet> calculateAll(ErrorDataSet dataSet)
    {
        List<Genotype> genotypes = service.findAll();
        List<Future<EntityFitnessDataSet>> futures = new ArrayList<>(genotypes.size());

        for (Genotype genotype : genotypes) {
            futures.add(activation.calculateEntityAsync(genotype, dataSet));
        }
        return FutureUtils.getResults(futures);
    }

}
