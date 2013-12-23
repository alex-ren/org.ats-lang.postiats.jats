package jats.utfpl.instruction;

public class InsCondRelease implements UtfplInstruction {

	public ValPrim m_cond;
	
	public InsCondRelease(ValPrim cond) {
		m_cond = cond;
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

	
