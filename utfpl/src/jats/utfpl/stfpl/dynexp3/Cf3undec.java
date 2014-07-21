package jats.utfpl.stfpl.dynexp3;

import jats.utfpl.stfpl.Cloc_t;

/*
 * Function definition.
 */
public class Cf3undec {
    public Cloc_t m_loc;
    public Cd3var m_var;  // function name
    public Cd3exp m_def;  // This is a lambda
    
    public Cf3undec(Cloc_t loc, Cd3var var, Cd3exp def) {
        m_loc = loc;
        m_var = var;
        m_def = def;
    }

}
