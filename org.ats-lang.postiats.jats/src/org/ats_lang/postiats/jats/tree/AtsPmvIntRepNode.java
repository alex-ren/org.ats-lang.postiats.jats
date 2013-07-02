package org.ats_lang.postiats.jats.tree;

import java.util.Map;

import org.ats_lang.postiats.jats.interpreter.FuncDef;
import org.ats_lang.postiats.jats.type.ATSType;
import org.ats_lang.postiats.jats.type.IntType;
import org.ats_lang.postiats.jats.utils.ATSScope;

public class AtsPmvIntRepNode extends ATSTypeNode {

    public ATSNode m_exp;

    public AtsPmvIntRepNode(ATSNode exp) {
        super(IntType.cType0);
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


