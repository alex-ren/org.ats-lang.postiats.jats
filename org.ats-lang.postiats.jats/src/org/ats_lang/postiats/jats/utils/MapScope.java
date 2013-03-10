package org.ats_lang.postiats.jats.utils;

import java.util.HashMap;
import java.util.Map;

public class MapScope<T> implements ATSScope<T> {
    private MapScope<T> m_parent;
    private Map<String, T> m_vars;

    private MapScope(MapScope<T> p) {
        m_parent = p;
        m_vars = new HashMap<String, T>();
    }
    
    public MapScope() {
        this(null);
    }

    @Override
    public void addValue(String id, T v) {
        m_vars.put(id, v);
    }

    @Override
    public T getValue(String id) {
        if (m_vars.containsKey(id)) {
            return m_vars.get(id);
        } else {
            if (m_parent != null) {
                return m_parent.getValue(id);
            } else {
                System.out.println("MapScope, value not found.");
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
}
