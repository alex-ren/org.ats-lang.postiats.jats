package jats.utfpl.stfpl.dynexp3;

import jats.utfpl.stfpl.staexp.Cs2cst;

import java.util.List;

/*
 * Define constant in statics.
 * Used for abstype, ...
 */
public class D3Cstacsts implements Id3ecl_node {
    public List<Cs2cst> m_csts;
    
    public D3Cstacsts(List<Cs2cst> csts) {
        m_csts = csts;
    }

}
