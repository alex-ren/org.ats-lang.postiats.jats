package jats.utfpl.stfpl.dynexp;


public class P2Tpat implements Ip2at_node {
    /* Pattern inside pattern. Seemly, this is weird. My guess is that in the real ATS syntax tree,
     * There should be more stuff in the class. But Hongwei just omitted it. (Maybe it is related to
     * types.) The net result is that we have a Cp2at inside a Cp2at using P2Tpat as a intermediate
     * encapsulation. However the inner Cp2at has a loc information which is smaller than that of the
     * outer Cp2at. Due to the way that we handle loc, it's difficult to return the inner Cp2at as the
     * final result. The same situation can also be seen in the case about how I handle D2ElamSta.
     * 
     */
    public Cp2at m_p2at;
    
    public P2Tpat(Cp2at p2at) {
        m_p2at = p2at;
    }

    @Override
    public void normalizeType() {
        m_p2at.p2at_node.normalizeType();
    }

	@Override
    public boolean isProof() {
		 return m_p2at.p2at_node.isProof(); 
    }

	@Override
    public boolean isVoid() {
		return m_p2at.p2at_node.isVoid(); 
    }

}
