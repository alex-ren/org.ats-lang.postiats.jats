package jats.utfpl.stfpl;

import java.util.ArrayList;
import java.util.List;

import jats.utfpl.stfpl.dynexp.Cd2cst;
import jats.utfpl.stfpl.dynexp.Cd2ecl;
import jats.utfpl.stfpl.dynexp.Cd2exp;
import jats.utfpl.stfpl.dynexp.Cd2sym;
import jats.utfpl.stfpl.dynexp.Cf2undec;
import jats.utfpl.stfpl.dynexp.Ci2mpdec;
import jats.utfpl.stfpl.dynexp.Cp2at;
import jats.utfpl.stfpl.dynexp.Cv2aldec;
import jats.utfpl.stfpl.dynexp.D2Cdatdecs;
import jats.utfpl.stfpl.dynexp.D2Cdcstdecs;
import jats.utfpl.stfpl.dynexp.D2Cextcode;
import jats.utfpl.stfpl.dynexp.D2Cfundecs;
import jats.utfpl.stfpl.dynexp.D2Cignored;
import jats.utfpl.stfpl.dynexp.D2Cimpdec;
import jats.utfpl.stfpl.dynexp.D2Cstacsts;
import jats.utfpl.stfpl.dynexp.D2Cvaldecs;
import jats.utfpl.stfpl.dynexp.D2EXPARGdyn;
import jats.utfpl.stfpl.dynexp.D2EXPARGsta;
import jats.utfpl.stfpl.dynexp.D2EannFunclo;
import jats.utfpl.stfpl.dynexp.D2EannSeff;
import jats.utfpl.stfpl.dynexp.D2EannType;
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
import jats.utfpl.stfpl.dynexp.D2Elist;
import jats.utfpl.stfpl.dynexp.D2Es0tring;
import jats.utfpl.stfpl.dynexp.D2Esym;
import jats.utfpl.stfpl.dynexp.D2Etup;
import jats.utfpl.stfpl.dynexp.D2Evar;
import jats.utfpl.stfpl.dynexp.Id2ecl_node;
import jats.utfpl.stfpl.dynexp.Id2exp_node;
import jats.utfpl.stfpl.dynexp.Id2exparg;
import jats.utfpl.stfpl.dynexp.Ilabp2at;
import jats.utfpl.stfpl.dynexp.Ip2at_node;
import jats.utfpl.stfpl.dynexp.LABP2ATnorm;
import jats.utfpl.stfpl.dynexp.LABP2ATomit;
import jats.utfpl.stfpl.dynexp.P2Tann;
import jats.utfpl.stfpl.dynexp.P2Tany;
import jats.utfpl.stfpl.dynexp.P2Tcon;
import jats.utfpl.stfpl.dynexp.P2Tempty;
import jats.utfpl.stfpl.dynexp.P2Tignored;
import jats.utfpl.stfpl.dynexp.P2Tpat;
import jats.utfpl.stfpl.dynexp.P2Trec;
import jats.utfpl.stfpl.dynexp.P2Tvar;
import jats.utfpl.stfpl.dynexp.ProgramUtfpl;
import jats.utfpl.stfpl.staexp.Cs2var;
import jats.utfpl.stfpl.staexp.Ifunclo;
import jats.utfpl.stfpl.staexp.SExpTypeExtractor;
import jats.utfpl.stfpl.stype.BoolType;
import jats.utfpl.stfpl.stype.FloatType;
import jats.utfpl.stfpl.stype.FunType;
import jats.utfpl.stfpl.stype.ISType;
import jats.utfpl.stfpl.stype.IntType;
import jats.utfpl.stfpl.stype.PolyParaType;
import jats.utfpl.stfpl.stype.PolyType;
import jats.utfpl.stfpl.stype.RecType;
import jats.utfpl.stfpl.stype.StringType;
import jats.utfpl.stfpl.stype.TypeCheckResult;
import jats.utfpl.stfpl.stype.VarType;
import jats.utfpl.stfpl.stype.VoidType;
import jats.utfpl.stfpl.stype.RecType.ILabPat;
import jats.utfpl.stfpl.stype.RecType.LabPatNorm;
import jats.utfpl.utils.Log;


