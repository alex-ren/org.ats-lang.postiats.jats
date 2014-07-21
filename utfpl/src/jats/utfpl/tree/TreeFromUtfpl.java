package jats.utfpl.tree;

import java.util.ArrayList;
import java.util.List;

import jats.utfpl.ccomp.CCompUtils;
import jats.utfpl.stfpl.Csymbol;
import jats.utfpl.stfpl.Ilabel;
import jats.utfpl.stfpl.LABint;
import jats.utfpl.stfpl.LABsym;
import jats.utfpl.stfpl.dynexp.Cd2cst;
import jats.utfpl.stfpl.dynexp.Cd2ecl;
import jats.utfpl.stfpl.dynexp.Cd2exp;
import jats.utfpl.stfpl.dynexp.Cd2sym;
import jats.utfpl.stfpl.dynexp.Cd2var;
import jats.utfpl.stfpl.dynexp.Cf2undec;
import jats.utfpl.stfpl.dynexp.Ci2mpdec;
import jats.utfpl.stfpl.dynexp.Cp2at;
import jats.utfpl.stfpl.dynexp.Cv2aldec;
import jats.utfpl.stfpl.dynexp.D2Cdcstdecs;
import jats.utfpl.stfpl.dynexp.D2Cextcode;
import jats.utfpl.stfpl.dynexp.D2Cfundecs;
import jats.utfpl.stfpl.dynexp.D2Cignored;
import jats.utfpl.stfpl.dynexp.D2Cimpdec;
import jats.utfpl.stfpl.dynexp.D2Cvaldecs;
import jats.utfpl.stfpl.dynexp.D2EXPARGdyn;
import jats.utfpl.stfpl.dynexp.D2EXPARGsta;
import jats.utfpl.stfpl.dynexp.D2Eapplst;
import jats.utfpl.stfpl.dynexp.D2Ecst;
import jats.utfpl.stfpl.dynexp.D2Eempty;
import jats.utfpl.stfpl.dynexp.D2Eexp;
import jats.utfpl.stfpl.dynexp.D2Ef0loat;
import jats.utfpl.stfpl.dynexp.D2Ei0nt;
import jats.utfpl.stfpl.dynexp.D2Eifopt;
import jats.utfpl.stfpl.dynexp.D2Eignored;
import jats.utfpl.stfpl.dynexp.D2ElamDyn;
import jats.utfpl.stfpl.dynexp.D2ElamMet;
import jats.utfpl.stfpl.dynexp.D2ElamSta;
import jats.utfpl.stfpl.dynexp.D2Elet;
import jats.utfpl.stfpl.dynexp.D2Es0tring;
import jats.utfpl.stfpl.dynexp.D2Esym;
import jats.utfpl.stfpl.dynexp.D2Evar;
import jats.utfpl.stfpl.dynexp.Edcstkind;
import jats.utfpl.stfpl.dynexp.Id2ecl_node;
import jats.utfpl.stfpl.dynexp.Id2exp_node;
import jats.utfpl.stfpl.dynexp.Id2exparg;
import jats.utfpl.stfpl.dynexp.Ilabp2at;
import jats.utfpl.stfpl.dynexp.Ip2at_node;
import jats.utfpl.stfpl.dynexp.LABP2ATnorm;
import jats.utfpl.stfpl.dynexp.LABP2ATomit;
import jats.utfpl.stfpl.dynexp.P2Tany;
import jats.utfpl.stfpl.dynexp.P2Tempty;
import jats.utfpl.stfpl.dynexp.P2Tignored;
import jats.utfpl.stfpl.dynexp.P2Tpat;
import jats.utfpl.stfpl.dynexp.P2Trec;
import jats.utfpl.stfpl.dynexp.P2Tvar;
import jats.utfpl.stfpl.dynexp.ProgramUtfpl;
import jats.utfpl.tree.IExp;


public class TreeFromUtfpl {
	
