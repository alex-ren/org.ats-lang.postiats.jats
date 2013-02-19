package org.ats_lang.postiats.jats.value;

import org.ats_lang.postiats.jats.type.ATSType;

public class ReturnValue implements ATSValue {
    private ATSValue m_v;
    
    public ReturnValue(ATSValue v) {
        m_v = v;
    }

    @Override
    public void copyfrom(ATSValue v) {
        throw new Error("ReturnValue.");
    }

    @Override
    public ATSValue getContent() {
        return m_v;
    }

    @Override
    public ATSValue deepcopy() {
        throw new Error("ReturnValue.");
    }

    @Override
    public ATSType getType() {
        throw new Error("ReturnValue.");
    }

}