public class UtfplTypeChecker {
    private ProgramUtfpl m_prog;
//    private Map<Cd2cst, ISType> m_tymap;

    public UtfplTypeChecker(ProgramUtfpl prog) {
        m_prog = prog;
    }
    
    public void typecheck() {
        for (Cd2ecl dec: m_prog.m_d2ecs) {
            typecheck_dec(dec);
        }
    }

    private void typecheck_dec(Cd2ecl dec) {
    	Id2ecl_node d2ecl = dec.d2ecl_node;
    	if (d2ecl instanceof D2Cdcstdecs) {
    		typecheck_dec((D2Cdcstdecs)d2ecl);
    	} else if (d2ecl instanceof D2Cextcode) {
    		return;
    	} else if (d2ecl instanceof D2Cfundecs) {
    		typecheck_dec((D2Cfundecs)d2ecl);
    	} else if (d2ecl instanceof D2Cignored) {
    	    Log.log4j.warn("D2Cignored encountered in type checking.");
    	} else if (d2ecl instanceof D2Cimpdec) {
    		typecheck_dec((D2Cimpdec)d2ecl);
    	} else if (d2ecl instanceof D2Cstacsts) {
    		typecheck_dec((D2Cstacsts)d2ecl);
    	} else if (d2ecl instanceof D2Cvaldecs) {
    		typecheck_dec((D2Cvaldecs)d2ecl);    
    	} else if (d2ecl instanceof D2Cdatdecs) {
    	    Log.log4j.warn("D2Cdatdecs encountered in type checking.");
    	} else {
    		throw new Error(dec + " is not supported.");
    	}
    }

	private void typecheck_dec(D2Cvaldecs d2ecl) {
        for (Cv2aldec valdec: d2ecl.m_v2ds) {
            typecheck_dec(valdec);
        }
        
    }

    private void typecheck_dec(Cv2aldec valdec) {
        ISType tyPat = oftype(valdec.v2aldec_pat);
        ISType tyExp = oftype(valdec.v2aldec_def);
        tyPat.match(tyExp);
    }

    private void typecheck_dec(D2Cstacsts d2ecl) {
//        for (Cs2cst cst: d2ecl.m_s2csts) {
//            
//        } 
	    // do nothing
    }

    private void typecheck_dec(D2Cimpdec d2ecl) {
        typecheck_imp(d2ecl.m_i2mpdec);
    }

    private void typecheck_imp(Ci2mpdec imp) {
        ISType ty = oftype(imp.i2mpdec_def);
        if (ty instanceof PolyType) {
            
        } else if (ty instanceof FunType) {
            
        } else {
            // throw new Error("Check this. And ty is " + ty);
        }
    }

    private void typecheck_dec(D2Cfundecs d2ecl) {
	    // collect type information from function headers
	    for (Cf2undec def: d2ecl.m_f2ds) {
	        oftype_fun_head(def);
	    }
	    
	    // type checking function body
        for (Cf2undec def: d2ecl.m_f2ds) {
            typecheck_fun_body(def);
        }
    }

	private void typecheck_fun_body(Cf2undec def) {
	    ISType type = def.f2undec_var.getSType();
	    FunType funTy = null;
	    if (type instanceof PolyType) {
	        funTy = ((PolyType)type).getParaFunType();
	    } else if (type instanceof FunType) {
	        funTy = (FunType)type;
	    } else {
	        throw new Error("check this");
	    }
	    
	    ISType retType = funTy.getRetType();
	    
	    typecheck_lam_ret(def.f2undec_def, retType);
        
    }

