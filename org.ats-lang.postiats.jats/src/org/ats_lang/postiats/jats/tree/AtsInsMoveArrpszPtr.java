package org.ats_lang.postiats.jats.tree;

import java.util.Map;

import org.ats_lang.postiats.jats.interpreter.FuncDef;
import org.ats_lang.postiats.jats.type.ATSType;
import org.ats_lang.postiats.jats.type.VoidType;
import org.ats_lang.postiats.jats.utils.ATSScope;

import org.ats_lang.postiats.jats.value.ArrPsz;
import org.ats_lang.postiats.jats.value.Ptrk;
import org.ats_lang.postiats.jats.value.SingletonValue;


public class AtsInsMoveArrpszPtr extends ATSTypeNode {
    public String m_tmp;  // name of the variable
    public ATSNode m_psz;
    
    public AtsInsMoveArrpszPtr(ATSType arrptrty, String tmp, ATSNode psz) {
        super(VoidType.cType);
        m_tmp = tmp;
        m_psz = psz;
        
        // update type
//        ATSType arraytype = psz.getType();
//        if (arraytype instanceof ArrayType) {
//            m_elety = ((ArrayType) arraytype).getInnerType();
//            if (arrptrty instanceof ArrPtrType) {
//                ((ArrPtrType)arrptrty).update(m_elety);
//            } else {
//                throw new Error("Type mismatch");
//            }
//        } else {
//            throw new Error("Type mismatch");
//        }
    }

//    @Override
//    // #define ATSINSmove_arrpsz_ptr(tmp, psz) (tmp = (psz).ptr)
//    // example
////    ATStmpdec(tmp0, atstype_arrpsz) ;
////    ATStmpdec(tmp1, atstype_arrptr) ;
////    ATSINSmove_arrpsz_ptr(tmp1, tmp0) ;
//    
//    // tmp := ArrPtrType
//    // psz := ArrPszType
//    public SingletonValue evaluate(Map<String, ATSType> types,
//            Map<String, FuncDef> funcs, ATSScope<Object> scope) {
//        
//        Object psz = m_psz.evaluate(types, funcs, scope);
//        // m_psz := ArrPszType
//        if (psz instanceof ArrPsz) {
//            Ptrk arrp = ((ArrPsz) psz).getPtr();
//            scope.addValue(m_tmp, arrp);
//            return SingletonValue.VOID;
//        } else {
//            throw new Error("Type mismatch");
//        }
//
//    }
    

    @Override
    public Object accept(ATSTreeVisitor visitor) {
        return visitor.visit(this);
        
    }
    
    
}


