package jats.utfpl.stfpl.pats;

public class AuxPats {
	
	public static String sym2name(String sym) {
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
        } else if (sym.equals("~")) {
            return "neg";
		} else {
			throw new Error("sym " + sym + " is not supported.");
		}
	}
	
	public static String convertConst(String c) {
		if (c.equals("true_bool")) {
			return "True";
		} else if (c.equals("false_bool")) {
			return "False";
		} else {
			return c;
		}
	}

}

