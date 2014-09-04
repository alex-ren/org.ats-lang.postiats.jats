package jats.utfpl.stfpl.dynexp3;

import jats.utfpl.stfpl.dynexp.Cd2con;
import jats.utfpl.stfpl.dynexp.Epckind;
import jats.utfpl.stfpl.stype.ISType;

import java.util.List;

/*
 * 06/04/2014: Currectly it's not exported to json yet.
 */
public class P3Tcon implements Ip3at_node {
	public Epckind m_pcknd;
	public Cd2con m_d2c;
	public int m_npf;
	public List<Cp3at> m_p2ts;
	
	public P3Tcon(Epckind pcknd, Cd2con d2c, int npf, List<Cp3at> p2ts) {
	    m_pcknd = pcknd;
	    m_d2c = d2c;
	    m_npf = npf;
	    m_p2ts = p2ts;
	    
	}

    @Override
    public ISType getType() {
        throw new Error("not supported");
    }

}
