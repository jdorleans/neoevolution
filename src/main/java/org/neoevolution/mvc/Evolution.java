//package org.neoevolution.mvc;
//
//import org.neo4j.graphdb.Direction;
//import org.neoevolution.core.configuration.NNConfiguration;
//import org.neoevolution.core.innovation.NeuronInnovation;
//import org.neoevolution.core.innovation.SynapseInnovation;
//import org.neoevolution.core.model.Population;
//import org.springframework.data.neo4j.annotation.NodeEntity;
//import org.springframework.data.neo4j.annotation.RelatedTo;
//
///**
// * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
// * @since Dec 21 2014
// */
////@NodeEntity
//public class Evolution extends AbstractEntity {
//
//    private static final long serialVersionUID = 3480233611246056557L;
//
//    @RelatedTo(type="EVOLVES")
//    private Population population;
//
//    @RelatedTo(type="CONFIGURES", direction = Direction.INCOMING)
//    private NNConfiguration configuration;
//
//    @RelatedTo(type="HAS")
//    private NeuronInnovation neuronInnovation;
//
//    @RelatedTo(type="HAS")
//    private SynapseInnovation synapseInnovation;
//
//    private Integer lastPopulationInnovation;
//
//    private Integer lastSpeciesInnovation;
//
//    private Integer lastGenotypeInnovation;
//
//
//    public Population getPopulation() {
//        return population;
//    }
//
//    public void setPopulation(Population population) {
//        this.population = population;
//    }
//
//    public NNConfiguration getConfiguration() {
//        return configuration;
//    }
//
//    public void setConfiguration(NNConfiguration configuration) {
//        this.configuration = configuration;
//    }
//
//    public NeuronInnovation getNeuronInnovation() {
//        return neuronInnovation;
//    }
//
//    public void setNeuronInnovation(NeuronInnovation neuronInnovation) {
//        this.neuronInnovation = neuronInnovation;
//    }
//
//    public SynapseInnovation getSynapseInnovation() {
//        return synapseInnovation;
//    }
//
//    public void setSynapseInnovation(SynapseInnovation synapseInnovation) {
//        this.synapseInnovation = synapseInnovation;
//    }
//
//    public Integer getLastPopulationInnovation() {
//        return lastPopulationInnovation;
//    }
//
//    public void setLastPopulationInnovation(Integer lastPopulationInnovation) {
//        this.lastPopulationInnovation = lastPopulationInnovation;
//    }
//
//    public Integer getLastSpeciesInnovation() {
//        return lastSpeciesInnovation;
//    }
//
//    public void setLastSpeciesInnovation(Integer lastSpeciesInnovation) {
//        this.lastSpeciesInnovation = lastSpeciesInnovation;
//    }
//
//    public Integer getLastGenotypeInnovation() {
//        return lastGenotypeInnovation;
//    }
//
//    public void setLastGenotypeInnovation(Integer lastGenotypeInnovation) {
//        this.lastGenotypeInnovation = lastGenotypeInnovation;
//    }
//}
