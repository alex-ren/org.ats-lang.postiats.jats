package org.ats_lang.postiats.jats.tree;

import java.util.Map;

import org.ats_lang.postiats.jats.interpreter.FuncDef;
import org.ats_lang.postiats.jats.type.ATSType;
import org.ats_lang.postiats.jats.type.ArrPtrType;
import org.ats_lang.postiats.jats.type.VoidType;
import org.ats_lang.postiats.jats.utils.ATSScope;

import org.ats_lang.postiats.jats.value.ArrPtr;
import org.ats_lang.postiats.jats.value.SingletonValue;


public class AtsInsMoveArrpszPtr extends ATSTypeNode {
    private String m_tmp;
    private ATSNode m_psz;
    
    public AtsInsMoveArrpszPtr(ATSType ty, String tmp, ATSNode psz) {
        super(VoidType.cType);
        if (ty instanceof ArrPtrType) {
        } else {
            throw new Error("type mismatch");
        }
        
        m_tmp = tmp;
        m_psz = psz;
    }

    @Override
    // #define ATSINSmove_arrpsz_ptr(tmp, psz) (tmp = (psz).ptr)
    // example
//    ATStmpdec(tmp0, atstype_arrpsz) ;
//    ATStmpdec(tmp1, atstype_arrptr) ;
//    ATSINSmove_arrpsz_ptr(tmp1, tmp0) ;
    
    // tmp := ArrayType
    // psz := ArrayType
    public SingletonValue evaluate(Map<String, ATSType> types,
            Map<String, FuncDef> funcs, ATSScope<Object> scope) {
        
        Object psz = m_psz.evaluate(types, funcs, scope);
        // m_psz := ArrayType
        if (psz instanceof Object[]) {
            ArrPtr.ArrayElement arre = new ArrPtr.ArrayElement((Object[])psz, 0);
            ArrPtr arrp = new ArrPtr(arre);
            scope.addValue(m_tmp, arrp);
            return SingletonValue.VOID;
        } else {
            throw new Error("Type mismatch");
        }

    }
}
