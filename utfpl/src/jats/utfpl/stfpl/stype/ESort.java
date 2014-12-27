package jats.utfpl.stfpl.stype;

import jats.utfpl.utils.Log;

public enum ESort {
    type("type"),
    t0ype("t0ype"),
    vtype("viewtype"),
    vt0ype("viewt0ype"),
    prop("prop"),
//    vprop("viewprop"),
    view("view"),
    advance("advance");
    
    private String m_str;
    
    private ESort(String str) {
        m_str = str;
    }
    
    static public ESort fromString(String name) {
        if (name.equals("type")) {
            return type;            
        } else if (name.equals("t0ype") || name.equals("t@ype")) {
            return t0ype;      
        } else if (name.equals("viewtype")) {
            return vtype;    
        } else if (name.equals("viewt0ype") || name.equals("viewt@ype")) {
            return vt0ype;    
        } else if (name.equals("prop")) {
            return prop;            
//        } else if (name.equals("viewprop")) {
//            return vprop;      
        } else if (name.equals("view")) {
            return view;                    
        } else {
        	Log.log4j.warn("unknown sort name " + name);
            return advance;
        }
    }
    
    @Override
    public String toString() {
        return m_str;
    }

}
