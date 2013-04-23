package org.ats_lang.postiats.jats.type;

import org.ats_lang.postiats.jats.value.Ptrk;
import org.ats_lang.postiats.jats.value.SingletonValue;

public abstract class PrimType extends ATSKindType {
    public PrimType(Decorator dec) {
        super(dec);
    }
    
    @Override
    public SingletonValue createNormalDefault() {
        return SingletonValue.NULL;
    }
    
    @Override
    public Ptrk createRefDefault() {
        return new Ptrk(this);
    }

//    public PrimValue castFrom(PrimValue pv);
}
