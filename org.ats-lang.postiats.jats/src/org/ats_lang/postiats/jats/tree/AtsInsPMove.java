package org.ats_lang.postiats.jats.tree;

import java.util.Map;

import org.ats_lang.postiats.jats.interpreter.FuncDef;
import org.ats_lang.postiats.jats.type.ATSType;
import org.ats_lang.postiats.jats.type.ArrPtrType;
import org.ats_lang.postiats.jats.type.VoidType;
import org.ats_lang.postiats.jats.utils.ATSScope;
import org.ats_lang.postiats.jats.value.SingletonValue;

public class AtsInsPMove extends ATSTypeNode {
    private ATSType m_tmpty;
    private String m_tmp;
    private ATSType m_hit;
    private ATSNode m_val;
    
    public AtsInsPMove(ATSType tmpty, String tmp, ATSType hit, ATSNode val) {
        super(VoidType.cType);
        if (tmpty instanceof ArrPtrType) {
        } else {
            throw new Error("not supported");
        }
        m_tmpty = tmpty;
        m_tmp = tmp;
        m_hit = hit;
        m_val = val;
    }
    
    @Override
    // #define ATSINSpmove(tmp, hit, val) (*(hit*)tmp = val)
    public SingletonValue evaluate(Map<String, ATSType> types, Map<String, FuncDef> funcs, ATSScope<Object> scope) {
        Object tmpval = scope.getValue(m_tmp);
        
        RefType
        if (m_tmp instanceof IdentifierNode) {
            Object val_v = m_val.evaluate(types, funcs, scope);
            // It must be a PtrValue
            PtrValue tmp_ptr = (PtrValue)scope.getValue(((IdentifierNode)m_tmp).getName());
            ATSValue tmp_v = tmp_ptr.deRef(m_hit);
            tmp_v.copyfrom(val_v);
            
            return SingletonValue.VOID;
        } else {
            throw new Error("ATSINSpmove: only name is supported now");
        }
    }
}
