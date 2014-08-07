package jats.utfpl.stfpl.stype;

import jats.utfpl.stfpl.Cstamp;
import jats.utfpl.stfpl.staexp.Cs2cst;

import java.util.HashMap;
import java.util.Map;

public class TNameCst implements ITypeName {
    private Cs2cst m_cst;
    
    static private Map<Cstamp, TNameCst> s_map = 
            new HashMap<Cstamp, TNameCst>();
    
    static public TNameCst fromCs2cst(Cs2cst cst) {
        TNameCst v = s_map.get(cst.m_stamp);
        if (null == v) {
            v = new TNameCst(cst);
            s_map.put(cst.m_stamp, v);
            return v;
        } else {
            return v;
        }
    }
    
    private TNameCst(Cs2cst cst) {
        m_cst = cst;
    }

}
