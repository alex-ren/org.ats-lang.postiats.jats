package jats.utfpl.utfpl.dynexp;

public enum Edcstkind {
    
    DCK_fun("DCKfun"),  // extern fun
    DCK_val("DCKval"),  // extern val

    DCK_praxi("DCKpraxi"),  // praxi
    DCK_prfun("DCKprfun"), // extern prfun
    
    DCK_prval("DCKprval"),  // extern prval
    DCK_castfn("DCKcastfn");  // extern castfn
    
    
    private String m_str;
    
    private Edcstkind(String str) {
        m_str = str;
    }
    
    @Override
    public String toString() {
        return m_str;        
    }

}

