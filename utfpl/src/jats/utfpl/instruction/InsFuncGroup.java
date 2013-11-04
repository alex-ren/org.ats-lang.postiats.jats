package jats.utfpl.instruction;

import java.util.ArrayList;
import java.util.List;

public class InsFuncGroup implements UtfplInstruction {
	public List<InsFuncDef> m_funLst;

	public InsFuncGroup(List<InsFuncDef> funLst) {
		m_funLst = funLst;
	}
	
	public InsFuncGroup(InsFuncDef fundef) {
		m_funLst = new ArrayList<InsFuncDef>();
		m_funLst.add(fundef);
	}
	
	@Override
	public Object accept(InsVisitor visitor) {
		return visitor.visit(this);
	}

	@Override
	public boolean hasSideEffect() {
		throw new Error("should not be called");
	}

}
