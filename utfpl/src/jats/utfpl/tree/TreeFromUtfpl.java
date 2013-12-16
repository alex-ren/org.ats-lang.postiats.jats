package jats.utfpl.tree;

import java.util.ArrayList;
import java.util.List;

import jats.utfpl.ccomp.CCompUtils;
import jats.utfpl.utfpl.Cd2cst;
import jats.utfpl.utfpl.Cd2ecl;
import jats.utfpl.utfpl.Cd2exp;
import jats.utfpl.utfpl.Cd2sym;
import jats.utfpl.utfpl.Cd2var;
import jats.utfpl.utfpl.Cf2undec;
import jats.utfpl.utfpl.Ci2mpdec;
import jats.utfpl.utfpl.Cp2at;
import jats.utfpl.utfpl.Csymbol;
import jats.utfpl.utfpl.Cv2aldec;
import jats.utfpl.utfpl.D2Cfundecs;
import jats.utfpl.utfpl.D2Cignored;
import jats.utfpl.utfpl.D2Cimpdec;
import jats.utfpl.utfpl.D2Cvaldecs;
import jats.utfpl.utfpl.D2EXPARGdyn;
import jats.utfpl.utfpl.D2EXPARGsta;
import jats.utfpl.utfpl.D2Eapplst;
import jats.utfpl.utfpl.D2Ecst;
import jats.utfpl.utfpl.D2Eempty;
import jats.utfpl.utfpl.D2Eexp;
import jats.utfpl.utfpl.D2Ef0loat;
import jats.utfpl.utfpl.D2Ei0nt;
import jats.utfpl.utfpl.D2Eifopt;
import jats.utfpl.utfpl.D2Eignored;
import jats.utfpl.utfpl.D2Elam;
import jats.utfpl.utfpl.D2Elet;
import jats.utfpl.utfpl.D2Es0tring;
import jats.utfpl.utfpl.D2Esym;
import jats.utfpl.utfpl.D2Evar;
import jats.utfpl.utfpl.Id2ecl_node;
import jats.utfpl.utfpl.Id2exp_node;
import jats.utfpl.utfpl.Id2exparg;
import jats.utfpl.utfpl.Ip2at_node;
import jats.utfpl.utfpl.P2Tany;
import jats.utfpl.utfpl.P2Tpat;
import jats.utfpl.utfpl.P2Tvar;
import jats.utfpl.utfpl.UtfplProgram;

public class TreeFromUtfpl {
	
	public ProgramTree trans(UtfplProgram uProg) {
		List<IDec> decs = new ArrayList<IDec>();
		for (Cd2ecl uD2ecl: uProg.m_d2ecs) {
			List<IDec> decLst = transGlobalCd2ecl(uD2ecl);
			decs.addAll(decLst);
			
		}
		
		return new ProgramTree(null, decs);
	}

	private List<IDec> transGlobalCd2ecl(Cd2ecl uNode) {
		Location loc = new Location(uNode.d2ecl_loc);
		Id2ecl_node uDecNode = uNode.d2ecl_node;
		
		List<IDec> decs = new ArrayList<IDec>();
		
	    if (uDecNode instanceof D2Cvaldecs) {
	    	return transGlobalD2Cvaldecs((D2Cvaldecs)uDecNode);
	    } else if (uDecNode instanceof D2Cfundecs) {
	    	decs.add(transD2Cfundecs(loc, (D2Cfundecs)uDecNode));
	    	return decs;
	    } else if (uDecNode instanceof D2Cimpdec) {
	    	decs.add(transD2Cimpdec(loc, (D2Cimpdec)uDecNode));
	    	return decs;
	    } else if (uDecNode instanceof D2Cignored) {
	    	System.err.println("D2Cignored is encounter.");
	    	return decs;
	    } else {
	    	throw new Error(uNode + " is not supported.");
	    }
    }

	private DecFunGroup transD2Cimpdec(Location loc, D2Cimpdec uNode) {
	    List<DecFunDef> funLst = new ArrayList<DecFunDef>();
	    DecFunDef funDef = transCi2mpdec(uNode.m_i2mpdec);
	    funLst.add(funDef);
	    DecFunGroup ret = new DecFunGroup(loc, funLst);
	    return ret;
    }

