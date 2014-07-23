package jats.utfpl.stfpl.instructions;

import java.util.HashMap;
import java.util.Map;

import jats.utfpl.stfpl.Cstamp;
import jats.utfpl.stfpl.dynexp3.Cd3cst;
import jats.utfpl.stfpl.stype.ISType;


public class VNameCst implements IVarName {
    public Cd3cst m_cst;
    
    static private Map<Cstamp, VNameCst> s_map = 
            new HashMap<Cstamp, VNameCst>();
    
    static public VNameCst fromCd3cst(Cd3cst cst) {
        VNameCst v = s_map.get(cst.m_stamp);
        if (null == v) {
            v = new VNameCst(cst);
            s_map.put(cst.m_stamp, v);
            return v;
        } else {
            return v;
        }
    }
    
    private VNameCst(Cd3cst cst) {
        m_cst = cst;
    }

    @Override
    public ISType getType() {
        return m_cst.m_stype;
    }
}
