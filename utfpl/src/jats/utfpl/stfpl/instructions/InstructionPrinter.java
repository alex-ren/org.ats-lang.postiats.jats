package jats.utfpl.stfpl.instructions;

import jats.utfpl.stfpl.stype.Utils;

import java.net.URL;

import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupFile;

public class InstructionPrinter {
    
    private STGroup m_stg;
    private STGroup m_stg_type;
    
    public  InstructionPrinter() {
    	
        URL fileURL = this.getClass().getResource("/jats/utfpl/stfpl/instructions/instructions.stg");
        m_stg = new STGroupFile(fileURL, "ascii", '<', '>');
        
        URL fileURL_stype = this.getClass().getResource("/jats/utfpl/stfpl/stype/stype.stg");
        m_stg_type = new STGroupFile(fileURL_stype, "ascii", '<', '>');
    }
    
    
    public String print(ProgramIns prog) {
    	// prog_st(fun_defs, main_inss) ::= <<
    	ST st = m_stg.getInstanceOf("prog_st");
    	for (IFunDef fun_def: prog.m_defs) {
    		st.add("fun_defs", printIFunDef(fun_def));
    	}
    	
    	for (IStfplInstruction ins: prog.m_main_inss) {
    		st.add("main_inss", printInstruction(ins));
    	}
    	
    	return st.render();
    }


	private ST printIFunDef(IFunDef node) {
	    if (node instanceof DefFunGroup) {
	        return printDefFunGroup((DefFunGroup)node);
	    } else if (node instanceof ImplFun) {
	        return printImplFun((ImplFun)node);
	    } else {
	        throw new Error(node + " is not supported");
	    }
    }


    private ST printImplFun(ImplFun node) {
        // ImplFun_st(name, paras, clo_info, ret_type, body) ::= <<
        ST st = m_stg.getInstanceOf("ImplFun_st");
        st.add("name", node.m_name.toStringIns());
        st.add("clo_info", Utils.getCloInfo(node.m_name.getType()).toString());
        
        for (IStfplInstruction ins: node.m_inss) {
            st.add("body", printInstruction(ins));
        }
        
        return st;
    }


    private ST printDefFunGroup(DefFunGroup node) {
        // DefFunGroup_st(fun_defs, fun_lab) ::= <<
        ST st = m_stg.getInstanceOf("DefFunGroup_st");
        for (DefFun fun_def: node.m_funs) {
            st.add("fun_defs", printDefFun(fun_def));
        }
        
        st.add("fun_lab", node.m_knd.toString());
        return st;       
        
    }


    private Object printDefFun(DefFun node) {
        // DefFun_st(name, paras, clo_info, ret_type, body) ::= <<
        ST st = m_stg.getInstanceOf("DefFun_st");
        st.add("name", node.m_name.toStringIns());
        
        if (null == node.m_name.getType()) {
            throw new Error("eeeeeeee");
        }
        st.add("clo_info", Utils.getCloInfo(node.m_name.getType()).toString());
        
        for (IStfplInstruction ins: node.m_inss) {
            st.add("body", printInstruction(ins));
        }
        
        return st;
        
    }


    private Object printInstruction(IStfplInstruction node) {
        if (node instanceof InsCall) {
            return printInsCall((InsCall)node);
        } else if (node instanceof InsClosure) {
            return printInsClosure((InsClosure)node);
        } else if (node instanceof InsCond) {
            return printInsCond((InsCond)node);
        } else if (node instanceof InsFormEnv) {
            return printInsFormEnv((InsFormEnv)node);
        } else if (node instanceof InsMove) {
            return printInsMove((InsMove)node);
        } else if (node instanceof InsPatLabDecompose) {
            return printInsPatLabDecompose((InsPatLabDecompose)node);
        } else if (node instanceof InsTuple) {
            return printInsTuple((InsTuple)node);
        } else {
            throw new Error(node + " is not supported.");
        }
    }


    private Object printInsTuple(InsTuple node) {
        return "InsTuple";
    }


    private Object printInsPatLabDecompose(InsPatLabDecompose node) {
        return "InsPatLabDecompose";
    }


    private Object printInsMove(InsMove node) {
        return "InsMove";
    }


    private Object printInsFormEnv(InsFormEnv node) {
        return "InsFormEnv";
    }


    private Object printInsCond(InsCond node) {
        return "InsCond";
    }


    private Object printInsClosure(InsClosure node) {
        return "InsClosure";
    }


    private Object printInsCall(InsCall node) {
        return "InsCall";
    }
    


}






































