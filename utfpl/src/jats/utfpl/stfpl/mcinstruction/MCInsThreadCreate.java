package jats.utfpl.stfpl.mcinstruction;

import jats.utfpl.patcsps.Aux;

public class MCInsThreadCreate implements IMCInstruction {
    public IMCValPrim m_tid;
    public MCSIdUser m_funlab;
    public IMCValPrim m_arg;
    
    public MCInsThreadCreate(IMCValPrim tid, MCSIdUser funlab, IMCValPrim arg) {
        m_tid = tid;
    	m_funlab = funlab;
        m_funlab.updateAddr(Aux.Address.createPointer());
    	m_arg = arg;    	
    }

	@Override
    public Object accept(IMCInsVisitor visitor) {
	    return visitor.visit(this);
    }

	@Override
    public Boolean hasSideEffect() {
	    return true;
    }

}
