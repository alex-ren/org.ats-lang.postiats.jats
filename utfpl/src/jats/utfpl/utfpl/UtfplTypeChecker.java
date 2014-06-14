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
import jats.utfpl.utfpl.dynexp.D2ElamDyn;
import jats.utfpl.utfpl.dynexp.D2ElamMet;
import jats.utfpl.utfpl.dynexp.D2ElamSta;
import jats.utfpl.utfpl.dynexp.Id2ecl_node;
import jats.utfpl.utfpl.dynexp.Ip2at_node;
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
import jats.utfpl.utfpl.stype.VarType;


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
	    for (Cf2undec def: d2ecl.m_f2ds) {
	        oftype_fun_head(def);  // FunType or UniType(FunType)
	    }
	    
        for (Cf2undec def: d2ecl.m_f2ds) {
            typecheck_fun_body(def);
        }
    }

	private void typecheck_fun_body(Cf2undec def) {
	    // todo
	    get type from def.f2undec_var.m_stype;
	    // if unitype, do sth.
	    
	    typecheck body against xxx
        
    }

    // Either FunType or UniType(FunType)
    private ISType oftype_fun_head(Cf2undec def) {
        ISType ty = oftype_fun_head(def.f2undec_def);
        def.f2undec_var.updateSType(ty);
        return ty;
    }

	// Either FunType or UniType(FunType)
    private ISType oftype_fun_head(Cd2exp def) {
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
        
        ISType ret = null;
        
        List<ISType> paraTyLst = new ArrayList<ISType>();
        if (lambda.d2exp_node instanceof D2ElamDyn) {
            D2ElamDyn lamDyn = (D2ElamDyn)lambda.d2exp_node;
            for (Cp2at pat: lamDyn.m_p2ts) {
                ISType paraTy = oftype(pat);
                paraTyLst.add(paraTy);
            }
            
            ISType retTy = oftypeLamDynRet(lamDyn.m_d2exp);
            
            ret = new FunType(lamDyn.m_npf, paraTyLst, retTy);
            if (!tyParaLst.isEmpty()) {
                ret = new UniType(tyParaLst, ret);
                return ret;
            }
            
        } else {
            throw new Error("function expression is not of the form of lamDyn");
        }
        
        
        
    }

    private ISType oftype(Cp2at pat) {
        Ip2at_node pnode = pat.p2at_node;
        if (pnode instanceof P2Tann) {
            return oftype((P2Tann)pnode);
        } else if (pnode instanceof P2Tany) {
            return new VarType();
        } else if (pnode instanceof P2Tcon) {
            // todo
        } else if (pnode instanceof P2Tempty) {
            // todo
        } else if (pnode instanceof P2Tignored) {
            // todo
        } else if (pnode instanceof P2Tpat) {
            // todo
        } else if (pnode instanceof P2Trec) {
            // todo
        } else if (pnode instanceof P2Tvar) {
            // todo
        } else {
            // todo
        }  // todo
    }

    private ISType oftype(P2Tann pnode) {
        ISType ty = SExpTypeExtractor.extractType(pnode.m_ann);
        ISType ret = typecheck(pnode.m_p2t, ty);
        // todo
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


