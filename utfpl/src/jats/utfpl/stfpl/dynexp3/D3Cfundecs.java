package jats.utfpl.stfpl.dynexp3;


import jats.utfpl.stfpl.dynexp.Efunkind;

import java.util.List;

/*
 * Multiple function definition.
 */
public class D3Cfundecs implements Id3ecl_node {
    public Efunkind m_knd;
    public List<Cf3undec> m_f3ds;
    
    public D3Cfundecs(Efunkind knd, List<Cf3undec> f3ds) {
        m_knd = knd;
        m_f3ds = f3ds;
    }

}

