package jats.utfpl.instruction;

import java.util.ArrayList;
import java.util.List;

public class FuncGroupIns implements UtfplInstruction {
	public List<FuncDefIns> m_funLst;

	public FuncGroupIns(List<FuncDefIns> funLst) {
		m_funLst = funLst;
	}
	
	public FuncGroupIns(FuncDefIns fundef) {
		m_funLst = new ArrayList<FuncDefIns>();
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
