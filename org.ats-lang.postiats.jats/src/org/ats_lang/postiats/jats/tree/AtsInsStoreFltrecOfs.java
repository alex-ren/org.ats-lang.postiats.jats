package org.ats_lang.postiats.jats.tree;

import java.util.Map;

import org.ats_lang.postiats.jats.ATSScope;
import org.ats_lang.postiats.jats.FuncDef;
import org.ats_lang.postiats.jats.type.ATSType;
import org.ats_lang.postiats.jats.value.ATSValue;
import org.ats_lang.postiats.jats.value.SingletonValue;
import org.ats_lang.postiats.jats.value.StructValue;

public class AtsInsStoreFltrecOfs implements ATSNode {
    private String m_tmp;
    private String m_lab;
    private ATSType m_tyrec;
    private ATSNode m_val;
    
    
    // #define ATSINSstore_fltrec_ofs(tmp, tyrec, lab, val) ((tmp).lab = val)
    // example
//    typedef
//    struct {
//    atstkind_t0ype(atstype_int) atslab$0; 
//    atstkind_t0ype(atstype_int) atslab$1; 
//    } postiats_tyrec_0 ;
//    
//    ATStmpdec(tmp11, postiats_tyrec_0) ;
//    
//    ATSINSstore_fltrec_ofs (tmp11, postiats_tyrec_0, atslab$0, ATSPMVi0nt(0)) ;

    public AtsInsStoreFltrecOfs(String tmp, ATSType tyrec, String lab, ATSNode val) {
        m_tmp = tmp;
        m_tyrec = tyrec;
        m_lab = lab;
        m_val = val;
    }

    @Override
    public ATSValue evaluate(Map<String, ATSType> types,
            Map<String, FuncDef> funcs, ATSScope scope) {
        ATSValue v = m_val.evaluate(types, funcs, scope);
        
        StructValue tmp = (StructValue)scope.getValue(m_tmp);
        tmp.updateItem(m_lab, v);
        
        return SingletonValue.VOID;        
    }

}
