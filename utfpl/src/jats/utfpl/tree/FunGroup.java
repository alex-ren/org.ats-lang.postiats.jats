package jats.utfpl.tree;

import java.util.List;

public class FunGroup implements Dec {
	public List<FunDef> m_funLst;
	
	public FunGroup(List<FunDef> funLst) {
		m_funLst = funLst;
	}

	@Override
	public Object accept(TreeVisitor visitor) {
		return visitor.visit(this);
	}

}
