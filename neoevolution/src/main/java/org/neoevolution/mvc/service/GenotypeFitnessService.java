package org.neoevolution.mvc.service;

import org.neoevolution.core.error.FitnessCalculator;
import org.neoevolution.mvc.dataset.EntityDataSet;
import org.neoevolution.mvc.dataset.FitnessDataSet;
import org.neoevolution.mvc.model.Genotype;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since 1.0
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


    public List<EntityDataSet> calculate(List<Long> ids, FitnessDataSet dataSet) {
        return ids.parallelStream().map(id -> activation.calculateEntity(service.find(id), dataSet)).collect(Collectors.toList());
    }

    public List<EntityDataSet> calculate(List<FitnessDataSet> dataSets) {
        return dataSets.parallelStream().map(dataSet -> activation.calculateEntity(service.find(dataSet.getId()), dataSet)).collect(Collectors.toList());
    }

    public List<EntityDataSet> calculateAll(FitnessDataSet dataSet) {
        List<Genotype> genotypes = service.findAll();
        return genotypes.parallelStream().map(genotype -> activation.calculateEntity(genotype, dataSet)).collect(Collectors.toList());
    }

}
