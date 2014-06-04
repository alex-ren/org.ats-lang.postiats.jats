package jats.utfpl.utfpl.dynexp;

import java.util.List;

/*
 * 06/04/2014: Currectly it's not exported to json yet.
 */
public class P2Tcon implements Ip2at_node {
	public Epckind m_pcknd;
	public Cd2con m_d2c;
	public int m_npf;
	public List<Cp2at> m_p2ts;
	
	public P2Tcon(Epckind pcknd, Cd2con d2c, int npf, List<Cp2at> p2ts) {
	    m_pcknd = pcknd;
	    m_d2c = d2c;
	    m_npf = npf;
	    m_p2ts = p2ts;
	    
	}

}
