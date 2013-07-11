package org.ats_lang.postiats.jats.interpreter;

import java.util.List;


public interface LibFunc extends FuncDef {

    public abstract Object evaluate(List<Object> args);
    
    public String getModuleName();
    
}
