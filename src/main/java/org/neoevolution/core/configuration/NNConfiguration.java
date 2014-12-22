package org.neoevolution.core.configuration;

import org.neoevolution.core.activation.ActivationFunctionType;
import org.neoevolution.mvc.Entity;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since Oct 22 2014
 */
public interface NNConfiguration extends Entity {

    Long nextPopulationInnovation();

    Long nextSpeciesInnovation();

    Long nextGenotypeInnovation();

    Long getPopulationInnovation();

    Long getSpeciesInnovation();

    Long getGenotypeInnovation();

    Integer getPopulationSize();

    Integer getMaxSpeciesSize();

    Integer getInputSize();

    Integer getOutputSize();

    Boolean isFullyConnected();

    Double getAddNeuronRate();

    Integer getHiddenMaxSize();

    Double getAddSynapseRate();

    Double getWeightSynapseRate();

    Double getWeightRange();

    Boolean isWeightSynapseReset();

    Double getSpeciesSizeRate();

    ActivationFunctionType getActivationBias();

    ActivationFunctionType getActivationInput();

    ActivationFunctionType getActivationHidden();

    ActivationFunctionType getActivationOutput();

}