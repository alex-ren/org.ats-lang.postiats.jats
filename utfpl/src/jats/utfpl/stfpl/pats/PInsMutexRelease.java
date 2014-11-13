package jats.utfpl.stfpl.pats;

public class PInsMutexRelease implements PIns {
	public PExp m_mutex;
	
	public PInsMutexRelease(PExp mutex) {
		m_mutex = mutex;
	}

	@Override
    public Object accept(PNodeVisitor visitor) {
	    return visitor.visit(this);
    }

}
