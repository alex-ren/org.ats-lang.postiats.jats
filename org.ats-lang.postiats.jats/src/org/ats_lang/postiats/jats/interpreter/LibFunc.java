package org.ats_lang.postiats.jats.interpreter;

import java.util.List;

import org.ats_lang.postiats.jats.value.ATSValue;

public interface LibFunc extends FuncDef {

    public ATSValue evaluate(List<ATSValue> args);
    
}
