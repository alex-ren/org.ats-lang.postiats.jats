package org.ats_lang.postiats.jats.tree;

import java.util.Map;

import org.ats_lang.postiats.jats.interpreter.FuncDef;
import org.ats_lang.postiats.jats.type.ATSType;
import org.ats_lang.postiats.jats.type.RefType;
import org.ats_lang.postiats.jats.type.VoidType;
import org.ats_lang.postiats.jats.utils.ATSScope;
import org.ats_lang.postiats.jats.value.SingletonValue;

public class AtsInsLoad extends ATSTypeNode {
    private String m_tmp;
    private ATSType m_tmpty;
    private ATSNode m_pmv;
    
    public AtsInsLoad(String tmp, ATSType tmpty, ATSNode pmv) {
    	super(VoidType.cType);
        m_tmp = tmp;
        m_tmpty = tmpty;
        m_pmv = pmv;
    }
    
    @Override
    // pats_ccomp_instrset
    // #define ATSINSload(tmp, pmv) (tmp = pmv)
    public SingletonValue evaluate(Map<String, ATSType> types, Map<String, FuncDef> funcs, ATSScope<Object> scope) {
            Object src = m_pmv.evaluate(types, funcs, scope);
            
            if (m_tmpty instanceof RefType) {
            	Object dst = scope.getValue(m_tmp);
            	ATSType srcType = m_pmv.getType();
            	RefType.update(dst, (RefType)m_tmpty, src, srcType);
            } else {
                // Initialize value.
            	scope.addValue(m_tmp, src);
            }
            
            return SingletonValue.VOID;
    }

}
