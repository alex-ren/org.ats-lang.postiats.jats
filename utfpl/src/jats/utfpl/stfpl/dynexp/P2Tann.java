package jats.utfpl.stfpl.dynexp;

import jats.utfpl.stfpl.staexp.Cs2exp;

public class P2Tann implements Ip2at_node {
    public Cp2at m_p2t;
    public Cs2exp m_ann;
    
    public P2Tann(Cp2at p2t, Cs2exp ann) {
        m_p2t = p2t;
        m_ann = ann;
    }

    @Override
    public void normalizeType() {
        m_p2t.p2at_node.normalizeType();
    }

	@Override
    public boolean isProof() {
	    return m_p2t.p2at_node.isProof();
    }

	@Override
    public boolean isVoid() {

		return m_p2t.p2at_node.isVoid();
    }

}
