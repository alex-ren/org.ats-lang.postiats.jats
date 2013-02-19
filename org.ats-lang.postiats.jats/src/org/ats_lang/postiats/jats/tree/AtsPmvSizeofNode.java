package org.ats_lang.postiats.jats.tree;

import java.util.Map;

import org.ats_lang.postiats.jats.ATSScope;
import org.ats_lang.postiats.jats.FuncDef;
import org.ats_lang.postiats.jats.type.ATSType;
import org.ats_lang.postiats.jats.value.SizeValue;

public class AtsPmvSizeofNode implements ATSNode {
    private ATSType m_hit;

    public AtsPmvSizeofNode(ATSType hit) {
        m_hit = hit;
    }
    
    @Override
    // #define ATSPMVsizeof(hit) (sizeof(hit))
    public SizeValue evaluate(Map<String, ATSType> types, Map<String, FuncDef> funcs, ATSScope scope) {
        return new SizeValue(m_hit.getSize());
    }

}
