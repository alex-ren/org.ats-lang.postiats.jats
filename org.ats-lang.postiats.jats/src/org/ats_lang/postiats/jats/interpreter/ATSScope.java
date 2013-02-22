package org.ats_lang.postiats.jats.interpreter;


import java.util.HashMap;
import java.util.Map;

import org.ats_lang.postiats.jats.value.ATSValue;

public class ATSScope {

    private ATSScope m_parent;
    private Map<String, ATSValue> m_vars;

    public ATSScope() {
        // only for the global scope, the parent is null
        this(null);
    }

    public ATSScope(ATSScope p) {
        m_parent = p;
        m_vars = new HashMap<String, ATSValue>();
    }
    
    public void addValue(String id, ATSValue v) {
        m_vars.put(id, v);   
    }
    
    public void updateValue(String id, ATSValue v) {
        if (m_vars.containsKey(id)) {
            ATSValue lvalue = m_vars.get(id);
            lvalue.copyfrom(v);
        }
        else if (m_parent != null) {
            m_parent.updateValue(id, v);
        } else {
        	throw new Error("Variable doesn't exist.");
        }
    }
    
    public ATSValue getValue(String id) {
        if (m_vars.containsKey(id)) {
            return m_vars.get(id);
        } else {
            if (m_parent != null) {
                return m_parent.getValue(id);
            } else {
                System.out.println("ATSScope, value not found.");
                return null;
            }
        }
    }

    public ATSScope parent() {
        return m_parent;
    }

}

