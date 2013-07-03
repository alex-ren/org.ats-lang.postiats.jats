package org.ats_lang.postiats.jats.tree;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.ats_lang.postiats.jats.interpreter.EvaluateVisitor;
import org.ats_lang.postiats.jats.interpreter.FuncDef;
import org.ats_lang.postiats.jats.interpreter.FuncPara;
import org.ats_lang.postiats.jats.type.ATSType;
import org.ats_lang.postiats.jats.utils.ATSScope;

public class UserFunc implements FuncDef, ATSNode {
    private String m_id;
    private ATSNode.FunDecorator m_dec;  // function decorator
    private ATSType m_type;  // return type
    private List<FuncPara> m_paras;
    private BlockNode m_body;

    // paras could be null, otherwise it's length > 0.
    // The FuncNode holds a scope so that we can populate arguments into the scope.
    public UserFunc(String id, ATSNode.FunDecorator decorator, ATSType type, List<FuncPara> paras, BlockNode body) {
        m_id = id;
        m_dec = decorator;
        m_type = type;
        m_paras = paras;
        m_body = body;
    }
    
    public String getName() {
        return m_id;
    }
    
    public boolean isDecl() {
        if (null == m_body) {
            return true;
        } else {
            return false;
        }
    }
    
    public ATSType getRetType() {
        return m_type;
    }
    
    public List<FuncPara> getParalst() {
        return m_paras;
    }
    
    /*
     * scope: The scope, which is used as the current scope should be provided by the caller.
     */
    public Object evaluate(Map<String, ATSType> types,
            Map<String, FuncDef> funcs, ATSScope<Object> scope, List<Object> args) {
        if (null != m_paras) {
            Iterator<FuncPara> iter_para = m_paras.iterator();
            Iterator<Object> iter_val = args.iterator();
            
            while (iter_para.hasNext()) {
                scope.addValue(iter_para.next().getId(), iter_val.next());
            }
        }
        // System.out.println("evaluating function: " + m_id);
        EvaluateVisitor visitor = new EvaluateVisitor(types, funcs, scope);
        return visitor.visit(m_body);
    }

    @Override
    public ATSType getType() {
        throw new Error("not supported");
    }

    @Override
    public Object accept(ATSTreeVisitor visitor) {
        return visitor.visit(this);
    }

}
