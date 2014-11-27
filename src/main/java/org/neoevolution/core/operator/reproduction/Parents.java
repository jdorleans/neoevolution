package org.neoevolution.core.operator.reproduction;

import org.neoevolution.core.model.Genotype;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since Oct 27 2014
 */
public class Parents {

    private boolean hasDominant;

    private Genotype dominant;

    private Genotype recessive;


    public Parents(Genotype parent1, Genotype parent2) {
        init(parent1, parent2);
    }


    private void init(Genotype parent1, Genotype parent2)
    {
        dominant = parent1;
        recessive = parent2;

        if (parent1.getFitness() > parent2.getFitness()) {
            hasDominant = true;
        }
        else if (parent2.getFitness() > parent1.getFitness()) {
            hasDominant = true;
            dominant = parent2;
            recessive = parent1;
        }
    }

    public boolean isEquals() {
        return dominant == recessive;
    }

    public Genotype getDominant() {
        return dominant;
    }
    public void setDominant(Genotype dominant) {
        this.dominant = dominant;
    }

    public Genotype getRecessive() {
        return recessive;
    }
    public void setRecessive(Genotype recessive) {
        this.recessive = recessive;
    }

    public boolean getHasDominant() {
        return hasDominant;
    }
    public void setHashDominant(boolean hasDominant) {
        this.hasDominant = hasDominant;
    }
}
