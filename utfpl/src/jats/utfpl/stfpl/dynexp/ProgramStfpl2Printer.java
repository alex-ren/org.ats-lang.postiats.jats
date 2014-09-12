package jats.utfpl.stfpl.dynexp;

import jats.utfpl.stfpl.staexp.Cs2cst;
import jats.utfpl.stfpl.staexp.Cs2exp;
import jats.utfpl.stfpl.staexp.FUNCLOclo;
import jats.utfpl.stfpl.staexp.FUNCLOfun;
import jats.utfpl.stfpl.staexp.Ifunclo;
import jats.utfpl.stfpl.staexp.Is2exp_node;
import jats.utfpl.stfpl.staexp.Is2rt;
import jats.utfpl.stfpl.staexp.S2Ecst;
import jats.utfpl.stfpl.staexp.S2RTbas;
import jats.utfpl.stfpl.staexp.S2RTfun;
import jats.utfpl.stfpl.staexp.S2RTtup;
import jats.utfpl.stfpl.staexp.SExpTypeExtractor;

import java.net.URL;

import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupFile;

public class ProgramStfpl2Printer {

    private STGroup m_stg;
    private STGroup m_stg_type;
    
    public  ProgramStfpl2Printer() {
        URL fileURL = this.getClass().getResource("/jats/utfpl/stfpl/dynexp/stfpl.stg");
        m_stg = new STGroupFile(fileURL, "ascii", '<', '>');
        
        URL fileURL_stype = this.getClass().getResource("/jats/utfpl/stfpl/stype/stype.stg");
        m_stg_type = new STGroupFile(fileURL_stype, "ascii", '<', '>');

    }
    
    public String print(ProgramStfpl2 uProg) {
        ST st = printUtfplProgram(uProg);
        return st.render();
    }
    
    private ST printUtfplProgram(ProgramStfpl2 uProg) {
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
        } else if (node instanceof D2Cinclude) {
            return printD2Cinclude((D2Cinclude)node);
        } else if (node instanceof D2Cnone) {
            return printD2Cnone((D2Cnone)node);
        } else if (node instanceof D2Clist) {
            return printD2Clist((D2Clist)node);
        } else if (node instanceof D2Cstaload) {
            return printD2Cstaload((D2Cstaload)node);     
        } else {
            throw new Error("Id2ecl_node " + node + " is not supported");
        }
    }
    
    private ST printD2Cstaload(D2Cstaload node) {
        // D2Cstaload_st(filename) ::= <<
        ST st = m_stg.getInstanceOf("D2Cstaload_st");
        st.add("filename", node.m_file);
        
        return st;
    }

    private ST printD2Clist(D2Clist node) {
        // D2Clist_st(d2cs) ::= <<
        ST st = m_stg.getInstanceOf("D2Clist_st");
        for (Cd2ecl d2c: node.m_d2cs) {
            st.add("d2cs", printCd2ecl(d2c));
        }
        
        return st;
    }

    private ST printD2Cnone(D2Cnone node) {
        // D2Cnone_st() ::= <<
        ST st = m_stg.getInstanceOf("D2Cnone_st");
        return st;
    }

    private ST printD2Cinclude(D2Cinclude node) {
        // D2Cinclude_st(knd, d2cs) ::= <<
        ST st = m_stg.getInstanceOf("D2Cinclude_st");
        st.add("knd", node.m_knd);
        for (Cd2ecl d2c: node.m_d2cs) {
            st.add("d2cs", printCd2ecl(d2c));
        }
        
        return st;
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
        // s2cst_st(symbol, stamp, srt) ::= <<
        ST st = m_stg.getInstanceOf("s2cst_st");
        st.add("symbol", cst.m_symbol);
        st.add("stamp", cst.m_stamp);
        st.add("srt", printIs2rt(cst.m_srt));
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
	    // s2exp_st(srt, stype) ::= <<
		ST st = m_stg.getInstanceOf("s2exp_st");
		st.add("stype", SExpTypeExtractor.extractType(node).toSTStfpl3(m_stg_type));
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
        ST st = m_stg.getInstanceOf("D2Es0tring_st");
        st.add("str", node.m_s0tring);
        return st;
    }

    private ST printD2ElamDyn(D2ElamDyn node) {
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



























