package org.ats_lang.postiats.jats.tree;

import java.util.Map;

import org.ats_lang.postiats.jats.interpreter.FuncDef;
import org.ats_lang.postiats.jats.type.ATSType;
import org.ats_lang.postiats.jats.utils.ATSScope;

public class AtsSelRecsinNode extends ATSTypeNode {
    public String m_pmv;
    public ATSType m_type;
    public String m_lab;

    public AtsSelRecsinNode(String pmv, ATSType type, String lab) {
        super(type); 
        m_pmv = pmv;
        m_type = type;
        m_lab = lab;
    }
//
//    @Override
//    // #define ATSselrecsin(pmv, tyrec, lab) (pmv)
//    // example
////    ATStmpdec(tmp16, atstkind_t0ype(atstype_int)) ;
////    ATSselrecsin(tmp16, atstkind_t0ype(atstype_int), atslab$1)
//    public Object evaluate(Map<String, ATSType> types,
//            Map<String, FuncDef> funcs, ATSScope<Object> scope) {
//        return scope.getValue(m_pmv);
//    }


    @Override
    public Object accept(ATSTreeVisitor visitor) {
        return visitor.visit(this);
        
    }
    
    
}


