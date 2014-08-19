package jats.utfpl.stfpl.dynexp3;

import java.util.List;

import jats.utfpl.stfpl.Cloc_t;
import jats.utfpl.stfpl.stype.ISType;

/*
 * Function definition.
 */
public class Cf3undec {
    public Cloc_t m_loc;
    public Cd3var m_var;  // function name

    public int m_lin;  // something to do with linear type?
    public List<Cp3at> m_p3ts;  // arguments including proof and normal arguments
                                // proof arguments appear first in the list
    
    public Cd3exp m_body;  // body of the function

    public ISType m_type;  // type of the whole function

    /*
     * m_var as well as m_def should contain the same type information.
     * But whether the two ISType's are actually the same object is not
     * clear. (I haven't really checked this.) But it doesn't matter.
     */
    
    public Cf3undec(Cloc_t loc, Cd3var var, int lin, List<Cp3at> p3ts, Cd3exp body, ISType type) {
        m_loc = loc;
        m_var = var;
        
        m_lin = lin;
        m_p3ts = p3ts;
        m_body = body;
        m_type = type;
    }

}
