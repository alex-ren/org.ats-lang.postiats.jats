package jats.utfpl.stfpl.mcinstruction;

public class MCInsCondRelease implements IMCInstruction {

	public IMCValPrim m_cond;
	
	public MCInsCondRelease(IMCValPrim cond) {
		m_cond = cond;
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

	
