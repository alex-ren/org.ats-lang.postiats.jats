package org.ats_lang.postiats.jats.tree;

import java.util.Map;

import org.ats_lang.postiats.jats.interpreter.FuncDef;
import org.ats_lang.postiats.jats.type.ATSType;
import org.ats_lang.postiats.jats.type.ArrPtrType;
import org.ats_lang.postiats.jats.type.VoidType;
import org.ats_lang.postiats.jats.utils.ATSScope;
import org.ats_lang.postiats.jats.value.ArrPtr;
import org.ats_lang.postiats.jats.value.SingletonValue;

public class AtsInsUpdatePtrInc extends ATSTypeNode {
    private String m_tmp;
    private ATSType m_ty;  // type of m_tmp
    private ATSType m_tyelt;
    
    // #define ATSINSupdate_ptrinc(tmp, tyelt) (tmp = (tyelt*)tmp + 1)
    // example
//    typedef void* atstype_arrptr ;
//    ATStmpdec(tmp1, atstype_arrptr) ;
//    ATSINSupdate_ptrinc(tmp1, atstkind_t0ype(atstype_double)) ;
    
    // ty = ArrPtrType
    public AtsInsUpdatePtrInc(ATSType ty, String tmp, ATSType tyelt) {
        super(VoidType.cType);
        m_tmp = tmp;
        m_ty = ty;
        m_tyelt = tyelt;
        if (m_ty instanceof ArrPtrType) {
            
        } else {
            throw new Error("not supported");
        }

    }

    @Override
    public SingletonValue evaluate(Map<String, ATSType> types,
            Map<String, FuncDef> funcs, ATSScope<Object> scope) {
        
        Object tmp = scope.getValue(m_tmp);
        
        if (tmp instanceof ArrPtr) {
            ((ArrPtr)tmp).inc();            
        } else {
            throw new Error("AtsInsUpdatePtrInc on non-ptr value");
        }
        
        return SingletonValue.VOID;
    }

}
