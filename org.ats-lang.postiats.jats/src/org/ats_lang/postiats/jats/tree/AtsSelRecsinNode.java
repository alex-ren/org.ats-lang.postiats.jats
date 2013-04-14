package org.ats_lang.postiats.jats.tree;

import java.util.Map;

import org.ats_lang.postiats.jats.interpreter.FuncDef;
import org.ats_lang.postiats.jats.interpreter.LValueScope;
import org.ats_lang.postiats.jats.type.ATSType;
import org.ats_lang.postiats.jats.value.ATSValue;

public class AtsSelRecsinNode implements ATSNode {
    private String m_pmv;
    private ATSType m_type;
    private String m_lab;

    public AtsSelRecsinNode(String pmv, ATSType type, String lab) {
        m_pmv = pmv;
        m_type = type;
        m_lab = lab;
    }

    @Override
    // #define ATSselrecsin(pmv, tyrec, lab) (pmv)
    // example
//    ATStmpdec(tmp16, atstkind_t0ype(atstype_int)) ;
//    ATSselrecsin(tmp16, atstkind_t0ype(atstype_int), atslab$1)
    public ATSValue evaluate(Map<String, ATSType> types,
            Map<String, FuncDef> funcs, LValueScope scope) {
        // TODO Auto-generated method stub
        return scope.getValue(m_pmv);
    }

}
