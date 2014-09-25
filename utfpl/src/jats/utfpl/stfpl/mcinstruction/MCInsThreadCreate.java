package jats.utfpl.stfpl.mcinstruction;

import jats.utfpl.patcsps.Aux;

public class MCInsThreadCreate implements IMCInstruction {
    public IMCValPrim m_tid;
    public MCSIdUser m_funlab;
    public IMCValPrim m_args;
    
    public MCInsThreadCreate(IMCValPrim tid, MCSIdUser funlab, IMCValPrim args) {
        m_tid = tid;
    	m_funlab = funlab;
        m_funlab.updateAddr(Aux.Address.createPointer());
    	m_args = args;    	
    }

	@Override
    public Object accept(InsVisitor visitor) {
	    return visitor.visit(this);
    }

	@Override
    public Boolean hasSideEffect() {
	    return true;
    }

}
