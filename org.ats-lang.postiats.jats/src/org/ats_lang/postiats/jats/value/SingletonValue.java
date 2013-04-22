package org.ats_lang.postiats.jats.value;

import org.ats_lang.postiats.jats.type.ATSType;

public class SingletonValue implements ATSValue {
	static {
	    NULL = new SingletonValue();
	    VOID = new SingletonValue();
	}

    public static final SingletonValue NULL;  // used for initial value
    public static final SingletonValue VOID;
    
	private SingletonValue() {}
	
	@Override
	public void copyfrom(ATSValue v) {
		throw new Error("Singleton Value.");
	}

	@Override
	public Object getContent() {
		throw new Error("Singleton Value.");
	}

    @Override
    public ATSValue deepcopy() {
        return this;
    }

    @Override
    public ATSType getType() {
        throw new Error("Singleton Value.");
    }

}
