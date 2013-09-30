package jats.utfpl.patcsps;

import jats.utfpl.instruction.TID;

import java.util.List;

public class PChannelRecv implements PChannelTransfer {

	public TID m_name;
	public List<TID> m_eleLst;
	
	public PChannelRecv(TID name, List<TID> eleLst) {
		m_name = name;
		m_eleLst = eleLst;
	}

	@Override
	public Object accept(PNodeVisitor visitor) {
		return visitor.visit(this);
	}
}


