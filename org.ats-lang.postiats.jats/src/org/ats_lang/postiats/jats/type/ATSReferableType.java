package org.ats_lang.postiats.jats.type;

import org.ats_lang.postiats.jats.value.Ptrk;
import org.ats_lang.postiats.jats.value.SingletonValue;

public abstract class ATSReferableType extends ATSKindType {
    public ATSReferableType(Decorator dec) {
        super(dec);
    }

    @Override
    public Ptrk createRefDefault() {
        return Ptrk.createPtrk(this, this.createNormalDefault());
    }
    

    // src := this type
    // create a new value
    abstract public Object cloneValue(Object src);



//    public PrimValue castFrom(PrimValue pv);
}
