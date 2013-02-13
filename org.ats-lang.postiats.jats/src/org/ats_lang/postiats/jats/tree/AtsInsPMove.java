package org.ats_lang.postiats.jats.tree;

import java.util.Map;

import org.ats_lang.postiats.jats.ATSScope;
import org.ats_lang.postiats.jats.FuncDef;
import org.ats_lang.postiats.jats.type.ATSType;
import org.ats_lang.postiats.jats.value.ATSValue;
import org.ats_lang.postiats.jats.value.SingletonValue;

public class AtsInsPMove implements ATSNode {
    private ATSNode m_tmp;
    private ATSType m_hit;
    private ATSNode m_val;
    
    public AtsInsPMove(ATSNode tmp, ATSType hit, ATSNode val) {
        m_tmp = tmp;
        m_hit = hit;
        m_val = val;
    }
    
    @Override
    // #define ATSINSpmove(tmp, hit, val) (*(hit*)tmp = val)
    public ATSValue evaluate(Map<String, ATSType> types, Map<String, FuncDef> funcs, ATSScope scope) {
        if (m_tmp instanceof IdentifierNode) {
            ATSValue val_v = m_val.evaluate(types, funcs, scope);
            ATSValue tmp_ptr = (ATSValue)scope.getValue(((IdentifierNode)m_tmp).getName());
            ATSValue tmp_v = (ATSValue)tmp_ptr.getContent();
            tmp_v.copyfrom(val_v);
            
            return SingletonValue.VOID;
        } else {
            throw new Error("ATSINSpmove: only name is supported now");
        }
    }
    
}
