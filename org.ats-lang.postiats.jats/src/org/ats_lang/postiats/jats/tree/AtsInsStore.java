package org.ats_lang.postiats.jats.tree;

import java.util.Map;

import org.ats_lang.postiats.jats.interpreter.FuncDef;
import org.ats_lang.postiats.jats.type.ATSType;
import org.ats_lang.postiats.jats.type.RefType;
import org.ats_lang.postiats.jats.type.VoidType;
import org.ats_lang.postiats.jats.utils.ATSScope;

import org.ats_lang.postiats.jats.value.SingletonValue;

public class AtsInsStore extends ATSTypeNode {
    private ATSNode m_pmv1;
    private ATSNode m_pmv2;

    public AtsInsStore(ATSNode pmv1, ATSNode pmv2) {
    	super(VoidType.cType);
        m_pmv1 = pmv1;
        m_pmv2 = pmv2;
    }

    @Override
    // #define ATSINSstore(pmv1, pmv2) (pmv1 = pmv2)
    public SingletonValue evaluate(Map<String, ATSType> types,
            Map<String, FuncDef> funcs, ATSScope<Object> scope) {
        Object dst = m_pmv1.evaluate(types, funcs, scope);
        Object src = m_pmv2.evaluate(types, funcs, scope);

        ATSType dstType = m_pmv1.getType();
        ATSType srcType = m_pmv2.getType();
        
        if (dstType instanceof RefType) {
            RefType.update(dst, (RefType)dstType, src, srcType);
        } else {
            throw new Error("Updating non-ref value");
        }
        
        return SingletonValue.VOID;

    }
    

}
