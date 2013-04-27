package org.ats_lang.postiats.jats.tree;

import java.util.Map;

import org.ats_lang.postiats.jats.interpreter.FuncDef;
import org.ats_lang.postiats.jats.type.ATSType;
import org.ats_lang.postiats.jats.type.PtrkType;
import org.ats_lang.postiats.jats.type.RefType;
import org.ats_lang.postiats.jats.utils.ATSScope;

public class AtsDeref extends ATSTypeNode {
    private ATSNode m_node;

    public AtsDeref(ATSType type, ATSNode node) {
        super(new RefType(type));
        m_node = node;
    }
    
    @Override
    // #define ATSderef(pmv, hit) (*(hit*)pmv)
    // deference shall return ATSValue
    public Object evaluate(Map<String, ATSType> types, Map<String, FuncDef> funcs, ATSScope<Object> scope) {
        Object v = m_node.evaluate(types, funcs, scope);
        return PtrkType.deref(v);
        
    }


}
