package org.ats_lang.postiats.jats.tree;

import java.util.Map;

import org.ats_lang.postiats.jats.interpreter.FuncDef;
import org.ats_lang.postiats.jats.interpreter.ValueScope;
import org.ats_lang.postiats.jats.type.ATSType;
import org.ats_lang.postiats.jats.value.ATSValue;

public class AtsPmvSimpleCastNode implements ATSNode {

    protected ATSType m_type;
    protected ATSNode m_node;
    
    public AtsPmvSimpleCastNode(ATSType type, ATSNode node) {
        m_type = type;
        m_node = node;
    }

    @Override
    // Currently, this is a non-op.
    public ATSValue evaluate(Map<String, ATSType> types,
            Map<String, FuncDef> funcs, ValueScope scope) {
        ATSValue v = m_node.evaluate(types, funcs, scope);
        return v;
    }

}
