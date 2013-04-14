package org.ats_lang.postiats.jats.tree;

import java.util.Map;

import org.ats_lang.postiats.jats.interpreter.FuncDef;
import org.ats_lang.postiats.jats.interpreter.LValueScope;
import org.ats_lang.postiats.jats.type.ATSType;
import org.ats_lang.postiats.jats.value.LValue;
import org.ats_lang.postiats.jats.value.Ptr;

public class AtsDerefNode implements ATSNode {
    private ATSType m_type;
    private ATSNode m_node;

    public AtsDerefNode(ATSType type, ATSNode node) {
        m_type = type;
        m_node = node;
    }
    
    @Override
    // #define ATSderef(pmv, hit) (*(hit*)pmv)
    // deference shall return ATSValue
    public Object evaluate(Map<String, ATSType> types, Map<String, FuncDef> funcs, LValueScope scope) {
        Object v = m_node.evaluate(types, funcs, scope);
        
        if (v instanceof LValue){
            System.out.println("pointer to pointer");
            v = ((LValue)v).getValue();
        }
        
        if (v instanceof Ptr) {
            return ((Ptr)v).deRef(m_type);
        } else {
            throw new Error("Type error.");
        }
    }

}
