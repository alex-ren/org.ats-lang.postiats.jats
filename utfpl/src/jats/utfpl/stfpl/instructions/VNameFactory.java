package jats.utfpl.stfpl.instructions;

import java.util.HashMap;
import java.util.Map;

import jats.utfpl.stfpl.Cstamp;
import jats.utfpl.stfpl.dynexp3.Cd3cst;
import jats.utfpl.stfpl.dynexp3.Cd3sym;
import jats.utfpl.stfpl.dynexp3.Cd3var;
import jats.utfpl.stfpl.stype.ISType;
import jats.utfpl.stfpl.stype.VoidType;

public class VNameFactory {
    // VNameId
    private int m_stamp;
    private VNameId m_anony_id;
    
    // VNameVar
    private Map<Cstamp, VNameVar> m_var_map;
    
    // VNameCst
    private Map<Cstamp, VNameCst> m_cst_map;
    
    public VNameFactory() {
        m_stamp = 0;
        m_anony_id = createVNameId("__anony", VoidType.cInstance);
        
        m_var_map =new HashMap<Cstamp, VNameVar>();
        
        m_cst_map = new HashMap<Cstamp, VNameCst>();
    }
 
    public VNameId createVNameId(String name, ISType stype) {
        return new VNameId(name, stype, ++m_stamp);
    }
    
    public VNameId getAnonyId() {
        return m_anony_id;
    }

    public VNameVar mapVNameVar(Cd3var var) {
        VNameVar v = m_var_map.get(var.m_stamp);
        if (null == v) {
            v = new VNameVar(var);
            m_var_map.put(var.m_stamp, v);
            return v;
        } else {
            return v;
        }
    }

    public VNameCst mapCd3cst(Cd3cst cst) {
        VNameCst v = m_cst_map.get(cst.m_stamp);
        if (null == v) {
            v = new VNameCst(cst);
            m_cst_map.put(cst.m_stamp, v);
            return v;
        } else {
            return v;
        }
    }

    public VNameSym createVNameSym(Cd3sym sym) {
        return new VNameSym(sym);
    }
    
    public IVarName duplicate(IVarName vname) {
        return new VNameId(vname.toStringNoStamp(), vname.getType(), ++m_stamp);
    }
    
}

