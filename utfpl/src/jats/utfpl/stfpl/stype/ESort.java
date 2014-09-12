package jats.utfpl.stfpl.stype;

public enum ESort {
    type("type"),
    t0ype("t0ype"),
    prop("prop"),
    advance("advance");
    
    private String m_str;
    
    private ESort(String str) {
        m_str = str;
    }
    
    static public ESort fromString(String name) {
        if (name.equals("type")) {
            return type;            
        } else if (name.equals("t0ype")) {
            return t0ype;            
        } else if (name.equals("prop")) {
            return prop;            
        } else {
            return advance;
        }
    }

}
