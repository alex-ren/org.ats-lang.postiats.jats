package org.ats_lang.postiats.jats.tree;

import org.ats_lang.postiats.jats.ATSScope;
import org.ats_lang.postiats.jats.type.ATSType;
import org.ats_lang.postiats.jats.value.ATSValue;

public class AtsPmvCastFn implements ATSNode {
    protected String m_d2c;
    protected ATSType m_hit;
    protected ATSNode m_arg;
    
    // #define ATSPMVcastfn(d2c, hit, arg) ((hit*)arg)
    // example
    // 
//    typedef void *atstype_ptrk ;
//    ATStmpdec(tmp12$2, atstkind_type(atstype_ptrk)) ;
//    ATSPMVcastfn(cast, atstkind_type(atstype_ptrk), tmp12$2)
    public AtsPmvCastFn(String d2c, ATSType hit, ATSNode arg) {
        m_d2c = d2c;
        m_hit = hit;
        m_arg = arg;
    }
    
    @Override
    public ATSValue evaluate(ATSScope scope) {
        // TODO Auto-generated method stub
        return null;
    }

}
