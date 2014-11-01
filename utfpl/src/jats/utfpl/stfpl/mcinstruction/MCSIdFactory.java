package jats.utfpl.stfpl.mcinstruction;

import jats.utfpl.stfpl.instructions.SId;

import java.util.HashMap;
import java.util.Map;

public class MCSIdFactory {
	private static Map<SId, MCSId> m_map;
	
	public MCSIdFactory() {
		m_map  = new HashMap<SId, MCSId>();
	}
	
	public MCSId fromSId(SId sid) {
	    MCSId mcsid = m_map.get(sid);
	    if (null == mcsid) {
	        mcsid = new MCSId(sid);
	        m_map.put(sid, mcsid);
	    }
	    return mcsid;
	}
	
//	public IMCSId createFromSId(SId sid) {
//		IMCSId prim = m_map.get(sid);
//		if (prim != null) {
//			return prim;
//		} else {
//			if (Aux.getFunctionType(sid.getType()) != null) {
//				MCSIdFun mcid = new MCSIdFun(sid);
//				m_map.put(sid, mcid);
//				return mcid;
//			} else {
//				MCSIdAtomVal mcid = new MCSIdAtomVal(sid);
//				m_map.put(sid, mcid);
//				return mcid;
//			}
//		}
//	}
	
//	public MCSId fromSIdAtomVal(SId sid) {
//        IMCSId prim = m_map.get(sid);
//        if (prim != null) {
//            return (MCSId)prim;
//        } else {
//            MCSId mcid = new MCSId(sid);
//            m_map.put(sid, mcid);
//            return mcid;
//        }
//    }
//	
//	   public MCSIdFun fromSIdFun(SId sid) {
//	        IMCSId prim = m_map.get(sid);
//	        if (prim != null) {
//	            return (MCSIdFun)prim;
//	        } else {
//	            MCSIdFun mcid = new MCSIdFun(sid);
//	            m_map.put(sid, mcid);
//	            return mcid;
//	        }
//	    }
}




