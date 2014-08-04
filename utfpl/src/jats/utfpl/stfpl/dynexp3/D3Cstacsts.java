package jats.utfpl.stfpl.dynexp3;

import jats.utfpl.stfpl.stype.TNameCst;

import java.util.List;

/*
 * Define constant in statics.
 * Used for abstype, ...
 */
public class D3Cstacsts implements Id3ecl_node {
    public List<TNameCst> m_csts;
    
    public D3Cstacsts(List<TNameCst> csts) {
        m_csts = csts;
    }

}
