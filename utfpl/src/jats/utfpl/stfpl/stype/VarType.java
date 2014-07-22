package jats.utfpl.stfpl.stype;

import java.util.Map;

public class VarType extends SortType {
    private SortType m_ty;
    
    public VarType() {
        super(ESort.unknown);
        m_ty = null;
    }
    
    public boolean isRaw() {
        return null == m_ty;
    }
    
    public void setType(ISType ty) {
        if (!(ty instanceof SortType)) {
            throw new Error("non-sort type not allowed");
        }
        m_ty = (SortType)ty;
        m_srt = m_ty.m_srt;
        
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
            ISType nty = m_ty.normalize();
            if (!(nty instanceof SortType)) {
                throw new Error("non-sort type not allowed");
            }
            m_ty = (SortType)nty;
            return m_ty;
        } else {
            return this;
        }

    }

    @Override
    public ISType instantiate(Map<PolyParaType, ISType> map) {
        // I simply disallow this to ease the implementation.
        throw new Error("should not happen");
    }
    
    @Override
    public boolean equals(Object right) {
        throw new Error("should not use this");
        
    }

    @Override
    public TypeCheckResult match(ISType ty) {
        ISType left = this.normalize();
        ISType right0 = ty.normalize();
        
        if (right0 instanceof VarType) {
            VarType right = (VarType)right0;
            if (this != right) {
                if (right.m_ty != null) {
                    return new TypeCheckResult("check this");
                }
                right.m_ty = this;
            } else {
                // do nothing
            }
        } else if (left instanceof VarType) {
            if (!(right0 instanceof SortType)) {
                return new TypeCheckResult("non-sort type not allowed");
            }
            m_ty = (SortType)right0;
        } else {
            left.match(right0);
        }
        
        if (null != m_ty) {
            m_srt = m_ty.m_srt;  // update sort
        }
        
        return new TypeCheckResult();
    }

}