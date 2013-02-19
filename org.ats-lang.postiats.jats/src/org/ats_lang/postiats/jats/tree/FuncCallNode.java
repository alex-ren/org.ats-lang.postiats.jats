package org.ats_lang.postiats.jats.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.ats_lang.postiats.jats.ATSScope;
import org.ats_lang.postiats.jats.FuncDef;
import org.ats_lang.postiats.jats.LibFunc;
import org.ats_lang.postiats.jats.UserFunc;
import org.ats_lang.postiats.jats.type.ATSType;
import org.ats_lang.postiats.jats.value.ATSValue;

public class FuncCallNode implements ATSNode {
    private String m_id;
    private List<ATSNode> m_paras;
    
    public FuncCallNode(String id, List<ATSNode> paras) {
        m_id = id;
        m_paras = paras;
    }
    
    @Override
    public ATSValue evaluate(Map<String, ATSType> types,
            Map<String, FuncDef> funcs, ATSScope scope) {
        List<ATSValue> m_args = new ArrayList<ATSValue>();
        for (ATSNode para: m_paras) {
            // clone is important
            m_args.add(para.evaluate(types, funcs, scope).deepcopy());
        }
        
        FuncDef fun = funcs.get(m_id);
        if (null == fun) {
            System.out.println("FuncCallNode::evaluate, fun " + m_id + " is not found");
            throw new Error("FuncCallNode::evaluate, fun " + m_id + " is not found");
        }

        // Only global scope can be seen inside the function.
        ATSScope aScope = new ATSScope(scope.parent());
        
        if (fun instanceof UserFunc) {
            return ((UserFunc)fun).evaluate(types, funcs, aScope, m_args);
        } else {
            return ((LibFunc)fun).evaluate(m_args);
        }
    
    }

}
