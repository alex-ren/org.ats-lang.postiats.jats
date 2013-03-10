package org.ats_lang.postiats.jats.tree;

import java.util.Map;

import org.ats_lang.postiats.jats.interpreter.FuncDef;
import org.ats_lang.postiats.jats.interpreter.ValueScope;
import org.ats_lang.postiats.jats.type.ATSType;
import org.ats_lang.postiats.jats.value.ATSValue;
import org.ats_lang.postiats.jats.value.SingletonValue;
import org.ats_lang.postiats.jats.value.StructValue;

public class AtsInsMoveArrpszPtr implements ATSNode {
    private String m_tmp;
    private ATSNode m_psz;
    
    public AtsInsMoveArrpszPtr(String tmp, ATSNode psz) {
        m_tmp = tmp;
        m_psz = psz;
    }

    @Override
    // #define ATSINSmove_arrpsz_ptr(tmp, psz) (tmp = (psz).ptr)
    // example
//    ATStmpdec(tmp0, atstype_arrpsz) ;
//    ATStmpdec(tmp1, atstype_arrptr) ;
//    ATSINSmove_arrpsz_ptr(tmp1, tmp0) ;
    
    public ATSValue evaluate(Map<String, ATSType> types,
            Map<String, FuncDef> funcs, ValueScope scope) {
        ATSValue tmp = scope.getValue(m_tmp);
        ATSValue psz = m_psz.evaluate(types, funcs, scope);
        
        if (psz instanceof StructValue) {
            tmp.copyfrom(((StructValue)psz).getItem("ptr"));
        } else {
            throw new Error("AtsInsMoveArrpszPtr has to be applied on struct");
        }
        
        return SingletonValue.VOID;
        
    }

}
