package jats.utfpl.stfpl.pats;

public class PProcChannel implements PProc {
	public PChannelTransfer m_ch;
	public PProc m_proc;
	
	public PProcChannel(PChannelTransfer ch, PProc proc) {
		m_ch = ch;
		m_proc = proc;
	}

	@Override
	public Object accept(PNodeVisitor visitor) {
		return visitor.visit(this);
	}

}
