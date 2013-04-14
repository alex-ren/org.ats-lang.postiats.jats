package org.ats_lang.postiats.jats.tree;

import java.util.Map;

import org.ats_lang.postiats.jats.interpreter.FuncDef;
import org.ats_lang.postiats.jats.interpreter.LValueScope;
import org.ats_lang.postiats.jats.type.ATSType;
import org.ats_lang.postiats.jats.value.ATSValue;
import org.ats_lang.postiats.jats.value.StructValue;

public class AtsSelFltRecNode implements ATSNode {
    private ATSNode m_pmv;
    private ATSType m_tyrec;
    private String m_lab;

    public AtsSelFltRecNode(ATSNode pmv, ATSType tyrec, String lab) {
        m_pmv = pmv;
        m_tyrec = tyrec;
        m_lab = lab;
    }
    
    @Override
    // #define ATSselfltrec(pmv, tyrec, lab) ((pmv).lab)
    // example
    
//    typedef
//    struct {
//    atstkind_t0ype(atstype_int) atslab$0; 
//    atstkind_t0ype(atstype_int) atslab$1; 
//    } postiats_tyrec_0 ;
//    
//    atsvoid_t0ype
//    loop_1 (atsrefarg1_type(postiats_tyrec_0) arg0, atstkind_t0ype(atstype_int) arg1)
//    
//    ATSselfltrec(ATSderef(arg0), postiats_tyrec_0, atslab$0)
    public ATSValue evaluate(Map<String, ATSType> types,
            Map<String, FuncDef> funcs, LValueScope scope) {
        ATSValue v = m_pmv.evaluate(types, funcs, scope);
        if (v instanceof StructValue) {
            return ((StructValue)v).getItem(m_lab);
        } else {
            throw new Error("AtsSelFltRecNode on non-structure.");
        }
    }

}
