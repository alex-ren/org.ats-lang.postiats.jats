package jats.utfpl.stfpl.dynexp;

import jats.utfpl.stfpl.Cloc_t;

/*
 * Function definition.
 */
public class Cf2undec {
    public Cloc_t f2undec_loc;
    public Cd2var f2undec_var;  // function name
    public Cd2exp f2undec_def;  // This is a lambda
    
    public Cf2undec(Cloc_t loc, Cd2var var, Cd2exp def) {
        f2undec_loc = loc;
        f2undec_var = var;
        f2undec_def = def;
    }
    
    public Cf2undec() {
        // used by gson
    }

}
