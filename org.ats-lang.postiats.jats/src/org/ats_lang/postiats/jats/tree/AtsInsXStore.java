package org.ats_lang.postiats.jats.tree;

import java.util.Map;

import org.ats_lang.postiats.jats.interpreter.FuncDef;
import org.ats_lang.postiats.jats.type.ATSReferableType;
import org.ats_lang.postiats.jats.type.ATSType;
import org.ats_lang.postiats.jats.type.RefType;
import org.ats_lang.postiats.jats.type.VoidType;
import org.ats_lang.postiats.jats.utils.ATSScope;
import org.ats_lang.postiats.jats.value.SingletonValue;

public class AtsInsXStore extends ATSTypeNode {

    private ATSReferableType m_elety;
    private String m_tmp;
    private ATSNode m_pmv1;
    private ATSNode m_pmv2;
    
    public AtsInsXStore(ATSReferableType elety, String tmp, ATSNode pmv1, ATSNode pmv2) {
        super(VoidType.cType);
        if (!elety.equals(((RefType)pmv1.getType()).defType())) {
            throw new Error("Type mismatch.");
        }
        m_elety = elety;
        m_tmp = tmp;
        m_pmv1 = pmv1;
        m_pmv2 = pmv2;
    }

    @Override
    // #define ATSINSxstore(tmp, pmv1, pmv2) (tmp = pmv1, pmv1 = pmv2, pmv2 = tmp)
    // example
//    ATStmpdec(tmp3$1, atstkind_t0ype(atstype_double)) ;
//    ATSINSxstore(tmp3$1, 
//                 ATSselarrptrind(arg0, atstkind_t0ype(atstype_double), [arg1]), 
//                 ATSselarrptrind(arg0, atstkind_t0ype(atstype_double), [arg2])) ;
    public SingletonValue evaluate(Map<String, ATSType> types,
            Map<String, FuncDef> funcs, ATSScope<Object> scope) {
        Object pmv1 = m_pmv1.evaluate(types, funcs, scope);
        Object pmv2 = m_pmv2.evaluate(types, funcs, scope);
        
        Object temp = RefType.cloneValue(pmv1, m_elety);
        
        scope.addValue(m_tmp, temp);
        
        RefType.update(pmv1, pmv2, m_pmv2.getType());
        RefType.update(pmv2, temp, m_elety);
        
        return SingletonValue.VOID;
        
    }

}
