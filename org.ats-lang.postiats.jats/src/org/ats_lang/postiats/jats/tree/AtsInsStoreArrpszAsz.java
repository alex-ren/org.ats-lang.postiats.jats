package org.ats_lang.postiats.jats.tree;

import java.util.Map;

import org.ats_lang.postiats.jats.interpreter.FuncDef;
import org.ats_lang.postiats.jats.type.ATSType;
import org.ats_lang.postiats.jats.type.VoidType;
import org.ats_lang.postiats.jats.utils.ATSScope;
import org.ats_lang.postiats.jats.value.ATSValue;
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
    public ATSValue evaluate(Map<String, ATSType> types, Map<String, FuncDef> funcs, ATSScope<Object> scope) {

        // Do nothing. The real operation is done in AtsInsStoreArrpszPtr.
        return SingletonValue.VOID;
    }

}
