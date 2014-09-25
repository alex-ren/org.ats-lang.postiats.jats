package jats.utfpl.stfpl.mcinstruction;



import jats.utfpl.instruction.InsMCAssert;
import jats.utfpl.instruction.TID;
import jats.utfpl.instruction.ValPrim;
import jats.utfpl.stfpl.ccomp.CCompUtils;
import jats.utfpl.stfpl.Ilabel;
import jats.utfpl.stfpl.csharpins.CSDecGroup;
import jats.utfpl.stfpl.csharpins.CSDefFunGroup;
import jats.utfpl.stfpl.csharpins.ICSInstruction;
import jats.utfpl.stfpl.csharptype.ICSTypeBooking;
import jats.utfpl.stfpl.dynexp3.Cd3cst;
import jats.utfpl.stfpl.dynexp3.D3Cextcode;
import jats.utfpl.stfpl.instructions.AtomValue;
import jats.utfpl.stfpl.instructions.DecGroup;
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
import jats.utfpl.stfpl.instructions.SId.SIdCategory;
import jats.utfpl.stfpl.instructions.SIdUser;
import jats.utfpl.stfpl.instructions.VNameCst;
import jats.utfpl.tree.ExpId;
import jats.utfpl.tree.IExp;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;


public class MCInstructionTransformer {    
    private List<IMCGlobalEntity> m_g_eneities;  // global variable / value
    
    private List<MCGlobalExtCode> m_exts;
    private List<MCDecGroup> m_decs;  // global declaration  // including lambda
    
    private List<MCDefFunGroup> m_defs;  // function definition
    private List<IMCInstruction> m_main_inss;  // global instructions
    private Set<ICSTypeBooking> m_track;  // type booking info
    private String m_main_name;
    
    public MCInstructionTransformer() {
        m_g_eneities = new ArrayList<IMCGlobalEntity>(); 
        m_exts = new ArrayList<MCGlobalExtCode>(); 
        m_decs = new ArrayList<MCDecGroup>(); 
        m_defs = new ArrayList<MCDefFunGroup>(); 
        m_main_inss = new ArrayList<IMCInstruction>(); 
        m_track = new HashSet<ICSTypeBooking>(); 
        m_main_name = null;
        
                
    }
    
