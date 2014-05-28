package jats.utfpl.utfpl.dynexp;

import jats.utfpl.utfpl.Cloc_t;

public class Cp2at {

    public Cloc_t p2at_loc;
    public Ip2at_node p2at_node;
    
    public Cp2at(Cloc_t loc, Ip2at_node node) {
        p2at_loc = loc;
        p2at_node = node;
    }
    
    public Cp2at() {
        // used by gson
    }

}
