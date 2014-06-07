package jats.utfpl.utfpl.staexp;

import jats.utfpl.utfpl.stype.ISType;


public class Cs2exp {
    public Is2rt s2exp_srt;
    public Is2exp_node s2exp_node;
    
    public Is2rt getSort() {
    	return s2exp_srt;
    }
    
    public Is2exp_node getType() {
    	return s2exp_node;
    }
    
    static public ISType extractType(Cs2exp s2e) {
    	Is2exp_node node = s2e.s2exp_node;
    	if (node instanceof S2Eapp) {
    		return extractType((S2Eapp)node);
    	} else if (node instanceof S2Ecst) {
    		return extractType((S2Ecst)node);
    	} else if (node instanceof S2Eeqeq) {
    		throw new Error("S2Eeqeq should not be seen.");
    	} else if (node instanceof S2Eerr) {
    		throw new Error("S2Eerr should not be seen.");
    	} else if (node instanceof S2Eexi) {
    		return extractType((S2Eexi)node);
    	} else if (node instanceof S2Eextkind) {
    		throw new Error("S2Eextkind should not be seen.");
    	} else if (node instanceof S2Efun) {
    		return extractType((S2Efun)node);
    	} else if (node instanceof S2Eignored) {
    		throw new Error("S2Eignored should not be seen.");
    	} else if (node instanceof S2Eint) {
    		return extractType((S2Eint)node);
    	} else if (node instanceof S2Eintinf) {
    		throw new Error("S2Eintinf should not be seen.");
    	} else if (node instanceof S2Esizeof) {
    		throw new Error("S2Esizeof should not be seen.");
    	} else if (node instanceof S2Euni) {
    		return extractType((S2Euni)node);
    	} else if (node instanceof S2Evar) {
    		return extractType((S2Evar)node);
    	} else {
    		throw new Error(node + " is not supported");
    	}
    	
    }

	private static ISType extractType(S2Eapp node) {
	    // TODO Auto-generated method stub
	    return null;
    }

}
