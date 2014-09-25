package jats.utfpl.stfpl.mcinstruction;

public class MCInsMutexRelease implements IMCInstruction {

	public IMCValPrim m_mutex;
	
	public MCInsMutexRelease(IMCValPrim mutex) {
		m_mutex = mutex;
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
