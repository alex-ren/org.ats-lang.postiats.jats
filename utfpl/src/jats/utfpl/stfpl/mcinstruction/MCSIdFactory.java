package jats.utfpl.stfpl.mcinstruction;

import jats.utfpl.stfpl.instructions.SId;
import jats.utfpl.stfpl.stype.Aux;

import java.util.HashMap;
import java.util.Map;

public class MCSIdFactory {
	private static Map<SId, IMCIdPrim> m_map;
	
	public MCSIdFactory() {
		m_map  = new HashMap<SId, IMCIdPrim>();
	}
	
	public IMCIdPrim createFromSId(SId sid) {
		IMCIdPrim prim = m_map.get(sid);
		if (prim != null) {
			return prim;
		} else {
			if (Aux.getFunctionType(sid.getType()) != null) {
				MCSIdFun mcid = new MCSIdFun(sid);
				m_map.put(sid, mcid);
				return mcid;
			} else {
				MCSIdAtom mcid = new MCSIdAtom(sid);
				m_map.put(sid, mcid);
				return mcid;
			}
		}
	}
}
