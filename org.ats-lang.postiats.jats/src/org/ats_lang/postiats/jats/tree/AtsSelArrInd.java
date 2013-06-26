package org.ats_lang.postiats.jats.tree;

import java.util.Map;

import org.ats_lang.postiats.jats.interpreter.FuncDef;
import org.ats_lang.postiats.jats.type.ATSType;
import org.ats_lang.postiats.jats.utils.ATSScope;

public class AtsSelArrInd extends ATSTypeNode {
    private ATSNode m_node;
    private ATSType m_type;
    private String m_lab;

    public AtsSelArrInd(ATSNode node, ATSType type, String lab) {
        super(null);
        throw new Error("not supported");
//        m_node = node;
//        m_type = type;
//        m_lab = lab;
    }


    @Override
    // #define ATSselarrind(pmv, tyarr, lab) (((tyarr)pmv).lab)
    // example
    public Object evaluate(Map<String, ATSType> types,
            Map<String, FuncDef> funcs, ATSScope<Object> scope) {
        // TODO Auto-generated method stub
        throw new Error("not supported");
//        return null;
    }
}
