package org.ats_lang.postiats.jats.interpreter;


import org.ats_lang.postiats.jats.utils.ATSScope;
import org.ats_lang.postiats.jats.utils.MapScope;
import org.ats_lang.postiats.jats.value.ATSValue;

public class ValueScope implements ATSScope<ATSValue> {
    
    private ATSScope<ATSValue> m_scope;

    public ValueScope() {
        m_scope = new MapScope<ATSValue>();
    }
    
    private ValueScope(ATSScope<ATSValue> p) {
        m_scope = p;
    }
    
    public void updateValue(String id, ATSValue v) {
        ATSValue lvalue = m_scope.getValue(id);
        if (lvalue == null) {
            throw new Error("Variable doesn't exist.");
        } else {
            lvalue.copyfrom(v);
        }
    }

    @Override
    public void addValue(String id, ATSValue v) {
        m_scope.addValue(id, v);
    }

    
    @Override
    public ATSValue getValue(String id) {
        return m_scope.getValue(id);
    }

    @Override
    public ValueScope getParent() {
        ATSScope<ATSValue> parent = m_scope.getParent();
        return new ValueScope(parent);
        
    }

    @Override
    // Caution: v.newScope().getParent() != v
    public ValueScope newScope() {
        return new ValueScope(m_scope.newScope());
    }
}
