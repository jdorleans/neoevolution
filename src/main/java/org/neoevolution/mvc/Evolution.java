package org.neoevolution.mvc;

import org.neo4j.graphdb.Direction;
import org.neoevolution.core.configuration.NNConfiguration;
import org.neoevolution.core.innovation.NeuronInnovation;
import org.neoevolution.core.innovation.SynapseInnovation;
import org.neoevolution.core.model.Population;
import org.springframework.data.neo4j.annotation.RelatedTo;

/**
* @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
* @since Dec 21 2014
*/
public abstract class Evolution<C extends NNConfiguration> extends AbstractEntity {

    private static final long serialVersionUID = 3480233611246056557L;

    @RelatedTo(type="EVOLVES")
    private Population population;

    @RelatedTo(type="CONFIGURES", direction = Direction.INCOMING)
    private C configuration;

    @RelatedTo(type="HAS")
    private NeuronInnovation neuronInnovation;

    @RelatedTo(type="HAS")
    private SynapseInnovation synapseInnovation;

    private Boolean finished;


    public Evolution() {
        finished = false;
    }


    public Population getPopulation() {
        return population;
    }

    public void setPopulation(Population population) {
        this.population = population;
    }

    public C getConfiguration() {
        return configuration;
    }

    public void setConfiguration(C configuration) {
        this.configuration = configuration;
    }

    public NeuronInnovation getNeuronInnovation() {
        return neuronInnovation;
    }

    public void setNeuronInnovation(NeuronInnovation neuronInnovation) {
        this.neuronInnovation = neuronInnovation;
    }

    public SynapseInnovation getSynapseInnovation() {
        return synapseInnovation;
    }

    public void setSynapseInnovation(SynapseInnovation synapseInnovation) {
        this.synapseInnovation = synapseInnovation;
    }

    public Boolean isFinished() {
        return finished;
    }
    public void setFinished(Boolean finished) {
        this.finished = finished;
    }

}
