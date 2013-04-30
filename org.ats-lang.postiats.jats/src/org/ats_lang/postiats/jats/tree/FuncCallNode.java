package org.ats_lang.postiats.jats.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.ats_lang.postiats.jats.interpreter.FuncDef;
import org.ats_lang.postiats.jats.interpreter.LibFunc;
import org.ats_lang.postiats.jats.interpreter.UserFunc;
import org.ats_lang.postiats.jats.type.ATSType;
import org.ats_lang.postiats.jats.type.FuncType;
import org.ats_lang.postiats.jats.type.RefType;
import org.ats_lang.postiats.jats.type.VoidType;
import org.ats_lang.postiats.jats.utils.ATSScope;

public class FuncCallNode extends ATSTypeNode {
    private FuncType m_functype;
    private String m_id;
    private List<ATSNode> m_paras;
    static int m_indent = 0;
    
    public FuncCallNode(ATSType functype, String id, List<ATSNode> paras) {
        super(VoidType.cType);
        if (functype instanceof FuncType) {
            m_functype = (FuncType)functype;
            m_id = id;
            m_paras = paras;
            this.updateType(m_functype.getReturnType());
        } else {
            throw new Error("type error");
        }

    }

    private void printIndent(int n) {
        System.out.println();
        for (int j = 0; j < n; ++j) {
            System.out.print(" ");
        }
    }

    @Override
    public Object evaluate(Map<String, ATSType> types,
            Map<String, FuncDef> funcs, ATSScope<Object> scope) {
        List<Object> m_args = null;
        
        if (m_paras != null) {
            m_args = new ArrayList<Object>();
            for (ATSNode para : m_paras) {
                // clone is important
                Object arg = para.evaluate(types, funcs, scope);
                ATSType argtype = para.getType();
                if (argtype instanceof RefType) {
                    // reference needs to be deep copied.
                    arg = RefType.cloneValue(arg, ((RefType) argtype).defType());
                }
                m_args.add(arg);
            }
        }
        
        FuncDef fun = funcs.get(m_id);
        if (null == fun) {
            System.out.println("FuncCallNode::evaluate, fun " + m_id + " is not found");
            throw new Error("FuncCallNode::evaluate, fun " + m_id + " is not found");
        }

        // Only global scope can be seen inside the function.
        ATSScope<Object> aScope = scope.getParent().newScope();
        
//        printIndent(m_indent);
//        System.out.println("Entering function: " + m_id);
        m_indent += 4;
        Object ret;
        if (fun instanceof UserFunc) {
            ret = ((UserFunc)fun).evaluate(types, funcs, aScope, m_args);
        } else {
            ret = ((LibFunc)fun).evaluate(m_args);
        }
        m_indent -= 4;
//        printIndent(m_indent);
//        System.out.println("Leaving function: " + m_id);
        return ret;
    }

}
