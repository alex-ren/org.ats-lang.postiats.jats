package jats.utfpl.utfpl.ccomp;

import java.util.HashMap;
import java.util.Map;

import jats.utfpl.utfpl.Csymbol;
import jats.utfpl.utfpl.stype.BoolType;
import jats.utfpl.utfpl.stype.ISType;

public class DefaultLibraryTypes {
    static private Map<String, ISType> m_map = new HashMap<String, ISType>();  // true_bool";
    static {
        m_map.put("true_bool", BoolType.cInstance);
        m_map.put("false_bool", BoolType.cInstance);
    }
    
    static public ISType queryType(Csymbol sym) {
        return m_map.get(sym.m_str);
    }

}
