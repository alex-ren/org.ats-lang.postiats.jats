package jats.utfpl.stfpl.pats;

import jats.utfpl.stfpl.mcinstruction.MCSId;

import java.util.List;

public class PChannelRecv implements PChannelTransfer {

	public MCSId m_name;
	public List<MCSId> m_eleLst;
	
	public PChannelRecv(MCSId name, List<MCSId> eleLst) {
		m_name = name;
		m_eleLst = eleLst;
	}

	@Override
	public Object accept(PNodeVisitor visitor) {
		return visitor.visit(this);
	}
}


