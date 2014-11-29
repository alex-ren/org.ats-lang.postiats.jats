package jats.utfpl.stfpl.staexp;

public class FunCloNA implements Ifunclo {
    
    public static FunCloNA cInstance = new FunCloNA();
    
    private FunCloNA() {}

    // no spec, by default is function
    @Override
    public boolean isClosure() {
        return false;
    }
    
    @Override
    public String toString() {
        return "n/a";
    }
    
	@Override
    public boolean match(Ifunclo right) {
	    if (null == right) {
	    	throw new Error("This should not happen.");
//	    	return true;
	    } else if (right instanceof FUNCLOclo) {
	    	return false;
	    } else if (right instanceof FunCloNA) {
	    	return true;
	    } else if (right instanceof FUNCLOfun) {
	    	return true;
	    } else {
	    	throw new Error("Ifunclo " + right + " is not supported.");
	    }
    }
	

}
