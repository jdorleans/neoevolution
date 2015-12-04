package org.neoevolution.mvc.service;

import org.neoevolution.core.activation.GenotypeActivation;
import org.neoevolution.mvc.dataset.ListDataSet;
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
public class GenotypeActivationService {

    @Autowired
    private GenotypeService service;

    @Autowired
    private GenotypeActivation activation;


    public List<Double> activate(Long id, List<Double> inputs) {
        return activation.activate(service.find(id), inputs);
    }

    public ListDataSet activate(Long id, ListDataSet inputSet) {
        return activation.activate(service.find(id), inputSet);
    }


    public List<ListDataSet> activate(List<Long> ids, ListDataSet inputSet) {
        return ids.parallelStream().map(id -> activation.activate(service.find(id), inputSet)).collect(Collectors.toList());
    }

    public List<ListDataSet> activate(List<ListDataSet> inputSets) {
        return inputSets.parallelStream().map(inputSet -> activation.activate(service.find(inputSet.getId()), inputSet)).collect(Collectors.toList());
    }

    public List<ListDataSet> activateAll(ListDataSet inputSet) {
        List<Genotype> genotypes = service.findAll();
        return genotypes.parallelStream().map(genotype -> activation.activate(genotype, inputSet)).collect(Collectors.toList());
    }

}
