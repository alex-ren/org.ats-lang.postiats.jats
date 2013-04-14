package org.ats_lang.postiats.jats.tree;

import java.util.Map;

import org.ats_lang.postiats.jats.interpreter.FuncDef;
import org.ats_lang.postiats.jats.interpreter.LValueScope;
import org.ats_lang.postiats.jats.type.ATSType;

public interface ATSNode {
    public enum FunDecorator {STATIC, GLOBAL};
	public Object evaluate(Map<String, ATSType> types, Map<String, FuncDef> funcs, LValueScope scope);
	public ATSType getType();
}
