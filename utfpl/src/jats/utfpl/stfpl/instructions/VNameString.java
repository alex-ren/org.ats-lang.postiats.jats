package jats.utfpl.stfpl.instructions;

import java.util.HashMap;
import java.util.Map;

import jats.utfpl.stfpl.stype.ISType;

public class VNameString implements IVarName {
    private static Map<String, VNameString> s_map = new HashMap<String, VNameString>();
    private ISType m_type;
    private String m_name;

    static VNameString fromString(String name, ISType type) {
        VNameString obj = s_map.get(name);
        if (null != obj) {
            return obj;
        } else {
            obj = new VNameString(name, type);
            s_map.put(name, obj);
            return obj;
        }
    }
    
    private VNameString(String name, ISType type) {
        m_name = name;
        m_type = type;
    }
    
    @Override
    public ISType getType() {
        return m_type;
    }

    @Override
    public String toStringCS() {
        return m_name;
    }

}
