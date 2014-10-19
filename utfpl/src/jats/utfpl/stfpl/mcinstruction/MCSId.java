package jats.utfpl.stfpl.mcinstruction;

import java.util.HashMap;
import java.util.Map;

import jats.utfpl.patcsps.Aux;
import jats.utfpl.stfpl.csharptype.ICSType;
import jats.utfpl.stfpl.instructions.IIdPrim;
import jats.utfpl.stfpl.instructions.SId;
import jats.utfpl.stfpl.instructions.SIdUser;
import jats.utfpl.stfpl.instructions.SId.SIdCategory;
import jats.utfpl.stfpl.stype.FunType;


public class MCSId implements IMCIdPrim {
	private IIdPrim m_id_prim;  todo
	private ICSType m_type;
	private Aux.Address m_addr;
	private Boolean m_has_effect;
	private int m_count;
	
	private static Map<SId, MCSId> s_map = new HashMap<SId, MCSId>();
	private static Map<SIdUser, MCSId> s_map_user = new HashMap<SIdUser, MCSId>();
	private static int c_count = 0;
	
	private MCSId(IIdPrim id_prim, ICSType type) {
	    m_id_prim = id_prim;
		m_type = type;
		m_addr = null;
		m_has_effect = null;
		m_count = c_count++; 
	}
	
    private MCSId(IIdPrim id_prim, ICSType type, Aux.Address addr,
            Boolean has_effect) {
        m_id_prim = id_prim;
        m_type = type;
        m_addr = addr;
        m_has_effect = has_effect;
        m_count = c_count++; 
    }

    public MCSId dup() {
        return new MCSId(m_id_prim, m_type, m_addr, m_has_effect);
    }
    
	public Boolean hasEffect() {
		return m_has_effect;
	}
	
	public void updateEffect(Boolean has_effect) {
		m_has_effect = has_effect;
	}
	
    public void updateAddr(Aux.Address addr) {
        if (!(m_id_prim instanceof SId)) {
            throw new Error("This should not happen.");
        }
        
        SId sid = (SId)m_id_prim;
    	FunType fun_type = jats.utfpl.stfpl.stype.Aux.getFunctionType(m_id_prim.getType());
    	if (null == fun_type) {
    		throw new Error("Should not happen");
    	}
    	
    	if (sid.getCat() != SIdCategory.eUserFun) {
    		throw new Error("not supported for function variable");
    	}
    	
    	if (null == m_addr) {
    		m_addr = addr;
    	} else {
    		// do nothing
    	}
        
    }
    
	public static MCSId fromSId(SId sid, ICSType type) {
		MCSId cssid = s_map.get(sid);
		if (null != cssid) {
		    return cssid;
		} else {
			cssid = new MCSId(sid, type);
			s_map.put(sid, cssid);
			return cssid;
		}
	}
	
    public static MCSId fromSId2(SId sid) {
        MCSId cssid = s_map.get(sid);
        if (null == cssid) {
            throw new Error("This should not happen. sid is " + sid.toStringCS());
        } else {
            return cssid;
        }
    }
    
    public static MCSId fromSIdUser(SIdUser var, ICSType type) {
        MCSId mc_sid = s_map_user.get(var);
        if (null != mc_sid) {
            throw new Error("Check this.");
//            return mc_sid;
        } else {
            MCSId ret = new MCSId(var, type);
            s_map_user.put(var, ret);
            return ret;
        }
        
       
    }
    
    public SId getSId() {
        if (m_id_prim instanceof SId) {
            return (SId)m_id_prim;
        } else {
            return ((SIdUser)m_id_prim).getSId();
        }
    }

	@Override
    public ICSType getType() {
	    return m_type;
    }

	
//	public String toStringCS() {
//	    return m_sid.toStringCS();
//	}


	
}
