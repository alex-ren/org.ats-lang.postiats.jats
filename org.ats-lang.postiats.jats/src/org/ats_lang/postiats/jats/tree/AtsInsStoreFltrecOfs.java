package org.ats_lang.postiats.jats.tree;

import java.util.Map;

import org.ats_lang.postiats.jats.interpreter.FuncDef;
import org.ats_lang.postiats.jats.type.ATSType;
import org.ats_lang.postiats.jats.type.VoidType;
import org.ats_lang.postiats.jats.utils.ATSScope;
import org.ats_lang.postiats.jats.value.ATSValue;
import org.ats_lang.postiats.jats.value.SingletonValue;

public class AtsInsStoreFltrecOfs extends ATSTypeNode {
    private String m_tmp;  // name of the structure
    private String m_lab;  // name of the member
    private ATSType m_tyrec;  // type of the structure
    private ATSNode m_val;  // value of the member
    
    
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
        super(VoidType.cType);
        m_tmp = tmp;
        m_tyrec = tyrec;
        m_lab = lab;
        m_val = val;
    }

    @Override
    public Object evaluate(Map<String, ATSType> types,
            Map<String, FuncDef> funcs, ATSScope<Object> scope) {
        ATSValue v = m_val.evaluate(types, funcs, scope);
        
        StructValue tmp = (StructValue)scope.getValue(m_tmp);
        tmp.updateItem(m_lab, v);
        
        return SingletonValue.VOID;        
    }

}
