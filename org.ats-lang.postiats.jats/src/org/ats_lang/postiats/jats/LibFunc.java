package org.ats_lang.postiats.jats;

import java.util.List;

import org.ats_lang.postiats.jats.value.ATSValue;

public interface LibFunc extends FuncDef {

    public ATSValue evaluate(List<ATSValue> args);
    
}
