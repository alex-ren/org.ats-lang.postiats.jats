package org.ats_lang.postiats.jats.tree;

import java.util.Map;

import org.ats_lang.postiats.jats.ATSScope;
import org.ats_lang.postiats.jats.type.ATSType;
import org.ats_lang.postiats.jats.value.ATSValue;
import org.ats_lang.postiats.jats.value.PtrValue;
import org.ats_lang.postiats.jats.value.SingletonValue;

public class AtsInsUpdatePtrInc implements ATSNode {
    private String m_tmp;
    private ATSType m_tyelt;
    
    // #define ATSINSupdate_ptrinc(tmp, tyelt) (tmp = (tyelt*)tmp + 1)
    // example
//    typedef void* atstype_arrptr ;
//    ATStmpdec(tmp1, atstype_arrptr) ;
//    ATSINSupdate_ptrinc(tmp1, atstkind_t0ype(atstype_double)) ;
    public AtsInsUpdatePtrInc(String tmp, ATSType tyelt) {
        m_tmp = tmp;
        m_tyelt = tyelt;

    }

    @Override
    public ATSValue evaluate(Map<String, ATSType> types,
            Map<String, FuncNode> funcs, ATSScope scope) {
        
        ATSValue tmp = scope.getValue(m_tmp);
        if (tmp instanceof PtrValue) {
            ((PtrValue)tmp).incIndex();            
        } else {
            throw new Error("AtsInsUpdatePtrInc on non-ptr value");
        }
        
        return SingletonValue.VOID;
    }

}
