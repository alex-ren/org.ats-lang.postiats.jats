package org.ats_lang.postiats.jats.interpreter;

import java.util.List;


public interface LibFunc extends FuncDef {

    public Object evaluate(List<Object> args);
    
}
