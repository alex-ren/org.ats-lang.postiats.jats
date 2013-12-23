package jats.utfpl.instruction;

public class InsMutexRelease implements UtfplInstruction {

	public ValPrim m_mutex;
	
	public InsMutexRelease(ValPrim mutex) {
		m_mutex = mutex;
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
