package jats.utfpl.stfpl.stype;

import jats.utfpl.utils.Log;

public enum ESort {
    type("type"),
    t0ype("t0ype"),
    vtype("viewtype"),
    vt0ype("viewt0ype"),
    prop("prop"),
    vprop("viewprop"),
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
        } else if (name.equals("viewtype")) {
            return vtype;    
        } else if (name.equals("viewt0ype")) {
            return vt0ype;    
        } else if (name.equals("prop")) {
            return prop;            
        } else if (name.equals("viewprop")) {
            return vprop;          
        } else {
        	Log.log4j.error("unknown sort name " + name);
            return advance;
        }
    }
    
    @Override
    public String toString() {
        return m_str;
    }

}
