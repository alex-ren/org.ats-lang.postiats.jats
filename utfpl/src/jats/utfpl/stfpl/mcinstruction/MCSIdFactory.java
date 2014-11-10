package jats.utfpl.stfpl.mcinstruction;

import jats.utfpl.stfpl.instructions.AtomValue;
import jats.utfpl.stfpl.instructions.IValPrim;
import jats.utfpl.stfpl.instructions.SId;
import jats.utfpl.stfpl.instructions.SIdFactory;
import jats.utfpl.stfpl.instructions.SIdUser;
import jats.utfpl.stfpl.stype.Aux;
import jats.utfpl.stfpl.stype.ISType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MCSIdFactory {
	private Map<SId, MCSId> m_map;
	private SIdFactory m_fac;
	
	public MCSIdFactory(SIdFactory fac) {
		m_map  = new HashMap<SId, MCSId>();
		
		m_fac = fac;
	}
	
	public SIdFactory getSIdFac() {
	    return m_fac;
	}
	
	public MCSId fromSId(SId sid) {
	    MCSId mcsid = m_map.get(sid);
	    if (null == mcsid) {
	        mcsid = new MCSId(sid);
	        m_map.put(sid, mcsid);
	    }
	    return mcsid;
	}
	
	public MCSId duplicate(MCSId mcsid) {
	  
	    SId sid = mcsid.getSId();
	    Boolean has_effect = mcsid.hasEffect();
	    
	    SId nsid = m_fac.duplicate(sid);
	    
	    MCSId nmcsid = new MCSId(nsid);
	    nmcsid.setEffect(has_effect);
	    
	    m_map.put(nsid, nmcsid);
	    return nmcsid;
	}
	/*
	 * Literal function name would be turned into closure.
	 */
	public IMCValPrim fromIValPrim(IValPrim vp
			, Map<SId, MCSId> map_clo_name
			, Map<SId, MCSId> map_name) {
		if (vp instanceof AtomValue) {
			return new MCAtomValue((AtomValue)vp);
		} else if (vp instanceof SIdUser) {
			SIdUser sid_user = (SIdUser)vp;
			SId sid = sid_user.getSId();
			ISType type = sid.getType();
			if (Aux.isClosure(type) && sid.isUserFun()) {
			    // turn function name into closure
				MCSId closure = map_clo_name.get(sid);
				return closure;
			} else {
				MCSId mcsid = map_name.get(sid);
				if (null == mcsid) {
					mcsid = fromSId(sid);
				}
				return mcsid;
			}
		} else if (vp instanceof SId) {
			SId sid = (SId)vp;
			ISType type = sid.getType();
			if (Aux.isClosure(type) && sid.isUserFun()) {
	            // turn function name into closure
				MCSId closure = map_clo_name.get(sid);
				return closure;
			} else {
				MCSId mcsid = map_name.get(sid);
				if (null == mcsid) {
					mcsid = fromSId(sid);
				}
				return mcsid;
			}
		} else {
			throw new Error(vp + " is not supported.");
		}
	}
	
	public List<IMCValPrim> fromIValPrimList(List<IValPrim> vps
			, Map<SId, MCSId> map_clo_name
			, Map<SId, MCSId> map_name) {
		List<IMCValPrim> mcvps = new ArrayList<IMCValPrim>();
		for (IValPrim vp: vps) {
			IMCValPrim mcvp = fromIValPrim(vp, map_clo_name, map_name);
			mcvps.add(mcvp);
		}
		
		return mcvps;
	}
	
}




