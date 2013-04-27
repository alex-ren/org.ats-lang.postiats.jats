package org.ats_lang.postiats.jats.tree;

import java.util.Map;

import org.ats_lang.postiats.jats.interpreter.FuncDef;
import org.ats_lang.postiats.jats.type.ATSType;
import org.ats_lang.postiats.jats.utils.ATSScope;

public class ValueNode extends ATSTypeNode {
    private Object m_v;
    
    public ValueNode(ATSType ty, Object v) {
        super(ty);
        m_v = v;
    }

    @Override
    public Object evaluate(Map<String, ATSType> types, Map<String, FuncDef> funcs, ATSScope<Object> scope) {
        return m_v;
    }
}
