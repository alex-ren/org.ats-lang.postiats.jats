package jats.utfpl.utfpl;

import jats.utfpl.utfpl.dynexp.Cd2cst;
import jats.utfpl.utfpl.dynexp.Cd2ecl;
import jats.utfpl.utfpl.dynexp.Cd2exp;
import jats.utfpl.utfpl.dynexp.Cf2undec;
import jats.utfpl.utfpl.dynexp.Ci2mpdec;
import jats.utfpl.utfpl.dynexp.Cp2at;
import jats.utfpl.utfpl.dynexp.Cv2aldec;
import jats.utfpl.utfpl.dynexp.D2Cdatdecs;
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
import jats.utfpl.utfpl.dynexp.D2Ecst;
import jats.utfpl.utfpl.dynexp.D2Eempty;
import jats.utfpl.utfpl.dynexp.D2Eexp;
import jats.utfpl.utfpl.dynexp.D2Ef0loat;
import jats.utfpl.utfpl.dynexp.D2Ei0nt;
import jats.utfpl.utfpl.dynexp.D2Eifopt;
import jats.utfpl.utfpl.dynexp.D2Eignored;
import jats.utfpl.utfpl.dynexp.D2ElamDyn;
import jats.utfpl.utfpl.dynexp.D2ElamMet;
import jats.utfpl.utfpl.dynexp.D2ElamSta;
import jats.utfpl.utfpl.dynexp.D2Elet;
import jats.utfpl.utfpl.dynexp.D2Elist;
import jats.utfpl.utfpl.dynexp.D2Es0tring;
import jats.utfpl.utfpl.dynexp.D2Esym;
import jats.utfpl.utfpl.dynexp.D2Etup;
import jats.utfpl.utfpl.dynexp.D2Evar;
import jats.utfpl.utfpl.dynexp.Edcstkind;
import jats.utfpl.utfpl.dynexp.Efunkind;
import jats.utfpl.utfpl.dynexp.Evalkind;
import jats.utfpl.utfpl.dynexp.Id2ecl_node;
import jats.utfpl.utfpl.dynexp.Id2exp_node;
import jats.utfpl.utfpl.dynexp.Id2exparg;
import jats.utfpl.utfpl.dynexp.Ilabp2at;
import jats.utfpl.utfpl.dynexp.Ip2at_node;
import jats.utfpl.utfpl.dynexp.LABP2ATnorm;
import jats.utfpl.utfpl.dynexp.LABP2ATomit;
import jats.utfpl.utfpl.dynexp.P2Tann;
import jats.utfpl.utfpl.dynexp.P2Tany;
import jats.utfpl.utfpl.dynexp.P2Tempty;
import jats.utfpl.utfpl.dynexp.P2Tignored;
import jats.utfpl.utfpl.dynexp.P2Tpat;
import jats.utfpl.utfpl.dynexp.P2Trec;
import jats.utfpl.utfpl.dynexp.P2Tvar;
import jats.utfpl.utfpl.dynexp.ProgramUtfpl;
import jats.utfpl.utfpl.staexp.Cs2cst;
import jats.utfpl.utfpl.staexp.Cs2exp;
import jats.utfpl.utfpl.staexp.FUNCLOclo;
import jats.utfpl.utfpl.staexp.FUNCLOfun;
import jats.utfpl.utfpl.staexp.Ifunclo;
import jats.utfpl.utfpl.staexp.Is2rt;
import jats.utfpl.utfpl.staexp.S2RT;

import java.net.URL;

import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupFile;

public class UtfplPrinter {

    private STGroup m_stg;
    
    public  UtfplPrinter() {
        URL fileURL = this.getClass().getResource("/jats/utfpl/utfpl/utfpl.stg");
        m_stg = new STGroupFile(fileURL, "ascii", '<', '>');

    }
    
    public String print(ProgramUtfpl uProg) {
        ST st = printUtfplProgram(uProg);
        return st.render();
    }
    
    private ST printUtfplProgram(ProgramUtfpl uProg) {
        // utfpl_prog_st(d2ecs) ::= <<
        ST st = m_stg.getInstanceOf("utfpl_prog_st");
        for (Cd2ecl d2ec: uProg.m_d2ecs) {
            st.add("d2ecs", printCd2ecl(d2ec));
        }
        return st;
    }
    
    private ST printCd2ecl(Cd2ecl dec) {
        ST st = printId2ecl_node(dec.d2ecl_node);
        return st;
    }
    
