package jats.utfpl.utfpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import jats.utfpl.utfpl.dynexp.Cd2cst;
import jats.utfpl.utfpl.dynexp.Cd2ecl;
import jats.utfpl.utfpl.dynexp.Cd2exp;
import jats.utfpl.utfpl.dynexp.Cf2undec;
import jats.utfpl.utfpl.dynexp.Cp2at;
import jats.utfpl.utfpl.dynexp.D2Cdcstdecs;
import jats.utfpl.utfpl.dynexp.D2Cextcode;
import jats.utfpl.utfpl.dynexp.D2Cfundecs;
import jats.utfpl.utfpl.dynexp.D2Cignored;
import jats.utfpl.utfpl.dynexp.D2Cimpdec;
import jats.utfpl.utfpl.dynexp.D2Cstacsts;
import jats.utfpl.utfpl.dynexp.D2Cvaldecs;
import jats.utfpl.utfpl.dynexp.D2EXPARGdyn;
import jats.utfpl.utfpl.dynexp.D2EXPARGsta;
import jats.utfpl.utfpl.dynexp.D2EannFunclo;
import jats.utfpl.utfpl.dynexp.D2EannSeff;
import jats.utfpl.utfpl.dynexp.D2EannType;
import jats.utfpl.utfpl.dynexp.D2Eapplst;
import jats.utfpl.utfpl.dynexp.D2ElamDyn;
import jats.utfpl.utfpl.dynexp.D2ElamMet;
import jats.utfpl.utfpl.dynexp.D2ElamSta;
import jats.utfpl.utfpl.dynexp.Id2ecl_node;
import jats.utfpl.utfpl.dynexp.Id2exp_node;
import jats.utfpl.utfpl.dynexp.Id2exparg;
import jats.utfpl.utfpl.dynexp.Ilabp2at;
import jats.utfpl.utfpl.dynexp.Ip2at_node;
import jats.utfpl.utfpl.dynexp.LABP2ATnorm;
import jats.utfpl.utfpl.dynexp.LABP2ATomit;
import jats.utfpl.utfpl.dynexp.P2Tann;
import jats.utfpl.utfpl.dynexp.P2Tany;
import jats.utfpl.utfpl.dynexp.P2Tcon;
import jats.utfpl.utfpl.dynexp.P2Tempty;
import jats.utfpl.utfpl.dynexp.P2Tignored;
import jats.utfpl.utfpl.dynexp.P2Tpat;
import jats.utfpl.utfpl.dynexp.P2Trec;
import jats.utfpl.utfpl.dynexp.P2Tvar;
import jats.utfpl.utfpl.dynexp.ProgramUtfpl;
import jats.utfpl.utfpl.staexp.Cs2exp;
import jats.utfpl.utfpl.staexp.Cs2var;
import jats.utfpl.utfpl.staexp.SExpTypeExtractor;
import jats.utfpl.utfpl.stype.AppType;
import jats.utfpl.utfpl.stype.FunType;
import jats.utfpl.utfpl.stype.ISType;
import jats.utfpl.utfpl.stype.PolyParaType;
import jats.utfpl.utfpl.stype.RecType;
import jats.utfpl.utfpl.stype.UniType;
import jats.utfpl.utfpl.stype.VarType;
import jats.utfpl.utfpl.stype.VoidType;
import jats.utfpl.utils.Log;


public class UtfplTypeChecker {
    private ProgramUtfpl m_prog;
//    private Map<Cd2cst, ISType> m_tymap;

    public UtfplTypeChecker(ProgramUtfpl prog) {
        m_prog = prog;
    }
    
    public void typecheck() {
        for (Cd2ecl dec: m_prog.m_d2ecs) {
            typecheck(dec);
        }
    }

    private void typecheck(Cd2ecl dec) {
    	Id2ecl_node d2ecl = dec.d2ecl_node;
    	if (d2ecl instanceof D2Cdcstdecs) {
    		typecheck((D2Cdcstdecs)d2ecl);
    	} else if (d2ecl instanceof D2Cextcode) {
    		return;
    	} else if (d2ecl instanceof D2Cfundecs) {
    		typecheck((D2Cfundecs)d2ecl);
    	} else if (d2ecl instanceof D2Cignored) {
    		typecheck((D2Cignored)d2ecl);
    	} else if (d2ecl instanceof D2Cimpdec) {
    		typecheck((D2Cimpdec)d2ecl);
    	} else if (d2ecl instanceof D2Cstacsts) {
    		typecheck((D2Cstacsts)d2ecl);
    	} else if (d2ecl instanceof D2Cvaldecs) {
    		typecheck((D2Cvaldecs)d2ecl);    		
    	} else {
    		throw new Error(dec + " is not supported.");
    	}
    }

