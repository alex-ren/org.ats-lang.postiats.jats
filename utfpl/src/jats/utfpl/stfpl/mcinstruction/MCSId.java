package jats.utfpl.stfpl.mcinstruction;

import jats.utfpl.stfpl.instructions.SId;
import jats.utfpl.stfpl.instructions.SIdFactory;
import jats.utfpl.stfpl.stype.AuxSType;
import jats.utfpl.stfpl.stype.ISType;

public class MCSId implements IMCValPrim {
	private SId m_sid;
	
	// The following three properties are related to functions.
    private AuxMCIns.Address m_addr;
    private boolean m_has_effect;
    private boolean m_isthread;
    
    // Used by factory.
	public MCSId(SId sid, AuxMCIns.Address addr) {
		
		m_sid = sid;
		m_addr = addr;
		
		if (null == addr && null != AuxSType.getFunctionType(sid.getType())) {
			throw new Error("Function must have address.");
		}
		
		
		m_has_effect = false;
		m_isthread = false;
	}
	
	private MCSId(SId sid, AuxMCIns.Address addr, 
			boolean has_effect, boolean isthread) {
		m_sid = sid;
		m_addr = addr;
		m_has_effect = has_effect;
		m_isthread = isthread;
	}
		
	// Used by factory
	public MCSId duplicate(SIdFactory fac) {
		SId nsid = fac.duplicate(m_sid);
		return new MCSId(nsid, m_addr, m_has_effect, m_isthread);
	}
	
	// Used by factory.
	public SId getSId() {
	    return m_sid;
	}
	
    public boolean hasAddress() {
        return null != m_addr;
    }
    
    public AuxMCIns.Address getAddr() {
        return m_addr;
    }
    
    public void setAsThread() {
        m_isthread = true;
    }
    
    public boolean isThread() {
    	return m_isthread;
    }
    

    public boolean hasEffect() {
        return m_has_effect;
    }
    
    public void setEffect(boolean has_effect) {
        m_has_effect = has_effect;
    }
	
    
    public boolean isRet() {
        return m_sid.isRetHolder();
    }
    

	@Override
	public String toStringMCIns() {
		return m_sid.toStringIns();
	}
	
	@Override
    public ISType getType() {
	    return m_sid.getType();
    }

}
