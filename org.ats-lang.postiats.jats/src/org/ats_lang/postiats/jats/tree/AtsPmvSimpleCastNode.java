package org.ats_lang.postiats.jats.tree;

import java.util.Map;

import org.ats_lang.postiats.jats.interpreter.ATSScope;
import org.ats_lang.postiats.jats.interpreter.FuncDef;
import org.ats_lang.postiats.jats.type.ATSType;
import org.ats_lang.postiats.jats.type.PrimType;
import org.ats_lang.postiats.jats.value.ATSValue;
import org.ats_lang.postiats.jats.value.PrimValue;

public class AtsPmvSimpleCastNode implements ATSNode {

    protected ATSType m_type;
    protected ATSNode m_node;
    
    public AtsPmvSimpleCastNode(ATSType type, ATSNode node) {
        m_type = type;
        m_node = node;
    }

    @Override
    public ATSValue evaluate(Map<String, ATSType> types,
            Map<String, FuncDef> funcs, ATSScope scope) {
        ATSValue v = m_node.evaluate(types, funcs, scope);
        if (!(m_type instanceof PrimType)) {
            throw new Error("AtsPmvSimpleCastNode::evaluate, cast to non-primitive type.");
        } else if (!(v instanceof PrimValue)) {
            throw new Error("AtsPmvSimpleCastNode::evaluate, cast from non-primitive type.");
        }
        else {
            return ((PrimType) m_type).castFrom((PrimValue)v);
        }
    }

}