	private void typecheck(D2Cfundecs d2ecl) {
	    // collect type information from function header
	    for (Cf2undec def: d2ecl.m_f2ds) {
	        oftype_fun_head(def);  // UniType(FunType)
	    }
	    
	    // type checking function body
        for (Cf2undec def: d2ecl.m_f2ds) {
            typecheck_fun_body(def);
        }
    }

	private void typecheck_fun_body(Cf2undec def) {
	    ISType type = def.f2undec_var.getSType();
	    FunType funtype = null;
	    if (type instanceof UniType) {
	        funtype = ((UniType)type).getParaFunType();
	    } else if (type instanceof FunType) {
	        funtype = (FunType)type;
	    } else {
	        throw new Error("check this");
	    }
	    
	    ISType retType = funtype.getRetType();
	    
	    typecheck_lam_ret(def.f2undec_def, retType);
        
    }

	private D2ElamDyn exposeLamDyn(Cd2exp lambda) {
        // lamSta. lamSta. lamMet. lam ()
        // lamSta
        while (lambda.d2exp_node instanceof D2ElamSta) {
            D2ElamSta lamSta = (D2ElamSta)lambda.d2exp_node;
            lambda = lamSta.m_d2exp;
        }
        // LamMet
        while (lambda.d2exp_node instanceof D2ElamMet) {
            D2ElamMet lamMet = (D2ElamMet)lambda.d2exp_node;
            lambda = lamMet.m_d2exp;
        }
        
        if (lambda.d2exp_node instanceof D2ElamDyn) {
            return (D2ElamDyn)lambda.d2exp_node;
        } else {
            throw new Error("Check this.");
        }
	}
	
    private void typecheck_lam_ret(Cd2exp lambda, ISType retType) {
        D2ElamDyn lam = exposeLamDyn(lambda);
        typecheck(lam.m_d2exp, retType);
    }

    private void typecheck(Cd2exp d2exp, ISType ty) {
        ISType ty0 = oftype(d2exp);
        ty0.match(ty);
    }

    private ISType oftype(Cd2exp d2exp) {
        Id2exp_node node = d2exp.d2exp_node;
        if (node instanceof D2EannFunclo) {
            return oftype((D2EannFunclo)node);
        } else if (node instanceof D2EannSeff) {
            return oftype((D2EannSeff)node);
        } else if (node instanceof D2EannType) {
            return oftype((D2EannType)node);
        } else if (node instanceof D2Eapplst) {
            return oftype((D2Eapplst)node);
        } else if (node instanceof D2Ecst) {
            return oftype((xxx)node);
        } else if (node instanceof D2Eempty) {
            return oftype((xxx)node);
        } else if (node instanceof D2Eexp) {
            return oftype((xxx)node);
        } else if (node instanceof D2Ef0loat) {
            return oftype((xxx)node);
        } else if (node instanceof D2Ei0nt) {
            return oftype((xxx)node);
        } else if (node instanceof D2Eifopt) {
            return oftype((xxx)node);
        } else if (node instanceof D2Eignored) {
            return oftype((xxx)node);
        } else if (node instanceof D2ElamDyn) {
            return oftype((xxx)node);
        } else if (node instanceof D2ElamMet) {
            return oftype((xxx)node);
        } else if (node instanceof D2ElamSta) {
            return oftype((xxx)node);
        } else if (node instanceof D2Elet) {
            return oftype((xxx)node);
        } else if (node instanceof D2Es0tring) {
            return oftype((xxx)node);
        } else if (node instanceof D2Esym) {
            return oftype((xxx)node);
        } else if (node instanceof D2Etup) {
            return oftype((xxx)node);
        } else if (node instanceof D2Evar) {
            return oftype((xxx)node);
        } else {
            throw new Error(d2exp + " is not supported");
        }

    }

    private ISType oftype(D2Eapplst node) {
        List<Id2exparg> argsLst = node.m_d2as_arg;
        ISType ty = oftype(node.m_d2e_fun);
        if (ty instanceof UniType) {
            FunType funTy = ((UniType)ty).getNormalFunType();
            return oftype(funTy, argsLst);
        } else if (ty instanceof VarType) {
            return oftype((VarType)ty, argsLst);
        } else {
            throw new Error("Such difficult type checking task is not acceptable currently.");
        }
    }
    
    /* ****************** ******************* */
    
