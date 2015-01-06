package org.neoevolution.mvc.model;

import org.springframework.data.neo4j.annotation.GraphId;

import java.io.Serializable;

public abstract class AbstractEntity implements Serializable, Comparable<AbstractEntity> {

    private static final long serialVersionUID = -5716985183796677682L;

    @GraphId
    protected Long id;


    protected AbstractEntity() { }

    protected AbstractEntity(Long id) {
        this.id = id;
    }


    @Override
    public boolean equals(Object obj)
    {
        boolean equals = false;

        if (this == obj) {
            equals = true;
        }
        else if (id != null && obj instanceof AbstractEntity) {
            AbstractEntity entity = (AbstractEntity) obj;
            equals = id.equals(entity.getId());
        }
        return equals;
    }

    @Override
    public int hashCode() {
        return (id == null ? System.identityHashCode(this) : id.hashCode());
    }

    @Override
    public int compareTo(AbstractEntity entity)
    {
        if (id == null) {
            return -1;
        } else if (entity == null || entity.getId() == null) {
            return 1;
        }
        return id.compareTo(entity.getId());
    }


    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

}
