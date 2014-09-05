package jats.utfpl.stfpl.dynexp3;

import jats.utfpl.stfpl.Cloc_t;

public class Ci3mpdec {
    public Cloc_t i3mpdec_loc;  // The whole range of the implementation.
    public Cloc_t i3mpdec_locid;  // The range of the id (function name)
    public Cd3cst i3mpdec_cst;  // function name
    public Cd3exp i3mpdec_def;  // function definition
    
    public String m_env_name;
    
    public Ci3mpdec(Cloc_t loc, Cloc_t locid, Cd3cst cst, Cd3exp def, String env_name) {
        i3mpdec_loc = loc;
        i3mpdec_locid = locid;
        i3mpdec_cst = cst;
        i3mpdec_def = def;
        m_env_name = env_name;
    }
}