	private D2ElamDyn exposeLamDyn(Cd2exp lambda) {
        // lamSta. lamSta. lamMet. lam ()
        
	    while (true) {
	        // lamSta
	        if (lambda.d2exp_node instanceof D2ElamSta) {
	            D2ElamSta lamSta = (D2ElamSta)lambda.d2exp_node;
	            lambda = lamSta.m_d2exp;
	        } else if (lambda.d2exp_node instanceof D2ElamMet) {  // LamMet
	            D2ElamMet lamMet = (D2ElamMet)lambda.d2exp_node;
	            lambda = lamMet.m_d2exp;
	        } else {
	            break;
	        }
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
        TypeCheckResult res = ty0.match(ty);
        if (!res.isGood()) {
            throw new Error("Type mismatch: " + res.getMsg() + "\n" + d2exp.d2exp_loc);
        }
    }

    private ISType oftype(Cd2exp d2exp) {
        Id2exp_node node = d2exp.d2exp_node;
        Cloc_t loc = d2exp.d2exp_loc;
        if (node instanceof D2EannFunclo) {
            return oftype((D2EannFunclo)node, loc);
        } else if (node instanceof D2EannSeff) {
            return oftype((D2EannSeff)node, loc);
        } else if (node instanceof D2EannType) {
            return oftype((D2EannType)node, loc);
        } else if (node instanceof D2Eapplst) {
            return oftype((D2Eapplst)node, loc);
        } else if (node instanceof D2Ecst) {
            return oftype((D2Ecst)node, loc);
        } else if (node instanceof D2Eempty) {
            ((D2Eempty)node).updateType(VoidType.cInstance);
            return ((D2Eempty)node).getSType();
        } else if (node instanceof D2Eexp) {
            return oftype(((D2Eexp)node).m_d2exp);
        } else if (node instanceof D2Ef0loat) {
            ((D2Ef0loat)node).updateType(FloatType.cInstance);
            return ((D2Ef0loat)node).getSType();
        } else if (node instanceof D2Ei0nt) {
            ((D2Ei0nt)node).updateType(IntType.cInstance);
            return ((D2Ei0nt)node).getSType();
        } else if (node instanceof D2Eifopt) {
            return oftype((D2Eifopt)node, loc);
        } else if (node instanceof D2Eignored) {
            throw new Error("Check this");
        } else if (node instanceof D2ElamDyn) {
            return oftype((D2ElamDyn)node, loc);
        } else if (node instanceof D2ElamMet) {
            return oftype((D2ElamMet)node, loc);
        } else if (node instanceof D2ElamSta) {
            return oftype((D2ElamSta)node, loc);
        } else if (node instanceof D2Elet) {
            return oftype((D2Elet)node, loc);
        } else if (node instanceof D2Es0tring) {
            ((D2Es0tring)node).updateType(StringType.cInstance);
            return ((D2Es0tring)node).getSType();
        } else if (node instanceof D2Esym) {
            return oftype((D2Esym)node, loc);
        } else if (node instanceof D2Etup) {
            return oftype((D2Etup)node, loc);
        } else if (node instanceof D2Elist) {
            return oftype((D2Elist)node, loc);            
        } else if (node instanceof D2Evar) {
            return oftype((D2Evar)node, loc);
        } else {
            throw new Error(d2exp + " is not supported");
        }

    }

    private ISType oftype(D2Elist node, Cloc_t loc) {
        List<ILabPat> labPatLst = new ArrayList<ILabPat>();
        
        int i = 0;
        for (Cd2exp exp: node.m_d2es) {
            ISType ty = oftype(exp);
            LABint lab = new LABint(i);
            LabPatNorm labexp = new LabPatNorm(lab, ty);
            labPatLst.add(labexp);
            ++i;
        }
        RecType ret = new RecType(labPatLst, node.m_npf);
        node.updateType(ret);
        return ret;
    }

    private VarType oftype(D2Evar node, Cloc_t loc) {
        VarType ret = new VarType();
        node.m_d2var.updateSType(ret);
        return ret;
    }

    private RecType oftype(D2Etup node, Cloc_t loc) {
        List<ILabPat> labPatLst = new ArrayList<ILabPat>();
        
        int i = 0;
        for (Cd2exp exp: node.m_d2es) {
            ISType ty = oftype(exp);
            LABint lab = new LABint(i);
            LabPatNorm labexp = new LabPatNorm(lab, ty);
            labPatLst.add(labexp);
            ++i;
        }
        RecType ret = new RecType(labPatLst, node.m_npf, node.m_knd);
        node.updateType(ret);
        return ret;
    }

    private ISType oftype(D2Esym node, Cloc_t loc) {
        Cd2sym sym = node.m_d2sym;
        if (null == sym.getSType()) {
            sym.updateSType(new VarType());
        }
        
        return sym.getSType();
    }

    private ISType oftype(D2Elet node, Cloc_t loc) {
        for (Cd2ecl dec: node.m_d2cs) {
            typecheck_dec(dec);
        }
        
        ISType ret = oftype(node.m_d2e_body);
        node.updateType(ret);
        return ret;
    }

    private ISType oftype(D2ElamMet node, Cloc_t loc) {
        return oftype(node.m_d2exp);
    }

    private PolyType oftype(D2ElamSta node, Cloc_t loc) {
        List<PolyParaType> tyParaLst = new ArrayList<PolyParaType>();
        for (Cs2var para: node.m_s2vs) {
            PolyParaType tyPara = SExpTypeExtractor.extractType(para);
            if (null != tyPara) {
                tyParaLst.add(tyPara);
            }
        }
        
        ISType restTy = oftype(node.m_d2exp);
        PolyType ret = new PolyType(tyParaLst, restTy);
        node.updateType(ret);
        return ret;
        
    }

    private FunType oftype(D2ElamDyn node, Cloc_t loc) {
        List<ISType> paraTyLst = new ArrayList<ISType>();
        for (Cp2at pat : node.m_p2ts) {
            ISType paraTy = oftype(pat);
            paraTyLst.add(paraTy);
        }

        ISType retTy = oftype(node.m_d2exp);
        
        Ifunclo funclo = getClosureInformation(node.m_d2exp);
        
        FunType ret = new FunType(node.m_npf, paraTyLst, retTy, funclo);
        node.updateSType(ret);

        return ret;
    }

    private ISType oftype(D2Eifopt node, Cloc_t loc) {
        typecheck(node.m_test, BoolType.cInstance);
        ISType ty = oftype(node.m_then);
        if (null != node.m_else) {
            typecheck(node.m_else, ty);
        }
        
        node.updateType(ty);
        return ty;
    }

    private ISType oftype(D2Ecst node, Cloc_t loc) {
        return oftype(node.m_d2cst, loc);
    }

    private ISType oftype(Cd2cst d2cst, Cloc_t loc) {
        if (null == d2cst.m_stype) {
            throw new Error("m_stype of " + d2cst.toString() + " should have been set. @\n" + loc);
        } else {
            return d2cst.m_stype;
        }
    }

    private ISType oftype(D2Eapplst node, Cloc_t loc) {
        List<Id2exparg> argsLst = node.m_d2as_arg;
        ISType ty = oftype(node.m_d2e_fun);
        ty = ty.normalize();
        ISType ret = null;
        if (ty instanceof PolyType) {
            FunType funTy = ((PolyType)ty).getNormalFunType();
            ret = oftype(funTy, argsLst, loc);
        } else if (ty instanceof FunType) {
            FunType funTy = (FunType)ty;
            ret = oftype(funTy, argsLst, loc);
        } else if (ty instanceof VarType) {
            ret = oftype((VarType)ty, argsLst);
        } else {
            throw new Error("Such difficult type checking task on " + 
                   ty + " is not acceptable currently." + loc);
        }
        
        node.updateSType(ret);
        return ret;
    }
    
    /* ****************** ******************* */
    
    private ISType oftype(FunType funType, List<Id2exparg> argsLst, Cloc_t loc) {
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
                    throw new Error("Type mismatched@\n" + loc);
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
                    return oftype((FunType)retType, nArgsLst, loc);
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
    
    private ISType oftype(VarType funType0, List<Id2exparg> argsLst) {
        if (!funType0.isRaw()) {
            throw new Error("should be uninitialized");
        }
        
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
                FunType funType = new FunType(argsType, retType, null);
                funType0.setType(funType);
                
                if (i == argsLst.size() - 1) {
                    return retType;
                }
                List<Id2exparg> nArgsLst = argsLst.subList(i, argsLst.size());
                return oftype((VarType)retType, nArgsLst);

            } else {
                throw new Error(args0 + " is not supported.");
            }
        }
        throw new Error("Should not happen."); 
    }
    
