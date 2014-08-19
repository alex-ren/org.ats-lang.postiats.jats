package jats.utfpl.stfpl.csharpins;

import java.util.HashMap;
import java.util.Map;

import jats.utfpl.stfpl.csharptype.ICSType;
import jats.utfpl.stfpl.instructions.SId;

public class CSSId implements ICSValPrim {
	public SId m_sid;
	private ICSType m_type;
	
	// basically this is not necessary
	private static Map<SId, CSSId> s_map = new HashMap<SId, CSSId>();
	
	private CSSId(SId sid, ICSType type) {
		m_sid = sid;
		m_type = type;
	}
	
	public static CSSId fromSId(SId sid, ICSType type) {
		CSSId cssid = s_map.get(sid);
		if (null != cssid) {
		    return cssid;
//			throw new Error("This should not happen. cssid is " + cssid.toStringCS());
		} else {
			cssid = new CSSId(sid, type);
			s_map.put(sid, cssid);
//			if (cssid.toStringCS().equals("ret2_id")) {
//			    throw new Error("ddddddddddddddd");
//			}
			System.out.println("cssid is " + cssid.toStringCS());
			return cssid;
		}
	}

	@Override
    public ICSType getType() {
	    return m_type;
    }
	
	public String toStringCS() {
	    return m_sid.toStringCS();
	}

    public static CSSId fromSId2(SId sid) {
        CSSId cssid = s_map.get(sid);
        if (null == cssid) {
            throw new Error("This should not happen. sid is " + sid.toStringCS());
        } else {
            return cssid;
        }
    }
	
}
