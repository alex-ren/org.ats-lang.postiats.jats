package jats.utfpl.utfpl.stype;

public class VarType implements ISType {
    private SortType m_ty;
    
    public VarType() {
        m_ty = null;
    }
    
    public void setType(SortType ty) {
        m_ty = ty;
    }

//    @Override
//    public boolean equals(ISType ty) {
//        return (m_ty == null) ? ty == null: ty.equals(m_ty);
//    }

}
