package jats.utfpl.stfpl.dynexp;

import jats.utfpl.utils.Log;

public enum Efunkind {
    FK_fn("fn"), // non-recursive function 
    FK_fnx("fnx"), // tail-recursive 
    FK_fun("fun"), // recursive
    
    FK_prfn("prfn"),
    FK_prfun("prfun"),
    
    FK_praxi("praxi"),
    FK_castfn("castfn");
    
    private String m_str;
    
    private Efunkind(String str) {
        m_str = str;
    }
    
    @Override
    public String toString() {
        return m_str;        
    }

    public static Efunkind fromInt(int knd) {
        Log.log4j.warn("Efunknd takes " + knd + ".");
        return FK_fun;
    }

}

