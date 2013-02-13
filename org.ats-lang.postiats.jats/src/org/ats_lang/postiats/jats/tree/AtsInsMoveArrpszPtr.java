package org.ats_lang.postiats.jats.tree;

import java.util.Map;

import org.ats_lang.postiats.jats.ATSScope;
import org.ats_lang.postiats.jats.FuncDef;
import org.ats_lang.postiats.jats.type.ATSType;
import org.ats_lang.postiats.jats.value.ATSValue;

public class AtsInsMoveArrpszPtr implements ATSNode {
    private String m_id;
    private ATSNode m_e;
    
    public AtsInsMoveArrpszPtr(String id, ATSNode e) {
        m_id = id;
        m_e = e;
    }

    @Override
    public ATSValue evaluate(Map<String, ATSType> types,
            Map<String, FuncDef> funcs, ATSScope scope) {
        throw new Error("AtsInsMoveArrpszPtr is not supported.");
    }

}
