package jats.utfpl.stfpl.instructions;

import java.util.HashMap;
import java.util.Map;

import jats.utfpl.stfpl.Cstamp;
import jats.utfpl.stfpl.dynexp3.Cd3var;
import jats.utfpl.stfpl.stype.ISType;

public class VNameClosurePara implements IVarName {
    public Cd3var m_var;
    
    static private Map<Cstamp, VNameClosurePara> s_map = 
            new HashMap<Cstamp, VNameClosurePara>();
    
    static public VNameClosurePara fromClosurePara(Cd3var var) {
        VNameClosurePara v = s_map.get(var.m_stamp);
        if (null == v) {
            v = new VNameClosurePara(var);
            s_map.put(var.m_stamp, v);
            return v;
        } else {
            return v;
        }
    }
    
    private VNameClosurePara(Cd3var var) {
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
