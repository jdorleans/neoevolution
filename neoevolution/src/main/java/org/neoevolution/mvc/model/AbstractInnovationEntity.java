package org.neoevolution.mvc.model;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since 1.0
 */
public abstract class AbstractInnovationEntity extends AbstractEntity {

    private static final long serialVersionUID = -5237757583908066829L;

    protected Long innovation;


    protected AbstractInnovationEntity() {
        this(1L);
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
        else if (innovation != null && obj instanceof AbstractInnovationEntity)
        {
            AbstractInnovationEntity entity = (AbstractInnovationEntity) obj;
            equals = innovation.equals(entity.getInnovation());

            if (id != null || entity.getId() != null) {
                equals &= super.equals(obj);
            }
        }
        return equals;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (innovation != null ? innovation.hashCode() : 0);
        return result;
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
            AbstractInnovationEntity e = (AbstractInnovationEntity) entity;

            if (e.getInnovation() != null) {
                compare = innovation.compareTo(e.getInnovation());
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
