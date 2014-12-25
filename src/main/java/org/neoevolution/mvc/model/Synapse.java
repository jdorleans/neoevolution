package org.neoevolution.mvc.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.springframework.data.neo4j.annotation.EndNode;
import org.springframework.data.neo4j.annotation.RelationshipEntity;
import org.springframework.data.neo4j.annotation.StartNode;

@RelationshipEntity(type = "SYNAPSE")
public class Synapse extends Gene {

    private static final long serialVersionUID = -9200265923058182225L;

    @StartNode
    @JsonBackReference
    private Neuron start;

    @EndNode
    @JsonBackReference
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
