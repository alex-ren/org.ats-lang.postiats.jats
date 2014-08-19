package jats.utfpl.stfpl.csharpins;

import jats.utfpl.stfpl.Ilabel;
import jats.utfpl.stfpl.csharptype.ICSTypeBooking;
import jats.utfpl.stfpl.dynexp3.D3Cextcode;
import jats.utfpl.stfpl.instructions.AtomValue;
import jats.utfpl.stfpl.instructions.DecGroup;
import jats.utfpl.stfpl.instructions.DefFun;
import jats.utfpl.stfpl.instructions.DefFunGroup;
import jats.utfpl.stfpl.instructions.IFunDef;
import jats.utfpl.stfpl.instructions.IStfplInstruction;
import jats.utfpl.stfpl.instructions.IValPrim;
import jats.utfpl.stfpl.instructions.ImplFun;
import jats.utfpl.stfpl.instructions.InsCall;
import jats.utfpl.stfpl.instructions.InsClosure;
import jats.utfpl.stfpl.instructions.InsCond;
import jats.utfpl.stfpl.instructions.InsFormEnv;
import jats.utfpl.stfpl.instructions.InsMove;
import jats.utfpl.stfpl.instructions.InsPatLabDecompose;
import jats.utfpl.stfpl.instructions.InsTuple;
import jats.utfpl.stfpl.instructions.SId;
import jats.utfpl.stfpl.instructions.SIdUser;

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
    private String m_main_name;

    public List<CSDecGroup> getDecs() {
        return m_decs;
    }
    
    public List<D3Cextcode> getExts() {
        return m_exts;
    }
    public List<CSDefFunGroup> getDefs() {
        return m_defs;
    }
    public List<ICSInstruction> getMainInss() {
        return m_main_inss;
    }
    
    public String getMainName() {
        return m_main_name;
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
        m_main_name = null;
    }
    
    public void transformProgram(List<DecGroup> decs,
    		List<D3Cextcode> exts,
    		List<IFunDef> defs,
    		List<IStfplInstruction> main_inss) {

    	for (DecGroup dec: decs) {
    		CSDecGroup csdec = transfrom(dec);
    		m_decs.add(csdec);
    	}
    	
    	m_exts = exts;
    	
    	for (IFunDef def: defs) {
    		CSDefFunGroup csdef = transform(def);
    		m_defs.add(csdef);
    	}
    	
    	m_main_inss = transform(main_inss);
    	
    }

    private CSDefFunGroup transform(IFunDef def) {
        if (def instanceof DefFunGroup) {
            return transform((DefFunGroup)def);
        } else if (def instanceof ImplFun) {
            return transform((ImplFun)def);
        } else {
            throw new Error(def + " is not supported.");
        }
    }
    
    private CSDefFunGroup transform(ImplFun node) {
        if (node.m_name.toStringCS().startsWith("main")) {
            m_main_name = node.m_name.toStringCS();
        }
        // Each closure has the potential to create a new type.
        Set<SIdUser> env = node.m_env;
        Set<CSSIdUser> csenv = null;
        
        if (null != env) {
            csenv = new HashSet<CSSIdUser>();
            for (SIdUser sid_user: env) {
                CSSIdUser cssid_user = CSSIdUser.fromSIdUser(
                        sid_user, sid_user.getType().toCSType(m_track).m_type);
                csenv.add(cssid_user);
            }
        }

        CSSId cs_env_id = null;
        if (null != node.m_env_name) {
            cs_env_id = StfplVP2CS(node.m_env_name);
        }
        
//        CSTBookingEnv env_book = null;
//        if (!env.isEmpty()) {
//            env_book = new CSTBookingEnv(cs_env_id, csenv);
//            m_track.add(env_book);
//        }
        
        List<CSDefFun> csdefs = new ArrayList<CSDefFun>();
        CSSId csid = StfplVP2CS(node.m_name);
        List<CSSId> csparas = new ArrayList<CSSId>();
        for (SId sid: node.m_paras) {
            CSSId cssid = StfplVP2CS(sid);
            csparas.add(cssid);
        }

        List<ICSInstruction> csinss = transform(node.m_inss);
        CSDefFun csdef = new CSDefFun(node.m_loc, csid, node.m_lin, csparas, csinss);
        csdefs.add(csdef);
        
        return new CSDefFunGroup(null/*no info about kind*/, csdefs, cs_env_id, csenv);
    }

    private CSDefFunGroup transform(DefFunGroup node) {
        // Each closure has the potential to create a new type.
        Set<SIdUser> env = node.m_env;
        Set<CSSIdUser> csenv = null;
        
        if (null != env) {
            csenv = new HashSet<CSSIdUser>();
            for (SIdUser sid_user: env) {
                CSSIdUser cssid_user = CSSIdUser.fromSIdUser(
                        sid_user, sid_user.getType().toCSType(m_track).m_type);
                csenv.add(cssid_user);
            }
            
        }

        CSSId cs_env_id = null;
        if (null != node.m_env_name) {
            cs_env_id = StfplVP2CS(node.m_env_name);
        }

//        CSTBookingEnv env_book = null;
//        if (!env.isEmpty()) {
//            env_book = new CSTBookingEnv(cs_env_id, csenv);
//            m_track.add(env_book);
//        }

		List<CSDefFun> csdefs = new ArrayList<CSDefFun>();
	    for (DefFun def: node.m_funs) {
	    	CSDefFun csdef = transform(def);
	    	csdefs.add(csdef);
	    }
	    
	    return new CSDefFunGroup(node.m_knd, csdefs, cs_env_id, csenv);
    }

	private CSDefFun transform(DefFun fun_def) {
	    CSSId csid = StfplVP2CS_second(fun_def.m_name);
	    
	    List<CSSId> csparas = new ArrayList<CSSId>();
	    for (SId sid: fun_def.m_paras) {
	        CSSId cssid = StfplVP2CS(sid);
	        csparas.add(cssid);
	    }

	    List<ICSInstruction> csinss = transform(fun_def.m_inss);
	    return new CSDefFun(fun_def.m_loc, csid, fun_def.m_lin, csparas, csinss);
    }
	
	private CSSId StfplVP2CS_second(SId sid) {  // This sid has been touched.
        return CSSId.fromSId2(sid);
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
	      } else if (ins instanceof InsFormEnv) {
	        return transform_ins((InsFormEnv)ins);
	    } else {
	    	throw new Error(ins + " is not supported");
	    }
    }

	private CSInsFormEnv transform_ins(InsFormEnv ins) {
	    CSSId name = StfplVP2CS_second(ins.m_name);
	    Set<CSSIdUser> env = StfplVP2CS(ins.m_env);
	    return new CSInsFormEnv(name, env);
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

	    CSSId name = StfplVP2CS_second(ins.m_name);
	    CSSIdUser env = StfplVP2CS(ins.m_env);
	    
	    return new CSInsClosure(name, env);
	    
    }

	private CSInsCall transform_ins(InsCall ins) {

	    CSSId holder = StfplVP2CS(ins.m_holder);
	    CSSIdUser fun = StfplVP2CS(ins.m_fun);
	    List<ICSValPrim> args = StfplVP2CS(ins.m_args);
	    
	    return new CSInsCall(holder, fun, args);
	    
    }
	
	private CSSId StfplVP2CS(SId sid) {
//	    System.out.println("sid is " + sid.toStringCS());
		return CSSId.fromSId(sid,
				sid.getType().toCSType(m_track).m_type);
	}
	
	   private CSSIdUser StfplVP2CS(SIdUser sid_user) {
	        return CSSIdUser.fromSIdUser(sid_user,
	                sid_user.getType().toCSType(m_track).m_type);
	    }
	
	private CSAtomValue StfplVP2CS(AtomValue v) {
		return new CSAtomValue(v, v.getType().toCSType(m_track).m_type);
	}
	
	private ICSValPrim StfplVP2CS(IValPrim vp) {
		if (vp instanceof AtomValue) {
			return StfplVP2CS((AtomValue)vp);
		} else if (vp instanceof SId) {
			return StfplVP2CS((SId)vp);
		}
		else if (vp instanceof SIdUser) {
			    return StfplVP2CS((SIdUser)vp);
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
	
	private Set<CSSIdUser> StfplVP2CS(Set<SIdUser> vps) {
		Set<CSSIdUser> csvps = new HashSet<CSSIdUser>();
		for (SIdUser vp: vps) {
		    CSSIdUser csvp = StfplVP2CS(vp);
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
