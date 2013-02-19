package org.ats_lang.postiats.jats.type;

import org.ats_lang.postiats.jats.value.PrimValue;

public interface PrimType extends ATSType {

    public PrimValue castFrom(PrimValue pv);
}
