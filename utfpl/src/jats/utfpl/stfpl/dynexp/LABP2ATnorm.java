package jats.utfpl.stfpl.dynexp;

import jats.utfpl.stfpl.Ilabel;


/*
 * The object of this class represents an item in the record.
 */
public class LABP2ATnorm implements Ilabp2at {
    public Ilabel m_lab;  // The label for the item.
                         // The label can be integer if the record is actually a list.
    public Cp2at m_pat;
    
    public LABP2ATnorm(Ilabel lab, Cp2at pat) {
        m_lab = lab;
        m_pat = pat;
    }

    @Override
    public void normalizeType() {
        m_pat.p2at_node.normalizeType();
    }

	@Override
    public boolean isProof() {
	    return m_pat.p2at_node.isProof();
    }

	@Override
    public boolean isVoid() {
		return m_pat.p2at_node.isVoid();
    }

}
