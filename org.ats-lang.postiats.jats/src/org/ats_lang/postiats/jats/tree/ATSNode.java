package org.ats_lang.postiats.jats.tree;

import org.ats_lang.postiats.jats.ATSScope;
import org.ats_lang.postiats.jats.value.ATSValue;

public interface ATSNode {
	public ATSValue evaluate(ATSScope scope);
}
