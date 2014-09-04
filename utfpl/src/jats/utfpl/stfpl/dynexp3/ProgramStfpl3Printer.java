package jats.utfpl.stfpl.dynexp3;

import jats.utfpl.stfpl.dynexp.Edcstkind;
import jats.utfpl.stfpl.dynexp.Efunkind;
import jats.utfpl.stfpl.staexp.Cs2cst;
import jats.utfpl.stfpl.staexp.Cs2exp;
import jats.utfpl.stfpl.staexp.FUNCLOclo;
import jats.utfpl.stfpl.staexp.FUNCLOfun;
import jats.utfpl.stfpl.staexp.Ifunclo;
import jats.utfpl.stfpl.staexp.Is2rt;
import jats.utfpl.stfpl.staexp.S2RTbas;
import jats.utfpl.stfpl.staexp.S2RTfun;
import jats.utfpl.stfpl.staexp.S2RTtup;

import java.net.URL;

import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupFile;

public class ProgramStfpl3Printer {

    private STGroup m_stg;
    
    public  ProgramStfpl3Printer() {
        URL fileURL = this.getClass().getResource("/jats/utfpl/stfpl/dynexp3/stfpl.stg");
        m_stg = new STGroupFile(fileURL, "ascii", '<', '>');

    }
    
    public String print(ProgramStfpl3 uProg) {
        ST st = printUtfplProgram(uProg);
        return st.render();
    }
    
    private ST printUtfplProgram(ProgramStfpl3 uProg) {
        // stfpl_prog_st(d3ecs) ::= <<
        ST st = m_stg.getInstanceOf("stfpl_prog_st");
        for (Cd3ecl dec: uProg.m_d3ecs) {
            st.add("d3ecs", printCd3ecl(dec));
        }
        return st;
    }
    
    private ST printCd3ecl(Cd3ecl dec) {
        ST st = printId3ecl_node(dec.m_node);
        return st;
    }
    
    private ST printId3ecl_node(Id3ecl_node node) {
        if (node instanceof D3Cdatdecs) {
            return printD3Cdatdecs((D3Cdatdecs)node);
        } else if (node instanceof D3Cdcstdecs) {
            return printD3Cdcstdecs((D3Cdcstdecs)node);
        } else if (node instanceof D3Cextcode) {
            return printD3Cextcode((D3Cextcode)node);   
        } else if (node instanceof D3Cfundecs) {
            D3Cfundecs fundecs = (D3Cfundecs)node;
            return printD3Cfundecs(fundecs);
        } else if (node instanceof D3Cimpdec) {
            return printD3Cimpdec((D3Cimpdec)node);
        } else if (node instanceof D3Cstacsts) {
            return printD3Cstacsts((D3Cstacsts)node);
        } else if (node instanceof D3Cvaldecs) {
            return printD3Cvaldecs((D3Cvaldecs)node);
        } else {
            throw new Error("Id3ecl_node " + node + " is not supported");
        }
    }


    private ST printD3Cdatdecs(D3Cdatdecs node) {
	    // D3Cdatdecs_st(knd, scsts) ::= <<
        ST st = m_stg.getInstanceOf("D3Cdatdecs_st");
        st.add("knd", node.m_knd);
        for (Cs2cst cst: node.m_s2csts) {
            st.add("scsts", printCs2cst(cst));
        }
        
        return st;
    }
    

    private Object printCs2cst(Cs2cst cst) {
        // s2cst_st(s2cst) ::= <<
        ST st = m_stg.getInstanceOf("s2cst_st");
        st.add("s2cst", cst);
        return st;
    }

    private ST printD3Cdcstdecs(D3Cdcstdecs node) {
        // D3Cdcstdecs_st(knd, dcsts) ::= <<
        ST st = m_stg.getInstanceOf("D3Cdcstdecs_st");
        st.add("knd", printEdcstkind(node.m_dck));
        for (Cd3cst cst: node.m_d3cst) {
            st.add("dcsts", printCd3cst(cst));
        }
        
        return st;
    }

    private ST printEdcstkind(Edcstkind node) {
        // dcstkind_st(knd) ::= <<
        ST st = m_stg.getInstanceOf("dcstkind_st");
        st.add("knd", node.toString());
        return st;
    }
    
    private ST printCd3cst(Cd3cst node) {
        // d3cst_st(sym, stamp, stype) ::= <<
        ST st = m_stg.getInstanceOf("d3cst_st");
        st.add("sym", node.m_symbol);
        st.add("stamp", node.m_stamp);
        if (null != node.m_stype) {
            st.add("stype", node.m_stype.toSTStfpl3(m_stg));
        }
        
        return st;
    }
    
