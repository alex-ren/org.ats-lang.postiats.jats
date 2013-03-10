package org.ats_lang.postiats.jats.tree;

import java.util.Map;

import org.ats_lang.postiats.jats.interpreter.FuncDef;
import org.ats_lang.postiats.jats.interpreter.ValueScope;
import org.ats_lang.postiats.jats.type.ATSType;
import org.ats_lang.postiats.jats.value.ATSValue;

public class IdentifierNode implements ATSNode {
    private String m_id;
    
    public IdentifierNode(String id) {
        m_id = id;
    }

    public String getName() {
        return m_id;
    }
    
    @Override
    public ATSValue evaluate(Map<String, ATSType> types, Map<String, FuncDef> funcs, ValueScope scope) {
        return scope.getValue(m_id);
    }

}
