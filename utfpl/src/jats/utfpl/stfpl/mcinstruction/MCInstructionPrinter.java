package jats.utfpl.stfpl.mcinstruction;

import jats.utfpl.stfpl.stype.Utils;

import java.net.URL;

import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupFile;

public class MCInstructionPrinter implements IMCInsVisitor {

	  
    private STGroup m_stg;
//    private STGroup m_stg_type;
    
    public  MCInstructionPrinter() {
    	
        URL fileURL = this.getClass().getResource("/jats/utfpl/stfpl/mcinstruction/mcinstructions.stg");
        m_stg = new STGroupFile(fileURL, "ascii", '<', '>');
        
//        URL fileURL_stype = this.getClass().getResource("/jats/utfpl/stfpl/stype/stype.stg");
//        m_stg_type = new STGroupFile(fileURL_stype, "ascii", '<', '>');
    }
    
    
    public String print(ProgramMCIns prog) {
    	// prog_st(fun_defs, main_inss) ::= <<
    	ST st = m_stg.getInstanceOf("prog_st");
    	for (MCDefFunGroup fungrp: prog.m_defs) {
    		st.add("fun_defs", printMCDefFunGroup(fungrp));
    	}
    	
    	for (IMCInstruction ins: prog.m_main_inss) {
    		st.add("main_inss", ins.accept(this));
    	}
    	
    	return st.render();
    }

    private ST printMCDefFunGroup(MCDefFunGroup grp) {
        // MCDefFunGroup_st(fun_defs, fun_lab) ::= <<
        ST st = m_stg.getInstanceOf("MCDefFunGroup_st");
        for (MCDefFun fun_def: grp.m_funs) {
            st.add("fun_defs", printDefFun(fun_def));
        }

        // implementation has no information of kind
        if (null != grp.m_knd) {
            st.add("fun_lab", grp.m_knd.toString());
        }

        return st;       
        
    }


    private ST printDefFun(MCDefFun node) {
        // MCDefFun_st(name, paras, clo_info, ret_type, body) ::= <<
        ST st = m_stg.getInstanceOf("MCDefFun_st");
        st.add("name", node.m_name.getSId().toStringIns());
        
        if (null == node.m_name.getType()) {
            throw new Error("eeeeeeee");
        }
        
        for (MCSId para: node.m_paras) {
        	st.add("paras", para.getSId().toStringIns());
        }

        st.add("clo_info", Utils.getCloInfo(node.m_name.getType()).toString());
        
        for (IMCInstruction ins: node.m_inss) {
            st.add("body", ins.accept(this));
        }
        
        return st;
        
    }

	@Override
    public Object visit(MCInsTuple ins) {
	    return "MCInsTuple";
    }


	@Override
    public Object visit(MCInsPatLabDecompose ins) {
		return "MCInsPatLabDecompose";
    }


	@Override
    public Object visit(MCInsCond ins) {
		return "MCInsCond";
    }


	@Override
    public Object visit(MCInsMove ins) {
		return "MCInsMove " + ins.m_vp.toStringMCIns() + 
				" " + ins.m_holder.toStringMCIns();
    }


	@Override
    public Object visit(MCInsCall ins) {
		return "MCInsCall";
    }


	@Override
    public Object visit(MCInsFormEnv ins) {
		return "MCInsFormEnv";
    }


	@Override
    public Object visit(MCInsGetEleFromEnv ins) {
		return "MCInsGetEleFromEnv";
    }


	@Override
    public Object visit(MCInsClosure ins) {
		return "MCInsClosure";
    }


	@Override
    public Object visit(MCInsThreadCreate ins) {
		return "MCInsThreadCreate";
    }


	@Override
    public Object visit(MCInsAtomRefCreate ins) {
		return "MCInsAtomRefCreate";
    }


	@Override
    public Object visit(MCInsAtomRefGet ins) {
		return "MCInsAtomRefGet";
    }


	@Override
    public Object visit(MCInsAtomRefUpdate ins) {
		return "MCInsAtomRefUpdate";
    }


	@Override
    public Object visit(MCInsSharedCreateCond ins) {
		return "MCInsSharedCreateCond";
    }


	@Override
    public Object visit(MCInsMutexCreate ins) {
		return "MCInsMutexCreate";
    }


	@Override
    public Object visit(MCInsMCAtomicStart ins) {
		return "MCInsMCAtomicStart";
    }


	@Override
    public Object visit(MCInsMCAssert ins) {
		return "MCInsMCAssert";
    }


	@Override
    public Object visit(MCInsMCGet ins) {
		return "MCInsMCGet";
    }


	@Override
    public Object visit(MCInsMCSet ins) {
		return "MCInsMCSet";
    }


	@Override
    public Object visit(MCInsMCVLockViewGet ins) {
		return "MCInsMCVLockViewGet";
    }
    

}
