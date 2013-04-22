package org.ats_lang.postiats.jats.type;

import org.ats_lang.postiats.jats.value.PrimValue;
import org.ats_lang.postiats.jats.value.SingletonValue;

public abstract class PrimType extends ATSKindType {
    public PrimType(Decorator dec) {
        super(dec);
    }
    
    @Override
    public SingletonValue createDefault() {
        return SingletonValue.NULL;
    }

//    public PrimValue castFrom(PrimValue pv);
}
