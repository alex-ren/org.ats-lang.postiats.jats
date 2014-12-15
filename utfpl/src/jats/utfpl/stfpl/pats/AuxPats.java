package jats.utfpl.stfpl.pats;

import jats.utfpl.stfpl.stype.BoolType;
import jats.utfpl.stfpl.stype.ISType;
import jats.utfpl.stfpl.stype.IntType;

public class AuxPats {
	
	public static String sym2name(String sym, ISType ty) {
		if (sym.equals("+")) {
			return "add";
		} else if (sym.equals("-")) {
			return "sub";
		} else if (sym.equals("*")) {
			return "mul";
		} else if (sym.equals("/")) {
			return "div";
		} else if (sym.equals(">")) {
			return "gt";
		} else if (sym.equals(">=")) {
			return "gte";
		} else if (sym.equals("<")) {
			return "lt";
		} else if (sym.equals("<=")) {
			return "lte";
		} else if (sym.equals("=")) {
			return "eq";
        } else if (sym.equals("<>")) {
            return "neq";  
        } else if (sym.equals("~")) {
        	if (ty instanceof BoolType) {
        		return "negation";
        	} else if (ty instanceof IntType) {
        		return "neg";
        	} else {
        		throw new Error("no match for symbol ~");
        	}
            
          
		} else {
			throw new Error("sym " + sym + " is not supported.");
		}
	}
	
	public static String convertConst(String c) {
		if (c.equals("true_bool")) {
			return "true";
		} else if (c.equals("false_bool")) {
			return "false";
		} else {
			return c;
		}
	}

}

