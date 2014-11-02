package jats.utfpl.stfpl.mcinstruction;

import jats.utfpl.stfpl.ccomp.CCompUtils;
import jats.utfpl.stfpl.Csymbol;
import jats.utfpl.stfpl.Ilabel;
import jats.utfpl.stfpl.LABsym;
import jats.utfpl.stfpl.csharptype.ICSTypeBooking;
import jats.utfpl.stfpl.dynexp3.Cd3cst;
import jats.utfpl.stfpl.dynexp3.Cd3var;
import jats.utfpl.stfpl.dynexp3.D3Cextcode;
import jats.utfpl.stfpl.instructions.AtomValue;
import jats.utfpl.stfpl.instructions.DecAtomValGroup;
import jats.utfpl.stfpl.instructions.DecFunGroup;
import jats.utfpl.stfpl.instructions.DefFun;
import jats.utfpl.stfpl.instructions.DefFunGroup;
import jats.utfpl.stfpl.instructions.IFunDef;
import jats.utfpl.stfpl.instructions.IStfplInstruction;
import jats.utfpl.stfpl.instructions.IValPrim;
import jats.utfpl.stfpl.instructions.IVarName;
import jats.utfpl.stfpl.instructions.ImplFun;
import jats.utfpl.stfpl.instructions.InsCall;
import jats.utfpl.stfpl.instructions.InsClosure;
import jats.utfpl.stfpl.instructions.InsCond;
import jats.utfpl.stfpl.instructions.InsFormEnv;
import jats.utfpl.stfpl.instructions.InsMove;
import jats.utfpl.stfpl.instructions.InsPatLabDecompose;
import jats.utfpl.stfpl.instructions.InsTuple;
import jats.utfpl.stfpl.instructions.SId;
import jats.utfpl.stfpl.instructions.SIdFactory;
import jats.utfpl.stfpl.instructions.SId.SIdCategory;
import jats.utfpl.stfpl.instructions.SIdUser;
import jats.utfpl.stfpl.instructions.VNameCst;
import jats.utfpl.stfpl.stype.ILabPat;
import jats.utfpl.stfpl.stype.LabPatNorm;
import jats.utfpl.stfpl.stype.RecType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/*
 * 
 * One major task to for processing closure.
 */

public class MCInstructionTransformer {    

    // list of all the global values, some
    // of which may not have implementation.
    private List<MCDecAtomValGroup> m_global_v;  
    
    // Declarations of all the functions, some
    // of which may not have implementation.
    private List<MCDecFunGroup> m_decs;  

    
    // function definition and implementation
    private List<MCDefFunGroup> m_defs;
    
    private List<MCGlobalExtCode> m_exts;
    
    // global instructions
    private List<IMCInstruction> m_main_inss;  

    private String m_main_name;
    
    /* ********** ************ */
    
    private MCSIdFactory m_mcsid_factory;
    private SIdFactory m_sid_factory;
    
    public MCInstructionTransformer(MCSIdFactory mcsid_factory
    		                      , SIdFactory sid_factory) {
        m_global_v = new ArrayList<MCDecAtomValGroup>();
        m_decs = new ArrayList<MCDecFunGroup>();        
        m_defs = new ArrayList<MCDefFunGroup>();
        
        m_exts = new ArrayList<MCGlobalExtCode>(); 

        m_main_inss = new ArrayList<IMCInstruction>(); 
//        m_track = new HashSet<ICSTypeBooking>(); 
        m_main_name = null;
        
        m_mcsid_factory = mcsid_factory;
        m_sid_factory = sid_factory;
    
    }
    
    public ProgramMCIns transformProgram(
            List<DecAtomValGroup> gvs,
            List<DecFunGroup> decs,
            List<IFunDef> defs,
            List<D3Cextcode> exts,
            List<IStfplInstruction> main_inss) {
        
        for (DecAtomValGroup grp0: gvs) {
            MCDecAtomValGroup grp = transform(grp0);
            m_global_v.add(grp);
        }

        for (DecFunGroup dec: decs) {
            MCDecFunGroup grp = transfrom(dec);
            m_decs.add(grp);
        }
        
        for (D3Cextcode ext_code: exts) {
            MCGlobalExtCode ext = new MCGlobalExtCode(ext_code.m_code);
            m_exts.add(ext);
        }
        
        for (IFunDef def: defs) {
            MCDefFunGroup mcdef = transform(def);
            m_defs.add(mcdef);
        }
        
        m_main_inss = transform(main_inss);
        
        return new ProgramMCIns(m_global_v, m_decs, m_defs, m_exts, m_main_inss, m_main_name);
        
    }
    
    private MCDecAtomValGroup transform(DecAtomValGroup grp) {
        List<MCSId> names = new ArrayList<MCSId>();
        for (SId sid: grp.m_names) {
            MCSId name = m_sid_factory.fromSIdAtomVal(sid);
            names.add(name);
        }
        
        return new MCDecAtomValGroup(grp.m_knd, names);
    }

