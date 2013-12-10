package jats.utfpl.tree;

import java.util.List;

public class DecFunGroup implements IDec {
	public List<DecFunDef> m_funLst;
	
	public DecFunGroup(List<DecFunDef> funLst) {
		m_funLst = funLst;
	}

	@Override
	public Object accept(TreeVisitor visitor) {
		return visitor.visit(this);
	}

}
