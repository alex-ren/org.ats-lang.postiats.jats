package org.ats_lang.postiats.jats.tree;

import java.util.Map;

import org.ats_lang.postiats.jats.interpreter.FuncDef;
import org.ats_lang.postiats.jats.type.ATSType;
import org.ats_lang.postiats.jats.type.SizeType;
import org.ats_lang.postiats.jats.utils.ATSScope;

public class AtsPmvSizeofNode extends ATSTypeNode {
    private ATSType m_hit;

    public AtsPmvSizeofNode(ATSType hit) {
        super(SizeType.cType0);
        m_hit = hit;
    }
    

    @Override
    public Integer evaluate(Map<String, ATSType> types,
            Map<String, FuncDef> funcs, ATSScope<Object> scope) {
        return m_hit.getSize();
    }
}
