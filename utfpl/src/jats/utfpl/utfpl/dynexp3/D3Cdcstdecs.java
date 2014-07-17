package jats.utfpl.utfpl.dynexp3;


import jats.utfpl.utfpl.dynexp.Edcstkind;

import java.util.List;

// function declaration, value declaration
public class D3Cdcstdecs implements Id3ecl_node {
    public int m_knd;
    public Edcstkind m_dck;
    public List<Cd3cst> m_d3cst;
    
    public D3Cdcstdecs(int knd, Edcstkind dck, List<Cd3cst> d3cst) {
        m_knd = knd;
        m_dck = dck;
        m_d3cst = d3cst;
    }
}