    private ISType oftype(FunType funType, List<Id2exparg> argsLst) {
        for (int i = 0; i < argsLst.size(); ++i) {
            Id2exparg args0 = argsLst.get(i);
            if (args0 instanceof D2EXPARGsta) {
                // skip
                Log.log4j.warn("D2EXPARGsta encountered.");
            } else if (args0 instanceof D2EXPARGdyn) {
                D2EXPARGdyn args = (D2EXPARGdyn)args0;
                List<ISType> argsType = funType.m_args;
                List<Cd2exp> argsExp = args.m_d2expLst;
                if (argsType.size() != argsExp.size()) {
                    throw new Error("Type mismatched");
                }
                for (int j = 0; j < argsType.size(); ++j) {
                    typecheck(argsExp.get(j), argsType.get(j));
                }
                
                ISType retType = funType.m_res;
                if (i == argsLst.size() - 1) {
                    return retType;
                }
                List<Id2exparg> nArgsLst = argsLst.subList(i, argsLst.size());
                if (retType instanceof FunType) {
                    return oftype((FunType)retType, nArgsLst);
                } else if (retType instanceof VarType) {
                    return oftype((VarType)retType, nArgsLst);
                } else {
                    throw new Error(retType + " is not supported.");
                }
            } else {
                throw new Error(args0 + " is not supported.");
            }
        }
        throw new Error("Should not happen.");
    }
    
    private ISType oftype(VarType funType, List<Id2exparg> argsLst) {
        for (int i = 0; i < argsLst.size(); ++i) {
            Id2exparg args0 = argsLst.get(i);
            if (args0 instanceof D2EXPARGsta) {
                // skip
                Log.log4j.warn("D2EXPARGsta encountered.");
            } else if (args0 instanceof D2EXPARGdyn) {
                D2EXPARGdyn args = (D2EXPARGdyn)args0;
                List<Cd2exp> argsExp = args.m_d2expLst;
                List<ISType> argsType = new ArrayList<ISType>();
                for (Cd2exp argExp: argsExp) {
                    ISType argType = oftype(argExp);
                    argsType.add(argType);
                }
                VarType retType = new VarType();
                FunType 
                
                
                
                
                List<ISType> argsType = funType.m_args;
                List<Cd2exp> argsExp = args.m_d2expLst;
                if (argsType.size() != argsExp.size()) {
                    throw new Error("Type mismatched");
                }
                for (int j = 0; j < argsType.size(); ++j) {
                    typecheck(argsExp.get(j), argsType.get(j));
                }
                
                ISType retType = funType.m_res;
                if (i == argsLst.size() - 1) {
                    return retType;
                }
                List<Id2exparg> nArgsLst = argsLst.subList(i, argsLst.size());
                if (retType instanceof FunType) {
                    return oftype((FunType)retType, nArgsLst);
                } else if (retType instanceof VarType) {
                    return oftype((VarType)retType, nArgsLst);
                } else {
                    throw new Error(retType + " is not supported.");
                }
            } else {
                throw new Error(args0 + " is not supported.");
            }
        }
        throw new Error("Should not happen."); 
    }
    
    /* ****************** ******************* */
    
    private ISType oftype(D2EannType node) {
        ISType ty0 = oftype(node.m_d2exp);
        ISType ty1 = SExpTypeExtractor.extractType(node.m_s2exp);
        if (null != ty1) {
            ty0.match(ty1);
        }
        return ty0;        
    }

    private ISType oftype(D2EannSeff node) {
        return oftype(node.m_d2exp);
    }

    private ISType oftype(D2EannFunclo node) {
        return oftype(node.m_d2exp);
    }

    // UniType(FunType)
    private UniType oftype_fun_head(Cf2undec def) {
        UniType ty = oftype_fun_head(def.f2undec_def);
        def.f2undec_var.updateSType(ty);
        return ty;
    }

