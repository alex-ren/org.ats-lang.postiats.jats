package jats.utfpl.stfpl.csharpins;

import jats.utfpl.stfpl.Ilabel;
import jats.utfpl.stfpl.csharptype.CSFunType;
import jats.utfpl.stfpl.csharptype.CSTBookingEnv;
import jats.utfpl.stfpl.csharptype.CSTBookingFun;
import jats.utfpl.stfpl.csharptype.CSTBookingRecord;
import jats.utfpl.stfpl.csharptype.ICSType;
import jats.utfpl.stfpl.csharptype.ICSTypeBooking;
import jats.utfpl.stfpl.dynexp3.D3Cextcode;

import java.net.URL;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupFile;

public class CSharpPrinter {

    private STGroup m_stg;
    
    private List<CSDecGroup> m_decs;  // global declaration
    private List<D3Cextcode> m_exts;
    private List<CSDefFunGroup> m_defs;  // function definition
    private List<ICSInstruction> m_main_inss;  // global instructions
    private Set<ICSTypeBooking> m_track;  // type booking info
    
    public  CSharpPrinter(List<CSDecGroup> decs,
                          List<D3Cextcode> exts,
                          List<CSDefFunGroup> defs,
                          List<ICSInstruction> main_inss,
                          Set<ICSTypeBooking> track) {
        URL fileURL = this.getClass().getResource("/jats/utfpl/stfpl/csharpins/csharp.stg");
        m_stg = new STGroupFile(fileURL, "ascii", '<', '>');
        m_decs = decs;
        m_exts = exts;
        m_defs = defs;
        m_main_inss = main_inss;
        m_track = track;

    }
    
    public String printCSharp() {
        // program_st(types, funs, main) ::= <<
        ST st_prog = m_stg.getInstanceOf("program_st");
        
        ST st_booking = printBooking(m_track);
        st_prog.add("types", st_booking);
        
        printFunGroups(st_prog, m_defs);
        
        return st_prog.render();
    }
    

    private ST printFunGroups(ST st_prog, List<CSDefFunGroup> fun_grps) {
        for (CSDefFunGroup fun_grp: fun_grps) {
            Set<CSSId> siblings = new HashSet<CSSId>();
            for (CSDefFun fun_def: fun_grp.m_funs) {
                siblings.add(fun_def.m_name);
            }
            
            for (CSDefFun fun_def: fun_grp.m_funs) {
                st_prog.add("funs", printFunDef(fun_def, siblings, fun_grp.m_env_book));
            }
        }
        return st_prog;
    }

    private ST printFunDef(CSDefFun def, Set<CSSId> siblings, CSTBookingEnv env_book) {
        // CSDefFun_st(is_clo, env_type, name, paras, ret_type, inss, form_closures) ::= <<
        ST st_fun = m_stg.getInstanceOf("CSDefFun_st");
        st_fun.add("name", def.m_name.toStringCS());
        
        CSFunType fun_ty = (CSFunType)def.m_name.getType();
        
        for (CSSId para: def.m_paras) {
            // fun_para_st(name, type) ::= <<
            ST st_para = m_stg.getInstanceOf("fun_para_st");
            st_para.add("name", para.toString());
            st_para.add("type", para.getType().toSt(m_stg, 0));
            st_fun.add("paras", st_para);
        }
        
        if (def.getType().isClosure()) {
            // different name for function and closure
            st_fun.add("is_clo", true);
            
            ST st_cloenv = m_stg.getInstanceOf("closure_env_para_t");
            st_fun.add("paras", st_cloenv);
            if (null != env_book) {
                st_fun.add("env_type", env_book.m_name.toStringCS());
            }

            // form_closure_t(fun_name) ::= <<
            ST st_form_clo = m_stg.getInstanceOf("form_closure_t");
            for (CSSId sib: siblings) {
                st_form_clo.add("fun_name", sib.toStringCS()); 
            }
            st_fun.add("form_closures", st_form_clo);
            
        } else {
            st_fun.add("is_clo", false);
        }
        
        ICSType ret_ty = fun_ty.getRetType();
//        System.out.println("=====ret_ty is " + ret_ty);
        st_fun.add("ret_type", ret_ty.toSt(m_stg, 0));

        for (ICSInstruction ins: def.m_inss) {
            st_fun.add("inss", ins.toST(m_stg));
        }
        
        
        
        return st_fun;
    }

