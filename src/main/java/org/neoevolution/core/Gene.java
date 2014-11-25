package org.neoevolution.core;

public abstract class Gene extends AbstractInnovationEntity {

    private static final long serialVersionUID = 3901529272692586447L;


    protected Gene(Long innovation) {
        super(innovation);
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof Gene && super.equals(obj));
    }

}
