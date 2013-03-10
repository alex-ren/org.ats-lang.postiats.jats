package org.ats_lang.postiats.jats.tree;

import java.util.Map;

import org.ats_lang.postiats.jats.interpreter.FuncDef;
import org.ats_lang.postiats.jats.interpreter.ValueScope;
import org.ats_lang.postiats.jats.type.ATSType;
import org.ats_lang.postiats.jats.type.ArrayType;
import org.ats_lang.postiats.jats.value.ATSValue;
import org.ats_lang.postiats.jats.value.ArrayValue;
import org.ats_lang.postiats.jats.value.PtrValue;
import org.ats_lang.postiats.jats.value.SingletonValue;
import org.ats_lang.postiats.jats.value.StructValue;

public class AtsInsStoreArrpszPtr implements ATSNode {
    private String m_tmp;
    private ATSType m_tyelt;
    private ATSNode m_asz;
    
    public AtsInsStoreArrpszPtr(String tmp, ATSType tyelt, ATSNode asz) {
        m_tmp = tmp;
        m_tyelt = tyelt;
        m_asz = asz;
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
            Map<String, FuncDef> funcs, ValueScope scope) {
        
        ATSValue asz = m_asz.evaluate(types, funcs, scope);
        int len = (Integer)asz.getContent();  // must be an integer

        ArrayType arr_type = new ArrayType(m_tyelt, len);
        ArrayValue arr_v = arr_type.createDefault();
        PtrValue arr_p = new PtrValue(arr_v);
        
        StructValue tmp = (StructValue)scope.getValue(m_tmp);
        tmp.updateItem("ptr", arr_p);
        return SingletonValue.VOID;
    }

}
