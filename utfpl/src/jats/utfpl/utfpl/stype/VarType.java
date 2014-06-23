package jats.utfpl.utfpl.stype;

public class VarType extends SortType {
    private ISType m_ty;
    
    public VarType() {
        super()
        m_ty = null;
    }
    
    public void setType(ISType ty) {
        m_ty = ty;
    }
    
    public ISType getType() {
        return m_ty;
    }

    /*
     * It's possible to have a VarType directly inside another VarType.
     * And we shall not eliminate such case, since it's possible someone is
     * holding the outer VarType while the other is holding the inner VarType.
     * But there can be at most 2 level. No 3 level is needed.
     * 
     * For the return type, if it is VarType, then it's m_ty must be null.
     */
    @Override
    public ISType normalize() {
        if (null != m_ty) {
            m_ty = m_ty.normalize();
            return m_ty;
        } else {
            return this;
        }

    }

    @Override
    public ISType instantiate(PolyParaType para, ISType arg) {
        // I simply disallow this to ease the implementation.
        throw new Error("should not happen");
    }
    
    @Override
    public boolean equals(Object right) {
        throw new Error("should not use this");
        
    }

    @Override
    public void match(ISType ty) {
        ISType left = this.normalize();
        ISType right0 = ty.normalize();
        
        if (right0 instanceof VarType) {
            VarType right = (VarType)right0;
            if (this != right) {
                if (right.m_ty != null) {
                    throw new Error("check this");
                }
                right.m_ty = this;
            } else {
                // do nothing
            }
        } else if (left instanceof VarType) {
            m_ty = right0;
        } else {
            left.match(right0);
        }
        
    }

}
