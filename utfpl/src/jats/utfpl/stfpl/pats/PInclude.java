package jats.utfpl.stfpl.pats;

public class PInclude implements PNode {
	public String m_path;
	
	public PInclude(String path) {
		m_path = path;
	}

	@Override
	public Object accept(PNodeVisitor visitor) {
		return visitor.visit(this);
	}

}
