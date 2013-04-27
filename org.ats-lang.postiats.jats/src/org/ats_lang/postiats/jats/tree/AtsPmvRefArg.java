package org.ats_lang.postiats.jats.tree;

import java.util.Map;

import org.ats_lang.postiats.jats.interpreter.FuncDef;
import org.ats_lang.postiats.jats.type.ATSType;
import org.ats_lang.postiats.jats.utils.ATSScope;

public class AtsPmvRefArg extends ATSTypeNode {
    private ATSNode m_node;

    public AtsPmvRefArg(ATSNode node) {
        super(node.getType());
        m_node = node;
    }
    
    @Override
    // #define ATSPMVrefarg0(val) (val)
    // #define ATSPMVrefarg1(ref) (ref)
    // no-op
    public Object evaluate(Map<String, ATSType> types,
            Map<String, FuncDef> funcs, ATSScope<Object> scope) {
        return m_node.evaluate(types, funcs, scope);
    }

}
