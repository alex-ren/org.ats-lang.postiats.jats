package jats.utfpl.instruction;

import jats.utfpl.patcsps.Aux;

public class InsThreadCreate implements UtfplInstruction {
    public ValPrim m_tid;
    public TID m_funlab;
    public ValPrim m_args;
    
    public InsThreadCreate(ValPrim tid, TID funlab, ValPrim args) {
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
