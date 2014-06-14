package jats.utfpl.utfpl.stype;

import jats.utfpl.utfpl.staexp.Cs2var;

public class PolyParaType extends BoxedType {
    private Cs2var m_var;
    
    public PolyParaType(Cs2var var) {
        m_var = var;
    }

    @Override
    public PolyParaType normalize() {
        return this;
    }

    @Override
    public ISType instantiate(PolyParaType para, ISType arg) {
        if (para.m_var == m_var) {
            return arg;
        } else {
            return this;
        }
    }
    
    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (o instanceof PolyParaType) {
            return m_var == ((PolyParaType)o).m_var;
        } else {
            return false;
        }
    }

    @Override
    public void match(ISType ty) {
        throw new Error("not expecting this");
//        PolyParaType left = this.normalize();
//        ISType right0 = ty.normalize();
        
    }

}
