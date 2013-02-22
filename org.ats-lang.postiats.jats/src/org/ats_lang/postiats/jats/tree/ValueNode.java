package org.ats_lang.postiats.jats.tree;

import java.util.Map;

import org.ats_lang.postiats.jats.interpreter.ATSScope;
import org.ats_lang.postiats.jats.interpreter.FuncDef;
import org.ats_lang.postiats.jats.type.ATSType;
import org.ats_lang.postiats.jats.value.ATSValue;

public class ValueNode implements ATSNode {
    private ATSValue m_v;
    
    public ValueNode(ATSValue v) {
        m_v = v;
    }

    @Override
    public ATSValue evaluate(Map<String, ATSType> types, Map<String, FuncDef> funcs, ATSScope scope) {
        return m_v;
    }

}