    /* ****************** ******************* */
    
    private ISType oftype(D2EannType node, Cloc_t loc) {
        ISType ty0 = oftype(node.m_d2exp);
        ISType ty1 = SExpTypeExtractor.extractType(node.m_s2exp);
        if (null != ty1) {
//            System.out.println("ty0 is " + ty0 + ", ty1 is " + ty1);
            ty0.match(ty1);
        }
        node.updateType(ty0);
        return ty0;        
    }

    private ISType oftype(D2EannSeff node, Cloc_t loc) {
        return oftype(node.m_d2exp);
    }

    private ISType oftype(D2EannFunclo node, Cloc_t loc) {
        return oftype(node.m_d2exp);
    }

    private ISType oftype_fun_head(Cf2undec def) {
        ISType ty = oftype_fun_head(def.f2undec_def);
        def.f2undec_var.updateSType(ty);
        return ty;
    }

    private ISType oftype_fun_head(Cd2exp def) {
        // lamSta. lamSta. lamMet. lam ()

        if (def.d2exp_node instanceof D2ElamSta) {
            D2ElamSta lamSta = (D2ElamSta)def.d2exp_node;
            
            List<PolyParaType> tyParaLst = new ArrayList<PolyParaType>();
            for (Cs2var para: lamSta.m_s2vs) {
                PolyParaType tyPara = SExpTypeExtractor.extractType(para);
                if (null != tyPara) {
                    tyParaLst.add(tyPara);
                }
            }
            
            ISType restTy = oftype_fun_head(lamSta.m_d2exp);
            PolyType ret = new PolyType(tyParaLst, restTy);
            lamSta.updateType(ret);
            return ret;
            
        } else if (def.d2exp_node instanceof D2ElamMet) {
            D2ElamMet lamMet = (D2ElamMet)def.d2exp_node;
            return oftype_fun_head(lamMet.m_d2exp);
            
        } else if (def.d2exp_node instanceof D2ElamDyn) {
            D2ElamDyn lamDyn = (D2ElamDyn)def.d2exp_node;
            List<ISType> paraTyLst = new ArrayList<ISType>();
            for (Cp2at pat: lamDyn.m_p2ts) {
                ISType paraTy = oftype(pat);
                paraTyLst.add(paraTy);
            }

            ISType retTy = getTypeLamDynRet(lamDyn.m_d2exp);
            
            Ifunclo funclo = getClosureInformation(lamDyn.m_d2exp);
            
            FunType funTy = new FunType(lamDyn.m_npf, paraTyLst, retTy, funclo);
            lamDyn.updateSType(funTy);
            return funTy;
            
        } else {
            throw new Error("This should not happen.");
        }
    }

