package org.ats_lang.postiats.jats.value;

public class SingletonValue {
	static {
	    NULL = new SingletonValue();
	    VOID = new SingletonValue();
	    c_stdout = new SingletonValue();
	    c_stderr = new SingletonValue();
	}

    public static final SingletonValue NULL;  // used for initial value
    public static final SingletonValue VOID;
    
    public static final SingletonValue c_stdout;
    public static final SingletonValue c_stderr;
    
	private SingletonValue() {}
	
//	@Override
//	public void copyfrom(ATSValue v) {
//		throw new Error("Singleton Value.");
//	}
//
//	@Override
//	public Object getContent() {
//		throw new Error("Singleton Value.");
//	}
//
//    @Override
//    public ATSValue deepcopy() {
//        return this;
//    }
//
//    @Override
//    public ATSType getType() {
//        throw new Error("Singleton Value.");
//    }

}
