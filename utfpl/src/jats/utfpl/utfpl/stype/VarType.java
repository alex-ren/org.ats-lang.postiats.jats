package jats.utfpl.utfpl.stype;

public class VarType implements ISType {
    private ISType m_ty;
    
    public VarType() {
        m_ty = null;
    }
    
    public void setType(ISType ty) {
        m_ty = ty;
    }
    
    public ISType getType() {
        return m_ty;
    }

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

//    @Override
//    public boolean equals(ISType ty) {
//        return (m_ty == null) ? ty == null: ty.equals(m_ty);
//    }

}
