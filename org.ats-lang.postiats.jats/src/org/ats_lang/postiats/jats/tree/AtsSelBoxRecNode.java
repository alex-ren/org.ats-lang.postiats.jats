package org.ats_lang.postiats.jats.tree;

import java.util.Map;

import org.ats_lang.postiats.jats.interpreter.FuncDef;
import org.ats_lang.postiats.jats.interpreter.ValueScope;
import org.ats_lang.postiats.jats.type.ATSType;
import org.ats_lang.postiats.jats.value.ATSValue;

public class AtsSelBoxRecNode implements ATSNode {
    private ATSNode m_node;
    private ATSType m_type;
    private String m_lab;

    public AtsSelBoxRecNode(ATSNode node, ATSType type, String lab) {
        m_node = node;
        m_type = type;
        m_lab = lab;
    }

    @Override
    public ATSValue evaluate(Map<String, ATSType> types,
            Map<String, FuncDef> funcs, ValueScope scope) {
        throw new Error("AtsSelBoxRecNode is not supported.");
    }
x
}
