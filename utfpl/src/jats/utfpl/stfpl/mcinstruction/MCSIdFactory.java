package jats.utfpl.stfpl.mcinstruction;

import jats.utfpl.stfpl.ccomp.CCompUtils;
import jats.utfpl.stfpl.instructions.AtomValue;
import jats.utfpl.stfpl.instructions.IValPrim;
import jats.utfpl.stfpl.instructions.SId;
import jats.utfpl.stfpl.instructions.SIdFactory;
import jats.utfpl.stfpl.instructions.SIdUser;
import jats.utfpl.stfpl.mcinstruction.AuxMCIns.AddressAllocator;
import jats.utfpl.stfpl.stype.AuxSType;
import jats.utfpl.stfpl.stype.ISType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MCSIdFactory {
	private Map<SId, MCSId> m_map;
	private SIdFactory m_fac;
	private AddressAllocator m_addr_allocator;
	
	public MCSIdFactory(SIdFactory fac, AddressAllocator alloc) {
		m_map  = new HashMap<SId, MCSId>();
		
		m_fac = fac;
		m_addr_allocator = alloc;
	}
	
	public SIdFactory getSIdFac() {
	    return m_fac;
	}
	
	public MCSId createLocalVar(String name, ISType stype) {
		SId sid = m_fac.createLocalVar(name, stype);
		return fromSId(sid);
	}
	
	public MCSId fromSId(SId sid) {
	    MCSId mcsid = m_map.get(sid);
	    if (null == mcsid) {
	    	if (null == AuxSType.getFunctionType(sid.getType())) {
	    		mcsid = new MCSId(sid, null, false);
	    	} else {
	    		boolean has_effect = false;
	    		// Check Function Name
	    		if (sid.isConstant()) {
	    			if (sid.toStringNoStamp().equals(CCompUtils.cConATSMutexAcquire) || 
	    			    sid.toStringNoStamp().equals(CCompUtils.cConATSMutexRelease) ||
	    			    sid.toStringNoStamp().equals(CCompUtils.cConATSSharedAcquire) ||
	    			    sid.toStringNoStamp().equals(CCompUtils.cConATSSharedRelease) ||
	    			    sid.toStringNoStamp().equals(CCompUtils.cConATSSharedSignal) ||
	    			    sid.toStringNoStamp().equals(CCompUtils.cConATSSharednSignal) ||
	    			    sid.toStringNoStamp().equals(CCompUtils.cConATSSharedBroadcast) ||
	    			    sid.toStringNoStamp().equals(CCompUtils.cConATSSharednBroadcast) ||
	    			    sid.toStringNoStamp().equals(CCompUtils.cConATSSharedCondwait) ||
	    			    sid.toStringNoStamp().equals(CCompUtils.cConATSSharednCondwait)
                    ) {
	    				has_effect = true;
	    			}
	    		}
	    		
	    		mcsid = new MCSId(sid, m_addr_allocator.createPointer(), has_effect);
	    	}
	        
	        m_map.put(sid, mcsid);
	    }
	    return mcsid;
	}

	public MCSId duplicate(MCSId mcsid) {

	    MCSId nmcsid = mcsid.duplicate(m_fac);
    
	    m_map.put(nmcsid.getSId(), nmcsid);
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

			if (AuxSType.isClosure(type) && sid.isUserFun()) {
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
			if (AuxSType.isClosure(type) && sid.isUserFun()) {
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




