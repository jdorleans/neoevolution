package org.neoevolution.mvc.model;

import org.neo4j.graphdb.Direction;
import org.neoevolution.mvc.model.configuration.NNConfiguration;
import org.springframework.data.neo4j.annotation.Fetch;
import org.springframework.data.neo4j.annotation.RelatedTo;

/**
* @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
* @since Dec 21 2014
*/
public abstract class Evolution<C extends NNConfiguration> extends AbstractEntity {

    private static final long serialVersionUID = 3480233611246056557L;

    @Fetch
    @RelatedTo(type="EVOLVES")
    protected Population population;

    @Fetch
    @RelatedTo(type="CONFIGURES", direction = Direction.INCOMING)
    protected C configuration;

    protected Boolean finished;


    protected Evolution() {
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

    public Boolean isFinished() {
        return finished;
    }
    public void setFinished(Boolean finished) {
        this.finished = finished;
    }

}
