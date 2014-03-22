package jats.utfpl.utfpl;


public enum Edcstkind {
    DCK_prfun("DCKprfun"), 
    DCK_val("DCKval"),
    DCK_fun("DCKfun");
    
    private String m_str;
    
    private Edcstkind(String str) {
        m_str = str;
    }
    
    @Override
    public String toString() {
        return m_str;        
    }

}

