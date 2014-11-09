package org.neoevolution.core;

import org.neoevolution.mvc.AbstractEntity;

public abstract class Gene extends AbstractEntity {

    private static final long serialVersionUID = -1875806151439510633L;

    protected Long innovation;


    protected Gene() { }

    protected Gene(Long id, Long innovation) {
        super(id);
        this.innovation = innovation;
    }


    @Override
    public boolean equals(Object obj)
    {
        boolean equals = false;

        if (this == obj) {
            equals = true;
        }
        else if (innovation != null && obj instanceof Gene) {
            Gene entity = (Gene) obj;
            equals = innovation.equals(entity.getInnovation());
        }
        return equals;
    }

    @Override
    public int hashCode() {
        return (innovation == null ? System.identityHashCode(this) : innovation.hashCode());
    }

    @Override
    public int compareTo(AbstractEntity entity)
    {
        int compare = 1;

        if (innovation == null) {
            compare = -1;
        }
        else if (entity instanceof Gene)
        {
            Gene gene = (Gene) entity;

            if (gene.getInnovation() != null) {
                compare = innovation.compareTo(gene.getInnovation());
            }
        }
        return compare;
    }


    public Long getInnovation() {
        return innovation;
    }
    public void setInnovation(Long innovation) {
        this.innovation = innovation;
    }

}
