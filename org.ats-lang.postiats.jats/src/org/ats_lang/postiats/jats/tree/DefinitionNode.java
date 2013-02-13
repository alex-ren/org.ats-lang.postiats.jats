package org.ats_lang.postiats.jats.tree;

import java.util.Map;

import org.ats_lang.postiats.jats.ATSScope;
import org.ats_lang.postiats.jats.FuncDef;
import org.ats_lang.postiats.jats.type.ATSType;
import org.ats_lang.postiats.jats.value.ATSValue;
import org.ats_lang.postiats.jats.value.SingletonValue;

public class DefinitionNode implements ATSNode {
    private ATSType m_ty;
    private String m_id;
    
    public DefinitionNode(ATSType ty, String id) {
        m_ty = ty;
        m_id = id;
    }
    
    public ATSType getType() {
        return m_ty;
    }
    
    public String getID() {
        return m_id;
    }
    
    @Override
    public ATSValue evaluate(Map<String, ATSType> types,
            Map<String, FuncDef> funcs, ATSScope scope) {
        scope.addValue(m_id, m_ty.createDefault());
        return SingletonValue.VOID;
    }

}
