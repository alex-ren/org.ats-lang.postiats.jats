package jats.utfpl.utfpl.dynexp3;

import jats.utfpl.utfpl.Cloc_t;

public class Cv2aldec {
    public Cloc_t v2aldec_loc;
    public Cp3at v2aldec_pat;
    public Cd3exp v2aldec_def;
    
    public Cv2aldec(Cloc_t loc, Cp3at pat, Cd3exp def) {
        v2aldec_loc = loc;
        v2aldec_pat = pat;
        v2aldec_def = def;
    }
    
    public Cv2aldec() {
        // used by gson
    }

}
