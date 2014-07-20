package jats.utfpl.utfpl.dynexp3;


import jats.utfpl.utfpl.dynexp.Evalkind;

import java.util.List;

public class D3Cvaldecs implements Id3ecl_node {
    public Evalkind m_knd;
    public List<Cv3aldec> m_v3ds;
    
    public D3Cvaldecs(Evalkind knd, List<Cv3aldec> v3ds) {
        m_knd = knd;
        m_v3ds = v3ds;
    }

}
