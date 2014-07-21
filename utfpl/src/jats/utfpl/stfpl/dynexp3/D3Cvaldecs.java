package jats.utfpl.stfpl.dynexp3;


import jats.utfpl.stfpl.dynexp.Evalkind;

import java.util.List;

public class D3Cvaldecs implements Id3ecl_node {
    public Evalkind m_knd;
    public List<Cv3aldec> m_v3ds;
    
    public D3Cvaldecs(Evalkind knd, List<Cv3aldec> v3ds) {
        m_knd = knd;
        m_v3ds = v3ds;
    }

}
