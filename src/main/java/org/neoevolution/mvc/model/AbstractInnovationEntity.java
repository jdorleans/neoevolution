package org.neoevolution.mvc.model;

public abstract class AbstractInnovationEntity extends AbstractEntity {

    private static final long serialVersionUID = -5237757583908066829L;

    protected Long innovation;


    protected AbstractInnovationEntity() {
        this(1l);
    }

    protected AbstractInnovationEntity(Long innovation) {
        super();
        this.innovation = innovation;
    }


    @Override
    public boolean equals(Object obj)
    {
        boolean equals = false;

        if (this == obj) {
            equals = true;
        }
        else if (innovation != null && obj instanceof AbstractInnovationEntity) {
            AbstractInnovationEntity entity = (AbstractInnovationEntity) obj;
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
        else if (entity instanceof AbstractInnovationEntity)
        {
            AbstractInnovationEntity gene = (AbstractInnovationEntity) entity;

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
