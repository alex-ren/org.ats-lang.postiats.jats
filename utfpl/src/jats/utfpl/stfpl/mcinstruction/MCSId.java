package jats.utfpl.stfpl.mcinstruction;

import jats.utfpl.stfpl.instructions.SId;
import jats.utfpl.stfpl.stype.ISType;

public class MCSId implements IMCValPrim {
	private SId m_sid;
    
    private boolean m_has_effect;
    
    private AuxMCIns.Address m_addr;
    
    // Used by factory.
	public MCSId(SId sid) {
		m_sid = sid;
		m_has_effect = false;
		m_addr = null;
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
    
    public void updateAddr(AuxMCIns.Address addr) {
        m_addr = addr;
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
