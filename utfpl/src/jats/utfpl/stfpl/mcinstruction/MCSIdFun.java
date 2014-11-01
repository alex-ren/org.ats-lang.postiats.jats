package jats.utfpl.stfpl.mcinstruction;



import jats.utfpl.patcsps.Aux;
import jats.utfpl.stfpl.instructions.SId;
import jats.utfpl.stfpl.stype.ISType;


public class MCSIdFun implements IMCSId {
	private SId m_sid;
	private Aux.Address m_addr;
	private Boolean m_has_effect;

	// Used by factory.
	public MCSIdFun(SId sid) {
		m_sid = sid;
		m_addr = null;
		m_has_effect = null;
	}

    
	public Boolean hasEffect() {
		return m_has_effect;
	}
	
	public void updateEffect(Boolean has_effect) {
		m_has_effect = has_effect;
	}


	@Override
    public ISType getType() {
	    return m_sid.getType();
    }
	

	
}