    private Ifunclo getClosureInformation(Cd2exp d2exp) {
        if (d2exp.d2exp_node instanceof D2EannFunclo) {
             return ((D2EannFunclo)d2exp.d2exp_node).m_funclo;
        } else {
            return null;
        }
    }

    /*
     * 
     */
    private ISType getTypeLamDynRet(Cd2exp d2exp) {
        if (d2exp.d2exp_node instanceof D2EannFunclo) {
            d2exp =((D2EannFunclo)d2exp.d2exp_node).m_d2exp;
        }
        
        if (d2exp.d2exp_node instanceof D2EannSeff) {
            D2EannSeff effNode = (D2EannSeff)d2exp.d2exp_node;
            
            if (effNode.m_d2exp.d2exp_node instanceof D2EannType) {
                D2EannType typeNode = (D2EannType)effNode.m_d2exp.d2exp_node;
                return SExpTypeExtractor.extractType(typeNode.m_s2exp);
            } else {
                // Log.log4j.warn(effNode.m_d2exp.d2exp_node + " is received, check this \n" + d2exp.d2exp_loc);
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
        
        RecType ret = new RecType(labpats, pnode.m_npf, pnode.m_knd);
        
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

    private void typecheck_dec(D2Cdcstdecs d2ecl) {
	    for (Cd2cst d2cst: d2ecl.m_d2cst) {
	    	typecheck_dec(d2cst);
	    }
	    
    }

	private ISType typecheck_dec(Cd2cst d2cst) {
	    ISType stype = SExpTypeExtractor.extractType(d2cst.m_type);
	    if (null == d2cst.m_stype && null != stype) {
	        d2cst.m_stype = stype;
	    }

	    return stype;
    }
    


}

