package jats.utfpl.utfpl.dynexp;


public enum Edcstkind {
    DCK_prfun("DCKprfun"), // extern prfun
    DCK_val("DCKval"),  // extern val
    DCK_fun("DCKfun");  // extern fun
    
    private String m_str;
    
    private Edcstkind(String str) {
        m_str = str;
    }
    
    @Override
    public String toString() {
        return m_str;        
    }

}

