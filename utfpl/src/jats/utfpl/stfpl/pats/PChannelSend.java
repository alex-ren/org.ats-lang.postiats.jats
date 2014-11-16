package jats.utfpl.stfpl.pats;

import jats.utfpl.stfpl.mcinstruction.MCSId;

import java.util.List;



public class PChannelSend implements PChannelTransfer {
	public MCSId m_name;
	public List<PExp> m_msgLst;
	
	public PChannelSend(MCSId name, List<PExp> msgLst) {
		m_name = name;
		m_msgLst = msgLst;
	}

	@Override
	public Object accept(PNodeVisitor visitor) {
		return visitor.visit(this);
	}

}
