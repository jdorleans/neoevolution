package org.neoevolution.mvc.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;
import org.neoevolution.mvc.json.InnovationSerializer;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since 1.0
 */
@RelationshipEntity(type = "SYNAPSE")
public class Synapse extends Gene {

    private static final long serialVersionUID = -9200265923058182225L;

    @StartNode
    @JsonSerialize(using = InnovationSerializer.class)
    private Neuron start;

    @EndNode
    @JsonSerialize(using = InnovationSerializer.class)
    private Neuron end;

    private Double weight;

    private Boolean enabled;


    public Synapse() {
        this(null, null);
    }

    public Synapse(Neuron start, Neuron end) {
        this(start, end, 0d);
    }

    public Synapse(Neuron start, Neuron end, Double weight) {
        this(null, start, end, weight, true);
    }

    public Synapse(Long innovation, Neuron start, Neuron end, Double weight, Boolean enabled) {
        super(innovation);
        this.start = start;
        this.end = end;
        this.weight = weight;
        this.enabled = enabled;
    }

    public Synapse(Synapse synapse) {
        this(synapse.getInnovation(), null, null, synapse.getWeight(), synapse.isEnabled());
    }


    @Override
    public boolean equals(Object obj) {
        return (obj instanceof Synapse && super.equals(obj));
    }

    @Override
    public String toString() {
        return start.toString() +"-[i:"+ innovation +", "+ enabled +", w:"+ weight +"]->"+ end.toString();
    }


    public Neuron getStart() {
        return start;
    }
    public void setStart(Neuron start) {
        this.start = start;
    }

    public Neuron getEnd() {
        return end;
    }
    public void setEnd(Neuron end) {
        this.end = end;
    }

    public Double getWeight() {
        return weight;
    }
    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Boolean isEnabled() {
        return enabled;
    }
    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

}
