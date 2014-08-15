package jats.utfpl.stfpl.dynexp3;

import jats.utfpl.stfpl.Cloc_t;

public class Ci3mpdec {
    public Cloc_t i2mpdec_loc;  // The whole range of the implementation.
    public Cloc_t i2mpdec_locid;  // The range of the id (function name)
    public Cd3cst i2mpdec_cst;  // function name
    public Cd3exp i2mpdec_def;  // function definition
    
    public String m_env_name;
    
    public Ci3mpdec(Cloc_t loc, Cloc_t locid, Cd3cst cst, Cd3exp def, String env_name) {
        i2mpdec_loc = loc;
        i2mpdec_locid = locid;
        i2mpdec_cst = cst;
        i2mpdec_def = def;
        m_env_name = env_name;
    }
}