    private ST printD3Cextcode(D3Cextcode node) {
        // D3Cextcode_st(extcode) ::= <<
        ST st = m_stg.getInstanceOf("D3Cextcode_st");
        st.add("extcode", node.m_code);
        return st;
    }

    private ST printD3Cfundecs(D3Cfundecs node) {
        // D3Cfundecs_st(knd, f3ds) ::= <<
        ST st = m_stg.getInstanceOf("D3Cfundecs_st");
        ST st1 = printEfunkind(node.m_knd);
        st.add("knd", st1);
        
        for (Cf3undec f3undec: node.m_f3ds) {
            st.add("f3ds", printCf3undec(f3undec));
        }
        
        return st;

    }
    
    private ST printEfunkind(Efunkind knd) {
        // funkind_st(knd) ::= <<
        ST st = m_stg.getInstanceOf("funkind_st");
        st.add("knd", knd.toString());
        return st;
    }
    
    private ST printCf3undec(Cf3undec node) {
        // f3undec_st(loc, var, stype, def) ::= <<
        ST st = m_stg.getInstanceOf("f3undec_st");
        st.add("var", node.m_var);
        st.add("stype", node.m_type.toSTStfpl3(m_stg));
        st.add("def", printCd3exp(node.m_body));
        
        return st;
    }
    
    private ST printCd3exp(Cd3exp node) {
        // d3exp_st(d3exp_node) ::= <<
        ST st = m_stg.getInstanceOf("d3exp_st");
        st.add("d3exp_node", printId3exp_node(node.m_node));
        return st;
    }