	// Either FunType or UniType(FunType)
    private UniType oftype_fun_head(Cd2exp def) {
        Cd2exp lambda = def;
        
        List<PolyParaType> tyParaLst = new ArrayList<PolyParaType>();
        
        // lamSta. lamSta. lamMet. lam ()
        // lamSta
        while (lambda.d2exp_node instanceof D2ElamSta) {
            D2ElamSta lamSta = (D2ElamSta)lambda.d2exp_node;
            for (Cs2var para: lamSta.m_s2vs) {
                PolyParaType tyPara = SExpTypeExtractor.extractType(para);
                if (null != tyPara) {
                    tyParaLst.add(tyPara);
                }
            }
            
            lambda = lamSta.m_d2exp;
        }
        // LamMet
        while (lambda.d2exp_node instanceof D2ElamMet) {
            D2ElamMet lamMet = (D2ElamMet)lambda.d2exp_node;
            lambda = lamMet.m_d2exp;
        }
        
        List<ISType> paraTyLst = new ArrayList<ISType>();
        if (lambda.d2exp_node instanceof D2ElamDyn) {
            D2ElamDyn lamDyn = (D2ElamDyn)lambda.d2exp_node;
            for (Cp2at pat: lamDyn.m_p2ts) {
                ISType paraTy = oftype(pat);
                paraTyLst.add(paraTy);
            }
            
            ISType retTy = gettypeLamDynRet(lamDyn.m_d2exp);
            
            FunType funtype = new FunType(lamDyn.m_npf, paraTyLst, retTy);
            
            UniType ret = new UniType(tyParaLst, funtype);

            return ret;
            
        } else {
            throw new Error("function expression is not of the form of lamDyn");
        }

    }

    /*
     * 
     */
    private ISType gettypeLamDynRet(Cd2exp d2exp) {
        if (d2exp.d2exp_node instanceof D2EannSeff) {
            D2EannSeff effNode = (D2EannSeff)d2exp.d2exp_node;
            
            if (effNode.m_d2exp.d2exp_node instanceof D2EannType) {
                D2EannType typeNode = (D2EannType)effNode.m_d2exp.d2exp_node;
                return SExpTypeExtractor.extractType(typeNode.m_s2exp);
            } else {
                Log.log4j.warn(effNode.m_d2exp.d2exp_node + " is received, check this");
                return new VarType();
            }
        } else {
            throw new Error("Check this.");
        }
    }

    private ISType oftype(Cp2at pat) {
        Ip2at_node pnode = pat.p2at_node;
        if (pnode instanceof P2Tann) {
            return oftype((P2Tann)pnode);
        } else if (pnode instanceof P2Tany) {
            return new VarType();
        } else if (pnode instanceof P2Tcon) {
            throw new Error("not supported yet");
        } else if (pnode instanceof P2Tempty) {
            return VoidType.cInstance;
        } else if (pnode instanceof P2Tignored) {
            throw new Error("Check this.");
        } else if (pnode instanceof P2Tpat) {
            return oftype(((P2Tpat)pnode).m_p2at);
        } else if (pnode instanceof P2Trec) {
            return oftype((P2Trec)pnode);
        } else if (pnode instanceof P2Tvar) {
            return oftype((P2Tvar)pnode);
        } else {
            throw new Error(pat + " is not supported.");
        }
    }

    private ISType oftype(P2Tvar pnode) {
        VarType type = new VarType();
        pnode.m_var.updateSType(type);
        return type;
    }

    private ISType oftype(P2Trec pnode) {
        List<RecType.ILabPat> labpats = new ArrayList<RecType.ILabPat>();
        for (Ilabp2at p2at: pnode.m_labpats) {
            if (p2at instanceof LABP2ATnorm) {
                LABP2ATnorm patnorm = (LABP2ATnorm)p2at;
                RecType.ILabPat labpat = oftype_pat(patnorm);
                labpats.add(labpat);
            } else if (p2at instanceof LABP2ATomit) {
                throw new Error(p2at + " is not supported.");
            } else {
                throw new Error(p2at + " is not supported.");
            }
        }
        
        RecType ret = new RecType(labpats, pnode.m_npf);
        
        return ret;
    }

    private RecType.ILabPat oftype_pat(LABP2ATnorm patnorm) {
        ISType ty = oftype(patnorm.m_pat);
        
        RecType.ILabPat ret = new RecType.LabPatNorm(patnorm.m_lab, ty);
        return ret;
    }

    private ISType oftype(P2Tann pnode) {
        ISType ty = SExpTypeExtractor.extractType(pnode.m_ann);
        ISType ret = typecheck(pnode.m_p2t, ty);
        return ret;
    }

    private ISType typecheck(Cp2at p2t, ISType ty) {
        ISType ty0 = oftype(p2t);
        ty0.match(ty);
        return ty0.normalize();
    }

    private void typecheck(D2Cdcstdecs d2ecl) {
	    for (Cd2cst d2cst: d2ecl.m_d2cst) {
	    	typecheck(d2cst);
	    }
	    
    }

	private ISType typecheck(Cd2cst d2cst) {
	    ISType stype = SExpTypeExtractor.extractType(d2cst.m_type);
	    d2cst.m_stype = stype;
	    
	    return stype;
    }
    


}