    private MCDecFunGroup transfrom(DecFunGroup grp) {
        List<MCSId> names = new ArrayList<MCSId>();
        for (SId sid: grp.m_names) {
        	MCSId name = m_sid_factory.fromSId(sid);
            names.add(name);
        }
        
        return new MCDecFunGroup(grp.m_knd, names);
    }
    

    private MCDefFunGroup transform(IFunDef def) {
        if (def instanceof DefFunGroup) {
            return transform((DefFunGroup)def);
        } else if (def instanceof ImplFun) {
            return transform((ImplFun)def);
        } else {
            throw new Error(def + " is not supported.");
        }
    }
    
    private MCDefFunGroup transform(ImplFun node) {
        if (node.m_name.toStringCS().startsWith("main")) {
            m_main_name = node.m_name.toStringCS();
        }
        // Each closure has the potential to create a new type.
        Set<SIdUser> env = node.m_env;
        if (null != env) {
            throw new Error("implemenatating a closure is not supported.");
        }

        MCSId cs_env_id = null;
        if (null != node.m_env_name) {
            cs_env_id = StfplVP2MC(node.m_env_name);
        }
        
//        CSTBookingEnv env_book = null;
//        if (!env.isEmpty()) {
//            env_book = new CSTBookingEnv(cs_env_id, csenv);
//            m_track.add(env_book);
//        }
        
        List<MCDefFun> mcdefs = new ArrayList<MCDefFun>();
        MCSId mcid = StfplVP2MC(node.m_name);
        List<MCSId> mcparas = new ArrayList<MCSId>();
        for (SId sid: node.m_paras) {
            MCSId mcsid = StfplVP2MC(sid);
            mcparas.add(mcsid);
        }

        List<IMCInstruction> mcinss = transform(node.m_inss);
        MCDefFun mcdef = new MCDefFun(node.m_loc, mcid, node.m_lin, mcparas, mcinss);
        mcdefs.add(mcdef);
        
        return new MCDefFunGroup(null/*no info about kind*/, mcdefs, cs_env_id, csenv);
    }

    private MCDefFunGroup transform(DefFunGroup node) {
    	// all the functions in the same group
        List<SId> grp_members = new ArrayList<SId>();
        List<SId> env_members = new ArrayList<SId>();
        if (node.isClosure()) {
            for (DefFun fun: node.m_funs) {
            	grp_members.add(fun.m_name);
            }
            
            for (SIdUser su: node.m_env) {
            	env_members.add(su.getSId());
            }
        }

        List<MCDefFun> mcdefs = new ArrayList<MCDefFun>();
        for (DefFun def: node.m_funs) {
            MCDefFun csdef = transform(def, grp_members, env_members, node.getEnvType());
            mcdefs.add(csdef);
        }
        
        return new MCDefFunGroup(node.m_knd, mcdefs);
    }

    private MCDefFun transform(DefFun fun_def
    		, List<SId> grp_members  // all the members in the same group
    		, List<SId> env_members  // elements in environment
    		, RecType env_type
    		) {
    	
    	SId fun_name = fun_def.m_name;
        MCSId mcfun_name = m_mcsid_factory.fromSId(fun_def.m_name);
        
        SId env_name = m_sid_factory.createEnvForPara(
        		fun_name.toStringNoStamp() + "_env", env_type);
        MCSId mcenv_name = m_mcsid_factory.fromSId(env_name);

        List<MCSId> mcparas = new ArrayList<MCSId>();
        for (SId sid: fun_def.m_paras) {
            MCSId mcsid = m_mcsid_factory.fromSId(sid);
            mcparas.add(mcsid);
        }
        
        // add instructions for creating closures for themselves
        // as well as getting element from environment
        List<IMCInstruction> ins_header = new ArrayList<IMCInstruction>();
        Map<SId, MCSId> clo_name_map = new HashMap<SId, MCSId>();
        Map<SId, MCSId> clo_env_map = new HashMap<SId, MCSId>();
        
        for (SId grp_member: grp_members) {
        	clo_env_map.put(grp_member, mcenv_name);  // function name => env name
        	
        	MCSId mcgrp_member = m_mcsid_factory.fromSId(grp_member);
        	SId clo_name = m_sid_factory.createLocalVar(
        			grp_member.toStringNoStamp()
        		  , grp_member.getType());
        	MCSId mcclo_name = m_mcsid_factory.fromSId(clo_name);
        	clo_name_map.put(grp_member, mcclo_name);  // function name => closure name
        	
        	MCInsClosure ins = new MCInsClosure(mcclo_name, mcenv_name);
        	ins_header.add(ins);  // add to inss

        }
        
        for (SId env_member: env_members) {
        	SId new_env_member = m_sid_factory.createLocalVar(
        			env_member.toStringNoStamp(), xxx)
        }
        
        

        List<IMCInstruction> mcinss = transform(fun_def.m_inss);
        return new MCDefFun(fun_def.m_loc, mcid, fun_def.m_lin, mcparas, mcinss);
    }
    