    private ST printBooking(Set<ICSTypeBooking> bookings) {
    	// ICSTypeBooking_st(grecords, gdelegates, genvs) ::= <<
    	ST st = m_stg.getInstanceOf("ICSTypeBooking_st");
	    for (ICSTypeBooking b: bookings) {
	    	if (b instanceof CSTBookingRecord) {
	    		ST stb = print((CSTBookingRecord)b);
	    		st.add("grecords", stb);
	    	} else if (b instanceof CSTBookingFun) {
	    		ST stb = print((CSTBookingFun)b);
	    		st.add("gdelegates", stb);
	    	} else if (b instanceof CSTBookingEnv) {
                ST stb = print((CSTBookingEnv)b);
                st.add("genvs", stb);
	    	}
	    }
	    
	    return st;
    }

	private ST print(CSTBookingEnv b) {
	    // CSTBookingEnv_st(name, members, init_paras, init_members) ::= <<
        ST st = m_stg.getInstanceOf("CSTBookingEnv_st");
        st.add("name", b.m_name.toStringCS());

        for (CSSIdUser var: b.m_env) {
            ST st_type = var.getType().toSt(m_stg, 1);
            String name = var.toString();

            // rec_member_st(type, name) ::= <<
            ST st_lab = m_stg.getInstanceOf("rec_member_st");
            st_lab.add("type", st_type);
            st_lab.add("name", name);
            st.add("members", st_lab);
            
            String name0 = name + "0";
            ST st_init_para = m_stg.getInstanceOf("fun_para_st");
            st_init_para.add("name", name0);
            st_init_para.add("type", st_type);
            st.add("init_paras", st_init_para);
            
            // assignment_st(holder, value) ::= <<
            ST st_ass = m_stg.getInstanceOf("assignment_st");
            st_ass.add("holder", name);
            st_ass.add("value", name0);
            st.add("init_members", st_ass);
        }
        return st;
    }

    private ST print(CSTBookingFun b) {
	    // CSTBookingFun_st(name, type_paras, paras, ret_para) ::= <<
	    ST st = m_stg.getInstanceOf("CSTBookingFun_st");
        st.add("name", b.m_name.toStringCS());
        int i = 1;
        while (i <= b.m_para_size) {
            String para_type = "X" + i;
            st.add("type_paras", para_type);
            String para = para_type + " m" + i;
            st.add("paras", para);
            ++i;
        }
        
        String ret_para = "Y";
        st.add("type_paras", ret_para);
        st.add("ret_para", ret_para);
        
        return st;
    }

    private ST print(CSTBookingRecord b) {
	    // CSTBookingRecord_st(name, type_paras, members, init_paras, init_members) ::= <<
	    ST st = m_stg.getInstanceOf("CSTBookingRecord_st");
	    st.add("name", b.m_name.toStringCS());
	    int i = 1;
	    for (Ilabel label: b.m_labs) {
	        String type_para = "T" + i;
	        st.add("type_paras", type_para);
	        
	        // rec_member_st(type, name) ::= <<
	        ST st_lab = m_stg.getInstanceOf("rec_member_st");
	        String Ti = "T" + i;
	        st_lab.add("type", Ti);
	        st_lab.add("name", label);
	        
	        st.add("members", st_lab);
	        
	        String label0 = label + "0";
	        ST st_init_para = m_stg.getInstanceOf("fun_para_st");
	        st_init_para.add("name", label0);
	        st_init_para.add("type", Ti);
	        st.add("init_paras", st_init_para);
	        
	        // assignment_st(holder, value) ::= <<
	        ST st_ass = m_stg.getInstanceOf("assignment_st");
	        st_ass.add("holder", label);
	        st_ass.add("value", label0);
	        st.add("init_members", st_ass);

	        ++i;
	    }
	    return st;
    }
    
}















