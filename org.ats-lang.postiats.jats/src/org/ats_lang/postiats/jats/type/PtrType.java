package org.ats_lang.postiats.jats.type;

import org.ats_lang.postiats.jats.value.PtrValue;
import org.ats_lang.postiats.jats.value.SingletonValue;

public class PtrType implements ATSType {

    @Override
    public int getSize() {
        return 4;
    }

    @Override
    public PtrValue createDefault() {
        return new PtrValue(SingletonValue.VOID);
    }
}