	private DecFunDef transCi2mpdec(Ci2mpdec uNode) {
	    Location loc = new Location(uNode.i2mpdec_loc);
	    ExpId id = new ExpId(loc, transCd2cst(uNode.i2mpdec_cst));
	    
	    IExp lam = transCd2exp(uNode.i2mpdec_def);
	    if (lam instanceof ExpLam) {
	    	ExpLam lambda = (ExpLam)lam;
	    	DecFunDef ret = new DecFunDef(loc, id, lambda.m_paralst, lambda.m_body);
	    	return ret;
	    } else {
	    	throw new Error("Should be a lambda.");
	    }
    }

	private List<IDec> transGlobalD2Cvaldecs(D2Cvaldecs uNode) {
		List<IDec> vdecs = new ArrayList<IDec>();
		for (Cv2aldec uValDec: uNode.m_v2ds) {
			vdecs.add(transGlobalCv2aldec(uValDec));
		}

		return vdecs;
		
    }

	private DecValDef transGlobalCv2aldec(Cv2aldec uNode) {
		ExpId id = transCp2at(uNode.v2aldec_pat);
		IExp def = transCd2exp(uNode.v2aldec_def);
		
		Location loc = new Location(uNode.v2aldec_loc);
		return new DecValDef(loc, id, def);
    }

	private IExp transCd2exp(Cd2exp uNode) {
        Id2exp_node uExpNode = uNode.d2exp_node;
        Location loc = new Location(uNode.d2exp_loc);
        if (uExpNode instanceof D2Eapplst) {
        	return transD2Eapplst(loc, (D2Eapplst)uExpNode);
        } else if (uExpNode instanceof D2Ecst) {
        	return transD2Ecst(loc, (D2Ecst)uExpNode);
        } else if (uExpNode instanceof D2Eempty) {
        	return ExpTuple.makeVoid(loc);
        } else if (uExpNode instanceof D2Eexp) {
        	return transD2Eexp(loc, (D2Eexp)uExpNode);
        } else if (uExpNode instanceof D2Ef0loat) {
        	return transD2Ef0loat(loc, (D2Ef0loat)uExpNode);
        } else if (uExpNode instanceof D2Ei0nt) {
        	return transD2Ei0nt(loc, (D2Ei0nt)uExpNode);
        } else if (uExpNode instanceof D2Eifopt) {
        	return transD2Eifopt(loc, (D2Eifopt)uExpNode);
        } else if (uExpNode instanceof D2Eignored) {
        	throw new Error("D2Eignored is not supported");
        } else if (uExpNode instanceof D2Elam) {
        	return transD2Elam(loc, (D2Elam)uExpNode);
        } else if (uExpNode instanceof D2Elet) {
        	return transD2Elet(loc, (D2Elet)uExpNode);
        } else if (uExpNode instanceof D2Es0tring) {
        	return transD2Es0tring(loc, (D2Es0tring)uExpNode);
        } else if (uExpNode instanceof D2Esym) {
        	return transD2Esym(loc, (D2Esym)uExpNode);
        } else if (uExpNode instanceof D2Evar) {
        	return transD2Evar(loc, (D2Evar)uExpNode);
        } else {
        	throw new Error(uExpNode + " is not supported");
        }
    }

    private ExpApp transD2Eapplst(Location loc, D2Eapplst uNode) {

        List<IExp> argsLst = new ArrayList<IExp>();
        if (uNode.m_d2as_arg.size() > 1) {
            System.err.println("D2Eapplst has more than one list of arguments");
        }
        for (Id2exparg exparg: uNode.m_d2as_arg) {
        	List<IExp> args = transId2exparg(exparg);
        	argsLst.addAll(args);
        }
        
        IExp fun = transCd2exp(uNode.m_d2e_fun);
        
        ExpApp ret = new ExpApp(loc, fun, argsLst);
        return ret;
        
    }

