package jats.utfpl.stfpl.dynexp3;


import jats.utfpl.stfpl.dynexp.Efunkind;

import java.util.List;
import java.util.Set;

/*
 * Multiple function definition.
 */
public class D3Cfundecs implements Id3ecl_node {
//    public static int s_id = 0;
//    public static int getGroupId() {
//        return ++s_id;
//    }
    
    public Efunkind m_knd;
    public List<Cf3undec> m_f3ds;
    public Set<Cd3var> m_env;  
    
//    public int m_grp_id;
    
    public D3Cfundecs(Efunkind knd, List<Cf3undec> f3ds, Set<Cd3var> env) {
        m_knd = knd;
        m_f3ds = f3ds;
        m_env = env;
    }

}