    private ST printId2ecl_node(Id2ecl_node node) {
        if (node instanceof D2Cfundecs) {
            D2Cfundecs fundecs = (D2Cfundecs)node;
            return printD2Cfundecs(fundecs);
        } else if (node instanceof D2Cvaldecs) {
            return printD2Cvaldecs((D2Cvaldecs)node);
        } else if (node instanceof D2Cimpdec) {
            return printD2Cimpdec((D2Cimpdec)node);
        } else if (node instanceof D2Cextcode) {
        	return printD2Cextcode((D2Cextcode)node);     
        } else if (node instanceof D2Cignored) {
            return null;
        } else if (node instanceof D2Cdcstdecs) {
            return printD2Cdcstdecs((D2Cdcstdecs)node);
        } else if (node instanceof D2Cstacsts) {
        	return printD2Cstacsts((D2Cstacsts)node);
        } else if (node instanceof D2Cdatdecs) {
        	return printD2Cdatdecs((D2Cdatdecs)node);
        } else {
            throw new Error("Id2ecl_node " + node + " is not supported");
        }
    }
    
    private ST printD2Cdatdecs(D2Cdatdecs node) {
	    // D2Cdatdecs_st(knd, s2csts) ::= <<
        ST st = m_stg.getInstanceOf("D2Cdatdecs_st");
        st.add("knd", node.m_knd);
        for (Cs2cst cst: node.m_s2csts) {
            st.add("scsts", printCs2cst(cst));
        }
        
        return st;
    }

