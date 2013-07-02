package org.ats_lang.postiats.jats.tree;

import java.util.Map;

import org.ats_lang.postiats.jats.interpreter.FuncDef;
import org.ats_lang.postiats.jats.type.ATSType;
import org.ats_lang.postiats.jats.utils.ATSScope;

public class AtsSelArrInd extends ATSTypeNode {
    public ATSNode m_node;
    public ATSType m_type;
    public String m_lab;

    public AtsSelArrInd(ATSNode node, ATSType type, String lab) {
        super(null);
        throw new Error("not supported");
//        m_node = node;
//        m_type = type;
//        m_lab = lab;
    }

//
//    @Override
//    // #define ATSselarrind(pmv, tyarr, lab) (((tyarr)pmv).lab)
//    // example
//    public Object evaluate(Map<String, ATSType> types,
//            Map<String, FuncDef> funcs, ATSScope<Object> scope) {
//        throw new Error("not supported");
////        return null;
//    }
    

    @Override
    public Object accept(ATSTreeVisitor visitor) {
        return visitor.visit(this);
        
    }
    
}


