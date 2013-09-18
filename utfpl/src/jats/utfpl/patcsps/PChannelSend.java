package jats.utfpl.patcsps;

import java.util.List;

import jats.utfpl.instruction.TID;

public class PChannelSend implements PChannelTransfer {
	public TID m_name;
	public List<PExp> m_msgLst;
	
	public PChannelSend(TID name, List<PExp> msgLst) {
		m_name = name;
		m_msgLst = msgLst;
	}

	@Override
	public Object accept(PNodeVisitor visitor) {
		return visitor.visit(this);
	}

}
