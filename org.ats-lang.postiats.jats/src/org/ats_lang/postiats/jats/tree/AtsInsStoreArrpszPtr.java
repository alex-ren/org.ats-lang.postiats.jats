package org.ats_lang.postiats.jats.tree;

import java.util.Map;

import org.ats_lang.postiats.jats.interpreter.FuncDef;
import org.ats_lang.postiats.jats.type.ATSReferableType;
import org.ats_lang.postiats.jats.type.ATSType;
import org.ats_lang.postiats.jats.type.ArrayType;
import org.ats_lang.postiats.jats.type.IntType;
import org.ats_lang.postiats.jats.type.VoidType;
import org.ats_lang.postiats.jats.utils.ATSScope;
import org.ats_lang.postiats.jats.value.ArrPsz;
import org.ats_lang.postiats.jats.value.Ptrk;
import org.ats_lang.postiats.jats.value.SingletonValue;

public class AtsInsStoreArrpszPtr extends ATSTypeNode {
    public String m_tmp;  // name of the array
    public ATSReferableType m_tyelt;  // Type of the element of the array
    public ATSNode m_asz;  // size of the array
    
    public AtsInsStoreArrpszPtr(ATSType tyarr, String tmp, ATSReferableType tyelt, ATSNode asz) {
        super(VoidType.cType);
        m_tmp = tmp;
        m_tyelt = tyelt;
        m_asz = asz;

//        // update type
//        if (tyarr instanceof ArrPszType) {
//            ((ArrPszType)tyarr).update(tyelt);
//        } else {
//            throw new Error("Wrong Type");
//        }
    }
//
//    @Override
//    // #define ATSINSstore_arrpsz_ptr(tmp, tyelt, asz) (tmp.ptr = ATS_MALLOC(asz*sizeof(tyelt)))
//    // example
////    typedef void* atstype_arrptr ;
////    typedef atstype_ulint atstype_size ;
////
////    typedef
////    struct {
////      atstype_arrptr ptr ; atstype_size size ;
////    } atstype_arrpsz ;
////    
////    #define atstkind_type(tk) tk
////    #define atstkind_t0ype(tk) tk
////    
////    ATStmpdec(tmp0, atstype_arrpsz) ;
////    ATSINSstore_arrpsz_ptr(tmp0, atstkind_t0ype(atstype_double), 3) ;
//
//    
//    public SingletonValue evaluate(Map<String, ATSType> types,
//            Map<String, FuncDef> funcs, ATSScope<Object> scope) {
//        Object asz = m_asz.evaluate(types, funcs, scope);
//               
//        // m_asz := RefType(IntType)
//        if (asz instanceof Ptrk) {
//            asz = ((Ptrk)asz).getValue(IntType.cType0);
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
//        Object arrpsz = scope.getValue(m_tmp);
//        if (arrpsz instanceof ArrPsz) {
//            ((ArrPsz) arrpsz).init(sz, m_tyelt);
//        } else {
//            throw new Error("Type mismatch");
//        }
//        
//        
//        return SingletonValue.VOID;
//    }


    @Override
    public Object accept(ATSTreeVisitor visitor) {
        return visitor.visit(this);
        
    }
}

