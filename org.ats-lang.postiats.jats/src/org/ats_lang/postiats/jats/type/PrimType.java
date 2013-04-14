package org.ats_lang.postiats.jats.type;

import org.ats_lang.postiats.jats.value.PrimValue;

public abstract class PrimType extends ATSKindType {
    public PrimType(Decorator dec) {
        super(dec);
    }

//    public PrimValue castFrom(PrimValue pv);
}
