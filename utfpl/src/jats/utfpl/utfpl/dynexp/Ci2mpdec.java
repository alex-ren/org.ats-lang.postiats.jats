package jats.utfpl.utfpl.dynexp;

import jats.utfpl.utfpl.Cloc_t;

public class Ci2mpdec {
    public Cloc_t i2mpdec_loc;  // The whole range of the implementation.
    public Cloc_t i2mpdec_locid;  // The range of the id (function name)
    public Cd2cst i2mpdec_cst;  // function name
    public Cd2exp i2mpdec_def;  // function definition
    
    public Ci2mpdec(Cloc_t loc, Cloc_t locid, Cd2cst cst, Cd2exp def) {
        i2mpdec_loc = loc;
        i2mpdec_locid = locid;
        i2mpdec_cst = cst;
        i2mpdec_def = def;
    }
    
    public Ci2mpdec() {
        // used by gson
    }
}
