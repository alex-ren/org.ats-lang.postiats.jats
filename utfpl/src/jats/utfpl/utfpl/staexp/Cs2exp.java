package jats.utfpl.utfpl.staexp;

import java.util.ArrayList;
import java.util.List;

import jats.utfpl.utfpl.stype.AppType;
import jats.utfpl.utfpl.stype.DefaultAppType;
import jats.utfpl.utfpl.stype.FunType;
import jats.utfpl.utfpl.stype.ISType;
import jats.utfpl.utfpl.stype.IntType;
import jats.utfpl.utfpl.stype.PolyParaType;
import jats.utfpl.utfpl.stype.PolyType;


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
    	    throw new Error("S2Ecst should not be seen.");
    	} else if (node instanceof S2Eeqeq) {
    		return null;
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
    	    throw new Error("S2Eint encountered.");
    	} else if (node instanceof S2Eintinf) {
    		throw new Error("S2Eint encountered.");
    	} else if (node instanceof S2Esizeof) {
    		return null;
    	} else if (node instanceof S2Euni) {
    		return extractType((S2Euni)node);
    	} else if (node instanceof S2Evar) {
    	    return extractType((S2Evar)node);
    	} else {
    		throw new Error(node + " is not supported");
    	}
    	
    }

	private static PolyParaType extractType(S2Evar node) {
	    return extractType(node.m_s2var);
    }
	
    private static PolyParaType extractType(Cs2var node) {
        if (node.isType()) {
            PolyParaType ret = new PolyParaType(node);
            return ret;
        } else {
            return null;
        }
    }

    private static PolyType extractType(S2Euni node) {
	    List<Cs2var> s2vs = node.m_s2vs;
	    List<PolyParaType> paras = new ArrayList<PolyParaType>();
	    
	    for (Cs2var s2var: s2vs) {
	        PolyParaType para = extractType(s2var);
	        
	        if (null != para) {
	            paras.add(para);
	        }
	    }
	    
	    ISType body = extractType(node.m_s2e_body);
	    
	    PolyType ret = new PolyType(paras, body);
	    
	    return ret;
    }

    private static FunType extractType(S2Efun node) {
        int npf = node.m_npf;
        List<ISType> args = extractTypeList(node.m_arg);
        ISType res = extractType(node.m_res);
        
        return new FunType(npf, args, res);
    }

    private static ISType extractType(S2Eexi node) {
        return extractType(node.m_s2e_body);
    }

    private static ISType extractType(S2Eapp node) {
	    // Currently, we get the type information from the function in the statics.
	    if (node.m_fun.s2exp_node instanceof S2Ecst) {
	        S2Ecst sfun = (S2Ecst)node.m_fun.s2exp_node;
	        String con = sfun.getName();
	        
	        if (con.equals(DefaultAppType.conInt)) {
	            return IntType.cInstance;
	        } else {
	            List<ISType> tys = extractTypeList(node.m_arglst);
	            AppType ret = new AppType(con, tys);
	            return ret;
	        }

	    } else {
	        throw new Error("check this.");
	    }
    }
    
    private static List<ISType> extractTypeList(List<Cs2exp> s2exps) {
        List<ISType> ret = new ArrayList<ISType>();
        for (Cs2exp exp: s2exps) {
            ISType ty = extractType(exp);
            if (ty != null) {
                ret.add(ty);
            }
        }
        
        return ret;
    }


}



