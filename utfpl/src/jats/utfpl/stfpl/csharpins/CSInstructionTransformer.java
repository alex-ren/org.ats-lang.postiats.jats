package jats.utfpl.stfpl.csharpins;

import jats.utfpl.stfpl.Ilabel;
import jats.utfpl.stfpl.csharptype.CSTBookingEnv;
import jats.utfpl.stfpl.csharptype.ICSTypeBooking;
import jats.utfpl.stfpl.dynexp.Cd2ecl;
import jats.utfpl.stfpl.dynexp.ProgramUtfpl;
import jats.utfpl.stfpl.dynexp3.Cd3ecl;
import jats.utfpl.stfpl.dynexp3.Cd3var;
import jats.utfpl.stfpl.dynexp3.Cp3at;
import jats.utfpl.stfpl.dynexp3.D3Cextcode;
import jats.utfpl.stfpl.dynexp3.P3Tvar;
import jats.utfpl.stfpl.instructions.AtomValue;
import jats.utfpl.stfpl.instructions.DecGroup;
import jats.utfpl.stfpl.instructions.DefFun;
import jats.utfpl.stfpl.instructions.DefFunGroup;
import jats.utfpl.stfpl.instructions.IStfplInstruction;
import jats.utfpl.stfpl.instructions.IValPrim;
import jats.utfpl.stfpl.instructions.InsCall;
import jats.utfpl.stfpl.instructions.InsClosure;
import jats.utfpl.stfpl.instructions.InsCond;
import jats.utfpl.stfpl.instructions.InsMove;
import jats.utfpl.stfpl.instructions.InsPatLabDecompose;
import jats.utfpl.stfpl.instructions.InsTuple;
import jats.utfpl.stfpl.instructions.SId;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class CSInstructionTransformer {
    private List<CSDecGroup> m_decs;  // global declaration
    private List<D3Cextcode> m_exts;
    private List<CSDefFunGroup> m_defs;  // function definition
    private List<ICSInstruction> m_main_inss;  // global instructions
    private Set<ICSTypeBooking> m_track;  // type booking info

    public List<CSDecGroup> getDecs() {
        return m_decs;
    }
    
    public List<D3Cextcode> getExts() {
        return m_exts;
    }
    public List<CSDefFunGroup> getDefs() {
        return m_defs;
    }
    public List<ICSInstruction> getMain_inss() {
        return m_main_inss;
    }
    public Set<ICSTypeBooking> getTrack() {
        return m_track;
    }
    
    public CSInstructionTransformer() {
        m_decs = new ArrayList<CSDecGroup>();
        m_exts = null;
        m_defs = new ArrayList<CSDefFunGroup>();
        m_main_inss = null;
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
    	
    	m_exts = exts;
    	
    	for (DefFunGroup def: defs) {
    		CSDefFunGroup csdef = transform(def);
    		m_defs.add(csdef);
    	}
    	
    	m_main_inss = transform(main_inss);
    	
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

	    List<ICSInstruction> csinss = transform(fun_def.m_inss);
	    
	    Set<CSVar> csenv = new HashSet<CSVar>();
	    for (Cd3var var: fun_def.m_env) {
	    	CSVar csvar = CSVar.fromCd3var(var, var.m_stype.toCSType(m_track).m_type);
	    	csenv.add(csvar);
	    }
	    
	    // Each closure has the potential to create a new type.
	    CSTBookingEnv benv = null;
	    if (!csenv.isEmpty()) {
	        benv = new CSTBookingEnv(csid, csenv);
	        m_track.add(benv);
	    }

	    return new CSDefFun(fun_def.m_loc, csid, fun_def.m_lin, csparas, csinss, csenv, benv);
    }
	
	List<ICSInstruction> transform(List<IStfplInstruction> inss) {
		List<ICSInstruction> csinss = new ArrayList<ICSInstruction>();
		for (IStfplInstruction ins: inss) {
			ICSInstruction csins = transform(ins);
			csinss.add(csins);
		}
		return csinss;
	}
	
	private ICSInstruction transform(IStfplInstruction ins) {
	    if (ins instanceof InsCall) {
	    	return transform_ins((InsCall)ins);
	    } else if (ins instanceof InsClosure) {
	    	return transform_ins((InsClosure)ins);
	    } else if (ins instanceof InsCond) {
	    	return transform_ins((InsCond)ins);
	    } else if (ins instanceof InsMove) {
	    	return transform_ins((InsMove)ins);
	    } else if (ins instanceof InsPatLabDecompose) {
	    	return transform_ins((InsPatLabDecompose)ins);
	    } else if (ins instanceof InsTuple) {
	    	return transform_ins((InsTuple)ins);
	    } else {
	    	throw new Error(ins + " is not supported");
	    }
    }

	private CSInsTuple transform_ins(InsTuple ins) {

	    List<ICSValPrim> elements = StfplVP2CS(ins.m_elements);
	    CSSId holder = StfplVP2CS(ins.m_holder);
	    return new CSInsTuple(elements, holder);
    }

	private CSInsPatLabDecompose transform_ins(InsPatLabDecompose ins) {

	    Ilabel lab = ins.m_lab;
	    CSSId holder = StfplVP2CS(ins.m_holder);
	    ICSValPrim vp = StfplVP2CS(ins.m_vp);
	    return new CSInsPatLabDecompose(lab, holder, vp);
    }

	private CSInsMove transform_ins(InsMove ins) {

	    CSSId holder = StfplVP2CS(ins.m_holder);
	    ICSValPrim vp = StfplVP2CS(ins.m_vp);
	    return new CSInsMove(vp, holder);
    }

	private CSInsCond transform_ins(InsCond ins) {

	    CSSId holder = StfplVP2CS(ins.m_holder);
	    ICSValPrim cond = StfplVP2CS(ins.m_cond);
	    List<ICSInstruction> btrue = transform(ins.m_btrue);
	    List<ICSInstruction> bfalse = null;
	    if (null != ins.m_bfalse) {
	    	bfalse = transform(ins.m_bfalse);
	    }
	    
	    return new CSInsCond(holder, cond, btrue, bfalse);
    }

	private CSInsClosure transform_ins(InsClosure ins) {

	    CSSId name = StfplVP2CS(ins.m_name);
	    Set<CSSId> env = StfplVP2CS(ins.m_env);
	    
	    return new CSInsClosure(name, env);
	    
    }

	private CSInsCall transform_ins(InsCall ins) {

	    CSSId holder = StfplVP2CS(ins.m_holder);
	    ICSValPrim fun = StfplVP2CS(ins.m_fun);
	    List<ICSValPrim> args = StfplVP2CS(ins.m_args);
	    
	    return new CSInsCall(holder, fun, args);
	    
    }
	
	private CSSId StfplVP2CS(SId sid) {
//	    System.out.println("sid is " + sid.toStringCS());
		return CSSId.fromSId(sid,
				sid.getType().toCSType(m_track).m_type);
	}
	
	private CSAtomValue StfplVP2CS(AtomValue v) {
		return new CSAtomValue(v, v.getType().toCSType(m_track).m_type);
	}
	
	private ICSValPrim StfplVP2CS(IValPrim vp) {
		if (vp instanceof AtomValue) {
			return StfplVP2CS((AtomValue)vp);
		} else if (vp instanceof SId) {
			return StfplVP2CS((SId)vp);
		} else {
			throw new Error(vp + " is not supported.");
		}
	}
	
	private List<ICSValPrim> StfplVP2CS(List<IValPrim> vps) {
		List<ICSValPrim> csvps = new ArrayList<ICSValPrim>();
		for (IValPrim vp: vps) {
			ICSValPrim csvp = StfplVP2CS(vp);
			csvps.add(csvp);
		}
		return csvps;
	}
	
//	private Set<ICSValPrim> StfplVP2CS(Set<IValPrim> vps) {
//		Set<ICSValPrim> csvps = new HashSet<ICSValPrim>();
//		for (IValPrim vp: vps) {
//			ICSValPrim csvp = StfplVP2CS(vp);
//			csvps.add(csvp);
//		}
//		return csvps;
//	}
	
	private Set<CSSId> StfplVP2CS(Set<SId> vps) {
		Set<CSSId> csvps = new HashSet<CSSId>();
		for (SId vp: vps) {
			CSSId csvp = StfplVP2CS(vp);
			csvps.add(csvp);
		}
		return csvps;
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
