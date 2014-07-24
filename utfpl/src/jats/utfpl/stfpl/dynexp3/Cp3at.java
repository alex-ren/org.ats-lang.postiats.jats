package jats.utfpl.stfpl.dynexp3;

import jats.utfpl.stfpl.Cloc_t;

public class Cp3at {

    public Cloc_t m_loc;
    public Ip3at_node m_node;
    
    public Cp3at(Cloc_t loc, Ip3at_node node) {
        m_loc = loc;
        m_node = node;
    }
    
//    /*
//     * This function may return null.
//     */
//    public Cd3var getCd3var() {
//        if (m_node instanceof P3Tvar) {
//            return ((P3Tvar)m_node).m_var;
//        } else {
//            return null;
//        }
//        
//    }

}
