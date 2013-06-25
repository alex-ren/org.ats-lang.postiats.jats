package org.ats_lang.postiats.jats.tree;

import java.util.Map;

import org.ats_lang.postiats.jats.interpreter.FuncDef;
import org.ats_lang.postiats.jats.type.ATSReferableType;
import org.ats_lang.postiats.jats.type.ATSType;
import org.ats_lang.postiats.jats.type.ArrPtrType;
import org.ats_lang.postiats.jats.type.RefType;
import org.ats_lang.postiats.jats.type.VoidType;
import org.ats_lang.postiats.jats.utils.ATSScope;
import org.ats_lang.postiats.jats.value.Ptrk;
import org.ats_lang.postiats.jats.value.SingletonValue;

public class AtsInsPMove extends ATSTypeNode {
    private ATSType m_tmpty;
    private String m_tmp;
    private ATSReferableType m_hit;
    private ATSNode m_val;
    
    public AtsInsPMove(ATSType tmpty, String tmp, ATSReferableType hit, ATSNode val) {
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
        Object val = m_val.evaluate(types, funcs, scope);
        ATSType valtype = m_val.getType();
        
        Ptrk arrp = (Ptrk)scope.getValue(m_tmp);
        if (valtype instanceof RefType) {
        	arrp.update(RefType.getValue(val, ((RefType) valtype).defType()), m_hit);
        } else if (valtype instanceof ATSReferableType) {
        	arrp.update(val, m_hit); 
        } else {
            throw new Error("ATSINSpmove: only name is supported now");
        }
        
        return SingletonValue.VOID;
    }
}
