package jats.utfpl.utfpl.dynexp;

import jats.utfpl.utfpl.Csymbol;

public class Cd2sym {
    public Csymbol d2sym_name;
    
    public Csymbol getSymbol() {
        return d2sym_name;
    }
    
    public Cd2sym() {
        // used by gson
    }
}