    public void transformProgram(List<DecGroup> decs,
            List<D3Cextcode> exts,
            List<IFunDef> defs,
            List<IStfplInstruction> main_inss) {

        for (DecGroup dec: decs) {
            MCDecGroup csdec = transfrom(dec);
            m_decs.add(csdec);
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
        
    }
    
    private MCDecGroup transfrom(DecGroup dec) {
        List<MCSId> mctids = new ArrayList<MCSId>();
        for (SId sid: dec.m_names) {
            MCSId mctid = MCSId.fromSId(sid, sid.getType().toCSType(m_track).m_type);
            mctids.add(mctid);
        }
        
        return new MCDecGroup(dec.m_knd, mctids);
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
        Set<MCSIdUser> csenv = null;
        
        if (null != env) {
            csenv = new HashSet<MCSIdUser>();
            for (SIdUser sid_user: env) {
                MCSIdUser cssid_user = MCSIdUser.fromSIdUser(
                        sid_user, sid_user.getType().toCSType(m_track).m_type);
                csenv.add(cssid_user);
            }
        }

        MCSId cs_env_id = null;
        if (null != node.m_env_name) {
            cs_env_id = StfplVP2CS(node.m_env_name);
        }
        
//        CSTBookingEnv env_book = null;
//        if (!env.isEmpty()) {
//            env_book = new CSTBookingEnv(cs_env_id, csenv);
//            m_track.add(env_book);
//        }
        
        List<MCDefFun> mcdefs = new ArrayList<MCDefFun>();
        MCSId mcid = StfplVP2CS(node.m_name);
        List<MCSId> mcparas = new ArrayList<MCSId>();
        for (SId sid: node.m_paras) {
            MCSId mcsid = StfplVP2CS(sid);
            mcparas.add(mcsid);
        }

        List<IMCInstruction> mcinss = transform(node.m_inss);
        MCDefFun mcdef = new MCDefFun(node.m_loc, mcid, node.m_lin, mcparas, mcinss);
        mcdefs.add(mcdef);
        
        return new MCDefFunGroup(null/*no info about kind*/, mcdefs, cs_env_id, csenv);
    }

    private MCDefFunGroup transform(DefFunGroup node) {
        // Each closure has the potential to create a new type.
        Set<SIdUser> env = node.m_env;
        Set<MCSIdUser> mcenv = null;
        
        if (null != env) {
            mcenv = new HashSet<MCSIdUser>();
            for (SIdUser sid_user: env) {
                MCSIdUser cssid_user = MCSIdUser.fromSIdUser(
                        sid_user, sid_user.getType().toCSType(m_track).m_type);
                mcenv.add(cssid_user);
            }
            
        }

        MCSId mc_env_id = null;
        if (null != node.m_env_name) {
            mc_env_id = StfplVP2CS(node.m_env_name);
        }

//        CSTBookingEnv env_book = null;
//        if (!env.isEmpty()) {
//            env_book = new CSTBookingEnv(cs_env_id, csenv);
//            m_track.add(env_book);
//        }

        List<MCDefFun> mcdefs = new ArrayList<MCDefFun>();
        for (DefFun def: node.m_funs) {
            MCDefFun csdef = transform(def);
            mcdefs.add(csdef);
        }
        
        return new MCDefFunGroup(node.m_knd, mcdefs, mc_env_id, mcenv);
    }

    private MCDefFun transform(DefFun fun_def) {
        MCSId mcid = StfplVP2MC_second(fun_def.m_name);
        
        List<MCSId> mcparas = new ArrayList<MCSId>();
        for (SId sid: fun_def.m_paras) {
            MCSId mcsid = StfplVP2CS(sid);
            mcparas.add(mcsid);
        }

        List<IMCInstruction> mcinss = transform(fun_def.m_inss);
        return new MCDefFun(fun_def.m_loc, mcid, fun_def.m_lin, mcparas, mcinss);
    }
    
    private MCSId StfplVP2MC_second(SId sid) {  // This sid has been touched.
        return MCSId.fromSId2(sid);
    }

    List<IMCInstruction> transform(List<IStfplInstruction> inss) {
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
        Set<MCSIdUser> env = StfplVP2CS(ins.m_env);
        return new MCInsFormEnv(name, env);
    }

    private MCInsTuple transform_ins(InsTuple ins) {

        List<IMCValPrim> elements = StfplVP2CS(ins.m_elements);
        MCSId holder = StfplVP2CS(ins.m_holder);
        return new MCInsTuple(elements, holder);
    }

    private MCInsPatLabDecompose transform_ins(InsPatLabDecompose ins) {

        Ilabel lab = ins.m_lab;
        MCSId holder = StfplVP2CS(ins.m_holder);
        IMCValPrim vp = StfplVP2CS(ins.m_vp);
        return new MCInsPatLabDecompose(lab, holder, vp);
    }

    private MCInsMove transform_ins(InsMove ins) {

        MCSId holder = StfplVP2CS(ins.m_holder);
        IMCValPrim vp = StfplVP2CS(ins.m_vp);
        return new MCInsMove(vp, holder);
    }

    private MCInsCond transform_ins(InsCond ins) {

        MCSId holder = StfplVP2CS(ins.m_holder);
        IMCValPrim cond = StfplVP2CS(ins.m_cond);
        List<IMCInstruction> btrue = transform(ins.m_btrue);
        List<IMCInstruction> bfalse = null;
        if (null != ins.m_bfalse) {
            bfalse = transform(ins.m_bfalse);
        }
        
        return new MCInsCond(holder, cond, btrue, bfalse, null);
    }

    private MCInsClosure transform_ins(InsClosure ins) {

        MCSId name = StfplVP2MC_second(ins.m_name);
        MCSIdUser env = StfplVP2CS(ins.m_env);
        
        return new MCInsClosure(name, env);
        
    }

    private IMCInstruction transform_ins(InsCall ins) {
        IVarName name = ins.m_fun.getSId().m_name;
        if (name instanceof VNameCst) {
            Cd3cst fname = ((VNameCst)name).m_cst;
            if (fname.compSymbolString(CCompUtils.cSysGvarCreate)) {
                // val gdst = sys_gvar_create (src)
                // initialize global variable
                
                MCSId dst = StfplVP2CS(ins.m_holder);
                // update category
                dst.m_sid.updateCat(SIdCategory.eGloVar);
                
                IMCValPrim src = StfplVP2CS(ins.m_args.get(0));
                
                return new MCInsStore(src, dst);
                
            } else if (fname.compSymbolString(CCompUtils.cSysGvarUpdate)) {
                // val () = sys_gvar_update (gdst, src): void
                MCSId dst = StfplVP2CS(ins.m_holder);
                IMCValPrim src = StfplVP2CS(ins.m_args.get(0));
                
                return new MCInsStore(src, dst);
            } else if (fname.compSymbolString(CCompUtils.cSysGvarGet)) {
                // val dst = sys_gvar_get (gsrc)
                MCSId dst = StfplVP2CS(ins.m_holder);
                IMCValPrim src = StfplVP2CS(ins.m_args.get(0));
                
                return new MCInsLoad((MCSIdUser)src, dst);
            } else if (fname.compSymbolString(CCompUtils.cSysMCSetInt)) {
                // prval () = mc_set_int (g1, local1, g2, local2)
                List<MCSIdUser> global_vars = new ArrayList<MCSIdUser>();
                List<IMCValPrim> local_values = new ArrayList<IMCValPrim>();
                
                Iterator<IValPrim> iter = ins.m_args.iterator();
                while (iter.hasNext()) {
                    IValPrim gv = iter.next();
                    MCSIdUser global_var = StfplVP2CS((SIdUser)gv);
                                        
                    // update category
                    global_var.getSId().updateCat(SIdCategory.eGloVar);
                    
                    global_vars.add(global_var);
                    
                    IValPrim lv = iter.next();
                    IMCValPrim local_value = StfplVP2CS(lv);
                    local_values.add(local_value);
                }
                
                return new MCInsMCSet(local_values, global_vars);
            } else if (fname.compSymbolString(CCompUtils.cSysMCGetInt)) {
                // prval local = mc_get_int (g1, g2)
                MCSId local_value = StfplVP2CS(ins.m_holder);
                
                List<MCSIdUser> global_vars = new ArrayList<MCSIdUser>();
                
                Iterator<IValPrim> iter = ins.m_args.iterator();
                while (iter.hasNext()) {
                    IValPrim gv = iter.next();
                    MCSIdUser global_var = StfplVP2CS((SIdUser)gv);
                                        
                    // update category
                    global_var.getSId().updateCat(SIdCategory.eGloVar);
                    
                    global_vars.add(global_var);
                }
                
                return new MCInsMCGet(local_value, global_vars);
            } else if (fname.compSymbolString(CCompUtils.cSysMCAssert)) {
                //   prval () = mc_assert (xx > 6)
                IMCValPrim v = StfplVP2CS(ins.m_args.get(0));

                return new MCInsMCAssert(v);
            } else if (fname.compSymbolString(CCompUtils.cSysMutexAlloc)) {
                // val holder = sys_mutex_allocate ()
                MCSId holder = StfplVP2CS(ins.m_holder);

                return new MCInsMutexAlloc(holder);
            } else if (fname.compSymbolString(CCompUtils.cSysMutexRelease)) {
                // val () = sys_mutex_release (m)
                IMCValPrim mutex = StfplVP2CS(ins.m_args.get(0));
                return new MCInsMutexRelease(mutex);

            } else if (fname.compSymbolString(CCompUtils.cSysCondAlloc)) {
                // val holder = sys_mutex_allocate ()
                MCSId holder = StfplVP2CS(ins.m_holder);

                return new MCInsCondAlloc(holder);
            } else if (fname.compSymbolString(CCompUtils.cSysCondRelease)) {
                // val () = sys_mutex_release (m)
                IMCValPrim mutex = StfplVP2CS(ins.m_args.get(0));
                return new MCInsCondRelease(mutex);

            } else if (fname.compSymbolString(CCompUtils.cSysThreadCreate)) {
                MCSId dst = StfplVP2CS(ins.m_holder);
                IMCValPrim src = StfplVP2CS(ins.m_args.get(0));
                
                return new xx(src, dst);
            } else {
                xx
            }
            
        }

        MCSId holder = StfplVP2CS(ins.m_holder);
        MCSIdUser fun = StfplVP2CS(ins.m_fun);
        List<IMCValPrim> args = StfplVP2CS(ins.m_args);
        
        return new MCInsCall(holder, fun, args);
        
        todo
        
    }
    
    private MCSId StfplVP2CS(SId sid) {
//      System.out.println("sid is " + sid.toStringCS());
        return MCSId.fromSId(sid,
                sid.getType().toCSType(m_track).m_type);
    }
    
       private MCSIdUser StfplVP2CS(SIdUser sid_user) {
            return MCSIdUser.fromSIdUser(sid_user,
                    sid_user.getType().toCSType(m_track).m_type);
        }
    
    private MCAtomValue StfplVP2CS(AtomValue v) {
        return new MCAtomValue(v, v.getType().toCSType(m_track).m_type);
    }
    
    private IMCValPrim StfplVP2CS(IValPrim vp) {
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
    
    private List<IMCValPrim> StfplVP2CS(List<IValPrim> vps) {
        List<IMCValPrim> mcvps = new ArrayList<IMCValPrim>();
        for (IValPrim vp: vps) {
            IMCValPrim mcvp = StfplVP2CS(vp);
            mcvps.add(mcvp);
        }
        return mcvps;
    }
    
//  private Set<ICSValPrim> StfplVP2CS(Set<IValPrim> vps) {
//      Set<ICSValPrim> csvps = new HashSet<ICSValPrim>();
//      for (IValPrim vp: vps) {
//          ICSValPrim csvp = StfplVP2CS(vp);
//          csvps.add(csvp);
//      }
//      return csvps;
//  }
    
    private Set<MCSIdUser> StfplVP2CS(Set<SIdUser> vps) {
        Set<MCSIdUser> csvps = new HashSet<MCSIdUser>();
        for (SIdUser vp: vps) {
            MCSIdUser csvp = StfplVP2CS(vp);
            csvps.add(csvp);
        }
        return csvps;
    }


    
}