    List<IMCInstruction> transform(List<IStfplInstruction> inss
    		, Map<SId, MCSId> clo_name  // function name => closure name
    		, Map<SId, MCSId> env_name  // function name => env name
    		, Map<SId, MCSId> map_name  // name change caused by element of the environment
    		) {
        List<IMCInstruction> mcinss = new ArrayList<IMCInstruction>();
        for (IStfplInstruction ins: inss) {
            IMCInstruction mcins = transform(ins);
            mcinss.add(mcins);
        }
        return mcinss;
    }
    
    private IMCInstruction transform(IStfplInstruction ins) {
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

    private MCInsFormEnv transform_ins(InsFormEnv ins) {
        MCSId name = StfplVP2MC_second(ins.m_name);
        Set<MCSId> env = StfplVP2MC(ins.m_env);
        return new MCInsFormEnv(name, env);
    }

    private MCInsTuple transform_ins(InsTuple ins) {

        List<IMCValPrim> elements = StfplVP2MC(ins.m_elements);
        MCSId holder = StfplVP2MC(ins.m_holder);
        return new MCInsTuple(elements, holder);
    }

    private MCInsPatLabDecompose transform_ins(InsPatLabDecompose ins) {

        Ilabel lab = ins.m_lab;
        MCSId holder = StfplVP2MC(ins.m_holder);
        IMCValPrim vp = StfplVP2MC(ins.m_vp);
        return new MCInsPatLabDecompose(lab, holder, vp);
    }

    private MCInsMove transform_ins(InsMove ins) {

        MCSId holder = StfplVP2MC(ins.m_holder);
        IMCValPrim vp = StfplVP2MC(ins.m_vp);
        return new MCInsMove(vp, holder);
    }

    private MCInsCond transform_ins(InsCond ins) {

        MCSId holder = StfplVP2MC(ins.m_holder);
        IMCValPrim cond = StfplVP2MC(ins.m_cond);
        List<IMCInstruction> btrue = transform(ins.m_btrue);
        List<IMCInstruction> bfalse = null;
        if (null != ins.m_bfalse) {
            bfalse = transform(ins.m_bfalse);
        }
        
        return new MCInsCond(holder, cond, btrue, bfalse, null);
    }

    private MCInsClosure transform_ins(InsClosure ins) {

        MCSId name = StfplVP2MC_second(ins.m_name);
        MCSId env = StfplVP2MC(ins.m_env);
        
        return new MCInsClosure(name, env);
        
    }

    private IMCInstruction transform_ins(InsCall ins) {
        IVarName name = ins.m_fun.getSId().m_name;
        if (name instanceof VNameCst) {
            Cd3cst fname = ((VNameCst)name).m_cst;
            if (fname.compSymbolString(CCompUtils.cSysGvarCreate)) {
                // val gdst = sys_gvar_create (src)
                // initialize global variable
                
                MCSId dst = StfplVP2MC(ins.m_holder);
                // update category
                ins.m_holder.updateCat(SIdCategory.eGloVar);
                
                IMCValPrim src = StfplVP2MC(ins.m_args.get(0));
                
                return new MCInsStore(src, dst);
                
            } else if (fname.compSymbolString(CCompUtils.cSysGvarUpdate)) {
                // val () = sys_gvar_update (gdst, src): void
                MCSId dst = StfplVP2MC(ins.m_holder);
                IMCValPrim src = StfplVP2MC(ins.m_args.get(0));
                
                return new MCInsStore(src, dst);
            } else if (fname.compSymbolString(CCompUtils.cSysGvarGet)) {
                // val dst = sys_gvar_get (gsrc)
                MCSId dst = StfplVP2MC(ins.m_holder);
                IMCValPrim src = StfplVP2MC(ins.m_args.get(0));
                
                return new MCInsLoad((MCSId)src, dst);
            } else if (fname.compSymbolString(CCompUtils.cSysMCSetInt)) {
                // prval () = mc_set_int (g1, local1, g2, local2)
                List<MCSId> global_vars = new ArrayList<MCSId>();
                List<IMCValPrim> local_values = new ArrayList<IMCValPrim>();
                
                Iterator<IValPrim> iter = ins.m_args.iterator();
                while (iter.hasNext()) {
                    IValPrim gv = iter.next();
                    MCSId global_var = StfplVP2MC((SIdUser)gv);
                                        
                    // update category
                    global_var.getSId().updateCat(SIdCategory.eGloVar);
                    
                    global_vars.add(global_var);
                    
                    IValPrim lv = iter.next();
                    IMCValPrim local_value = StfplVP2MC(lv);
                    local_values.add(local_value);
                }
                
                return new MCInsMCSet(local_values, global_vars);
            } else if (fname.compSymbolString(CCompUtils.cSysMCGetInt)) {
                // prval local = mc_get_int (g1, g2)
                MCSId local_value = StfplVP2MC(ins.m_holder);
                
                List<MCSId> global_vars = new ArrayList<MCSId>();
                
                Iterator<IValPrim> iter = ins.m_args.iterator();
                while (iter.hasNext()) {
                    IValPrim gv = iter.next();
                    MCSId global_var = StfplVP2MC((SIdUser)gv);
                                        
                    // update category
                    global_var.getSId().updateCat(SIdCategory.eGloVar);
                    
                    global_vars.add(global_var);
                }
                
                return new MCInsMCGet(local_value, global_vars);
            } else if (fname.compSymbolString(CCompUtils.cSysMCAssert)) {
                //   prval () = mc_assert (xx > 6)
                IMCValPrim v = StfplVP2MC(ins.m_args.get(0));

                return new MCInsMCAssert(v);
            } else if (fname.compSymbolString(CCompUtils.cSysMutexAlloc)) {
                // val holder = sys_mutex_allocate ()
                MCSId holder = StfplVP2MC(ins.m_holder);

                return new MCInsMutexAlloc(holder);
            } else if (fname.compSymbolString(CCompUtils.cSysMutexRelease)) {
                // val () = sys_mutex_release (m)
                IMCValPrim mutex = StfplVP2MC(ins.m_args.get(0));
                return new MCInsMutexRelease(mutex);

            } else if (fname.compSymbolString(CCompUtils.cSysCondAlloc)) {
                // val holder = sys_mutex_allocate ()
                MCSId holder = StfplVP2MC(ins.m_holder);

                return new MCInsCondAlloc(holder);
            } else if (fname.compSymbolString(CCompUtils.cSysCondRelease)) {
                // val () = sys_mutex_release (m)
                IMCValPrim mutex = StfplVP2MC(ins.m_args.get(0));
                return new MCInsCondRelease(mutex);

            } else if (fname.compSymbolString(CCompUtils.cSysThreadCreate)) {
            	// val () = sys_thread_create (tid, funlab, arg)
            	IMCValPrim tid = StfplVP2MC(ins.m_args.get(0));
            	MCSId funlab = StfplVP2MC((SIdUser)ins.m_args.get(0));
            	IMCValPrim arg = StfplVP2MC(ins.m_args.get(0));

                return new MCInsThreadCreate(tid, funlab, arg);
            } else {
            	// do nothing
            }
        }

        MCSId holder = StfplVP2MC(ins.m_holder);
        MCSId fun = StfplVP2MC(ins.m_fun);
        List<IMCValPrim> args = StfplVP2MC(ins.m_args);
        
        return new MCInsCall(holder, fun, args);
    }
    

//    private MCAtomValue StfplVP2MC(AtomValue v) {
//        return new MCAtomValue(v, v.getType().toCSType(m_track).m_type);
//    }
//    
//    private IMCValPrim StfplVP2MC(IValPrim vp) {
//        if (vp instanceof AtomValue) {
//            return StfplVP2MC((AtomValue)vp);
//        } else if (vp instanceof SId) {
//            return StfplVP2MC((SId)vp);
//        }
//        else if (vp instanceof SIdUser) {
//                return StfplVP2MC((SIdUser)vp);
//        } else {
//            throw new Error(vp + " is not supported.");
//        }
//    }
//    
//    private List<IMCValPrim> StfplVP2MC(List<IValPrim> vps) {
//        List<IMCValPrim> mcvps = new ArrayList<IMCValPrim>();
//        for (IValPrim vp: vps) {
//            IMCValPrim mcvp = StfplVP2MC(vp);
//            mcvps.add(mcvp);
//        }
//        return mcvps;
//    }
//    
//    private MCSId StfplVP2MC(SId sid) {
////      System.out.println("sid is " + sid.toStringCS());
//        return MCSId.fromSId(sid,
//                sid.getType().toCSType(m_track).m_type);
//    }
//    
//
//    private MCSId StfplVP2MC(SIdUser sid_user) {
//        return MCSId.fromSIdUser(sid_user,
//                sid_user.getType().toCSType(m_track).m_type);
//    }
//
//    private Set<MCSId> StfplVP2MC(Set<SIdUser> vps) {
//        Set<MCSId> csvps = new HashSet<MCSId>();
//        for (SIdUser vp: vps) {
//            MCSId csvp = StfplVP2MC(vp);
//            csvps.add(csvp);
//        }
//        return csvps;
//    }


    
}