	private ST printD2Cstacsts(D2Cstacsts node) {
	    // D2Cstacsts_st(scsts) ::= <<
        ST st = m_stg.getInstanceOf("D2Cstacsts_st");
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

	private ST printD2Cdcstdecs(D2Cdcstdecs node) {
        // D2Cdcstdecs_st(knd, dcsts) ::= <<
        ST st = m_stg.getInstanceOf("D2Cdcstdecs_st");
        st.add("knd", printEdcstkind(node.m_dck));
        for (Cd2cst cst: node.m_d2cst) {
            st.add("dcsts", printCd2cst(cst));
        }
        
        return st;
    }

    private ST printEdcstkind(Edcstkind node) {
        // dcstkind_st(knd) ::= <<
        ST st = m_stg.getInstanceOf("dcstkind_st");
        st.add("knd", node.toString());
        return st;
    }
    
    private ST printD2Cextcode(D2Cextcode node) {
	    // D2Cextcode_st(extcode) ::= <<
    	ST st = m_stg.getInstanceOf("D2Cextcode_st");
    	st.add("extcode", node.m_code);
    	return st;
    }

	private ST printD2Cimpdec(D2Cimpdec node) {
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

    private ST printD2Cvaldecs(D2Cvaldecs node) {
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

    private ST printD2Cfundecs(D2Cfundecs node) {
        // D2Cfundecs_st(knd, f2ds) ::= <<
        ST st = m_stg.getInstanceOf("D2Cfundecs_st");
        ST st1 = printEfunkind(node.m_knd);
        st.add("knd", st1);
        
        for (Cf2undec f2undec: node.m_f2ds) {
            st.add("f2ds", printCf2undec(f2undec));
        }
        
        return st;

    }
    
    private ST printEfunkind(Efunkind knd) {
        // funkind_st(knd) ::= <<
        ST st = m_stg.getInstanceOf("funkind_st");
        st.add("knd", knd.toString());
        return st;
    }
    
    private ST printCf2undec(Cf2undec node) {
        // f2undec_st(loc, var, def) ::= <<
        ST st = m_stg.getInstanceOf("f2undec_st");
        st.add("var", node.f2undec_var);
        st.add("def", printCd2exp(node.f2undec_def));
        
        return st;
    }
    
    private ST printCd2exp(Cd2exp node) {
        // d2exp_st(d2exp_node) ::= <<
        ST st = m_stg.getInstanceOf("d2exp_st");
        st.add("d2exp_node", printId2exp_node(node.d2exp_node));
        return st;
    }
    
    private ST printId2exp_node(Id2exp_node node) {
        if (node instanceof D2EannFunclo) {
            return printD2EannFunclo((D2EannFunclo)node);  
        } else if (node instanceof D2EannSeff) {
            return printD2EannSeff((D2EannSeff)node);
        } else if (node instanceof D2EannType) {
            return printD2EannType((D2EannType)node);              
        } else if (node instanceof D2Eapplst) {
            return printD2Eapplst((D2Eapplst)node);
        } else if (node instanceof D2Ecst) {
            return printD2Ecst((D2Ecst)node);
        } else if (node instanceof D2Eempty) {
        	return printD2Eempty();
        } else if (node instanceof D2Eexp) {
            return printD2Eexp((D2Eexp)node);
        } else if (node instanceof D2Ef0loat) {
            return printD2Ef0loat((D2Ef0loat)node);
        } else if (node instanceof D2Ei0nt) {
            return printD2Ei0nt((D2Ei0nt)node);
        } else if (node instanceof D2Eifopt) {
            return printD2Eifopt((D2Eifopt)node);
        } else if (node instanceof D2Eignored) {
            return printD2Eignored((D2Eignored)node);
        } else if (node instanceof D2ElamMet) {
            return printD2ElamMet((D2ElamMet)node);  
        } else if (node instanceof D2ElamDyn) {
            return printD2ElamDyn((D2ElamDyn)node);
        } else if (node instanceof D2ElamSta) {
            return printD2ElamSta((D2ElamSta)node);   
        } else if (node instanceof D2Elet) {
            return printD2Elet((D2Elet)node);
        } else if (node instanceof D2Es0tring) {
            return printD2Es0tring((D2Es0tring)node);
        } else if (node instanceof D2Esym) {
            return printD2Esym((D2Esym)node);
        } else if (node instanceof D2Evar) {
            return printD2Evar((D2Evar)node);
        } else if (node instanceof D2Etup) {
            return printD2Etup((D2Etup)node);
        } else if (node instanceof D2Elist) {
            return printD2Elist((D2Elist)node);            
        } else {
            throw new Error(node + " is not supported.");
        }
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

    private ST printD2Etup(D2Etup node) {
        // D2Etup_st(knd, d2es) ::= <<
        ST st = m_stg.getInstanceOf("D2Etup_st");
        st.add("knd", node.m_knd);
        for (Cd2exp d2e: node.m_d2es) {
            st.add("d2es", printCd2exp(d2e));
        }
        return st;
    }

    private ST printCd2cst(Cd2cst node) {
    	// d2cst_st(sym, stamp, s2exp) ::= <<
    	ST st = m_stg.getInstanceOf("d2cst_st");
    	st.add("sym", node.m_symbol);
    	st.add("stamp", node.m_stamp);
    	if (null != node.m_type) {
    	    st.add("s2exp", printCs2exp(node.m_type));
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
	    if (node instanceof S2RT) {
	    	return printS2RT((S2RT)node);
	    } else {
	    	throw new Error("Is2rt " + node + " is not supported");
	    }
    }

	private ST printS2RT(S2RT node) {
	    // S2RT_st(srt) ::= <<
		ST st = m_stg.getInstanceOf("S2RT_st");
		st.add("srt", node.m_srt);
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

    private ST printD2Eempty() {
    	// D2Eempty_st() ::= <<
	    ST st = m_stg.getInstanceOf("D2Eempty_st");
	    return st;
    }

	private ST printD2Elet(D2Elet node) {
        // D2Elet_st(d2cs, d2e_body) ::= <<
        ST st = m_stg.getInstanceOf("D2Elet_st");
        for (Cd2ecl d2ecl: node.m_d2cs) {
            st.add("d2cs", printCd2ecl(d2ecl));
        }
        st.add("d2e_body", printCd2exp(node.m_d2e_body));
        
        return st;
    }

    private ST printD2Evar(D2Evar node) {
        // D2Evar_st(var) ::= <<
        ST st = m_stg.getInstanceOf("D2Evar_st");
        st.add("var", node.m_d2var);
        return st;
    }

    private ST printD2Esym(D2Esym node) {
        // D2Esym_st(d2sym) ::= <<
        ST st = m_stg.getInstanceOf("D2Esym_st");
        st.add("d2sym", node.m_d2sym);        

        return st;
    }

    private ST printD2Es0tring(D2Es0tring node) {
        // D2Es0tring_st(str) ::=<<
        ST st = m_stg.getInstanceOf("D2Es0tring");
        st.add("str", node.m_s0tring);
        return st;
    }

    private ST printD2ElamDyn(D2ElamDyn node) {
        // D2ElamDyn_st(p2ts, exp) ::= <<
        ST st = m_stg.getInstanceOf("D2ElamDyn_st");
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

    private ST printD2Eifopt(D2Eifopt node) {
        // D2Eifopt_st(testa, thena, elsea) ::= <<
        ST st = m_stg.getInstanceOf("D2Eifopt_st");
        st.add("testa", printCd2exp(node.m_test));
        st.add("thena", printCd2exp(node.m_then));
        st.add("elsea", printCd2exp(node.m_else));
        
        return st;
    }

    private ST printD2Ei0nt(D2Ei0nt node) {
        // D2Ei0nt_st(int) ::=<<
        ST st = m_stg.getInstanceOf("D2Ei0nt_st");
        st.add("int", node.m_i0nt);
        return st;
    }

    private ST printD2Ef0loat(D2Ef0loat node) {
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

    private ST printD2Ecst(D2Ecst node) {
        // D2Ecst_st(cst) ::= <<
        ST st = m_stg.getInstanceOf("D2Ecst_st");
        st.add("cst", printCd2cst(node.m_d2cst));
        return st;
    }

    private ST printD2Eapplst(D2Eapplst node) {
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



