    private ST printId3exp_node(Id3exp_node node) {
        if (node instanceof D3Eapplst) {
            return printD3Eapplst((D3Eapplst)node);
        } else if (node instanceof D3Ecst) {
            return printD3Ecst((D3Ecst)node);
        } else if (node instanceof D3Eempty) {
            return printD3Eempty();
        } else if (node instanceof D3Ef0loat) {
            return printD3Ef0loat((D3Ef0loat)node);
        } else if (node instanceof D3Ei0nt) {
            return printD3Ei0nt((D3Ei0nt)node);
        } else if (node instanceof D3Eifopt) {
            return printD3Eifopt((D3Eifopt)node);
        } else if (node instanceof D3ElamDyn) {
            return printD3ElamDyn((D3ElamDyn)node);
        } else if (node instanceof D3Elet) {
            return printD3Elet((D3Elet)node);
        } else if (node instanceof D3Es0tring) {
            return printD3Es0tring((D3Es0tring)node);
        } else if (node instanceof D3Esym) {
            return printD3Esym((D3Esym)node);
        } else if (node instanceof D3Etup) {
            return printD3Etup((D3Etup)node);
        } else if (node instanceof D3Evar) {
            return printD3Evar((D3Evar)node);
        } else {
            throw new Error(node + " is not supported.");
        }
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

	private ST printD3Cstacsts(D2Cstacsts node) {
	    // D2Cstacsts_st(scsts) ::= <<
        ST st = m_stg.getInstanceOf("D2Cstacsts_st");
        for (Cs2cst cst: node.m_s2csts) {
            st.add("scsts", printCs2cst(cst));
        }
        
        return st;
    }




	private ST printD3Cimpdec(D2Cimpdec node) {
        // D2Cimpdec_st(i2mpdec) ::= <<
        ST st = m_stg.getInstanceOf("D2Cimpdec_st");
        st.add("i2mpdec", printCi2mpdec(node.m_i2mpdec));
        return st;
    }

    private ST printCi2mpdec(Ci2mpdec node) {
        // i2mpdec_st(cst, def) ::= <<
        ST st = m_stg.getInstanceOf("i2mpdec_st");
        st.add("cst", printCd2cst(node.i2mpdec_cst));
        st.add("def", printCd2exp(node.i2mpdec_def));
        return st;
        
    }

    private ST printD3Cvaldecs(D2Cvaldecs node) {
        // D2Cvaldecs_st(knd, v2ds) ::= <<
        ST st = m_stg.getInstanceOf("D2Cvaldecs_st");
        st.add("knd", printEvalkind(node.m_knd));
        for (Cv2aldec valdec: node.m_v2ds) {
            st.add("v2ds", printCv2aldec(valdec));
        }
        
        return st;
    }

    private ST printCv2aldec(Cv2aldec node) {
        // v2aldec_st(pat, def) ::= <<
        ST st = m_stg.getInstanceOf("v2aldec_st");
        st.add("pat", printCp2at(node.v2aldec_pat));
        st.add("def", printCd2exp(node.v2aldec_def));
        
        return st;
    }

    private ST printEvalkind(Evalkind node) {
        // valkind_st(knd) ::= <<
        ST st = m_stg.getInstanceOf("valkind_st");
        st.add("knd", node.toString());
        return st;
    }

    

    


    

    private ST printD2Elist(D2Elist node) {
        // D2Elist_st(knd, prfs, d2es) ::= <<
        ST st = m_stg.getInstanceOf("D2Elist_st");
        int i = node.m_npf;
        for (Cd2exp d2e: node.m_d2es) {
            if (i > 0) {
                st.add("prfs", printCd2exp(d2e));
            } else {
                st.add("d2es", printCd2exp(d2e));
            }
            --i;            
        }
        return st;
    }

    private ST printD3Etup(D2Etup node) {
        // D2Etup_st(knd, d2es) ::= <<
        ST st = m_stg.getInstanceOf("D2Etup_st");
        st.add("knd", node.m_knd);
        for (Cd2exp d2e: node.m_d2es) {
            st.add("d2es", printCd2exp(d2e));
        }
        return st;
    }

    private ST printD2EannType(D2EannType node) {
	    // D2EannType_st(d2exp, s2exp) ::= <<
        ST st = m_stg.getInstanceOf("D2EannType_st");
        st.add("d2exp", printCd2exp(node.m_d2exp));
        st.add("s2exp", printCs2exp(node.m_s2exp));
        return st;
    }

	private ST printCs2exp(Cs2exp node) {
	    // s2exp_st(srt, s2exp) ::= <<
		ST st = m_stg.getInstanceOf("s2exp_st");
		st.add("srt", printIs2rt(node.s2exp_srt));
		return st;
    }

	private ST printIs2rt(Is2rt node) {
	    if (node instanceof S2RTbas) {
	    	return printS2RTbas((S2RTbas)node);
	    } else if (node instanceof S2RTfun) {
	        return printS2RTfun((S2RTfun)node);
	    } else if (node instanceof S2RTtup) {
	        return printS2RTtup((S2RTtup)node);
	    } else {
	    	throw new Error("Is2rt " + node + " is not supported");
	    }
    }

	private ST printS2RTtup(S2RTtup node) {
        // S2RTtup_st(eles) ::= <<
        ST st = m_stg.getInstanceOf("S2RTtup_st");
        for (Is2rt ele: node.m_s2ts) {
            st.add("eles", printIs2rt(ele));
        }

        return st;
    }

    private ST printS2RTfun(S2RTfun node) {
        // S2RTfun_st(args, res) ::= <<
	    ST st = m_stg.getInstanceOf("S2RTfun_st");
	    for (Is2rt arg: node.m_args) {
	        st.add("args", printIs2rt(arg));
	    }
	    st.add("res", printIs2rt(node.m_res));
	    return st;
    }

    private ST printS2RTbas(S2RTbas node) {
	    // S2RTbas_st(sym) ::= <<
		ST st = m_stg.getInstanceOf("S2RTbas_st");
		st.add("sym", node.m_sym);
		return st;
    }

	private ST printD2EannSeff(D2EannSeff node) {
	    // D2EannSeff_st(d2exp) ::= <<
        ST st = m_stg.getInstanceOf("D2EannSeff_st");
        st.add("d2exp", printCd2exp(node.m_d2exp));

        return st;
    }

	private ST printD2EannFunclo(D2EannFunclo node) {
        // D2EannFunclo_st(funclo, d2exp) ::= <<
        ST st = m_stg.getInstanceOf("D2EannFunclo_st");
        st.add("funclo", printIfunclo(node.m_funclo));
        st.add("d2exp", printCd2exp(node.m_d2exp));
        return st;
    }

	private Object printIfunclo(Ifunclo funclo) {
	    // Ifunclo_st(funclo) ::= <<
		ST st = m_stg.getInstanceOf("Ifunclo_st");
		if (funclo instanceof FUNCLOfun) {
			st.add("funclo", "fun");
		} else if (funclo instanceof FUNCLOclo) {
			FUNCLOclo clo = (FUNCLOclo)funclo;
			if (clo.m_knd == 1) {
				st.add("funclo", "clo_ptr");
			} else if (clo.m_knd == 0) {
				st.add("funclo", "clo_clo");
			} else if (clo.m_knd == -1) {
				st.add("funclo", "clo_ref");
			} else {
				throw new Error("Unknown type for closure.");
			}
		} else {
			throw new Error("Unknown type for clofun");
		}
		
		return st;
		
    }

	private ST printD2ElamMet(D2ElamMet node) {
        // D2ElamMet_st(d2exp) ::= <<
        ST st = m_stg.getInstanceOf("D2ElamMet_st");
        st.add("d2exp", printCd2exp(node.m_d2exp));
        return st;
    }

    private ST printD2ElamSta(D2ElamSta node) {
        // D2ElamSta_st(d2exp) ::= <<
        ST st = m_stg.getInstanceOf("D2ElamSta_st");
        st.add("d2exp", printCd2exp(node.m_d2exp));
        return st;
    }

    private ST printD2Eignored(D2Eignored node) {
        // D2Eignored_st() ::= <<
        ST st = m_stg.getInstanceOf("D2Eignored_st");
        return st;
    }

    private ST printD3Eempty() {
    	// D2Eempty_st() ::= <<
	    ST st = m_stg.getInstanceOf("D2Eempty_st");
	    return st;
    }

	private ST printD3Elet(D2Elet node) {
        // D2Elet_st(d2cs, d2e_body) ::= <<
        ST st = m_stg.getInstanceOf("D2Elet_st");
        for (Cd2ecl d2ecl: node.m_d2cs) {
            st.add("d2cs", printCd2ecl(d2ecl));
        }
        st.add("d2e_body", printCd2exp(node.m_d2e_body));
        
        return st;
    }

    private ST printD3Evar(D2Evar node) {
        // D2Evar_st(var) ::= <<
        ST st = m_stg.getInstanceOf("D2Evar_st");
        st.add("var", node.m_d2var);
        return st;
    }

    private ST printD3Esym(D2Esym node) {
        // D2Esym_st(d2sym) ::= <<
        ST st = m_stg.getInstanceOf("D2Esym_st");
        st.add("d2sym", node.m_d2sym);        

        return st;
    }

    private ST printD3Es0tring(D2Es0tring node) {
        // D2Es0tring_st(str) ::=<<
        ST st = m_stg.getInstanceOf("D2Es0tring_st");
        st.add("str", node.m_s0tring);
        return st;
    }

    private ST printD3ElamDyn(D2ElamDyn node) {
        // D2ElamDyn_st(p2ts, exp, clo) ::= <<
        ST st = m_stg.getInstanceOf("D2ElamDyn_st");
        Ifunclo funclo = node.getSType().getFunClo();
        if (null == funclo) {
            
        } else {
            if (funclo.isClosure()) {
                st.add("clo", "clo");
            } else {
                st.add("clo", "fun");
            }
        }
        
        for (Cp2at pat: node.m_p2ts) {
            st.add("p2ts", printCp2at(pat));
        }
        st.add("exp", printCd2exp(node.m_d2exp));
        return st;
    }

    private ST printCp2at(Cp2at node) {
        // p2at_st(node) ::= <<
        ST st = m_stg.getInstanceOf("p2at_st");
        st.add("node", printIp2at_node(node.p2at_node));
        return st;
    }

    private ST printIp2at_node(Ip2at_node node) {
        if (node instanceof P2Tany) {
            return printP2Tany((P2Tany)node);
        } else if (node instanceof P2Tpat) {
            return printP2Tpat((P2Tpat)node);
        } else if (node instanceof P2Tvar) {
            return printP2Tvar((P2Tvar)node);
        } else if (node instanceof P2Tignored) {
        	return printP2Tignored((P2Tignored)node);
        } else if (node instanceof P2Tempty) {
            return printP2Tempty((P2Tempty)node);
        } else if (node instanceof P2Trec) {
            return printP2Trec((P2Trec)node);
        } else if (node instanceof P2Tann) {
            return printP2Tann((P2Tann)node); 
        } else {
            throw new Error(node + " is not supported");
        }
    }
    
    private ST printP2Tann(P2Tann node) {
	    // P2Tann_st(pat, type) ::= <<
        ST st = m_stg.getInstanceOf("P2Tann_st");
        st.add("pat", printCp2at(node.m_p2t));
        st.add("type", printCs2exp(node.m_ann));

        return st;
    }

	private ST printP2Trec(P2Trec node) {
        // P2Trec_st(labpats) ::= <<
        ST st = m_stg.getInstanceOf("P2Trec_st");
        for (Ilabp2at labpat: node.m_labpats) {
            st.add("labpats", printIlabp2at(labpat));
        }
        return st;
        
    }

    private ST printIlabp2at(Ilabp2at node) {
        if (node instanceof LABP2ATnorm) {
            return printLABP2ATnorm((LABP2ATnorm)node);
        } else if (node instanceof LABP2ATomit) {
            return printLABP2ATomit((LABP2ATomit)node);
        } else {
            throw new Error("not supported");
        }
    }

    private ST printLABP2ATnorm(LABP2ATnorm node) {
        // LABP2ATnorm_st(lab, pat) ::= <<
        ST st = m_stg.getInstanceOf("LABP2ATnorm_st");
        st.add("pat", printCp2at(node.m_pat));
        return st;
    }

    private ST printLABP2ATomit(LABP2ATomit node) {
        // LABP2ATomit_st() ::= <<
        ST st = m_stg.getInstanceOf("LABP2ATomit_st");
        return st;
    }

    private ST printP2Tempty(P2Tempty node) {
        // P2Tempty_st() ::= <<
        ST st = m_stg.getInstanceOf("P2Tempty_st");
        return st; 
    }

    private ST printP2Tignored(P2Tignored node) {
	    // P2Tignored_st() ::= <<
	    ST st = m_stg.getInstanceOf("P2Tignored_st");
	    return st; 
    }

	private ST printP2Tvar(P2Tvar node) {
        // P2Tvar_st(var) ::= <<
        ST st = m_stg.getInstanceOf("P2Tvar_st");
        st.add("var", node.m_var);
        
        return st;
    }

    private ST printP2Tpat(P2Tpat node) {
        // P2Tpat_st(pat) ::= <<
        ST st = m_stg.getInstanceOf("P2Tpat_st");
//        System.err.println("pat in pat");
        st.add("pat", printCp2at(node.m_p2at));
        return st;
    }

    private ST printP2Tany(P2Tany node) {
        // P2Tany_st() ::= <<
        ST st = m_stg.getInstanceOf("P2Tany_st");
        return st;
        
    }

    private ST printD3Eifopt(D2Eifopt node) {
        // D2Eifopt_st(testa, thena, elsea) ::= <<
        ST st = m_stg.getInstanceOf("D2Eifopt_st");
        st.add("testa", printCd2exp(node.m_test));
        st.add("thena", printCd2exp(node.m_then));
        st.add("elsea", printCd2exp(node.m_else));
        
        return st;
    }

    private ST printD3Ei0nt(D2Ei0nt node) {
        // D2Ei0nt_st(int) ::=<<
        ST st = m_stg.getInstanceOf("D2Ei0nt_st");
        st.add("int", node.m_i0nt);
        return st;
    }

    private ST printD3Ef0loat(D2Ef0loat node) {
        // D2Ef0loat_st(flt) ::=<<
        ST st = m_stg.getInstanceOf("D2Ef0loat_st");
        st.add("flt", node.m_f0loat);
        return st;
    }

    private ST printD2Eexp(D2Eexp node) {
        // D2Eexp_st(exp) ::= <<
        ST st = m_stg.getInstanceOf("D2Eexp_st");
        st.add("exp", printCd2exp(node.m_d2exp));
        
        return st;
    }

    private ST printD3Ecst(D2Ecst node) {
        // D2Ecst_st(cst) ::= <<
        ST st = m_stg.getInstanceOf("D2Ecst_st");
        st.add("cst", printCd2cst(node.m_d2cst));
        return st;
    }

    private ST printD3Eapplst(D2Eapplst node) {
        // D2Eapplst_st(fun, args) ::= <<
        ST st = m_stg.getInstanceOf("D2Eapplst_st");
        st.add("fun", printCd2exp(node.m_d2e_fun));
        
        Id2exparg exparg = null;
        if (node.m_d2as_arg.size() > 1) {
//            System.err.println("D2Eapplst has " + node.m_d2as_arg.size() +  " lists of arguments");
            exparg = node.m_d2as_arg.get(1);
        } else {
            exparg = node.m_d2as_arg.get(0);
        }
            
        // for (Id2exparg arg: node.m_d2as_arg) {
            st.add("args", printId2exparg(exparg));
        // }
        return st;
    }

    private ST printId2exparg(Id2exparg node) {
        if (node instanceof D2EXPARGdyn) {
            return printD2EXPARGdyn((D2EXPARGdyn)node);
        } else if (node instanceof D2EXPARGsta) {
            return printD2EXPARGsta((D2EXPARGsta)node);
        } else {
            throw new Error("not supported");
        }
    }

    private ST printD2EXPARGdyn(D2EXPARGdyn node) {
        // D2EXPARGdyn_st(d2exps) ::= <<
        ST st = m_stg.getInstanceOf("D2EXPARGdyn_st");
        for (Cd2exp d2exp: node.m_d2expLst) {
            st.add("d2exps", printCd2exp(d2exp));
        }

        return st;
    }

    private ST printD2EXPARGsta(D2EXPARGsta node) {
        throw new Error("D2EXPARGsta is encountered");
    }
}



























