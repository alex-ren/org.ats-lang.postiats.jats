package jats.utfpl.stfpl.mcinstruction;

import java.util.HashMap;
import java.util.Map;

import jats.utfpl.patcsps.Aux;
import jats.utfpl.stfpl.csharptype.ICSType;
import jats.utfpl.stfpl.instructions.SId;

public class MCSId implements IMCIdPrim {
	public SId m_sid;
	private ICSType m_type;
	private Aux.Address m_addr;
	
	private static Map<SId, MCSId> s_map = new HashMap<SId, MCSId>();
	
	private MCSId(SId sid, ICSType type) {
		m_sid = sid;
		m_type = type;
		m_addr = null;
	}
	
    public void updateAddr(Aux.Address addr) {
        if (!m_sid.getType().isFunction()) {
            
        }
        m_addr = addr;
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

	@Override
    public ICSType getType() {
	    return m_type;
    }
	
//	public String toStringCS() {
//	    return m_sid.toStringCS();
//	}


	
}
