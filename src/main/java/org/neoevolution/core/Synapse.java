package org.neoevolution.core;

import org.springframework.data.neo4j.annotation.EndNode;
import org.springframework.data.neo4j.annotation.RelationshipEntity;
import org.springframework.data.neo4j.annotation.StartNode;

@RelationshipEntity(type = "SYNAPSE")
public class Synapse extends Gene {

    private static final long serialVersionUID = -9200265923058182225L;

    @StartNode
    private Neuron start;

    @EndNode
    private Neuron end;

    private Double weight;

    private Boolean enabled;


    public Synapse() {
        this(null, null, 0d, true);
    }

    public Synapse(Neuron start, Neuron end, Double weight, Boolean enabled) {
        this(null, start, end, weight, enabled);
    }

    public Synapse(Long innovation, Neuron start, Neuron end, Double weight, Boolean enabled) {
        super(null, innovation);
        this.start = start;
        this.end = end;
        this.weight = weight;
        this.enabled = enabled;
    }

    public Synapse(Synapse synapse) {
        this(synapse.getInnovation(), null, null, synapse.getWeight(), synapse.isEnabled());
    }


    @Override
    public String toString() {
        return start.toString() +"-["+ enabled +", w:"+ weight +"]->"+ end.toString();
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
