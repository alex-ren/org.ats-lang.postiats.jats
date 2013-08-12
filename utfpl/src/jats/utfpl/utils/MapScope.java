package jats.utfpl.utils;

import java.util.HashMap;
import java.util.Map;

public class MapScope<T> implements Scope<T> {
    private MapScope<T> m_parent;
    private Map<String, Entry<T> > m_vars;
    private int m_level;

    private MapScope(MapScope<T> p) {
        m_parent = p;
        m_vars = new HashMap<String, Entry<T> >();
        if (null != p) {
            m_level = p.getLevel() + 1;
        } else {
            m_level = 0;
        }
    }
    
    public MapScope() {
        this(null);
    }


    @Override
    public void addValueWithModule(String id, T v, String moduleName) {
        addValue(id, v, moduleName);        
    }
    
    @Override
    public void addValue(String id, T v) {
        addValue(id, v, "");
    }
    
    private void addValue(String id, T v, String moduleName) {
        if (false == updateValue(id, v, moduleName)) {
            m_vars.put(id, new Entry<T>(v, moduleName));
        }
    }
    
    private boolean updateValue(String id, T v, String moduleName) {
        if (m_vars.containsKey(id)) {
            m_vars.put(id, new Entry<T>(v, moduleName));
            return true;
        } else {
            if (null == m_parent) {
                return false;
            } else {
                return m_parent.updateValue(id, v, moduleName);
            }
        }
    }

    @Override
    public Entry<T> getEntry(String id) {
        if (m_vars.containsKey(id)) {
            return m_vars.get(id);
        } else {
            if (m_parent != null) {
                return m_parent.getEntry(id);
            } else {
//                System.out.println("MapScope, value for " + id + " is not found.");
//                throw new Error("MapScope error @" + this);
                return null;
            }
        }
    }

    @Override
    public MapScope<T> getParent() {
        return m_parent;
    }

    @Override
    public MapScope<T> newScope() {
        return new MapScope<T>(this);
        
    }

    @Override
    public T getValue(String id) {
        Entry<T> ent = getEntry(id);
        
        return null != ent? ent.m_val: null;
    }

    @Override
    public int getLevel() {
        return m_level;
    }

}
