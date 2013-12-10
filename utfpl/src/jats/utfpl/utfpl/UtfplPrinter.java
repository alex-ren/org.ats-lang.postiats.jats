package jats.utfpl.utfpl;

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
    
    public String print(UtfplProgram uProg) {
        ST st = printUtfplProgram(uProg);
        return st.render();
    }
    
    private ST printUtfplProgram(UtfplProgram uProg) {
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
        } else if (node instanceof D2Cignored) {
            return null;
        } else {
            throw new Error("not supported");
        }
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
        st.add("cst", node.i2mpdec_cst);
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
        if (node instanceof D2Eapplst) {
            return printD2Eapplst((D2Eapplst)node);
        } else if (node instanceof D2Ecst) {
            return printD2Ecst((D2Ecst)node);
        } else if (node instanceof D2Eexp) {
            return printD2Eexp((D2Eexp)node);
        } else if (node instanceof D2Ef0loat) {
            return printD2Ef0loat((D2Ef0loat)node);
        } else if (node instanceof D2Ei0nt) {
            return printD2Ei0nt((D2Ei0nt)node);
        } else if (node instanceof D2Eifopt) {
            return printD2Eifopt((D2Eifopt)node);
        } else if (node instanceof D2Eignored) {
            return null;
        } else if (node instanceof D2Elam) {
            return printD2Elam((D2Elam)node);
        } else if (node instanceof D2Es0tring) {
            return printD2Es0tring((D2Es0tring)node);
        } else if (node instanceof D2Esym) {
            return printD2Esym((D2Esym)node);
        } else if (node instanceof D2Evar) {
            return printD2Evar((D2Evar)node);
        } else {
            throw new Error("not supported");
        }
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

    private ST printD2Elam(D2Elam node) {
        // D2Elam_st(p2ts, exp) ::= <<
        ST st = m_stg.getInstanceOf("D2Elam_st");
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
        } else {
            throw new Error("not supported");
        }
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
        st.add("cst", node.m_d2cst);
        return st;
    }

    private ST printD2Eapplst(D2Eapplst node) {
        // D2Eapplst_st(fun, args) ::= <<
        ST st = m_stg.getInstanceOf("D2Eapplst_st");
        st.add("fun", printCd2exp(node.m_d2e_fun));
        if (node.m_d2as_arg.size() > 1) {
            System.err.println("D2Eapplst has list of arguments");
        }
        for (Id2exparg arg: node.m_d2as_arg) {
            st.add("args", printId2exparg(arg));
        }
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
//        if (node.m_d2expLst.size() > 1) {
//            throw new Error("why do we need a list here?");
//        }
        for (Cd2exp d2exp: node.m_d2expLst) {
            st.add("d2exps", printCd2exp(d2exp));
        }

        return st;
    }

    private ST printD2EXPARGsta(D2EXPARGsta node) {
        throw new Error("D2EXPARGsta is encountered");
    }
}



























