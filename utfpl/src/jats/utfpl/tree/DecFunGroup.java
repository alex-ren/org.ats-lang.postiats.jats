package jats.utfpl.tree;

import java.util.List;

public class DecFunGroup extends IDec {
	public List<DecFunDef> m_funLst;
	
	public DecFunGroup(Location loc, List<DecFunDef> funLst) {
	    super(loc);
		m_funLst = funLst;
	}

	@Override
	public Object accept(TreeVisitor visitor) {
		return visitor.visit(this);
	}

}
