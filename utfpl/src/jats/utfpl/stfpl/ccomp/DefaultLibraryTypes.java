package jats.utfpl.stfpl.ccomp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import jats.utfpl.stfpl.Csymbol;
import jats.utfpl.stfpl.staexp.FUNCLOfun;
import jats.utfpl.stfpl.stype.BoolType;
import jats.utfpl.stfpl.stype.FunType;
import jats.utfpl.stfpl.stype.ISType;
import jats.utfpl.stfpl.stype.VoidType;

public class DefaultLibraryTypes {
    static private Map<String, ISType> m_map = new HashMap<String, ISType>();  // true_bool";
    static {
        m_map.put("true_bool", BoolType.cInstance);
        m_map.put("false_bool", BoolType.cInstance);
        
        FunType main_type = new FunType(
                -1/*no proof*/, 
                new ArrayList<ISType>(), 
                VoidType.cInstance,
                FUNCLOfun.cInstance,
                1 /*has effect*/);
                
        m_map.put("main_void_0", main_type);

    }
    
    static public ISType queryType(Csymbol sym) {
        return m_map.get(sym.m_str);
    }
}