	public ProgramTree trans(ProgramUtfpl uProg) {
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
	    } else if (uDecNode instanceof D2Cextcode) {
	        decs.add(transD2Cextcode(loc, (D2Cextcode)uDecNode));
	        return decs;
	    } else if (uDecNode instanceof D2Cdcstdecs) {
	        return transGlobalD2Cdcstdecs(loc, (D2Cdcstdecs)uDecNode);
	    } else if (uDecNode instanceof D2Cignored) {
//	    	System.err.println("D2Cignored is encounter.");
	    	return decs;
	    } else {
	    	throw new Error(uNode + " is not supported.");
	    }
    }

	private List<IDec> transGlobalD2Cdcstdecs(Location loc, D2Cdcstdecs decs) {
        List<IDec> cstdecs = new ArrayList<IDec>();
        if (decs.m_dck == Edcstkind.DCK_prfun) {
            // do nothing
        } else if (decs.m_dck == Edcstkind.DCK_val) {
            for (Cd2cst cstdec: decs.m_d2cst) {
                cstdecs.add(transGlobalCstValDec(loc, cstdec));
            }
        } else if (decs.m_dck == Edcstkind.DCK_fun) {
            for (Cd2cst cstdec: decs.m_d2cst) {
                cstdecs.add(transGlobalCstFunDec(loc, cstdec));
            }
        } else {
            throw new Error("kind " + decs.m_knd + " is not supported");
        }

        return cstdecs;
    }

    private DecVarDef transGlobalCstValDec(Location loc, Cd2cst cstdec) {
        ExpId id = new ExpId(loc, transCd2cst(cstdec));
        DecVarDef dec = new DecVarDef(loc, id, null);
        return dec;
    }

    private DecFunDec transGlobalCstFunDec(Location loc, Cd2cst cstdec) {
        ExpId id = new ExpId(loc, transCd2cst(cstdec));
        DecFunDec dec = new DecFunDec(loc, id, null, new ArrayList<ExpId>());
        return dec;
    }

    private DecExtCode transD2Cextcode(Location loc, D2Cextcode uNode) {
        return new DecExtCode(loc, uNode.m_code);
    }

    private DecFunImpl transD2Cimpdec(Location loc, D2Cimpdec uNode) {
	    DecFunImpl funDef = transCi2mpdec(uNode.m_i2mpdec);
	    return funDef;
    }

	private DecFunImpl transCi2mpdec(Ci2mpdec uNode) {
	    Location loc = new Location(uNode.i2mpdec_loc);
	    ExpId id = new ExpId(loc, transCd2cst(uNode.i2mpdec_cst));
	    
	    IExp lam = transCd2exp(uNode.i2mpdec_def);
	    if (lam instanceof ExpLam) {
	    	ExpLam lambda = (ExpLam)lam;
	    	DecFunImpl ret = new DecFunImpl(loc, id, null, lambda.m_paralst, lambda.m_body);
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

	private IDec transGlobalCv2aldec(Cv2aldec uNode) {
		Location loc = new Location(uNode.v2aldec_loc);
		
		IPat pat = transCp2at(uNode.v2aldec_pat);
		IExp def = transCd2exp(uNode.v2aldec_def);
		if (def instanceof ExpApp) {
			ExpApp app = (ExpApp)def;
			ExpId funName = app.getFunName();
			if (funName.isSysGvarCreate()) {
				DecVarDef dec = new DecVarDef(loc, (ExpId)IPat2IExp(pat), app.m_explst.get(0));
				return dec;
			} else if (funName.isSysGarrCreate()) {
			    ExpAtom szExp = (ExpAtom)app.m_explst.get(0);
			    int sz = szExp.toInt();
			    DecVarArrayDef dec = new DecVarArrayDef(loc, (ExpId)IPat2IExp(pat), sz, EType.eInteger);
                return dec;
            } else if (funName.isSysGvarUpdate()) {
				ExpId realId = (ExpId) app.m_explst.get(0);
				DecVarAssign dec = new DecVarAssign(loc, realId, app.m_explst.get(1));
				return dec;
            } else if (funName.isSysMCGetInt()) {
                throw new Error("funName.isSysMCGetInt()");
			} else {
				return new DecValDef(loc, IPat2IExp(pat), def);
			}
			
		} else {
			return new DecValDef(loc, IPat2IExp(pat), def);
		}

    }

	private IExp transCd2exp(Cd2exp uNode) {
        Id2exp_node uExpNode = uNode.d2exp_node;
        Location loc = new Location(uNode.d2exp_loc);
        if (uExpNode instanceof D2Eapplst) {
        	D2Eapplst d2App = (D2Eapplst)uExpNode;
        	IExp fun = transCd2exp(d2App.m_d2e_fun);
        	if (fun instanceof ExpId) {
            	if (((ExpId)fun).isSysGvarGet()) {
            		return transId2exparg(d2App.m_d2as_arg.get(d2App.m_d2as_arg.size() - 1)).get(0);
            	}
        	}
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
        } else if (uExpNode instanceof D2ElamDyn) {
        	return transD2Elam(loc, (D2ElamDyn)uExpNode);
        } else if (uExpNode instanceof D2ElamSta) {
            return transCd2exp(((D2ElamSta)uExpNode).m_d2exp);
        } else if (uExpNode instanceof D2ElamMet) {
            return transCd2exp(((D2ElamMet)uExpNode).m_d2exp);            
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
        Id2exparg exparg = null;
        if (uNode.m_d2as_arg.size() > 1) {
//            System.err.println("D2Eapplst has " + uNode.m_d2as_arg.size() +  "lists of arguments");
            exparg = uNode.m_d2as_arg.get(1);
        } else {
            exparg = uNode.m_d2as_arg.get(0);
        }
        
//        for (Id2exparg exparg: uNode.m_d2as_arg) {
        	List<IExp> tempargs = transId2exparg(exparg);
        	argsLst.addAll(tempargs);
//        }
        
        IExp fun = transCd2exp(uNode.m_d2e_fun);
        
        if (fun instanceof ExpId) {
        	if (((ExpId)fun).isSysThreadCreate()) {
        		// val () = sys_thread_create (1 (*thread id*), funid, arg)
        		ExpId funListNil = new ExpId(loc, CCompUtils.cSysListNil);
        		ExpId funListCons = new ExpId(loc, CCompUtils.cSysListCons);
        		ExpId funThreadCreate = new ExpId(loc, CCompUtils.cSysThreadCreate);
        		
        		List<IExp> args = new ArrayList<IExp>();
        		ExpApp appListNil = new ExpApp(loc, funListNil, args);
        		
        		args = new ArrayList<IExp>();
        		// (arg, sys_list_nil ())
        		args.add(argsLst.get(2));
        		args.add(appListNil);
        		ExpApp appListCons = new ExpApp(loc, funListCons, args);
        		
        		args = new ArrayList<IExp>();
        		// val () = sys_thread_create (1 (*thread id*), funid, args)
        		args.add(argsLst.get(0));
        		args.add(argsLst.get(1));
        		args.add(appListCons);
        		ExpApp appThreadCreate = new ExpApp(loc, funThreadCreate, args);
        		
        		return appThreadCreate;
        		
        	}
        	else {
                ExpApp ret = new ExpApp(loc, fun, argsLst);
                return ret;
        	}
        } else {
        	throw new Error("function as first class value is not supported");
        }
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

	private ExpLam transD2Elam(Location loc, D2ElamDyn uNode) {
        List<ExpId> paraLst = new ArrayList<ExpId>();
        for (Cp2at pat: uNode.m_p2ts) {
        	paraLst.add((ExpId)IPat2IExp(transCp2at(pat)));
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
//	    	System.err.println("D2Cignored is encounter.");
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
	    	DecFunDef ret = new DecFunDef(loc, id, null, lambda.m_paralst, lambda.m_body);
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

	private IDec transLocalCv2aldec(Cv2aldec uNode) {
		Location loc = new Location(uNode.v2aldec_loc);
		
		IPat pat = transCp2at(uNode.v2aldec_pat);
		IExp def = transCd2exp(uNode.v2aldec_def);
		if (def instanceof ExpApp) {
			ExpApp app = (ExpApp)def;
			ExpId funName = app.getFunName();
			if (funName.isSysGvarCreate() || funName.isSysGarrCreate()) {
				throw new Error("Cannot define global variable locally.");
			} else if (funName.isSysGvarUpdate()) {
				ExpId realId = (ExpId) app.m_explst.get(0);
				DecVarAssign dec = new DecVarAssign(loc, realId, app.m_explst.get(1));
				return dec;
			} else if (funName.isSysMCGetInt()) {
			    List<ExpId> vals = null;
			    if (pat instanceof PatVar) {
			        ExpId val = (ExpId)IPat2IExp(pat);
			        vals = new ArrayList<ExpId>();
			        vals.add(val);
			    } else if (pat instanceof PatRecord) {
			        vals = PatRecord2ExpIdList((PatRecord)pat);
			    }

			    return new DecMCGet(loc, vals, IExpLst2ExpIdLst(app.m_explst));
			} else {
			    IExp id = IPat2IExp(pat);
				return new DecValBind(loc, id, def);
			}
			
		} else {
		    IExp id = IPat2IExp(pat);
			return new DecValBind(loc, id, def);
		}
    }
	
	private List<ExpId> IExpLst2ExpIdLst(List<IExp> explst) {
        List<ExpId> ids = new ArrayList<ExpId>();
        for (IExp exp: explst) {
            ExpId id = (ExpId)exp;
            ids.add(id);
        }
        return ids;
    }

    private List<ExpId> PatRecord2ExpIdList(PatRecord pat) {
	    List<ExpId> ids = new ArrayList<ExpId>();
	    for (PatRecord.PatLabItem labitem: pat.m_labs) {
	        ExpId id = (ExpId)IPat2IExp(labitem.m_pat);
	        ids.add(id);	        
	    }
	    return ids;
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
	    return transCsymbol(uNode.m_d2sym_name);
    }

	private ExpId transD2Evar(Location loc, D2Evar uNode) {
	    return new ExpId(loc, transCd2var(uNode.m_d2var));
    }

	private IPat transCp2at(Cp2at uNode) {
		Ip2at_node uPat = uNode.p2at_node;
		Location loc = new Location(uNode.p2at_loc);
		if (uPat instanceof P2Tany) {
			return transP2Tany(loc, (P2Tany)uPat);
		} else if (uPat instanceof P2Tpat) {
			return transP2Tpat(loc, (P2Tpat)uPat);
		} else if (uPat instanceof P2Tvar) {
			return transP2Tvar(loc, (P2Tvar)uPat);
		} else if (uPat instanceof P2Tignored) {
			return transP2Tignored(loc, (P2Tignored)uPat);
        } else if (uPat instanceof P2Tempty) {
            return transP2Tempty(loc, (P2Tempty)uPat);		
        } else if (uPat instanceof P2Trec) {
            return transP2Trec(loc, (P2Trec)uPat);
		} else {
			throw new Error(uNode + " is not supported");
		}
    }

    private PatRecord transP2Trec(Location loc, P2Trec uNode) {
        List<PatRecord.PatLabItem> labs = new ArrayList<PatRecord.PatLabItem>();
        for (Ilabp2at labp2at: uNode.m_labpats) {
            PatRecord.PatLabItem labitem = transIlabp2at(labp2at);
            labs.add(labitem);
        }
        
        PatRecord ret = new PatRecord(loc, labs);
        return ret;
    }

    private PatRecord.PatLabItem transIlabp2at(Ilabp2at labp2at) {
        if (labp2at instanceof LABP2ATnorm) {
            LABP2ATnorm norm = (LABP2ATnorm)labp2at;
            PatRecord.PatLabItem.Ilabel patlab = transIlabel(norm.m_lab);
            IPat patval = transCp2at(norm.m_pat);
            return new PatRecord.PatLabItem(patlab, patval);     
        } else if (labp2at instanceof LABP2ATomit) {
            throw new Error("This should not happen.");
        } else {
            throw new Error("labp2at " + labp2at + " is not supported");
        }
    }

    private PatRecord.PatLabItem.Ilabel transIlabel(Ilabel lab) {
        if (lab instanceof LABsym) {
            return new PatRecord.PatLabItem.PatLabSym(transCsymbol(((LABsym)lab).m_sym));
        } else if (lab instanceof LABint) {
            return new PatRecord.PatLabItem.PatLabInt(((LABint)lab).m);
        } else {
            throw new Error("Ilabel " + lab + " is not supported.");
        }
    }

    private PatEmpty transP2Tempty(Location loc, P2Tempty uNode) {
        return new PatEmpty(loc);
    }

	private PatIgnore transP2Tignored(Location loc, P2Tignored uPat) {
	    return new PatIgnore(loc);
    }

	private PatVar transP2Tvar(Location loc, P2Tvar uNode) {
	    return new PatVar(loc, transCd2var(uNode.m_var));
    }

	private String transCd2var(Cd2var uNode) {
	    return transCsymbol(uNode.m_sym);
    }

	private String transCsymbol(Csymbol uNode) {
	    return uNode.m_str;
    }

	private IPat transP2Tpat(Location loc, P2Tpat uNode) {
		// We use the loc information which is more related to the object of our focus,
	    // instead of the loc information of the encapsulation.
	    return transCp2at(uNode.m_p2at);
    }

	private PatAny transP2Tany(Location loc, P2Tany uNode) {
	    return new PatAny(loc);
    }
	
	
	IExp IPat2IExp(IPat pat) {
	    if (pat instanceof PatAny) {
	        return new ExpId(pat.getLoc(), "_");
	    } else if (pat instanceof PatVar) {
	        return new ExpId(pat.getLoc(), ((PatVar)pat).m_sid);
	    } else if (pat instanceof PatIgnore) {
	        return new ExpId(pat.getLoc(), null);
	    } else if (pat instanceof PatEmpty) {
	        return ExpTuple.makeVoid(pat.getLoc()); 
	    } else if (pat instanceof PatRecord) {
	        throw new Error("PatRecord is not supported");
	    } else {
            throw new Error(pat + " is not supported");
        }
	}
	

}














