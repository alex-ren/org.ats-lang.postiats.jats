package jats.utfpl.stfpl.stype;

import java.util.Map;
import java.util.Set;

import jats.utfpl.stfpl.staexp.Cs2var;
import jats.utfpl.utils.Log;

public class PolyParaType extends BoxedType {
    private Cs2var m_var;
    
    public PolyParaType(Cs2var var) {
        m_var = var;
    }
    
    /*
     * For equality test.
     */
    @Override
    public int hashCode() {
        return m_var.hashCode();
    }
    
    /*
     * For equality test.
     */
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
    public String toString() {
        return m_var.toString();
    }

    @Override
    public PolyParaType normalize() {
        return this;
    }



    @Override
    public TypeCheckResult match(ISType ty) {
        ISType right = ty.normalize();
        if (right instanceof VarType) {
            ((VarType)right).setType(this);
            return new TypeCheckResult();
        } else if (right instanceof PolyParaType) {
            if (!this.equals(right)) {
                return new TypeCheckResult("Type mismatch: " + Log.getFilePos());
            } else {
                return new TypeCheckResult();
            }
        } else {
            return new TypeCheckResult("should not happen");
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

    @Override
    public NamifyResult namify(Map<ITypeName, NamedType> map,
            Set<PolyParaType> env) {
        if (!env.contains(this)) {
            NamifyResult ret = new NamifyResult(null, null, true);
            return ret;
        } else {
            NamifyResult ret = new NamifyResult(null, null, false);
            return ret;
        }
    }

    @Override
    public boolean equalCSharp(ISType type, Map<PolyParaType, PolyParaType> env) {
        if (type instanceof PolyParaType) {
            PolyParaType rtype1 = (PolyParaType)type;
            PolyParaType rtype2 = env.get(this);
            if (null == rtype2) {
                throw new Error("This should not happen.");
            } else {
                if (rtype1.equals(rtype2)) {
                    return true;
                } else {
                    return false;
                }
            }
           
        } else {
            return false;
        }
    }

}
