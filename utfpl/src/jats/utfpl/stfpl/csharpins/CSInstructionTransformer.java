package jats.utfpl.stfpl.csharpins;

import jats.utfpl.stfpl.csharptype.ICSTypeBooking;
import jats.utfpl.stfpl.dynexp.Cd2ecl;
import jats.utfpl.stfpl.dynexp.ProgramUtfpl;
import jats.utfpl.stfpl.dynexp3.Cd3ecl;
import jats.utfpl.stfpl.dynexp3.Cd3var;
import jats.utfpl.stfpl.dynexp3.Cp3at;
import jats.utfpl.stfpl.dynexp3.D3Cextcode;
import jats.utfpl.stfpl.dynexp3.P3Tvar;
import jats.utfpl.stfpl.instructions.DecGroup;
import jats.utfpl.stfpl.instructions.DefFun;
import jats.utfpl.stfpl.instructions.DefFunGroup;
import jats.utfpl.stfpl.instructions.IStfplInstruction;
import jats.utfpl.stfpl.instructions.SId;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupFile;

public class CSInstructionTransformer {
    private List<CSDecGroup> m_decs;  // global declaration
    private List<D3Cextcode> m_exts;
    private List<CSDefFunGroup> m_defs;  // function definition
    private List<ICSInstruction> m_main_inss;  // global instructions
    private Set<ICSTypeBooking> m_track;  // type booking info

    public CSInstructionTransformer() {
        m_decs = new ArrayList<CSDecGroup>();
        m_exts = new ArrayList<D3Cextcode>();
        m_defs = new ArrayList<CSDefFunGroup>();
        m_main_inss = new ArrayList<ICSInstruction>();
        m_track = new HashSet<ICSTypeBooking>();
        
    }
    
    public void transformProgram(List<DecGroup> decs,
    		List<D3Cextcode> exts,
    		List<DefFunGroup> defs,
    		List<IStfplInstruction> main_inss) {

    	for (DecGroup dec: decs) {
    		CSDecGroup csdec = transfrom(dec);
    		m_decs.add(csdec);
    	}
    	
    	for (DefFunGroup def: defs) {
    		CSDefFunGroup csdef = transform(def);
    		m_defs.add(csdef);
    	}
    	x
    }

	private CSDefFunGroup transform(DefFunGroup def_grp) {
		List<CSDefFun> csdefs = new ArrayList<CSDefFun>();
	    for (DefFun def: def_grp.m_funs) {
	    	CSDefFun csdef = transform(def);
	    	csdefs.add(csdef);
	    }
	    
	    return new CSDefFunGroup(def_grp.m_knd, csdefs);
    }

	private CSDefFun transform(DefFun fun_def) {
	    CSSId csid = CSSId.fromSId(fun_def.m_name,
	    		                   fun_def.m_name.getType().toCSType(m_track).m_type);
	    
	    List<CSVar> csparas = new ArrayList<CSVar>();
	    for (Cp3at pat: fun_def.m_p3ts) {
	    	if (pat.m_node instanceof P3Tvar) {
	    		Cd3var var = ((P3Tvar)pat.m_node).m_var;
	    		CSVar csvar = CSVar.fromCd3var(var, var.m_stype.toCSType(m_track).m_type);
	    		csparas.add(csvar);
	    	}
	    }
	    
	    List<ICSInstruction> csinss = new ArrayList<ICSInstruction>();
	    for (IStfplInstruction ins: fun_def.m_inss) {
	    	ICSInstruction csins = transform(ins);
	    	csinss.add(csins);
	    }
	    
	    Set<CSVar> csenv = new HashSet<CSVar>();
	    for (Cd3var var: fun_def.m_env) {
	    	CSVar csvar = CSVar.fromCd3var(var, var.m_stype.toCSType(m_track).m_type);
	    	csenv.add(csvar);
	    }
	    
	    return new CSDefFun(fun_def.m_loc, csid, fun_def.m_lin, csparas, csinss, csenv);
    }

	private ICSInstruction transform(IStfplInstruction ins) {
	    // TODO Auto-generated method stub
	    return null;
    }

	private CSDecGroup transfrom(DecGroup dec) {
		List<CSSId> cssids = new ArrayList<CSSId>();
	    for (SId sid: dec.m_names) {
	    	CSSId cssid = CSSId.fromSId(sid, sid.getType().toCSType(m_track).m_type);
	    	cssids.add(cssid);
	    }
	    
	    return new CSDecGroup(dec.m_knd, cssids);
    }
    
}