	private List<IExp> transId2exparg(Id2exparg uNode) {
	    if (uNode instanceof D2EXPARGdyn) {
	    	return transD2EXPARGdyn((D2EXPARGdyn)uNode);
	    } else if (uNode instanceof D2EXPARGsta) {
	    	return transD2EXPARGsta((D2EXPARGsta)uNode);
	    } else {
	    	throw new Error(uNode + " is not supported");
	    }
    }

	private List<IExp> transD2EXPARGdyn(D2EXPARGdyn uNode) {
		List<IExp> expLst = new ArrayList<IExp>();

	    for (Cd2exp uExp: uNode.m_d2expLst) {
	    	IExp exp = transCd2exp(uExp);
	    	expLst.add(exp);
	    }
	    return expLst;
	    
    }

	private List<IExp> transD2EXPARGsta(D2EXPARGsta uNode) {
	    throw new Error("D2EXPARGsta is not supported");
    }

	private IExp transD2Ecst(Location loc, D2Ecst uNode) {
		if (uNode.isTrue()) {
			return ExpAtom.makeTrue(loc);
		} else if (uNode.isFalse()) {
			return ExpAtom.makeFalse(loc);
		} else {
	    	String sym = transCd2cst(uNode.m_d2cst);
	    	ExpId ret = new ExpId(loc, sym);
	    	return ret;
		}
    }

	private String transCd2cst(Cd2cst uNode) {
	    String sym = transCsymbol(uNode.m_symbol);
	    return sym;
    }

	private IExp transD2Eexp(Location loc, D2Eexp uNode) {
	    return transCd2exp(uNode.m_d2exp);
    }

	private ExpAtom transD2Ef0loat(Location loc, D2Ef0loat uNode) {
		throw new Error("float is not supported.");
    }

	private ExpAtom transD2Ei0nt(Location loc, D2Ei0nt uNode) {
    	return ExpAtom.makeInt(loc, uNode.m_i0nt);
    }

	private ExpIf transD2Eifopt(Location loc, D2Eifopt uNode) {

        IExp cond = transCd2exp(uNode.m_test);
        IExp btrue = transCd2exp(uNode.m_then);
        IExp bfalse = null;
        if (null != uNode.m_else) {
        	bfalse = transCd2exp(uNode.m_else);
        }
        ExpIf ret = new ExpIf(loc, cond, btrue, bfalse);
        return ret;
    }

	private ExpLam transD2Elam(Location loc, D2Elam uNode) {
        List<ExpId> paraLst = new ArrayList<ExpId>();
        for (Cp2at pat: uNode.m_p2ts) {
        	paraLst.add(transCp2at(pat));
        }
        IExp body = transCd2exp(uNode.m_d2exp);
        
        ExpLam ret = new ExpLam(loc, paraLst, body);
        return ret;
        
    }

	private ExpLet transD2Elet(Location loc, D2Elet uNode) {
	    List<IDec> decs = new ArrayList<IDec>();
	    for (Cd2ecl uDec: uNode.m_d2cs) {
	    	List<IDec> decLst = transLocalCd2ecl(uDec);
	    	decs.addAll(decLst);
	    	
	    }
	    
	    IExp exp = transCd2exp(uNode.m_d2e_body);
	    
	    return new ExpLet(loc, decs, exp);
	    
    }

	// IDec can be null;
	private List<IDec> transLocalCd2ecl(Cd2ecl uNode) {
		Location loc = new Location(uNode.d2ecl_loc);
		Id2ecl_node uDecNode = uNode.d2ecl_node;
		
		List<IDec> decs = new ArrayList<IDec>();
		
	    if (uDecNode instanceof D2Cvaldecs) {
	    	return transLocalD2Cvaldecs((D2Cvaldecs)uDecNode);
	    } else if (uDecNode instanceof D2Cfundecs) {
	    	decs.add(transD2Cfundecs(loc, (D2Cfundecs)uDecNode));
	    	return decs;
	    } else if (uDecNode instanceof D2Cimpdec) {
	    	decs.add(transD2Cimpdec(loc, (D2Cimpdec)uDecNode));
	    	return decs;
	    } else if (uDecNode instanceof D2Cignored) {
	    	System.err.println("D2Cignored is encounter.");
	    	return decs;
	    } else {
	    	throw new Error(uNode + " is not supported.");
	    }
    }
	
