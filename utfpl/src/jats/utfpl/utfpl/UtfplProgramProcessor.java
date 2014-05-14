package jats.utfpl.utfpl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class UtfplProgramProcessor {

    public UtfplProgramProcessor() {
    }

    public ProgramUtfpl removeProof(ProgramUtfpl prog) {
        List<Cd2ecl> decs = new ArrayList<Cd2ecl>();
        for (Cd2ecl dec: prog.m_d2ecs) {
            Cd2ecl new_dec = removeProof(dec);
            if (null != new_dec) {
                decs.add(new_dec);
            }
        }
        ProgramUtfpl nprog = new ProgramUtfpl(decs);
        return nprog;
    }

    private Cd2ecl removeProof(Cd2ecl dec) {
        Id2ecl_node d2ecl_node = dec.d2ecl_node;
        if (d2ecl_node instanceof D2Cextcode) {
            // do nothing
        } else if (d2ecl_node instanceof D2Cfundecs) {
            d2ecl_node = removeProof((D2Cfundecs)d2ecl_node);
        } else if (d2ecl_node instanceof D2Cvaldecs) {
            d2ecl_node = removeProof((D2Cvaldecs)d2ecl_node);
        } else if (d2ecl_node instanceof D2Cimpdec) {
            d2ecl_node = removeProof((D2Cimpdec)d2ecl_node);
        } else if (d2ecl_node instanceof D2Cdcstdecs) {
            // do nothing
        } else if (d2ecl_node instanceof D2Cignored) {
            // do nothing
        } else {
            throw new Error("node" + d2ecl_node + " is not supported");
        }
        if (null != d2ecl_node) {
            return new Cd2ecl(dec.d2ecl_loc, d2ecl_node);
        } else {
            return null;
        }
        
    }

    private D2Cimpdec removeProof(D2Cimpdec d2ecl_node) {
        return new D2Cimpdec(d2ecl_node.m_knd, removeProof(d2ecl_node.m_i2mpdec));        
    }

    private Ci2mpdec removeProof(Ci2mpdec imp) {
        return new Ci2mpdec(imp.i2mpdec_loc, imp.i2mpdec_locid, 
                imp.i2mpdec_cst, removeProof(imp.i2mpdec_def));
    }

    private Id2ecl_node removeProof(D2Cvaldecs d2ecl_node) {
        List<Cv2aldec> new_v2addecs = new ArrayList<Cv2aldec>();
        
        switch (d2ecl_node.m_knd) {
        case VK_val:
        case VK_val_pos:
        case VK_val_neg:
        case VK_ignored: {
            for (Cv2aldec v2aldec: d2ecl_node.m_v2ds) {
                Cv2aldec new_v2aldec = removeProof(v2aldec);
                if (null != new_v2aldec) {
                    new_v2addecs.add(new_v2aldec);
                }
            }
            break;
        }
        case VK_prval: {
            for (Cv2aldec v2aldec: d2ecl_node.m_v2ds) {
                Cv2aldec new_v2aldec = removeProofPrval(v2aldec);
                if (null != new_v2aldec) {
                    new_v2addecs.add(new_v2aldec);
                }
            }
            break;
        }
        default:
            throw new Error("EvalKind " + d2ecl_node.m_knd + " is not supported.");
        }
        
        if (0 == new_v2addecs.size()) {
            return null;
        } else {
            return new D2Cvaldecs(d2ecl_node.m_knd, new_v2addecs);
        }
    }
    
    private Cv2aldec removeProof(Cv2aldec v2aldec) {
        Cd2exp new_def = removeProof(v2aldec.v2aldec_def);

        Cp2at new_pat = removeProof(v2aldec.v2aldec_pat);
        if (new_pat.p2at_node instanceof P2Trec) {
            P2Trec p2trec = (P2Trec)new_pat.p2at_node;
            List<Ilabp2at> labp2ats = p2trec.m_labpats;
            int size = labp2ats.size();
            if (0 == size) {
                new_pat = new Cp2at(new_pat.p2at_loc, P2Tempty.cInstance);
            } else if (1 == size) {
                // Turn singleton list into a value.
                new_pat = ((LABP2ATnorm)labp2ats.get(0)).m_pat;                
            } else {
                // do nothing
            }
        }
        
        Cv2aldec ret = new Cv2aldec(v2aldec.v2aldec_loc, new_pat, new_def);
        return ret;
    }

    public boolean isMCCall(Id2exp_node node) {
        if (node instanceof D2Eapplst) {
            D2Eapplst app = (D2Eapplst)node;
            if (isMCFun(app.m_d2e_fun.d2exp_node)) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
    
    private boolean isMCFun(Id2exp_node node) {
        if (node instanceof D2Ecst) {
            D2Ecst cst = (D2Ecst)node;
            if (cst.m_d2cst.m_symbol.m_str.startsWith("mc_")) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    /* handle the case (model checking related)
     * fun foo .<>. (x: int):<fun> int = let
     *   prval (pf|) = gen_proof ()  // remove this
     * in
     *   (pf | 3)  // turn into 3
     * end
     * prval (pf | xx) = foo (yy)  // turn into prval xx = foo (yy)
     */
    private Cv2aldec removeProofPrval(Cv2aldec v2aldec) {
        Cd2exp new_def = removeProof(v2aldec.v2aldec_def);
        
        Cp2at new_pat = removeProof(v2aldec.v2aldec_pat);
        if (new_pat.p2at_node instanceof P2Trec) {
            P2Trec p2trec = (P2Trec)new_pat.p2at_node;
            List<Ilabp2at> labp2ats = p2trec.m_labpats;
            int size = labp2ats.size();
            if (0 == size) {
                if (!isMCCall(new_def.d2exp_node)) {  // totally remove this value dec
                    return null;
                } else {
                    new_pat = new Cp2at(new_pat.p2at_loc, P2Tempty.cInstance);
                }
            } else if (1 == size) {
                // Turn singleton list into a value.
                new_pat = ((LABP2ATnorm)labp2ats.get(0)).m_pat;                
            } else {
                // do nothing
            }
        }
        
        Cv2aldec ret = new Cv2aldec(v2aldec.v2aldec_loc, new_pat, new_def);
        return ret;
    }

    private D2Cfundecs removeProof(D2Cfundecs d2ecl_node) {
        List<Cf2undec> new_f2ds = new ArrayList<Cf2undec>();
        for (Cf2undec f2undec: d2ecl_node.m_f2ds) {
            Cf2undec new_f2undec = removeProof(f2undec);
            if (null == new_f2undec) {
                throw new Error("Check this");
            }
            new_f2ds.add(new_f2undec);
        }
        
        D2Cfundecs ret = new D2Cfundecs(d2ecl_node.m_knd, new_f2ds);
        return ret;
    }

    private Cf2undec removeProof(Cf2undec f2undec) {
        Cd2exp new_def = removeProof(f2undec.f2undec_def);
        
        Cf2undec ret = new Cf2undec(f2undec.f2undec_loc, f2undec.f2undec_var, new_def);
        return ret;
    }

    private Cd2exp removeProof(Cd2exp d2exp) {
        Id2exp_node d2exp_node = d2exp.d2exp_node;
        if (d2exp_node instanceof D2Eapplst) {  // remove proofs
            d2exp_node = removeProof((D2Eapplst)d2exp_node);
        } else if (d2exp_node instanceof D2Eexp) {
            d2exp_node = removeProof((D2Eexp)d2exp_node);
        } else if (d2exp_node instanceof D2Eifopt) {
            d2exp_node = removeProof((D2Eifopt)d2exp_node);
        } else if (d2exp_node instanceof D2ElamDyn) {
            d2exp_node = removeProof((D2ElamDyn)d2exp_node);
        } else if (d2exp_node instanceof D2ElamSta) {
            d2exp_node = removeProof((D2ElamSta)d2exp_node);
        } else if (d2exp_node instanceof D2ElamMet) {
            d2exp_node = removeProof((D2ElamMet)d2exp_node);            
        } else if (d2exp_node instanceof D2Elet) {
            d2exp_node = removeProof((D2Elet)d2exp_node);
        } else if (d2exp_node instanceof D2Ecst) {
            // do nothing
        } else if (d2exp_node instanceof D2Eempty) {
            // do nothing
        } else if (d2exp_node instanceof D2Ef0loat) {
            // do nothing
        } else if (d2exp_node instanceof D2Ei0nt) {
            // do nothing
        } else if (d2exp_node instanceof D2Eignored) {
            // do nothing
        } else if (d2exp_node instanceof D2Es0tring) {
            // do nothing
        } else if (d2exp_node instanceof D2Esym) {
            // do nothing
        } else if (d2exp_node instanceof D2Evar) {
            // do nothing
        } else {
            throw new Error("d2exp " + d2exp + " is not supported.");
        }
        
        Cd2exp ret = new Cd2exp(d2exp.d2exp_loc, d2exp_node);
        return ret;
        
    }

    private D2ElamMet removeProof(D2ElamMet d2exp_node) {
        return new D2ElamMet(removeProof(d2exp_node.m_d2exp));
    }

    private D2Elet removeProof(D2Elet d2exp_node) {
        List<Cd2ecl> new_d2ecls = new ArrayList<Cd2ecl>();
        for (Cd2ecl d2ecl: d2exp_node.m_d2cs) {
            Cd2ecl new_decl = removeProof(d2ecl);
            if (null != new_decl) {
                new_d2ecls.add(new_decl);
            }
            
        }
        
        Cd2exp new_d2exp = removeProof(d2exp_node.m_d2e_body);
        
        D2Elet ret = new D2Elet(new_d2ecls, new_d2exp);
        
        return ret;
    }

    private D2ElamSta removeProof(D2ElamSta d2exp_node) {
        return new D2ElamSta(removeProof(d2exp_node.m_d2exp));
    }

    private D2ElamDyn removeProof(D2ElamDyn d2exp_node) {

        int start = d2exp_node.m_npf;
        if (start < 0) {
            start = 0;
        }
        
        List<Cp2at> paraLst = new ArrayList<Cp2at>();
        Iterator<Cp2at> iter = d2exp_node.m_p2ts.iterator();
        // skip parameters for proof
        for (int i = 0; i < start; ++i) {
            iter.next();
        }
        while (iter.hasNext()) {
            Cp2at new_p2at = removeProof(iter.next());
            paraLst.add(new_p2at);
            
        }
        
        Cd2exp new_d2exp = removeProof(d2exp_node.m_d2exp);
        D2ElamDyn ret = new D2ElamDyn(paraLst, new_d2exp, 0/*remove proof parameter*/, d2exp_node.m_lin);
        return ret;
    }

    private Cp2at removeProof(Cp2at p2at) {
        Ip2at_node new_p2at_node = p2at.p2at_node;
        if (new_p2at_node instanceof P2Tany) {
            // do nothing
        } else if (new_p2at_node instanceof P2Tempty) {
            // do nothing
        } else if (new_p2at_node instanceof P2Tignored) {
            // do nothing
        } else if (new_p2at_node instanceof P2Tpat) {
            new_p2at_node = removeProof((P2Tpat)new_p2at_node);
        } else if (new_p2at_node instanceof P2Trec) {
            new_p2at_node = removeProof((P2Trec)new_p2at_node);
        } else if (new_p2at_node instanceof P2Tvar) {
            // do nothing
        } else {
            throw new Error("p2at_node " + new_p2at_node + " is not supported.");
        }
        
        Cp2at ret = new Cp2at(p2at.p2at_loc, new_p2at_node);
        return ret;
    }

    /*
     * 
     */
    private Ip2at_node removeProof(P2Trec p2at_node) {
        int start = p2at_node.m_npf;
        if (start < 0) {
            start = 0;
        }
        List<Ilabp2at> new_labp2ats = new ArrayList<Ilabp2at>();
        Iterator<Ilabp2at> iter = p2at_node.m_labpats.iterator();
        for (int i = 0; i < start; ++i) {
            iter.next();
        }
        while (iter.hasNext()) {
            Ilabp2at new_labp2at = iter.next();
            if (new_labp2at instanceof LABP2ATnorm) {
                new_labp2at = removeProof((LABP2ATnorm)new_labp2at);
                new_labp2ats.add(new_labp2at);
            } else if (new_labp2at instanceof LABP2ATomit) {
                // do nothing // no more LABP2ATomit
            } else {
                throw new Error("Ilabp2at " + new_labp2at + " is not supported.");
            }
        }
        
        return new P2Trec(new_labp2ats, 0/*no proof*/);
    }

    private LABP2ATnorm removeProof(LABP2ATnorm labp2at) {
        Ilabel lab = labp2at.m_lab;
        if (lab instanceof LABint) {
            int old = ((LABint) lab).m;
            lab = new LABint(old);
        } else if (lab instanceof LABsym) {
            // do nothing
        } else {
            throw new Error("Ilabel " + lab + " is not supported.");
        }
        
        Cp2at pat = removeProof(labp2at.m_pat);
        
        LABP2ATnorm ret = new LABP2ATnorm(lab, pat);
        return ret;
    }

    private P2Tpat removeProof(P2Tpat p2at_node) {
        return new P2Tpat(removeProof(p2at_node.m_p2at));
    }

    private D2Eifopt removeProof(D2Eifopt d2exp_node) {
        Cd2exp n_test = removeProof(d2exp_node.m_test);
        Cd2exp n_then = removeProof(d2exp_node.m_then);
        Cd2exp n_else = null;
        if (null != d2exp_node.m_else) {
            n_else = removeProof(d2exp_node.m_else);
        }
        
        return new D2Eifopt(n_test, n_then, n_else);
    }

    private D2Eexp removeProof(D2Eexp d2exp_node) {
        return new D2Eexp(removeProof(d2exp_node.m_d2exp));
    }

    private D2Eapplst removeProof(D2Eapplst d2exp_node) {
        Cd2exp new_fun = removeProof(d2exp_node.m_d2e_fun);
        List<Id2exparg> new_args = new ArrayList<Id2exparg>();
        for (Id2exparg d2exparg: d2exp_node.m_d2as_arg) {
            Id2exparg new_d2exparg = d2exparg;
            if (d2exparg instanceof D2EXPARGdyn) {
                new_d2exparg = removeProof((D2EXPARGdyn)new_d2exparg);
            } else if (d2exparg instanceof D2EXPARGsta) {
                // do nothing
            } else {
                throw new Error("d2exparg " + d2exparg + " is not supported.");
            }
            new_args.add(new_d2exparg);
        }
        
        D2Eapplst ret = new D2Eapplst(new_fun, new_args);
        return ret;
    }

    /*
     * Remove the input arguments which are proofs.
     */
    private D2EXPARGdyn removeProof(D2EXPARGdyn d2exparg) {
        int start = d2exparg.m_i;
        if (start < 0) {
            start = 0;
        }
        
        List<Cd2exp> new_args = new ArrayList<Cd2exp>();
        Iterator<Cd2exp> iter = d2exparg.m_d2expLst.iterator();
        for (int i = 0; i < start; ++i) {
            iter.next();
        }
        while (iter.hasNext()) {
            Cd2exp new_exp = removeProof(iter.next());
            new_args.add(new_exp);
        }
        
        D2EXPARGdyn ret = new D2EXPARGdyn(0 /*no proof any more*/, d2exparg.m_loc, new_args);
        return ret;
        
    }


    
}

