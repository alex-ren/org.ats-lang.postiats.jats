package jats.utfpl.stfpl.mcinstruction;


import jats.utfpl.stfpl.stype.AuxSType;

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

        st.add("clo_info", AuxSType.getClosureInfo(node.m_name.getType()).toString());
        
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
		// MCInsCond_st(holder, cond, tb, fb) ::= <<
		
		ST st = m_stg.getInstanceOf("MCInsCond_st");
		if (null != ins.m_holder) {
			st.add("holder", ins.m_holder.toStringMCIns());
		}

		st.add("cond", ins.m_cond.toStringMCIns());
		for (IMCInstruction ains: ins.m_btrue) {
			st.add("tb", ains.accept(this));
		}
		
		for (IMCInstruction ains: ins.m_bfalse) {
			st.add("fb", ains.accept(this));
		}
		
		return st;
    }


	@Override
    public Object visit(MCInsMove ins) {
		if (ins.m_holder == null) {
			throw new Error("eeeeeeeeee1");
		}
		if (ins.m_vp == null) {
			throw new Error("eeeeeeeeee2");
		}
		return "MCInsMove " + ins.m_vp.toStringMCIns() + 
				" " + ins.m_holder.toStringMCIns();
    }


	@Override
    public Object visit(MCInsCall ins) {
		// MCInsCall_st(ret, funname, args) ::= <<
		ST st = m_stg.getInstanceOf("MCInsCall_st");
		st.add("ret", ins.m_holder.toStringMCIns());
		st.add("funname", ins.m_fun.toStringMCIns());
		for (IMCValPrim arg: ins.m_args) {
			st.add("args", arg.toStringMCIns());
		}
		
		return st;
    }


	@Override
    public Object visit(MCInsFormEnv ins) {
		// MCInsFormEnv_st(holder, eles) ::= <<
		ST st = m_stg.getInstanceOf("MCInsFormEnv_st");
		st.add("holder", ins.m_holder.toStringMCIns());
		for (MCSId id: ins.m_env) {
			if (id == null) {
				throw new Error("xxxxxxxxxxxxxxxxxxx");
			}
			st.add("eles", id.toStringMCIns());
		}
		
		return st;
    }


	@Override
    public Object visit(MCInsGetEleFromEnv ins) {
		// MCInsGetEleFromEnv(ret, envname, index) ::= <<
		ST st = m_stg.getInstanceOf("MCInsGetEleFromEnv");
		st.add("ret", ins.m_holder.toStringMCIns());
		st.add("envname", ins.m_env.toStringMCIns());
		st.add("index", ins.m_index);
		return st;
    }


	@Override
    public Object visit(MCInsClosure ins) {
		// MCInsClosure_st(holder, fun, env) ::= <<
		ST st = m_stg.getInstanceOf("MCInsClosure_st");
		st.add("holder", ins.m_holder.toStringMCIns());
		st.add("fun", ins.m_fun.toStringMCIns());
		st.add("env", ins.m_env.toStringMCIns());
		return st;
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


	@Override
	public Object visit(MCInsArrayRefCreate ins) {
		return "MCInsArrayRefCreate";
	}


	@Override
	public Object visit(MCInsArrayRefUpdate ins) {
		return "MCInsArrayRefUpdate";
	}


	@Override
	public Object visit(MCInsArrayRefGet ins) {
		return "MCInsArrayRefGet";
	}


	@Override
	public Object visit(MCInsTIdAllocate ins) {
		return "MCInsTIdAllocate";
	}


	@Override
	public Object visit(MCInsSharedCreate ins) {
		return "MCInsSharedCreate";
	}

    

}