	private DecFunGroup transD2Cfundecs(Location loc, D2Cfundecs uNode) {

	    List<DecFunDef> funLst = new ArrayList<DecFunDef>();
	    for (Cf2undec uFunDec: uNode.m_f2ds) {
	    	DecFunDef funDef = transCf2undec(uFunDec);
	    	funLst.add(funDef);
	    }
	    
	    DecFunGroup ret = new DecFunGroup(loc, funLst);
	    return ret;
    }

	private DecFunDef transCf2undec(Cf2undec uNode) {

	    Location loc = new Location(uNode.f2undec_loc);
	    ExpId id = new ExpId(loc, transCd2var(uNode.f2undec_var));
	    
	    IExp lam = transCd2exp(uNode.f2undec_def);
	    if (lam instanceof ExpLam) {
	    	ExpLam lambda = (ExpLam)lam;
	    	DecFunDef ret = new DecFunDef(loc, id, lambda.m_paralst, lambda.m_body);
	    	return ret;
	    } else {
	    	throw new Error("Should be a lambda.");
	    }
	    
    }

	private List<IDec> transLocalD2Cvaldecs(D2Cvaldecs uNode) {
		List<IDec> vdecs = new ArrayList<IDec>();
		for (Cv2aldec uValDec: uNode.m_v2ds) {
			vdecs.add(transLocalCv2aldec(uValDec));
		}

		return vdecs;

    }

	private DecValBind transLocalCv2aldec(Cv2aldec uNode) {
		ExpId id = transCp2at(uNode.v2aldec_pat);
		IExp def = transCd2exp(uNode.v2aldec_def);
		
		Location loc = new Location(uNode.v2aldec_loc);
		return new DecValBind(loc, id, def);
    }
	
	private ExpAtom transD2Es0tring(Location loc, D2Es0tring uNode) {
    	// todo: check escape character
	    return ExpAtom.makeString(loc, uNode.m_s0tring);
    }

	private ExpId transD2Esym(Location loc, D2Esym uNode) {
	    String sym = transCd2sym(uNode.m_d2sym);
	    // map symbols (e.g. +, -, *, /, >, and etc) into functions
	    String funName = CCompUtils.sym2name(sym);
	    return new ExpId(loc, funName);
    }

	private String transCd2sym(Cd2sym uNode) {
	    return transCsymbol(uNode.d2sym_name);
    }

	private ExpId transD2Evar(Location loc, D2Evar uNode) {
	    return new ExpId(loc, transCd2var(uNode.m_d2var));
    }

	private ExpId transCp2at(Cp2at uNode) {
		Ip2at_node uPat = uNode.p2at_node;
		Location loc = new Location(uNode.p2at_loc);
		if (uPat instanceof P2Tany) {
			return transP2Tany(loc, (P2Tany)uPat);
		} else if (uPat instanceof P2Tpat) {
			return transP2Tpat(loc, (P2Tpat)uPat);
		} else if (uPat instanceof P2Tvar) {
			return transP2Tvar(loc, (P2Tvar)uPat);
		} else {
			throw new Error(uNode + " is not supported");
		}
    }

	private ExpId transP2Tvar(Location loc, P2Tvar uNode) {
	    return new ExpId(loc, transCd2var(uNode.m_var));
    }

	private String transCd2var(Cd2var uNode) {
	    return transCsymbol(uNode.m_sym);
    }

	private String transCsymbol(Csymbol uNode) {
	    return uNode.m_str;
    }

	private ExpId transP2Tpat(Location loc, P2Tpat uNode) {
		// loc is not used at all.
	    return transCp2at(uNode.m_p2at);
    }

	private ExpId transP2Tany(Location loc, P2Tany uNode) {
	    return new ExpId(loc, "_");
    }
	
	

}














