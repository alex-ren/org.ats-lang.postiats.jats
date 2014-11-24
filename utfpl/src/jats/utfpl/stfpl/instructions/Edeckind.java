package jats.utfpl.stfpl.instructions;

import jats.utfpl.stfpl.dynexp.Edcstkind;
import jats.utfpl.stfpl.dynexp.Efunkind;
import jats.utfpl.stfpl.dynexp.Evalkind;

public enum Edeckind {
    DEC_fn("DECfn"),  // non-recursive function 
    DEC_fnx("DECfnx"),  // tail-recursive 
    DEC_fun("DECfun"),  // recursive
    DEC_castfn("DECcastfn"),
    
    DEC_val("DECval"),
    DEC_prval("DECprval");

    private String m_str;
    
    private Edeckind(String str) {
        m_str = str;
    }
    
    static public Edeckind fromEfunkind(Efunkind funknd) {
        switch(funknd)
        {
        case FK_fn:
            return DEC_fn;
        case FK_fnx:
            return DEC_fnx;
        case FK_fun:
            return DEC_fun;
        case FK_castfn:
            return DEC_castfn;
        case FK_prfn:  // accept prfun
        	return DEC_fn;
        case FK_prfun:  // accept prfun
        	return DEC_fun;
        default:
            throw new Error("not supported");
        }

    }
    
    static public Edeckind fromEcstkind(Edcstkind cstknd) {
        switch(cstknd)
        {
        case DCK_fun:
            return DEC_fun;
        case DCK_prfun:
            return DEC_fun;
        case DCK_val:
            return DEC_val;
        case DCK_castfn:
            return DEC_castfn;
        default:
            throw new Error("not supported " + cstknd);
        }

    }
    
    
    static public Edeckind fromEvalkind(Evalkind cstknd) {
        switch(cstknd)
        {
        case VK_val:
            return DEC_val;
        case VK_val_pos:
            return DEC_val;
        case VK_val_neg:
            return DEC_val;
        case VK_prval:
            return DEC_prval;
        default:
            throw new Error("not supported");
        }

    }
    
    @Override
    public String toString() {
        return m_str;        
    }
}


