package org.ats_lang.postiats.jats.tree;

import java.util.Map;

import org.ats_lang.postiats.jats.interpreter.FuncDef;
import org.ats_lang.postiats.jats.type.ATSType;
import org.ats_lang.postiats.jats.type.IntType;
import org.ats_lang.postiats.jats.type.VoidType;
import org.ats_lang.postiats.jats.utils.ATSScope;
import org.ats_lang.postiats.jats.value.ArrPsz;
import org.ats_lang.postiats.jats.value.Ptrk;
import org.ats_lang.postiats.jats.value.SingletonValue;


public class AtsInsStoreArrpszAsz extends ATSTypeNode {
    private String m_tmp;
    private ATSNode m_asz;
    
    public AtsInsStoreArrpszAsz(String tmp, ATSNode asz) {
        super(VoidType.cType);
        m_tmp = tmp;
        m_asz = asz;
    }
    
    @Override
    // #define ATSINSstore_arrpsz_asz(tmp, asz) (tmp.size = asz)
	// example
	//    typedef
	//    struct {
	//      atstype_arrptr ptr ; atstype_size size ;
	//    } atstype_arrpsz ;
	//    ATStmpdec(tmp0, atstype_arrpsz) ;
	//    ATSINSstore_arrpsz_asz(tmp0, 3) ;
    public SingletonValue evaluate(Map<String, ATSType> types, Map<String, FuncDef> funcs, ATSScope<Object> scope) {
        // no-op.
        // Initializatin is done in AtrInsStoreArrpszPtr.
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
//            ((ArrPsz) arrpsz).setSize(sz);
//        } else {
//            throw new Error("Type mismatch");
//        }
//        
        return SingletonValue.VOID;
    }

}
