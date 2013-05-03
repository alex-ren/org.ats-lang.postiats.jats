package org.ats_lang.postiats.jats.type;

import org.ats_lang.postiats.jats.value.Ptrk;
import org.ats_lang.postiats.jats.value.SingletonValue;

public abstract class ATSPrimType extends ATSKindType {
    public ATSPrimType(Decorator dec) {
        super(dec);
    }
    
    @Override
    public SingletonValue createNormalDefault() {
        return SingletonValue.NULL;
    }
    
    @Override
    public Ptrk createRefDefault() {
        return new Ptrk(this.createNormalDefault());
    }
    
    @Override
    public boolean equals(ATSType ty) {
        return this == ty;
    }

//    public PrimValue castFrom(PrimValue pv);
}
