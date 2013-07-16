package org.ats_lang.postiats.jats.tree;

import org.ats_lang.postiats.jats.type.IntType;

public class AtsPmvIntRepNode extends ATSTypeNode {

    public ATSNode m_exp;

    public AtsPmvIntRepNode(ATSNode exp) {
        super(IntType.cType);
        if (exp instanceof ValueNode) {
        } else {
            throw new Error("type mismatch");
        }
        m_exp = exp;
    }
//    
//    @Override
//    public Integer evaluate(Map<String, ATSType> types,
//            Map<String, FuncDef> funcs, ATSScope<Object> scope) {
//        return (Integer)m_exp.evaluate(types, funcs, scope);
//    }


    @Override
    public Object accept(ATSTreeVisitor visitor) {
        return visitor.visit(this);
        
    }
    
    
}


