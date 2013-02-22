package org.ats_lang.postiats.jats.tree;

import java.util.Map;

import org.ats_lang.postiats.jats.interpreter.ATSScope;
import org.ats_lang.postiats.jats.interpreter.FuncDef;
import org.ats_lang.postiats.jats.type.ATSType;
import org.ats_lang.postiats.jats.value.ATSValue;

public class AtsPmvRefArg implements ATSNode {
    private ATSNode m_node;

    public AtsPmvRefArg(ATSNode node) {
        m_node = node;
    }
    
    @Override
    // #define ATSPMVrefarg0(val) (val)
    // #define ATSPMVrefarg1(ref) (ref)
    // no-op
    public ATSValue evaluate(Map<String, ATSType> types,
            Map<String, FuncDef> funcs, ATSScope scope) {
        return m_node.evaluate(types, funcs, scope);
    }

}
