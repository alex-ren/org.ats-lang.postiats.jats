package jats.utfpl.stfpl.dynexp3;

import jats.utfpl.stfpl.dynexp.Edcstkind;
import jats.utfpl.stfpl.dynexp.Efunkind;
import jats.utfpl.stfpl.dynexp.Evalkind;
import jats.utfpl.stfpl.staexp.Cs2cst;
import jats.utfpl.stfpl.stype.FunType;
import jats.utfpl.stfpl.stype.ISType;
import jats.utfpl.stfpl.stype.PolyType;
import jats.utfpl.utils.Log;

import java.net.URL;

import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupFile;

public class ProgramStfpl3Printer {

    private STGroup m_stg;
    private STGroup m_stg_type;
    
    public  ProgramStfpl3Printer() {
        URL fileURL = this.getClass().getResource("/jats/utfpl/stfpl/dynexp3/stfpl.stg");
        m_stg = new STGroupFile(fileURL, "ascii", '<', '>');
        
        URL fileURL_stype = this.getClass().getResource("/jats/utfpl/stfpl/stype/stype.stg");
        m_stg_type = new STGroupFile(fileURL_stype, "ascii", '<', '>');
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
        } else if (node instanceof D3Clocal) {
            return printD3Clocal((D3Clocal)node);            
        } else {
            throw new Error("Id3ecl_node " + node + " is not supported");
        }
    }


    private ST printD3Clocal(D3Clocal node) {
	    // D3Clocal_st(d3cs0, d3cs1) ::= <<
        ST st = m_stg.getInstanceOf("D3Clocal_st");
        for (Cd3ecl dec: node.m_d3cs) {
            st.add("d3cs1", printCd3ecl(dec));
        }
        
        return st;
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
            st.add("stype", node.m_stype.toSTStfpl3(m_stg_type));
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
        // D3Cfundecs_st(knd, env, f3ds) ::= <<
        ST st = m_stg.getInstanceOf("D3Cfundecs_st");
        ST st1 = printEfunkind(node.m_knd);
        st.add("knd", st1);
        for (Cd3var var: node.m_env) {
        	st.add("env", var.toString());
        }
        
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
        // f3undec_st(loc, var, stype, clo, body) ::= <<
        ST st = m_stg.getInstanceOf("f3undec_st");
        st.add("var", node.m_var);
        st.add("stype", node.m_type.toSTStfpl3(m_stg_type));
        
        for (Cp3at pat: node.m_p3ts) {
            st.add("p3ts", printCp3at(pat));
        }
        
        ISType type = node.m_type;
        st.add("clo", getCloInfo(type));
        
        st.add("body", printCd3exp(node.m_body));
        
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
    
    private ST printD3Eapplst(D3Eapplst node) {
        // D3Eapplst_st(fun, args) ::= <<
        ST st = m_stg.getInstanceOf("D3Eapplst_st");
        st.add("fun", printCd3exp(node.m_fun));
        
        for (D3EXPARGdyn arg: node.m_args) {
            st.add("args", printD3EXPARGdyn(arg));
        }
        
        return st;
    }
    
    private ST printD3EXPARGdyn(D3EXPARGdyn node) {
        // D3EXPARGdyn_st(d3exps) ::= <<
        ST st = m_stg.getInstanceOf("D3EXPARGdyn_st");
        for (Cd3exp d3exp: node.m_d3expLst) {
            st.add("d3exps", printCd3exp(d3exp));
        }

        return st;
    }
    
    private ST printD3Ecst(D3Ecst node) {
        // D3Ecst_st(cst) ::= <<
        ST st = m_stg.getInstanceOf("D3Ecst_st");
        st.add("cst", printCd3cst(node.m_d3cst));
        return st;
    }

    private ST printD3Ei0nt(D3Ei0nt node) {
        // D3Ei0nt_st(int) ::=<<
        ST st = m_stg.getInstanceOf("D3Ei0nt_st");
        st.add("int", node.m_i0nt);
        return st;
    }

    private ST printD3Ef0loat(D3Ef0loat node) {
        // D3Ef0loat_st(flt) ::=<<
        ST st = m_stg.getInstanceOf("D3Ef0loat_st");
        st.add("flt", node.m_f0loat);
        return st;
    }

    private ST printD3Eifopt(D3Eifopt node) {
        // D3Eifopt_st(testa, thena, elsea) ::= <<
        ST st = m_stg.getInstanceOf("D3Eifopt_st");
        st.add("testa", printCd3exp(node.m_test));
        st.add("thena", printCd3exp(node.m_then));
        st.add("elsea", printCd3exp(node.m_else));
        
        return st;
    }
    
    private String getCloInfo(ISType type) {
        while (true) {
            if (type instanceof FunType) {
                return ((FunType)type).getCloInfo();
            } else if (type instanceof PolyType) {
                type = ((PolyType)type).m_body;
                continue;
            } else {
                throw new Error("This should not happen.");
            }
        }
    }

    private ST printD3ElamDyn(D3ElamDyn node) {
        // D3ElamDyn_st(p3ts, exp, clo) ::= <<
        ST st = m_stg.getInstanceOf("D3ElamDyn_st");

        ISType type = node.getType();
        st.add("clo", getCloInfo(type));
        
        for (Cp3at pat: node.m_p3ts) {
            st.add("p3ts", printCp3at(pat));
        }
        st.add("exp", printCd3exp(node.m_d3exp));
        return st;
    }
    
    private ST printCp3at(Cp3at node) {
        // p3at_st(node) ::= <<
        ST st = m_stg.getInstanceOf("p3at_st");
        st.add("node", printIp3at_node(node.m_node));
        return st;
    }

    private ST printIp3at_node(Ip3at_node node) {
        if (node instanceof P3Tany) {
            return printP3Tany((P3Tany)node);
        } else if (node instanceof P3Tcon) {
            throw new Error("P3Tcon is not finished yet.");      
        } else if (node instanceof P3Tempty) {
            return printP3Tempty((P3Tempty)node);
        } else if (node instanceof P3Trec) {
            return printP3Trec((P3Trec)node);
        } else if (node instanceof P3Tvar) {
            return printP3Tvar((P3Tvar)node);
        } else {
            throw new Error(node + " is not supported");
        }
    }
    
    private ST printP3Tany(P3Tany node) {
        // P3Tany_st() ::= <<
        ST st = m_stg.getInstanceOf("P3Tany_st");
        return st;
        
    }
    
    private ST printP3Tempty(P3Tempty node) {
        // P3Tempty_st() ::= <<
        ST st = m_stg.getInstanceOf("P3Tempty_st");
        return st; 
    }
    
    private ST printP3Trec(P3Trec node) {
        // P3Trec_st(isbox, labpats) ::= <<
        ST st = m_stg.getInstanceOf("P3Trec_st");
        for (LABP3ATnorm labpat: node.m_labpats) {
            st.add("labpats", printLABP3ATnorm(labpat));
        }
        
        st.add("isbox", node.isBoxed());
        return st;
        
    }
    
    private ST printLABP3ATnorm(LABP3ATnorm node) {
        // LABP3ATnorm_st(lab, pat) ::= <<
        ST st = m_stg.getInstanceOf("LABP3ATnorm_st");
        st.add("pat", printCp3at(node.m_pat));
        st.add("lab", node.m_lab);
        return st;
    }
    
    private ST printP3Tvar(P3Tvar node) {
        // P3Tvar_st(var, type) ::= <<
        ST st = m_stg.getInstanceOf("P3Tvar_st");
        st.add("var", node.m_var);
        st.add("type", node.getType().toSTStfpl3(m_stg_type));
        
        return st;
    }

    private ST printD3Elet(D3Elet node) {
        // D3Elet_st(d3cs, d3e_body) ::= <<
        ST st = m_stg.getInstanceOf("D3Elet_st");
        for (Cd3ecl d3ecl: node.m_d3cs) {
            st.add("d3cs", printCd3ecl(d3ecl));
        }
        st.add("d3e_body", printCd3exp(node.m_body));
        
        return st;
    }
    
    private ST printD3Es0tring(D3Es0tring node) {
        // D3Es0tring_st(str) ::=<<
        ST st = m_stg.getInstanceOf("D3Es0tring_st");
        st.add("str", node.m_s0tring);
        return st;
    }
    
    private ST printD3Esym(D3Esym node) {
        // D3Esym_st(d3sym) ::= <<
        ST st = m_stg.getInstanceOf("D3Esym_st");
        st.add("d3sym", node.m_d3sym);        

        return st;
    }

    private ST printD3Etup(D3Etup node) {
        // D3Etup_st(knd, d3es) ::= <<
        ST st = m_stg.getInstanceOf("D3Etup_st");
        st.add("knd", node.m_knd);
        for (Cd3exp d3e: node.m_d3es) {
            st.add("d3es", printCd3exp(d3e));
        }
        return st;
    }
    
    private ST printD3Evar(D3Evar node) {
        // D3Evar_st(var, type) ::= <<
        ST st = m_stg.getInstanceOf("D3Evar_st");
        st.add("var", node.m_d3var);
        st.add("type", node.getType().toSTStfpl3(m_stg_type));
        return st;
    }

    private ST printD3Cimpdec(D3Cimpdec node) {
        // D3Cimpdec_st(cst, def) ::= <<
        ST st = m_stg.getInstanceOf("D3Cimpdec_st");
        Ci3mpdec imp = node.m_i3mpdec;
        
        st.add("cst", printCd3cst(imp.i3mpdec_cst));
        st.add("def", printCd3exp(imp.i3mpdec_def));
        return st;
    }
    
    private ST printD3Cstacsts(D3Cstacsts node) {
        // D3Cstacsts_st(scsts) ::= <<
        ST st = m_stg.getInstanceOf("D3Cstacsts_st");
        for (Cs2cst cst: node.m_csts) {
            st.add("scsts", printCs2cst(cst));
        }
        
        return st;
    }
    
    private ST printD3Cvaldecs(D3Cvaldecs node) {
        // D3Cvaldecs_st(knd, v3ds) ::= <<
        ST st = m_stg.getInstanceOf("D3Cvaldecs_st");
        st.add("knd", printEvalkind(node.m_knd));
        for (Cv3aldec valdec: node.m_v3ds) {
            st.add("v3ds", printCv3aldec(valdec));
        }
        
        return st;
    }

    private ST printEvalkind(Evalkind node) {
        // valkind_st(knd) ::= <<
        ST st = m_stg.getInstanceOf("valkind_st");
        st.add("knd", node.toString());
        return st;
    }
    
    private ST printCv3aldec(Cv3aldec node) {
        // v3aldec_st(pat, def) ::= <<
        ST st = m_stg.getInstanceOf("v3aldec_st");
        st.add("pat", printCp3at(node.m_pat));
        st.add("def", printCd3exp(node.m_def));
        
        return st;
    }

    private ST printD3Eempty() {
        // D3Eempty_st() ::= <<
        ST st = m_stg.getInstanceOf("D3Eempty_st");
        return st;
    }
    
    

}



























