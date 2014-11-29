package jats.utfpl.stfpl.staexp;

public class FUNCLOfun implements Ifunclo {
    public static FUNCLOfun cInstance = new FUNCLOfun();
    private FUNCLOfun() {}
    
    @Override
    public boolean isClosure() {
        return false;
    }
    
    @Override
    public String toString() {
        return "fun";
    }
    
	@Override
    public boolean match(Ifunclo right) {
	    if (null == right) {
	    	throw new Error("This should not happen.");
//	    	return false;
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
