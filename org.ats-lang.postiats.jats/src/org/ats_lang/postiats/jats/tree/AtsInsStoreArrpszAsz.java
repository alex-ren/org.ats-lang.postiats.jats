package org.ats_lang.postiats.jats.tree;

import java.util.Map;

import org.ats_lang.postiats.jats.ATSScope;
import org.ats_lang.postiats.jats.type.ATSType;
import org.ats_lang.postiats.jats.value.ATSValue;

public class AtsInsStoreArrpszAsz implements ATSNode {
    private String m_tmp;
    private ATSNode m_asz;
    
    public AtsInsStoreArrpszAsz(String tmp, ATSNode asz) {
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
    public ATSValue evaluate(Map<String, ATSType> types, Map<String, FuncNode> funcs, ATSScope scope) {
    	ATSValue asz = m_asz.evaluate(types, funcs, scope);
        ATSValue tmp = scope.getValue(m_tmp);
        tmp.updateItem("size", asz);
        return ATSValue.VOID;
    }

}
