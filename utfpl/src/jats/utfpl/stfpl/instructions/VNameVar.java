package jats.utfpl.stfpl.instructions;

import java.util.HashMap;
import java.util.Map;

import jats.utfpl.stfpl.Cstamp;
import jats.utfpl.stfpl.dynexp3.Cd3var;
import jats.utfpl.stfpl.stype.ISType;


public class VNameVar implements IVarName {
    public Cd3var m_var;
    
    static private Map<Cstamp, VNameVar> s_map = 
            new HashMap<Cstamp, VNameVar>();
    
    static public VNameVar fromCd3var(Cd3var var) {
        VNameVar v = s_map.get(var.m_stamp);
        if (null == v) {
            v = new VNameVar(var);
            s_map.put(var.m_stamp, v);
            return v;
        } else {
            return v;
        }
    }
    
    private VNameVar(Cd3var var) {
        m_var = var;
    }

    @Override
    public ISType getType() {
        return m_var.m_stype;
    }

    @Override
    public String toStringCS() {
        return m_var.toString();
    }
    
}
