package jats.utfpl.stfpl.mcinstruction;


public class MCInsThreadCreate implements IMCInstruction {
    public MCSId m_funlab;
    public IMCValPrim m_arg;
    public IMCValPrim m_tid;
    public boolean m_isret;
    
    public MCInsThreadCreate(MCSId funlab
                     , IMCValPrim arg
                     , IMCValPrim tid
                     , boolean isret) {
        m_funlab = funlab;
        if (m_funlab.getAddr() == null) {
        	throw new Error("Effective function should have a pointer.");
        }
        m_arg = arg;
        m_tid = tid;
    	m_isret = isret;	
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
