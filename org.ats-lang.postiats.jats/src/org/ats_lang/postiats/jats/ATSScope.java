package org.ats_lang.postiats.jats;


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
            lvalue.update(v);
        }
        else if (m_parent != null) {
            m_parent.updateValue(id, v);
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

//    public void assign(String var, TLValue value) {
//        if(resolve(var) != null) {
//            // There is already such a variable, re-assign it
//            this.reAssign(var, value);
//        }
//        else {
//            // A newly declared variable
//            variables.put(var, value);
//        }
//    }
//
//    public Scope copy() {
//        // Create a shallow copy of this scope. Used in case functions are
//        // are recursively called. If we wouldn't create a copy in such cases,
//        // changing the variables would result in changes ro the Maps from
//        // other "recursive scopes".
//        Scope s = new Scope();
//        s.variables = new HashMap<String, TLValue>(this.variables);
//        return s;
//    }
//
//    public boolean isGlobalScope() {
//        return parent == null;
//    }

    public ATSScope parent() {
        return parent;
    }

//    private void reAssign(String identifier, TLValue value) {
//        if(variables.containsKey(identifier)) {
//            // The variable is declared in this scope
//            variables.put(identifier, value);
//        }
//        else if(parent != null) {
//            // The variable was not declared in this scope, so let
//            // the parent scope re-assign it
//            parent.reAssign(identifier, value);
//        }
//    }
//
//    public TLValue resolve(String var) {
//        TLValue value = variables.get(var);
//        if(value != null) {
//            // The variable resides in this scope
//            return value;
//        }
//        else if(!isGlobalScope()) {
//            // Let the parent scope look for the variable
//            return parent.resolve(var);
//        }
//        else {
//            // Unknown variable
//            return null;
//        }
//    }
}

