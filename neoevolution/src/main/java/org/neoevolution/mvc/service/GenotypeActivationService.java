package org.neoevolution.mvc.service;

import org.neoevolution.core.activation.GenotypeActivation;
import org.neoevolution.mvc.dataset.ListDataSet;
import org.neoevolution.mvc.model.Genotype;
import org.neoevolution.util.FutureUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

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


    public List<ListDataSet> activate(List<Long> ids, ListDataSet inputSet)
    {
        List<Future<ListDataSet>> futures = new ArrayList<>(ids.size());

        for (Long id : ids) {
            futures.add(activation.activateAsync(service.find(id), inputSet));
        }
        return FutureUtils.getResults(futures);
    }

    public List<ListDataSet> activate(List<ListDataSet> inputSets)
    {
        List<Future<ListDataSet>> futures = new ArrayList<>(inputSets.size());

        for (ListDataSet inputSet : inputSets) {
            futures.add(activation.activateAsync(service.find(inputSet.getId()), inputSet));
        }
        return FutureUtils.getResults(futures);
    }

    public List<ListDataSet> activateAll(ListDataSet inputSet)
    {
        List<Genotype> genotypes = service.findAll();
        List<Future<ListDataSet>> futures = new ArrayList<>(genotypes.size());

        for (Genotype genotype : genotypes) {
            futures.add(activation.activateAsync(genotype, inputSet));
        }
        return FutureUtils.getResults(futures);
    }

}
