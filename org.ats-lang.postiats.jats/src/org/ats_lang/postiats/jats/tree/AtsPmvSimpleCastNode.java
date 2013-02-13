package org.ats_lang.postiats.jats.tree;

import java.util.Map;

import org.ats_lang.postiats.jats.ATSScope;
import org.ats_lang.postiats.jats.FuncDef;
import org.ats_lang.postiats.jats.type.ATSType;
import org.ats_lang.postiats.jats.type.CharType;
import org.ats_lang.postiats.jats.type.F0loatType;
import org.ats_lang.postiats.jats.type.FloatType;
import org.ats_lang.postiats.jats.type.I0ntType;
import org.ats_lang.postiats.jats.type.IntType;
import org.ats_lang.postiats.jats.type.StringType;
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
        Object iv = v.getContent();
        if (IntType.cType == m_type || I0ntType.cType == m_type) {
            return new PrimValue((Integer)iv);
        } else if (FloatType.cType == m_type || F0loatType.cType == m_type) {
            return new PrimValue((Float)iv);
        } else if (CharType.cType == m_type) {
            return new PrimValue((Character)iv);
        } else if (StringType.cType == m_type) {
            return new PrimValue((String)iv);
        } else {
            throw new Error("unsupported type cast");
        }
    }

}
