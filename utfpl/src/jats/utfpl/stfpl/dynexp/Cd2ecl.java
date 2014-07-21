package jats.utfpl.stfpl.dynexp;

import jats.utfpl.stfpl.Cloc_t;

public class Cd2ecl {
    public Cloc_t d2ecl_loc;
    public Id2ecl_node d2ecl_node;
    
    public Cd2ecl(Cloc_t loc, Id2ecl_node node) {
        d2ecl_loc = loc;
        d2ecl_node = node;
    }
    
    public Cd2ecl() {
        // Used by gson.
    }

}
