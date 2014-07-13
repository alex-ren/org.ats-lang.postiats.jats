package jats.utfpl.utfpl.stype;

import java.util.Map;

import jats.utfpl.utfpl.staexp.Cs2var;

public class PolyParaType extends BoxedType {
    private Cs2var m_var;
    
    public PolyParaType(Cs2var var) {
        m_var = var;
    }
    
    @Override
    public int hashCode() {
        return m_var.hashCode();
    }

    @Override
    public String toString() {
        return m_var.toString();
    }

    @Override
    public PolyParaType normalize() {
        return this;
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
        ISType right = ty.normalize();
        if (right instanceof VarType) {
            ((VarType)right).setType(this);
        } else if (right instanceof PolyParaType) {
            if (!this.equals(right)) {
                throw new Error("type mismatch, check this");
            }
        } else {
            throw new Error("should not happen");
        }

    }

    @Override
    public ISType instantiate(Map<PolyParaType, ISType> map) {
        if (map.containsKey(this)) {
            return map.get(this);
        } else {
            return this;
        }
    }

}
