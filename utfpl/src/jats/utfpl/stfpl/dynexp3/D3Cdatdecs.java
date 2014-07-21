package jats.utfpl.stfpl.dynexp3;

import jats.utfpl.utfpl.staexp.Cs2cst;

import java.util.List;

/*
 * Definition of dataype
 */
public class D3Cdatdecs implements Id3ecl_node {
    public int m_knd;
    public List<Cs2cst> m_s2csts;
    
    public D3Cdatdecs(int knd, List<Cs2cst> s2csts) {
        m_knd = knd;
        m_s2csts = s2csts;
    }

}


