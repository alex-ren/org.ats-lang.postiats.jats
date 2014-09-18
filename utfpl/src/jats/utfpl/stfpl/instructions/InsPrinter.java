package jats.utfpl.stfpl.instructions;

import jats.utfpl.stfpl.dynexp3.D3Cextcode;

import java.net.URL;
import java.util.List;

import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupFile;

public class InsPrinter {

//    private List<DecGroup> m_decs;  // global declaration
//    
//    private List<D3Cextcode> m_exts;
//    
//    private List<IFunDef> m_defs;
//    
//    private List<IStfplInstruction> m_main_inss;  // global instructions
    
    private STGroup m_stg;
    private STGroup m_stg_type;
    
    public  InsPrinter(){
//    	m_decs = decs;
//    	m_exts = exts;
//    	m_defs = defs;
//    	m_main_inss = main_inss;
    	
        URL fileURL = this.getClass().getResource("/jats/utfpl/stfpl/instructions/instructions.stg");
        m_stg = new STGroupFile(fileURL, "ascii", '<', '>');
        
        URL fileURL_stype = this.getClass().getResource("/jats/utfpl/stfpl/stype/stype.stg");
        m_stg_type = new STGroupFile(fileURL_stype, "ascii", '<', '>');
    }
    
    
    public String print(List<DecGroup> decs,
	  		   List<D3Cextcode> exts,
	  		   List<IFunDef> defs,
	  		   List<IStfplInstruction> main_inss) {
    	// prog_st(fun_defs, main_inss) ::= <<
    	ST st = m_stg.getInstanceOf("program_st");
    	for (IFunDef fun_def: defs) {
    		st.add("fun_defs", printIFunDef(fun_def));
    	}
    	
    	for (IStfplInstruction ins: main_inss) {
    		st.add("main_inss", printIStfplInstruction(ins));
    	}
    	
    	return st.render();
    }


	private ST printIFunDef(IFunDef node) {
	    if (node instanceof)
    }

}






