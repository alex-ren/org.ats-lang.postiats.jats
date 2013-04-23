package org.ats_lang.postiats.jats.tree;

import java.util.Map;

import org.ats_lang.postiats.jats.interpreter.FuncDef;
import org.ats_lang.postiats.jats.type.ATSType;
import org.ats_lang.postiats.jats.type.ArrayType;
import org.ats_lang.postiats.jats.type.VoidType;
import org.ats_lang.postiats.jats.utils.ATSScope;
import org.ats_lang.postiats.jats.value.ATSValue;
import org.ats_lang.postiats.jats.value.Ptrk;
import org.ats_lang.postiats.jats.value.SingletonValue;

public class AtsInsStoreArrpszPtr extends ATSTypeNode {
    private String m_tmp;  // name of the array
    private ATSType m_tyelt;  // Type of the element of the array
    private ATSNode m_asz;  // size of the array
    
    public AtsInsStoreArrpszPtr(ATSScope<ATSType> tyscope, String tmp, ATSType tyelt, ATSNode asz) {
        super(VoidType.cType);
        throw new Error("not supported");
//        m_tmp = tmp;
//        m_tyelt = tyelt;
//        m_asz = asz;
//        
//        ATSType tyarr = tyscope.getValue(tmp);
//        if (tyarr instanceof ArrayType) {
//            ((ArrayType)tyarr).update(tyelt);
//        } else {
//            throw new Error("Wrong Type");
//        }
    }

    @Override
    // #define ATSINSstore_arrpsz_ptr(tmp, tyelt, asz) (tmp.ptr = ATS_MALLOC(asz*sizeof(tyelt)))
    // example
//    typedef void* atstype_arrptr ;
//    typedef atstype_ulint atstype_size ;
//
//    typedef
//    struct {
//      atstype_arrptr ptr ; atstype_size size ;
//    } atstype_arrpsz ;
//    
//    #define atstkind_type(tk) tk
//    #define atstkind_t0ype(tk) tk
//    
//    ATStmpdec(tmp0, atstype_arrpsz) ;
//    ATSINSstore_arrpsz_ptr(tmp0, atstkind_t0ype(atstype_double), 3) ;

    public ATSValue evaluate(Map<String, ATSType> types,
            Map<String, FuncDef> funcs, ATSScope<Object> scope) {
        throw new Error("not supported");
//        Object asz = m_asz.evaluate(types, funcs, scope);
//        
//        if (asz instanceof Ptrk) {
//            asz = ((Ptrk)asz).getValue();
//        }
//        
//        Integer sz = null;
//        
//        if (asz instanceof Integer) {
//            sz = (Integer)asz;
//        } else {
//            throw new Error("Type error");
//        }
//
//        Object [] arr = new Object[sz];
//        for (int i = 0; i < sz; ++i) {
//            arr[i] = m_tyelt.createDefault();
//        }
//        scope.addValue(m_tmp, arr);
//        
//        return SingletonValue.VOID;
    }

}
