package org.neoevolution.mvc.model;

import org.neo4j.ogm.annotation.Relationship;
import org.neoevolution.mvc.model.configuration.NNConfiguration;

import static org.neo4j.ogm.annotation.Relationship.INCOMING;

/**
* @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
* @since 1.0
*/
public abstract class Evolution<C extends NNConfiguration> extends AbstractEntity {

    private static final long serialVersionUID = 3480233611246056557L;

    @Relationship(type="EVOLVES")
    protected Population population;

    @Relationship(type="CONFIG", direction = INCOMING)
    protected C configuration;

    protected Boolean finished;


    protected Evolution() {
        finished = false;
    }


    @Override
    public boolean equals(Object obj) {
        return (obj instanceof Evolution && super.equals(obj));
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
