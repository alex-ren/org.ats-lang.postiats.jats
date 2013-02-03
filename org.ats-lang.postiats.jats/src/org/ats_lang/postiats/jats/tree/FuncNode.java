package org.ats_lang.postiats.jats.tree;

import java.util.List;

import org.ats_lang.postiats.jats.ATSScope;
import org.ats_lang.postiats.jats.FuncPara;
import org.ats_lang.postiats.jats.type.ATSType;
import org.ats_lang.postiats.jats.value.ATSValue;

public class FuncNode implements ATSNode {
    private String m_id;
    private ATSNode.FunDecorator m_dec;  // function decorator
    private ATSType m_type;  // return type
    private List<FuncPara> m_paras;
    private ATSNode  m_body;

    // paras could be null, otherwise it's length > 0.
    // The FuncNode holds a scope so that we can populate arguments into the scope.
    public FuncNode(String id, ATSNode.FunDecorator decorator, ATSType type, List<FuncPara> paras, ATSNode body) {
        m_id = id;
        m_dec = decorator;
        m_type = type;
        m_paras = paras;
        m_body = body;
    }
    
    public boolean isDecl() {
        if (null == m_body) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public ATSValue evaluate(ATSScope scope) {
        // todo: Basically, we just return the function itself.
        return null;
    }

}
