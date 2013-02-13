package org.ats_lang.postiats.jats.tree;

import java.util.Map;

import org.ats_lang.postiats.jats.ATSScope;
import org.ats_lang.postiats.jats.FuncDef;
import org.ats_lang.postiats.jats.type.ATSType;
import org.ats_lang.postiats.jats.value.ATSValue;

public interface ATSNode {
    public enum FunDecorator {STATIC, GLOBAL};
	public ATSValue evaluate(Map<String, ATSType> types, Map<String, FuncDef> funcs, ATSScope scope);
}
