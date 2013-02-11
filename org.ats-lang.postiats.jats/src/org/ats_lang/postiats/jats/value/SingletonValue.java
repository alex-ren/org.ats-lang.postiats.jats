package org.ats_lang.postiats.jats.value;

public class SingletonValue implements ATSValue {
	static {
	    NULL = new SingletonValue();
	    VOID = new SingletonValue();
	}

    public static final ATSValue NULL;
    public static final ATSValue VOID;
    
	private SingletonValue() {}
	
	@Override
	public void copyfrom(ATSValue v) {
		throw new Error("Singleton Value.");
	}

	@Override
	public Object getContent() {
		// TODO Auto-generated method stub
		throw new Error("Singleton Value.");
	}

}
