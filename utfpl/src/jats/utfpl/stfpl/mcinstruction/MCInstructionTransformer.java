package jats.utfpl.stfpl.mcinstruction;

import jats.utfpl.stfpl.ccomp.CCompUtils;
import jats.utfpl.stfpl.Ilabel;
import jats.utfpl.stfpl.dynexp3.Cd3cst;
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
import jats.utfpl.stfpl.instructions.ProgramIns;
import jats.utfpl.stfpl.instructions.SId;
import jats.utfpl.stfpl.instructions.SIdUser;
import jats.utfpl.stfpl.instructions.VNameCst;
import jats.utfpl.stfpl.stype.AuxSType;
import jats.utfpl.stfpl.stype.ISType;
import jats.utfpl.stfpl.stype.RecType;
import jats.utfpl.utils.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
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
    
    // names of functions which are used to create threads
    private Set<MCSId> m_thread_names;
    
    /* ********** ************ */
    // Auxiliary members for transform.
    
    private MCSIdFactory m_mcsid_factory;
//    private SIdFactory m_sid_factory;
    private Map<SId, IFunDef> m_fun_map;
    
    public ProgramIns m_prog;
    
    /* *********** ********** */
    
    public MCInstructionTransformer(
                                    MCSIdFactory mcsid_factory
    		                      , Map<SId, IFunDef> fun_map
    		                      , ProgramIns prog
    		                      ) {
        m_global_v = new ArrayList<MCDecAtomValGroup>();
        m_decs = new ArrayList<MCDecFunGroup>();        
        m_defs = new ArrayList<MCDefFunGroup>();
        
        m_exts = new ArrayList<MCGlobalExtCode>(); 

        m_main_inss = new ArrayList<IMCInstruction>();
        m_main_name = null;
        
        m_thread_names = new HashSet<MCSId>();
        
        /* ********** ********** */
        
        m_mcsid_factory = mcsid_factory;
        m_fun_map = fun_map;
        
        
        m_prog = prog;
    
    }
    
    public ProgramMCIns transform(
            ) {

        for (DecAtomValGroup grp0: m_prog.m_gvs) {
            MCDecAtomValGroup grp = transform(grp0);
            m_global_v.add(grp);
        }

        for (DecFunGroup dec: m_prog.m_decs) {
            MCDecFunGroup grp = transfrom(dec);
            m_decs.add(grp);
        }
        
        for (D3Cextcode ext_code: m_prog.m_exts) {
            MCGlobalExtCode ext = new MCGlobalExtCode(ext_code.m_code);
            m_exts.add(ext);
        }
        
        for (IFunDef def: m_prog.m_defs) {
            MCDefFunGroup mcdef = transform(def);
            m_defs.add(mcdef);
        }
        
        Map<SId, MCSId> map_clo_name = new HashMap<SId, MCSId>();
        Map<SId, MCSId> map_env_name = new HashMap<SId, MCSId>();
        Map<SId, MCSId> map_name = new HashMap<SId, MCSId>();
        m_main_inss = transform(null, m_prog.m_main_inss
        		, map_clo_name
                , map_env_name
                , map_name);
        
        ProgramMCIns prog = new ProgramMCIns(m_global_v, m_decs, m_defs, m_exts, m_main_inss, m_main_name);
        MCInstructionProcessor processor = new MCInstructionProcessor(prog, m_mcsid_factory);
        return processor.process();
        
    }
    
    private MCDecAtomValGroup transform(DecAtomValGroup grp) {
        List<MCSId> names = new ArrayList<MCSId>();
        for (SId sid: grp.m_names) {
            MCSId name = m_mcsid_factory.fromSId(sid);
            names.add(name);
        }
        
        return new MCDecAtomValGroup(grp.m_knd, names);
    }

    private MCDecFunGroup transfrom(DecFunGroup grp) {
        List<MCSId> names = new ArrayList<MCSId>();
        for (SId sid: grp.m_names) {
        	MCSId name = m_mcsid_factory.fromSId(sid);
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

        List<MCDefFun> mcdefs = new ArrayList<MCDefFun>();
        MCSId mcid = m_mcsid_factory.fromSId(node.m_name);
        List<MCSId> mcparas = new ArrayList<MCSId>();
        for (SId sid: node.m_paras) {
            MCSId mcsid = m_mcsid_factory.fromSId(sid);
            mcparas.add(mcsid);
        }


        Map<SId, MCSId> map_clo_name = new HashMap<SId, MCSId>();
        Map<SId, MCSId> map_env_name = new HashMap<SId, MCSId>();
        Map<SId, MCSId> map_name = new HashMap<SId, MCSId>();
        
        List<IMCInstruction> mcinss = transform(node.m_name, node.m_inss
						        		, map_clo_name
						                , map_env_name
						                , map_name);
        MCDefFun mcdef = new MCDefFun(node.m_loc
                , mcid
                , node.m_lin
                , mcparas
//                , null
//                , null
                , mcinss);
        mcdefs.add(mcdef);
        
        return new MCDefFunGroup(null/*no info about kind*/, mcdefs);
    }

    private MCDefFunGroup transform(DefFunGroup node) {
    	// all the functions in the same group
        List<SId> grp_members = new ArrayList<SId>();
        List<SId> env_members = new ArrayList<SId>();
        if (node.isClosure()) {
            for (DefFun fun: node.m_funs) {
                
                for (SIdUser su: node.m_env) {
                	env_members.add(su.getSId());
                }
            	grp_members.add(fun.m_name);
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
    	
//    	Log.log4j.info("MCInstructionTransform DefFun fun is " + fun_def.m_name.toStringIns() +
//    			" funtype is " + AuxSType.getClosureInfo(fun_def.m_name.getType()).toString());
        
        // add instructions for creating closures for themselves
        // as well as getting element from environment
        List<IMCInstruction> mcinss = new ArrayList<IMCInstruction>();
        Map<SId, MCSId> map_clo_name = new HashMap<SId, MCSId>();
        Map<SId, MCSId> map_env_name = new HashMap<SId, MCSId>();
        Map<SId, MCSId> map_name = new HashMap<SId, MCSId>();
        
//        Map<SId, MCSId> map_env_ele = new HashMap<SId, MCSId>();
        
        // function name
    	SId fun_name = fun_def.m_name;
        MCSId mcfun_name = m_mcsid_factory.fromSId(fun_def.m_name);

        // process all the parameters
        List<MCSId> mcparas = new ArrayList<MCSId>();
        for (SId sid: fun_def.m_paras) {
            MCSId mcsid = m_mcsid_factory.fromSId(sid);
            mcparas.add(mcsid);
        }
        
        // name for the environment of the function
        SId env_name = m_mcsid_factory.getSIdFac().createEnvForPara(
        		fun_name.toStringNoStamp() + "_env", env_type);
        MCSId mcenv_name = m_mcsid_factory.fromSId(env_name);

        if (AuxSType.isClosure(mcfun_name.getType())) {
            mcparas.add(mcenv_name);  // Turn env into a normal parameter.
        }


        // This is done below in the loop for group members.
//        map_env_name.put(fun_name, mcenv_name);  
        
        // process all the function names of the current group
        // including current function itself.
        for (SId grp_member: grp_members) {
        	if (AuxSType.isClosure(grp_member.getType())) {
        		
            	map_env_name.put(grp_member, mcenv_name);  // function name => env name
            	
            	// Form closures for all the members in the group.
            	MCSId mcgrp_member = m_mcsid_factory.fromSId(grp_member);
            	
            	SId clo_name = m_mcsid_factory.getSIdFac().createLocalVar(
            			grp_member.toStringNoStamp() + "_clo"
            		  , grp_member.getType());
            	MCSId mcclo_name = m_mcsid_factory.fromSId(clo_name);
            	map_clo_name.put(grp_member, mcclo_name);  // function name => closure name
            	
            	MCInsClosure ins = new MCInsClosure(mcclo_name, mcgrp_member, mcenv_name);
            	mcinss.add(ins);  // add to inss
        	}
        }
        
        int index = 0;
        for (SId env_member: env_members) {
        	ISType member_type = env_member.getType();
        	MCInsGetEleFromEnv ins = null;
        	if (AuxSType.isClosure(member_type) && env_member.isUserFun()) {
        		/* ******** *********** */
        		// To get type.
        		DefFunGroup member_fun_grp = (DefFunGroup)m_fun_map.get(env_member);
        		member_type = member_fun_grp.getEnvType();  // The environment of the function.

        		/* ******** *********** */
        		
        		// get the env for a closure from the environment
        		SId env_sid = m_mcsid_factory.getSIdFac().createLocalVar(
            			env_member.toStringWithStamp() + "_env", member_type);
        		MCSId mcenv_sid = m_mcsid_factory.fromSId(env_sid);
        		map_env_name.put(env_member, mcenv_sid);  // function name => env name
//        		map_env_ele.put(env_member, mcenv_sid);  // element in env => its new name

        		ins = new MCInsGetEleFromEnv(
        				mcenv_sid  // new value
        				, mcenv_name  // environment of the function
        				, env_member.toStringWithStamp()  // tag
        				, index
        				);
        		mcinss.add(ins);  // add to inss
        		
        		/* ******** *********** */
        		
        		// create a closure        		
        		SId closure_sid = m_mcsid_factory.getSIdFac().createLocalVar(
            			env_member.toStringWithStamp() + "_closure", member_type);
        		MCSId mcclosure_sid = m_mcsid_factory.fromSId(closure_sid);
        		map_clo_name.put(env_member, mcclosure_sid);  // function name => closure name
        		
        		MCSId env_fun_name = m_mcsid_factory.fromSId(env_member);  // function name
        		MCInsClosure ins2 = new MCInsClosure(
        				mcclosure_sid
        				, env_fun_name
        				, mcenv_sid  // environment of the function
        				);
        		mcinss.add(ins2);  // add to inss
        		
        		
        	} else {
        		SId env_sid = m_mcsid_factory.getSIdFac().createLocalVar(
            			env_member.toStringWithStamp(), member_type);
        		MCSId mcenv_sid = m_mcsid_factory.fromSId(env_sid);
        		
        		map_name.put(env_member, mcenv_sid);  // name => name
//        		map_env_ele.put(env_member, mcenv_sid);  // element in env => its new name
        		
        		ins = new MCInsGetEleFromEnv(
        				mcenv_sid  // new value
        				, mcenv_name  // environment of the function
        				, env_member.toStringWithStamp()  // tag
        				, index
        				);
        		mcinss.add(ins);  // add to inss        		
        	}
        	++index;
        	
        	

        }

        // process the body of the function
        List<IMCInstruction> mcinss_tail = transform(fun_def.m_name, fun_def.m_inss
        		                      , map_clo_name
        		                      , map_env_name
        		                      , map_name);
        
        mcinss.addAll(mcinss_tail);
        return new MCDefFun(fun_def.m_loc
        		, mcfun_name
        		, fun_def.m_lin
        		, mcparas
//        		, mcenv_name
//        		, map_env_ele
        		, mcinss);
    }
    

    List<IMCInstruction> transform(SId funname, List<IStfplInstruction> inss
    		, Map<SId, MCSId> map_clo_name  // function name => closure name
    		, Map<SId, MCSId> map_env_name  // function name => env name
    		, Map<SId, MCSId> map_name  // name change caused by element of the environment
    		) {

        List<IMCInstruction> mcinss = new ArrayList<IMCInstruction>();
        for (IStfplInstruction ins: inss) {
            IMCInstruction mcins = transform(ins
            		                , map_clo_name
            		                , map_env_name
            		                , map_name);
            mcinss.add(mcins);
        }
        return mcinss;
    }
    
    private IMCInstruction transform(IStfplInstruction ins
    		, Map<SId, MCSId> map_clo_name  // function name => closure name
    		, Map<SId, MCSId> map_env_name  // function name => env name
    		, Map<SId, MCSId> map_name  // name change caused by element of the environment
    		) {
        if (ins instanceof InsCall) {
            return transform_ins((InsCall)ins, map_clo_name, map_env_name, map_name);
        } else if (ins instanceof InsFormEnv) {
            return transform_ins((InsFormEnv)ins, map_clo_name, map_env_name, map_name);
        } else if (ins instanceof InsClosure) {
            return transform_ins((InsClosure)ins, map_clo_name, map_env_name, map_name);
        } else if (ins instanceof InsCond) {
            return transform_ins((InsCond)ins, map_clo_name, map_env_name, map_name);
        } else if (ins instanceof InsMove) {
            return transform_ins((InsMove)ins, map_clo_name, map_env_name, map_name);
        } else if (ins instanceof InsPatLabDecompose) {
            return transform_ins((InsPatLabDecompose)ins, map_clo_name, map_env_name, map_name);
        } else if (ins instanceof InsTuple) {
            return transform_ins((InsTuple)ins, map_clo_name, map_env_name, map_name);
        } else {
            throw new Error(ins + " is not supported");
        }
    }

    private MCInsFormEnv transform_ins(InsFormEnv ins
    		, Map<SId, MCSId> map_clo_name  // function name => closure name
    		, Map<SId, MCSId> map_env_name  // function name => env name
    		, Map<SId, MCSId> map_name  // name change caused by element of the environment
    		) {
        // The SId (ins.m_name) may be of inappropriate type (using closure, not env).
    	// We build a new SId here.
    	SId old_name = ins.m_name;
//    	Log.log4j.info("form env " + old_name.toStringIns());
    	RecType env_type = ins.getFunGroup().getEnvType();
    	SId new_name = m_mcsid_factory.getSIdFac().createEnvForclosure("env", env_type);
    	MCSId mcnew_name = m_mcsid_factory.fromSId(new_name);
    	
    	map_name.put(old_name, mcnew_name);  // name => name
    	
    	for (DefFun fun_def: ins.getFunGroup().m_funs) {
    		map_env_name.put(fun_def.m_name, mcnew_name);  // map: function name => env_name
    	}
    	
    	List<MCSId> env_eles = new ArrayList<MCSId>();
    	for (SIdUser ele_user: ins.m_env) {
    		SId ele = ele_user.getSId();
    		ISType ele_type = ele.getType();
    		MCSId mcele = null;
    		if (AuxSType.isClosure(ele_type) && ele.isUserFun()) {
    			mcele = map_env_name.get(ele); 
        		if (mcele == null) {
        			throw new Error("This should not happen. " + "ele is " + ele.toStringIns() +
        		    ". Maybe you specify the enclosing function as a closure or change ele to a function.");
        		}
    		} else {
    			mcele = m_mcsid_factory.fromSId(ele);
    		}
    		

    		env_eles.add(mcele);
    		
    	}

        return new MCInsFormEnv(mcnew_name, env_eles);
    }
    
    private MCInsClosure transform_ins(InsClosure ins
    		, Map<SId, MCSId> map_clo_name  // function name => closure name
    		, Map<SId, MCSId> map_env_name  // function name => env name
    		, Map<SId, MCSId> map_name  // name change caused by element of the environment
    		) {

    	// name of the function
    	SId fun_name = ins.m_name;
    	MCSId mcfun_name = m_mcsid_factory.fromSId(fun_name);
    	
    	// name of the closure
    	SId closure = m_mcsid_factory.getSIdFac().createLocalVar(
    			fun_name.toStringNoStamp() + "_closure"
    		  , fun_name.getType());
    	MCSId mcclo_name = m_mcsid_factory.fromSId(closure);
    	map_clo_name.put(fun_name, mcclo_name);  // function name => closure name
    	
    	MCSId mcenv_name = map_env_name.get(fun_name);
    	if (null == mcenv_name) {
    		throw new Error("This should not happen.");
    	}

    	MCInsClosure ins_clo = new MCInsClosure(mcclo_name, mcfun_name, mcenv_name);
    	return ins_clo;
    }


    private MCInsTuple transform_ins(InsTuple ins
    		, Map<SId, MCSId> map_clo_name  // function name => closure name
    		, Map<SId, MCSId> map_env_name  // function name => env name
    		, Map<SId, MCSId> map_name  // name change caused by element of the environment
    		) {

    	MCSId mcholder = m_mcsid_factory.fromSId(ins.m_holder);
    	
    	List<IMCValPrim> elements = m_mcsid_factory.fromIValPrimList(
    			                       ins.m_elements, map_clo_name, map_name);

        return new MCInsTuple(elements, mcholder);
    }

    private MCInsPatLabDecompose transform_ins(InsPatLabDecompose ins
    		, Map<SId, MCSId> map_clo_name  // function name => closure name
    		, Map<SId, MCSId> map_env_name  // function name => env name
    		, Map<SId, MCSId> map_name  // name change caused by element of the environment
    		) {

        Ilabel lab = ins.m_lab;
        MCSId holder = m_mcsid_factory.fromSId(ins.m_holder);
        IMCValPrim vp = m_mcsid_factory.fromIValPrim(ins.m_vp, map_clo_name, map_name);
        return new MCInsPatLabDecompose(lab, ins.m_index, holder, vp);
    }

    private MCInsMove transform_ins(InsMove ins
    		, Map<SId, MCSId> map_clo_name  // function name => closure name
    		, Map<SId, MCSId> map_env_name  // function name => env name
    		, Map<SId, MCSId> map_name  // name change caused by element of the environment
    		) {

        MCSId holder = m_mcsid_factory.fromSId(ins.m_holder);
        IMCValPrim vp = m_mcsid_factory.fromIValPrim(ins.m_vp, map_clo_name, map_name);
        if (null == vp) {
        	if (ins.m_vp instanceof SId) {
        		throw new Error("SId vp is null for " + ((SId)ins.m_vp).toStringIns());
        	} else {
        		throw new Error("SIdUser vp is null for " + ((SIdUser)ins.m_vp).getSId().toStringIns());
        	}
        	
        }
        return new MCInsMove(vp, holder);
    }

    private MCInsCond transform_ins(InsCond ins
    		, Map<SId, MCSId> map_clo_name  // function name => closure name
    		, Map<SId, MCSId> map_env_name  // function name => env name
    		, Map<SId, MCSId> map_name  // name change caused by element of the environment
    		) {

        MCSId holder = m_mcsid_factory.fromSId(ins.m_holder);
        IMCValPrim cond = m_mcsid_factory.fromIValPrim(ins.m_cond, map_clo_name, map_name);
        List<IMCInstruction> btrue = transform(null, ins.m_btrue
								        		, map_clo_name
								                , map_env_name
								                , map_name);
        List<IMCInstruction> bfalse = null;
        if (null != ins.m_bfalse) {
            bfalse = transform(null, ins.m_bfalse
	        		, map_clo_name
	                , map_env_name
	                , map_name);
        }
        
        return new MCInsCond(holder, cond, btrue, bfalse, null);
    }

    private IMCInstruction transform_ins(InsCall ins
            , Map<SId, MCSId> map_clo_name  // function name => closure name
            , Map<SId, MCSId> map_env_name  // function name => env name
            , Map<SId, MCSId> map_name  // name change caused by element of the environment
            ) {
//        List<IMCInstruction> inss = new ArrayList<IMCInstruction>();
        
        MCSId mcholder = m_mcsid_factory.fromSId(ins.m_holder);
        
        IVarName name = ins.m_fun.getSId().m_name;
        if (name instanceof VNameCst) {
            Cd3cst fname = ((VNameCst)name).m_cst;
            if (fname.compSymbolString(CCompUtils.cConATSSharedCreate)) {
                // fun conats_shared_create {a: viewtype} (ele: a): shared (a)
                IValPrim vp = ins.m_args.get(0);
                IMCValPrim mcvp = m_mcsid_factory.fromIValPrim(vp, map_clo_name, map_name);
                return new MCInsSharedCreate(mcholder, mcvp, MCAtomValue.createFromInt(1));
            }
            else if (fname.compSymbolString(CCompUtils.cConATSSharednCreate)) {
                    // fun conats_sharedn_create {a: viewt@ype} {n:pos} (ele: a, n: int n): shared_t (a, n)
                    IValPrim vp = ins.m_args.get(0);
                    IMCValPrim mcvp = m_mcsid_factory.fromIValPrim(vp, map_clo_name, map_name);
                    IValPrim n = ins.m_args.get(1);
                    IMCValPrim mcn = m_mcsid_factory.fromIValPrim(n, map_clo_name, map_name);                    
                    return new MCInsSharedCreate(mcholder, mcvp, mcn);
                           
            } else if (fname.compSymbolString(CCompUtils.cConATSMutexCreate)) {
                // fun conats_mutex_create (): mutex

                return new MCInsMutexCreate(mcholder);
            } else if (fname.compSymbolString(CCompUtils.cConATSAtomRefCreate)) {
                // fun conats_atomref_create {a:t@ype} (data: a): atomref a
                IValPrim vp = ins.m_args.get(0);
                IMCValPrim mcvp = m_mcsid_factory.fromIValPrim(vp, map_clo_name, map_name);
                return new MCInsAtomRefCreate(mcholder, mcvp);
                
            } else if (fname.compSymbolString(CCompUtils.cConATSAtomRefUpdate)) {
                // fun conats_atomref_update {a:t@ype} (gv: atomref a, data: a): void
                
                IValPrim ref = ins.m_args.get(0);
                IMCValPrim mcref = m_mcsid_factory.fromIValPrim(ref, map_clo_name, map_name);
                if (mcref instanceof MCAtomValue) {
                    throw new Error("This should not happen.");
                }
                
                MCSId mcsid_ref = (MCSId)mcref;
                
                IValPrim vp = ins.m_args.get(1);
                IMCValPrim mcvp = m_mcsid_factory.fromIValPrim(vp, map_clo_name, map_name);
                
                boolean isret = ins.m_holder.isRetHolder();

                return new MCInsAtomRefUpdate(mcsid_ref, mcvp, isret);
                
            } else if (fname.compSymbolString(CCompUtils.cConATSAtomRefGet)) {
                // fun conats_atomref_get {a:t@ype} (gv: atomref a): a
                
                IValPrim ref = ins.m_args.get(0);
                IMCValPrim mcref = m_mcsid_factory.fromIValPrim(ref, map_clo_name, map_name);
                if (mcref instanceof MCAtomValue) {
                    throw new Error("This should not happen.");
                }

                MCSId mcsid_ref = (MCSId)mcref;
                return new MCInsAtomRefGet(mcsid_ref, mcholder);
            } else if (fname.compSymbolString(CCompUtils.cConATSAtomArrayRefCreate)) {
                // fun conats_atomarrayref_create {a:t@ype} (len: int, data: a): atomarrayref a
                IValPrim len = ins.m_args.get(0);
                IMCValPrim mclen = m_mcsid_factory.fromIValPrim(len, map_clo_name, map_name);
                
                IValPrim vp = ins.m_args.get(1);
                IMCValPrim mcvp = m_mcsid_factory.fromIValPrim(vp, map_clo_name, map_name);
                return new MCInsArrayRefCreate(mcholder, mclen, mcvp);
                
            } else if (fname.compSymbolString(CCompUtils.cConATSAtomArrayRefUpdate)) {
                // fun conats_atomarrayref_update {a:t@ype} (gv: atomarrayref a, pos: int, data: a): void
                
                IValPrim ref = ins.m_args.get(0);
                IMCValPrim mcref = m_mcsid_factory.fromIValPrim(ref, map_clo_name, map_name);
                if (mcref instanceof MCAtomValue) {
                    throw new Error("This should not happen.");
                }
                MCSId mcsid_ref = (MCSId)mcref;
                
                IValPrim pos = ins.m_args.get(1);
                IMCValPrim mcpos = m_mcsid_factory.fromIValPrim(pos, map_clo_name, map_name);
                
                IValPrim vp = ins.m_args.get(2);
                IMCValPrim mcvp = m_mcsid_factory.fromIValPrim(vp, map_clo_name, map_name);
                
                boolean isret = ins.m_holder.isRetHolder();

                return new MCInsArrayRefUpdate(mcsid_ref, mcpos, mcvp, isret);
                
            } else if (fname.compSymbolString(CCompUtils.cConATSAtomArrayRefGet)) {
                // fun conats_atomarrayref_get {a:t@ype} (gv: atomarrayref a, pos: int): a
                
                IValPrim ref = ins.m_args.get(0);
                IMCValPrim mcref = m_mcsid_factory.fromIValPrim(ref, map_clo_name, map_name);
                if (mcref instanceof MCAtomValue) {
                    throw new Error("This should not happen.");
                }
                MCSId mcsid_ref = (MCSId)mcref;
                
                IValPrim pos = ins.m_args.get(1);
                IMCValPrim mcpos = m_mcsid_factory.fromIValPrim(pos, map_clo_name, map_name);
                
                return new MCInsArrayRefGet(mcsid_ref, mcpos, mcholder);
            } else if (fname.compSymbolString(CCompUtils.cConATSThreadCreate)) {
                // fun conats_thread_create {a:type} (tfun: thread_fun_t a, arg: a, tid: tid): void
                
                IValPrim tfun = ins.m_args.get(0);
                if (tfun instanceof AtomValue) {
                    throw new Error("This should not happen.");
                }
                
                SId sid_tfun = null;
                if (tfun instanceof SIdUser) {
                	sid_tfun = ((SIdUser)tfun).getSId();
//                    throw new Error("It's not allowed to create thread by closure.");
                } else {
                	 sid_tfun = (SId)tfun;
                }

                if (false == sid_tfun.isUserFun()) {
                    throw new Error("Thread can be created by literal function name.");
                }
                
                MCSId mcsid_tfun = m_mcsid_factory.fromSId(sid_tfun);
                m_thread_names.add(mcsid_tfun);
                
                IValPrim arg = ins.m_args.get(1);
                IMCValPrim mcarg = m_mcsid_factory.fromIValPrim(arg, map_clo_name, map_name);
                
                IValPrim tid = ins.m_args.get(2);
                IMCValPrim mctid = m_mcsid_factory.fromIValPrim(tid, map_clo_name, map_name);
                
                boolean isret = ins.m_holder.isRetHolder();

                mcsid_tfun.setAsThread();
                return new MCInsThreadCreate(mcsid_tfun, mcarg, mctid, isret);

            } else if (fname.compSymbolString(CCompUtils.cMCSetInt)) {
                // prfun mc_set_int {id: sid} (id: (mc_gv_t id), x: int): void

                IValPrim gv = ins.m_args.get(0);
                IMCValPrim mcgv = m_mcsid_factory.fromIValPrim(gv, map_clo_name, map_name);
                if (mcgv instanceof MCAtomValue) {
                    throw new Error("This should not happen.");
                }
                
                MCSId mcsid_gv = (MCSId)mcgv;
                
                IValPrim vp = ins.m_args.get(1);
                IMCValPrim mcvp = m_mcsid_factory.fromIValPrim(vp, map_clo_name, map_name);
                
                boolean isret = ins.m_holder.isRetHolder();

                return new MCInsMCSet(mcvp, mcsid_gv, isret);
                
            } else if (fname.compSymbolString(CCompUtils.cMCGetInt)) {
                // prfun mc_get_int {id1: sid} (id1: mc_gv_t id1): [x1: int] (int_value_of (id1, x1) | int x1)

                IValPrim gv = ins.m_args.get(0);
                IMCValPrim mcgv = m_mcsid_factory.fromIValPrim(gv, map_clo_name, map_name);
                if (mcgv instanceof MCAtomValue) {
                    throw new Error("This should not happen.");
                }

                MCSId mcsid_gv = (MCSId)mcgv;
                return new MCInsMCGet(mcholder, mcsid_gv);

                
            } else if (fname.compSymbolString(CCompUtils.cMCAtomicStart)) {
                // prfun mc_atomic_start (): void

                return new MCInsMCAtomicStart();
            } else if (fname.compSymbolString(CCompUtils.cMCAtomicEnd)) {
                // prfun mc_atomic_end (): void

                return new MCInsMCAtomicEnd();
            } else if (fname.compSymbolString(CCompUtils.cMCAssert)) {
                // prfun mc_assert {b: bool} (x: bool b):<fun> [b == true] void
                
                IValPrim vp = ins.m_args.get(0);
                IMCValPrim mcvp = m_mcsid_factory.fromIValPrim(vp, map_clo_name, map_name);
                
                boolean isret = ins.m_holder.isRetHolder();

                return new MCInsMCAssert(mcvp, isret);
            } else if (fname.compSymbolString(CCompUtils.cMCVLockViewGet)) {
                // prfun mc_vlockview_get {x,y: nat} {xi,yi: pos} ( x: int x, y: int y, xi: int xi, yi: int yi): mc_vlockview (x, y, xi, yi)

                List<IMCValPrim> mcargs = m_mcsid_factory.fromIValPrimList(ins.m_args, map_clo_name, map_name);
                
                return new MCInsMCVLockViewGet(mcargs, mcholder); 
            } else if (fname.compSymbolString(CCompUtils.cMCVLockViewPut)) {
                // prfun mc_vlockview_put {x,y: nat} {xi,yi: pos} (v: mc_vlockview (x, y, xi, yi): void
                IValPrim v = ins.m_args.get(0);
                IMCValPrim mcv = m_mcsid_factory.fromIValPrim(v, map_clo_name, map_name);
                if (mcv instanceof MCAtomValue) {
                    throw new Error("This should not happen.");
                }
                boolean isret = ins.m_holder.isRetHolder();
                
                return new MCInsMCVLockViewPut((MCSId)mcv, isret); 
            
            } else if (fname.compSymbolString(CCompUtils.cConATSTidAllocate)) {
                // fun conats_tid_allocate (): tid
                return new MCInsTIdAllocate(mcholder);
            	
            } else {
//            	throw new Error(fname.toStringNoStamp() + " is not supported.");
                // do nothing for other extern functions
            }
        }

        // Form normal function call.
        
        // We can only invoke a function by its name.
        SId fun_name = ins.m_fun.getSId();
        if (!fun_name.isUserFun() && !fun_name.isConstant()) {
            throw new Error("Invocation by function pointer is not allowed. id is " + 
                      fun_name.toStringIns() + " category is " + fun_name.getCategory());
        }
        MCSId mcfun = m_mcsid_factory.fromSId(fun_name);

        List<IMCValPrim> mcargs = m_mcsid_factory.fromIValPrimList(ins.m_args, map_clo_name, map_name);

        if (AuxSType.isClosure(fun_name.getType())) {
        	MCSId mcenv = map_env_name.get(fun_name);
        	if (null == mcenv) {
        		Log.log4j.error("Check this. Cannot find the env for fun_name: " + fun_name.toStringWithStamp());
        	} else {
        		mcargs.add(mcenv);  // Turn env into a normal argument.
        	}
            
        }
        
        return new MCInsCall(mcholder, mcfun, mcargs);
    }

}



