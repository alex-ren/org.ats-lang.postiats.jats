package jats.utfpl.stfpl.instructions;

import jats.utfpl.stfpl.Cloc_t;
import jats.utfpl.stfpl.Csymbol;
import jats.utfpl.stfpl.LABsym;

import jats.utfpl.stfpl.dynexp.Edcstkind;
import jats.utfpl.stfpl.dynexp.Efunkind;
import jats.utfpl.stfpl.dynexp.Evalkind;
import jats.utfpl.stfpl.dynexp3.Cd3cst;
import jats.utfpl.stfpl.dynexp3.Cd3ecl;
import jats.utfpl.stfpl.dynexp3.Cd3exp;
import jats.utfpl.stfpl.dynexp3.Cd3var;
import jats.utfpl.stfpl.dynexp3.Cf3undec;
import jats.utfpl.stfpl.dynexp3.Cp3at;
import jats.utfpl.stfpl.dynexp3.Cv3aldec;
import jats.utfpl.stfpl.dynexp3.D3Cdatdecs;
import jats.utfpl.stfpl.dynexp3.D3Cdcstdecs;
import jats.utfpl.stfpl.dynexp3.D3Cextcode;
import jats.utfpl.stfpl.dynexp3.D3Cfundecs;
import jats.utfpl.stfpl.dynexp3.D3Cimpdec;
import jats.utfpl.stfpl.dynexp3.D3Cstacsts;
import jats.utfpl.stfpl.dynexp3.D3Cvaldecs;
import jats.utfpl.stfpl.dynexp3.D3EXPARGdyn;
import jats.utfpl.stfpl.dynexp3.D3Eapplst;
import jats.utfpl.stfpl.dynexp3.D3Ecst;
import jats.utfpl.stfpl.dynexp3.D3Eempty;
import jats.utfpl.stfpl.dynexp3.D3Ef0loat;
import jats.utfpl.stfpl.dynexp3.D3Ei0nt;
import jats.utfpl.stfpl.dynexp3.D3Eifopt;
import jats.utfpl.stfpl.dynexp3.D3ElamDyn;
import jats.utfpl.stfpl.dynexp3.D3Elet;
import jats.utfpl.stfpl.dynexp3.D3Es0tring;
import jats.utfpl.stfpl.dynexp3.D3Esym;
import jats.utfpl.stfpl.dynexp3.D3Etup;
import jats.utfpl.stfpl.dynexp3.D3Evar;
import jats.utfpl.stfpl.dynexp3.Id3ecl_node;
import jats.utfpl.stfpl.dynexp3.Id3exp_node;
import jats.utfpl.stfpl.dynexp3.Ip3at_node;
import jats.utfpl.stfpl.dynexp3.LABP3ATnorm;
import jats.utfpl.stfpl.dynexp3.P3Tany;
import jats.utfpl.stfpl.dynexp3.P3Tcon;
import jats.utfpl.stfpl.dynexp3.P3Tempty;
import jats.utfpl.stfpl.dynexp3.P3Trec;
import jats.utfpl.stfpl.dynexp3.P3Tvar;
import jats.utfpl.stfpl.dynexp3.ProgramStfpl3;
import jats.utfpl.stfpl.instructions.InsCall.ECallType;
import jats.utfpl.stfpl.instructions.SId.SIdCategory;
import jats.utfpl.stfpl.staexp.FUNCLOfun; 
import jats.utfpl.stfpl.stype.AuxSType;
import jats.utfpl.stfpl.stype.FunType;
import jats.utfpl.stfpl.stype.ILabPat;
import jats.utfpl.stfpl.stype.ISType;
import jats.utfpl.stfpl.stype.LabPatNorm;
import jats.utfpl.stfpl.stype.RecType;
import jats.utfpl.utils.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;

/*
 * 1. Handle closure.
 *   create instruction of FormEnv
 * 
 * 2. Mark global value (global variable will be marked as global value, 
 *    which will be updated at later stage.).
 * 
 */
public class InstructionTransformer {
    
    private List<DecAtomValGroup> m_gvs;  // list of all the global values, some
                                   // of which may not have implementation.
    
    private List<DecFunGroup> m_decs;  // Declarations of all the functions, some
                                       // of which may not have implementation.

    private List<IFunDef> m_defs;  // function definition and implementation
    
    private List<D3Cextcode> m_exts;
    
    private List<IStfplInstruction> m_main_inss;  // global instructions
    
    private SIdFactory m_sid_factory;
    
    private Map<SId, IFunDef> m_fun_map;
    
    private ProgramStfpl3 m_prog;  // program to be converted
    
    public InstructionTransformer(SIdFactory sid_factory, ProgramStfpl3 prog) {
        m_decs = new ArrayList<DecFunGroup>();
        m_gvs = new ArrayList<DecAtomValGroup>();
        m_exts = new ArrayList<D3Cextcode>();
        m_defs = new ArrayList<IFunDef>();
        m_main_inss = new ArrayList<IStfplInstruction>();
        
        m_sid_factory = sid_factory;
        
        m_fun_map = new HashMap<SId, IFunDef>();
        
        
        m_prog = prog;  // program to be converted
        
    }
    
    public Map<SId, IFunDef> getFunMap() {
        return m_fun_map;
    }
    
    public List<DecFunGroup> getDecs() {
    	return m_decs;
    }
    
    public List<D3Cextcode> getExts() {
    	return m_exts;
    }
    
    public List<IFunDef> getDefs() {
    	return m_defs;
    }
    
    public List<IStfplInstruction> getMainInss() {
    	return m_main_inss;
    }
    
    
    public ProgramIns transform() {
        transform(m_prog.m_d3ecs, new HashSet<Cd3var>(), m_main_inss, true);
        processClosureEnvType();  // create types for env
        
        return new ProgramIns(m_gvs, m_decs, m_defs, m_exts, m_main_inss);
    }
    
    private void processClosureEnvType() {
    	for (IFunDef fun: m_defs) {
    		if (fun instanceof DefFunGroup) {
    			DefFunGroup fun_grp = (DefFunGroup)fun;
    			if (fun_grp.isClosure()) {
    				processClosureEnvTypeImpl(fun_grp);
    			}
    		}
    	}
    }
    
    /*
     * Update the type for the environment of closure.
     * Turn function type into struct type if necessary.
     */
    private RecType processClosureEnvTypeImpl(DefFunGroup fun_grp) {
    	RecType env_type = fun_grp.getEnvType();
    	if (null != env_type) {
    		return env_type;
    	}
        List<ILabPat> labpats = new ArrayList<ILabPat>();
        for (SIdUser env_ele: fun_grp.m_env) {
        	SId ele = env_ele.getSId();
        	Csymbol nsym = new Csymbol(ele.toStringWithStamp());
        	LABsym labsym = new LABsym(nsym);
        	
        	ISType ele_type = ele.getType();
        	if (AuxSType.isClosure(ele_type)) {  // is closure
        		IFunDef ifun = m_fun_map.get(ele);
        		if (null != ifun) {  // is function name
            		DefFunGroup clo_grp = (DefFunGroup)ifun;
            		ele_type = processClosureEnvTypeImpl(clo_grp);
            		
            		if (false == ele.isUserFun()) {
            			throw new Error("This should not happen.");
            		}
        		}
        	}
            
            LabPatNorm labpat = new LabPatNorm(labsym, ele_type);
            labpats.add(labpat);
        }
        
        env_type = new RecType(labpats, -1/*no proof*/, 1/*boxed*/);
        fun_grp.updateEnvType(env_type);
    	return env_type;
    }
    
    /*
     * is_top is for marking all global variable / value
     */
    
    private void transform(List<Cd3ecl> d3ecs, Set<Cd3var> env,  // names from outside
            List<IStfplInstruction> inss, boolean is_top) {
        for (Cd3ecl d3ec: d3ecs) {
            transform(d3ec, env, inss, is_top);
        }
    }

    private void transform(Cd3ecl d3ec, Set<Cd3var> env, 
            List<IStfplInstruction> inss, boolean is_top) {
        Id3ecl_node node0 = d3ec.m_node;
        if (node0 instanceof D3Cdcstdecs) {
            transform((D3Cdcstdecs)node0, env, inss, is_top);
        } else if (node0 instanceof D3Cextcode) {
            m_exts.add((D3Cextcode)node0);
        } else if (node0 instanceof D3Cfundecs) {
            transform(d3ec.m_loc, (D3Cfundecs)node0, env, inss, is_top);
        } else if (node0 instanceof D3Cimpdec) {
            transform((D3Cimpdec)node0, d3ec.m_loc, env, inss, is_top);
        } else if (node0 instanceof D3Cstacsts) {
            Log.log4j.warn("D3Cstacsts encountered in generating instruction.");
        } else if (node0 instanceof D3Cvaldecs) {
            transform((D3Cvaldecs)node0, d3ec.m_loc, env, inss, is_top);
        } else if (node0 instanceof D3Cdatdecs) {
            throw new Error("D3Cdatdecs not supported yet");
        } else {
            throw new Error(node0 + " is not supported.");
        }
    }

    /*
     * Assume there is no mutually recursively defined values.
     * E.g.
     *     val x = y + 1
     *     and y = x + 1
     *     
     *     val fx = lam x => fy x
     *     and fy = lam y = fx y
     */
    private void transform(D3Cvaldecs node0, Cloc_t m_loc, Set<Cd3var> env,
            List<IStfplInstruction> inss, boolean is_top) {
        List<SId> sids = new ArrayList<SId>();
        for (Cv3aldec valdec: node0.m_v3ds) {
            transfrom(node0.m_knd, valdec, env, inss, is_top, sids);
        }
        if (is_top) {
            if (sids.isEmpty() == false) {
                Edeckind knd = Edeckind.fromEvalkind(node0.m_knd);
                DecAtomValGroup grp = new DecAtomValGroup(knd, sids);
                m_gvs.add(grp);
            }
        }
        
    }

    private void transfrom(Evalkind knd, Cv3aldec valdec, Set<Cd3var> env,
            List<IStfplInstruction> inss, boolean is_top, List<SId> sids) {
        
        Ip3at_node node0 = valdec.m_pat.m_node;
        if (node0 instanceof P3Tany
            || node0 instanceof P3Tempty
            || node0 instanceof P3Tvar) {
            SId holder = null;
            if (node0 instanceof P3Tvar) {
                if (is_top) {
                    holder = m_sid_factory.mapFromCd3var(((P3Tvar)node0).m_var, SIdCategory.eGloValue);
                } else {
                    holder = m_sid_factory.mapFromCd3var(((P3Tvar)node0).m_var, SIdCategory.eLocalVar);
                }
                sids.add(holder);
            }
//            System.out.println("P3Tvar is " + ((P3Tvar)node0).m_var.toString() + " holder is " + holder);
            transform(valdec.m_def, env, inss, holder, false);
            return;
        } else {  // P3Tcon, P3Trec
            SId holder = null;
            IValPrim vp = transform(valdec.m_def, env, inss, holder, false);
            transform(valdec.m_pat, vp, inss, is_top, sids);
            return;
        }
    }

    private void transform(Cp3at pat, IValPrim vp,
            List<IStfplInstruction> inss, boolean is_top, List<SId> sids) {
        Ip3at_node node0 = pat.m_node;
        if (node0 instanceof P3Tany) {
            throw new Error("Should not happen.");
        } else if (node0 instanceof P3Tvar) {
            throw new Error("Should not happen.");
        } else if (node0 instanceof P3Tempty) {
            throw new Error("Should not happen.");
        } else if (node0 instanceof P3Trec) {
            transform((P3Trec)node0, vp, inss, is_top, sids);
        } else if (node0 instanceof P3Tcon) {
            throw new Error("P3Tcon is not supported yet.");
        } else {
            throw new Error(node0 + " is not supported");
        }
        
    }

    private void transform(P3Trec node, IValPrim vp,
            List<IStfplInstruction> inss, boolean is_top, List<SId> sids) {
    	int index = 0;
        for (LABP3ATnorm lab_pat : node.m_labpats) {
            Ip3at_node node0 = lab_pat.m_pat.m_node;
            if (node0 instanceof P3Tany 
                || node0 instanceof P3Tempty
                || node0 instanceof P3Tvar) {
                SId holder = null;
                if (node0 instanceof P3Tvar) {
                    if (is_top) {
                        holder = m_sid_factory.mapFromCd3var(((P3Tvar) node0).m_var,
                                SIdCategory.eGloValue);
                    } else {
                        holder = m_sid_factory.mapFromCd3var(((P3Tvar) node0).m_var,
                                SIdCategory.eLocalVar);
                    }
                    
                    sids.add(holder);  // new name for value
                    
                    InsPatLabDecompose ins = 
                    		new InsPatLabDecompose(lab_pat.m_lab, index, holder, vp);
                    inss.add(ins);
                }

            } else { // P3Tcon P3Trec
                SId holder = m_sid_factory.createLocalVar("pat",
                        lab_pat.m_pat.m_node.getType());
                InsPatLabDecompose ins = 
                		new InsPatLabDecompose(lab_pat.m_lab, index, holder, vp);
                inss.add(ins);
                transform(lab_pat.m_pat, holder, inss, is_top, sids);
            }
            ++index;
        }
    }
    
    // generate SId for parameter
    private List<SId> collectPatVarFunPara(List<Cp3at> pats) {
        List<SId> paras = new ArrayList<SId>();
        for (Cp3at pat: pats) {
            Ip3at_node node0 = (Ip3at_node)pat.m_node;
            if (node0 instanceof P3Tany) {
                throw new Error("Should not happen.");
            } else if (node0 instanceof P3Tvar) {
                SId para = m_sid_factory.mapFromCd3var(((P3Tvar) node0).m_var, SIdCategory.ePara);
                paras.add(para);
            } else if (node0 instanceof P3Tempty) {
                throw new Error("Should not happen.");
            } else if (node0 instanceof P3Trec) {
                throw new Error("Should not happen.");
            } else if (node0 instanceof P3Tcon) {
                throw new Error("Should not happen.");
            } else {
                throw new Error(node0 + " is not supported");
            }
        }
        return paras;
    }

    private ImplFun transform(D3Cimpdec node0, Cloc_t loc, Set<Cd3var> env,
            List<IStfplInstruction> inss, boolean is_top) {
        Cd3exp fun_exp = node0.m_i3mpdec.i3mpdec_def;
        D3ElamDyn lambda = (D3ElamDyn)(fun_exp.m_node);
        
        SId name = m_sid_factory.mapFromCd3cst(node0.m_i3mpdec.i3mpdec_cst, SIdCategory.eUserFun);
        int lin = lambda.m_lin;
        
        List<Cp3at> p3ts = lambda.m_p3ts;
        List<SId> paras = collectPatVarFunPara(p3ts);
        
        Set<Cd3var> env2 = lambda.m_env;
        
        // transform the body of the function
        List<IStfplInstruction> inss2 = new ArrayList<IStfplInstruction>();
        transform(lambda.m_d3exp, env2, inss2, m_sid_factory.createRetHolder("ret", AuxSType.getRetType(lambda.getType())), false);
  
//        SId env_name = null;
//        Set<SIdUser> form_env = null;
        
        // create the closure if necessary
        ISType clo_type = lambda.getType();
        if (AuxSType.isClosure(clo_type)) {  // closure
            if (inss != m_main_inss) {
                throw new Error("Implementing closure is only allowed on the first level.");
            }
            
//            form_env = new HashSet<SIdUser>();
            
            for (Cd3var d3var: env2) {
                SId sid = m_sid_factory.getSIdFromCd3var(d3var);
                if (!sid.isGlobalValue()) {  // ** global var will not be put into env.
                    throw new Error("implementation for closure is now allowed");
//                    if (env.contains(d3var)) {
//                        SIdUser env_member = m_sid_factory.createSIdUserByCd3var(d3var, true);
//                        form_env.add(env_member);
//                    } else {
//                        SIdUser env_member = m_sid_factory.createSIdUserByCd3var(d3var, false);
//                        form_env.add(env_member);
//                    }
                }
            }
            
            List<ILabPat> labpats = new ArrayList<ILabPat>();
            for (Cd3var env_ele: env2) {
                Csymbol nsym = new Csymbol(env_ele.toString());
                LABsym labsym = new LABsym(nsym);
                LabPatNorm labpat = new LabPatNorm(labsym, env_ele.m_stype);
                labpats.add(labpat);
            }
//            RecType env_type = new RecType(labpats, -1, 1);
//            env_name = m_sid_factory.createEnvForclosure("env", env_type);
//            InsFormEnv ins_env = new InsFormEnv(env_name, form_env);
//            inss.add(ins_env);
            
//            SIdUser env_name_user = new SIdUser(env_name, false);
//            InsClosure ins_clo = new InsClosure(name, env_name_user);
//            inss.add(ins_clo);
        }
        
        ImplFun ret = new ImplFun(loc, name, lin, paras, inss2/*, env_name, form_env*/);
        
        m_fun_map.put(name, ret);  // map name to definition
        
        m_defs.add(ret);
        return ret;
    }

    // extern xxx
    private void transform(D3Cdcstdecs node0, Set<Cd3var> env,  
            List<IStfplInstruction> inss, boolean is_top) {
        List<SId> names = new ArrayList<SId>();
        
        boolean is_fun = false;
        
        for (Cd3cst cst: node0.m_d3cst) {
            SIdCategory cat = null;
        	if (Edcstkind.DCK_val == node0.m_dck) {
        	    if (!is_top) {
        	        throw new Error("extern val not in global space");
        	    }
        		cat = SIdCategory.eGloValue;
        	} else {
        	    is_fun = true;
        		cat = SIdCategory.eUserFun;
        	}
        	
        	SId sid = m_sid_factory.mapFromCd3cst(cst, cat);
        	names.add(sid);
        }
        
        Edeckind knd = Edeckind.fromEcstkind(node0.m_dck);
        if (is_fun) {
            m_decs.add(new DecFunGroup(knd, names));
        } else {
            if (is_top == false) {
                throw new Error("This cannot happen.");
            }
            m_gvs.add(new DecAtomValGroup(knd, names));
        }
    }

    // fun foo () ...
    private void transform(Cloc_t loc, D3Cfundecs node0, Set<Cd3var> env, // env of the outer function
            List<IStfplInstruction> inss, boolean is_top) {

    	List<SIdUser> form_env = null;
        SId env_name = null;
        InsFormEnv ins_env = null;
        
        // form environment if necessary
        if (node0.m_is_clo) {
            form_env = new ArrayList<SIdUser>();
            
            for (Cd3var d3var: node0.m_env /*env of the current function*/) {
            	SId sid = m_sid_factory.getSIdFromCd3var(d3var);
            	if (!sid.isGlobalValue()) {  // ** global var will not be put into env.
                    if (env.contains(d3var)) {
                        SIdUser env_member = m_sid_factory.createSIdUserByCd3var(d3var, true);
                        form_env.add(env_member);
                    } else {
                        SIdUser env_member = m_sid_factory.createSIdUserByCd3var(d3var, false);
                        form_env.add(env_member);
                    }
            	}
            }
            
            List<ILabPat> labpats = new ArrayList<ILabPat>();
            for (Cd3var env_ele: node0.m_env) {
                Csymbol nsym = new Csymbol(env_ele.toString());
                LABsym labsym = new LABsym(nsym);
                LabPatNorm labpat = new LabPatNorm(labsym, env_ele.m_stype);
                labpats.add(labpat);
            }
            RecType env_type = new RecType(labpats, -1/*no proof*/, 1/*boxed*/);
            env_name = m_sid_factory.createEnvForclosure("env", env_type);
            
            ins_env = new InsFormEnv(env_name, form_env);
            inss.add(ins_env);
            
        }
        
        List<SId> names = new ArrayList<SId>();
        List<DefFun> funs = new ArrayList<DefFun>();
        

        for (Cf3undec f3undec: node0.m_f3ds) {
            // populate the map
            SId name = m_sid_factory.mapFromCd3var(f3undec.m_var, SIdCategory.eUserFun);
            names.add(name);
        }
        
        for (Cf3undec f3undec: node0.m_f3ds) {
            
            DefFun fundef = transform(f3undec,
                    node0.m_env/*the is the env for the function*/, 
                    env_name,
                    inss, is_top);
            funs.add(fundef);
        }
        
        Edeckind knd = Edeckind.fromEfunkind(node0.m_knd);
        DecFunGroup protos = new DecFunGroup(knd, names);
        m_decs.add(protos);
        
        DefFunGroup fungrp = new DefFunGroup(node0.m_knd, funs, env_name, form_env);
        
        for (SId name: names) {
        	m_fun_map.put(name, fungrp);   // map name to definition
        }
        m_defs.add(fungrp);
        
        // Update the group information for the use of next stage.
        if (null != ins_env) {
        	ins_env.setFunGroup(fungrp);

        }
    }

    /*
     * create function definition and, closure if necessary
     * We are inside the function body.
     * 
     * env: the environment for the function
     * env_name: current env_name if not null
     */
    private DefFun transform(Cf3undec f3undec, Set<Cd3var> env, SId env_name,
            List<IStfplInstruction> inss, boolean is_top) {
        
        // SId has already been created when transforming D3Cfundecs.
        SId name = m_sid_factory.getSIdFromCd3var(f3undec.m_var);
        
        List<Cp3at> p3ts = f3undec.m_p3ts;
        List<SId> paras = collectPatVarFunPara(p3ts);
        
        Cd3exp body = f3undec.m_body;
        
        Cloc_t loc = f3undec.m_loc;
        
        int lin = f3undec.m_lin; 
        

        // transform the body of the function
        List<IStfplInstruction> inss2 = new ArrayList<IStfplInstruction>();
        transform(body, env, inss2, m_sid_factory.createRetHolder("ret", AuxSType.getRetType(f3undec.m_type)), false);
        DefFun def_fun = new DefFun(loc, name, lin, paras, inss2);

        // create the closure if necessary
        ISType clo_type = f3undec.m_type;
        if (AuxSType.isClosure(clo_type)) {  // closure
            SIdUser env_name_user = new SIdUser(env_name, false);
            InsClosure ins_clo = new InsClosure(name, env_name_user);
            inss.add(ins_clo);
        }

        return def_fun;
    }

    private IValPrim transform(Cd3exp d3exp, Set<Cd3var> env, 
            List<IStfplInstruction> inss, SId holder, boolean is_top) {
       Cloc_t loc = d3exp.m_loc;
       Id3exp_node node0 = d3exp.m_node;
       
       if (node0 instanceof D3Eapplst) {
           return transform((D3Eapplst)node0, env, inss, holder, is_top);
       } else if (node0 instanceof D3Ecst) {
           return transform((D3Ecst)node0, env, inss, holder, is_top);
       } else if (node0 instanceof D3Eempty) {
           return transform((D3Eempty)node0, env, inss, holder, is_top);
       } else if (node0 instanceof D3Ef0loat) {
           return transform((D3Ef0loat)node0, env, inss, holder, loc, is_top);
       } else if (node0 instanceof D3Ei0nt) {
           return transform((D3Ei0nt)node0, env, inss, holder, loc, is_top);
       } else if (node0 instanceof D3Eifopt) {
           return transform((D3Eifopt)node0, env, inss, holder, is_top);
       } else if (node0 instanceof D3ElamDyn) {
           return transform((D3ElamDyn)node0, env, inss, holder, loc, is_top);
       } else if (node0 instanceof D3Elet) {
           return transform((D3Elet)node0, env, inss, holder, loc, is_top);
       } else if (node0 instanceof D3Es0tring) {
           return transform((D3Es0tring)node0, env, inss, holder, loc, is_top);
       } else if (node0 instanceof D3Esym) {
           return transform((D3Esym)node0, env, inss, holder, is_top);
       } else if (node0 instanceof D3Etup) {
           return transform((D3Etup)node0, env, inss, holder, is_top);           
       } else if (node0 instanceof D3Evar) {
           return transform((D3Evar)node0, env, inss, holder, is_top);
       } else {
           throw new Error(node0 + " is not supported.");
       }
    }

    private IValPrim transform(D3Ef0loat node0, Set<Cd3var> env,
            List<IStfplInstruction> inss, SId holder, Cloc_t loc, boolean is_top) {
        AtomValue v = new AtomValue(node0.m_ty, node0.m_f0loat);
        
        if (null != holder && holder.isRetHolder()) {
            InsMove aIns = new InsMove(v, holder);
            inss.add(aIns);
            SIdUser ret = new SIdUser(holder, false);
            return ret;
        } else {
            return v;
        }
    }
    
    private IValPrim transform(D3Ei0nt node0, Set<Cd3var> env,
            List<IStfplInstruction> inss, SId holder, Cloc_t loc, boolean is_top) {
        AtomValue v = new AtomValue(node0.m_ty, node0.m_i0nt);
        
        // holder may be a return holder
        if (null != holder) {
            InsMove aIns = new InsMove(v, holder);
            inss.add(aIns);
            SIdUser ret = new SIdUser(holder, false);
            return ret;
        } else {
            return v;
        }
    }
    
    private IValPrim transform(D3Es0tring node0, Set<Cd3var> env,
            List<IStfplInstruction> inss, SId holder, Cloc_t loc, boolean is_top) {
        AtomValue v = new AtomValue(node0.m_ty, node0.m_s0tring);
        
        if (null != holder && holder.isRetHolder()) {
            InsMove aIns = new InsMove(v, holder);
            inss.add(aIns);
            SIdUser ret = new SIdUser(holder, false);
            return ret;
        } else {
            return v;
        }
    }

    private SIdUser transform(D3Etup node0, Set<Cd3var> env,
            List<IStfplInstruction> inss, SId holder, boolean is_top) {
        List<IValPrim> elements = new ArrayList<IValPrim>();
        for (Cd3exp d3exp: node0.m_d3es) {
            IValPrim element = transform(d3exp, env, inss, null, is_top);
            elements.add(element);
        }
        
        if (null == holder) {
            holder = m_sid_factory.createLocalVar("tup", node0.getType());
        }
        
        InsTuple ins = new InsTuple(elements, holder);
        inss.add(ins);
        
        SIdUser ret = new SIdUser(holder, false);
        
        return ret;
    }

    private IValPrim transform(D3Elet node0, Set<Cd3var> env,
            List<IStfplInstruction> inss, SId holder, Cloc_t loc, boolean is_top) {
        transform(node0.m_d3cs, env, inss, false);
        return transform(node0.m_body, env, inss, holder, false);     
    }

    // lam (x: int) => x
    private SIdUser transform(D3ElamDyn node0, Set<Cd3var> env,
            List<IStfplInstruction> inss, SId holder, Cloc_t loc, boolean is_top) {
        D3ElamDyn lambda = node0;

        int lin = lambda.m_lin; 
        
        List<Cp3at> p3ts = lambda.m_p3ts;
        List<SId> paras = collectPatVarFunPara(p3ts);

        Set<Cd3var> env2 = lambda.m_env;

        // unnamed lambda
        SId name = m_sid_factory.createLambdaFunction("lam", lambda.getType());
        
        SId env_name = null;
        List<SIdUser> form_env = null;
        
        // create the closure if necessary
        ISType clo_type = lambda.getType();
        if (AuxSType.isClosure(clo_type)) {  // closure
            form_env = new ArrayList<SIdUser>();
            
            for (Cd3var d3var: env2) {
                SId sid = m_sid_factory.getSIdFromCd3var(d3var);
                if (!sid.isGlobalValue()) {  // ** global var will not be put into env.
                    if (env.contains(d3var)) {
                        SIdUser env_member = m_sid_factory.createSIdUserByCd3var(d3var, true);
                        form_env.add(env_member);
                    } else {
                        SIdUser env_member = m_sid_factory.createSIdUserByCd3var(d3var, false);
                        form_env.add(env_member);
                    }
                }
            }
            
            List<ILabPat> labpats = new ArrayList<ILabPat>();
            for (Cd3var env_ele: env2) {
                Csymbol nsym = new Csymbol(env_ele.toString());
                LABsym labsym = new LABsym(nsym);
                LabPatNorm labpat = new LabPatNorm(labsym, env_ele.m_stype);
                labpats.add(labpat);
            }
            RecType env_type = new RecType(labpats, -1, 1);
            env_name = m_sid_factory.createEnvForclosure("env", env_type);
            
            InsFormEnv ins_env = new InsFormEnv(env_name, form_env);
            inss.add(ins_env);
            
            SIdUser env_name_user = new SIdUser(env_name, false);
            InsClosure ins_clo = new InsClosure(name, env_name_user);
            inss.add(ins_clo);
        }

        // transform the body of the function
        List<IStfplInstruction> inss2 = new ArrayList<IStfplInstruction>();
        transform(lambda.m_d3exp
        		, node0.m_env
        		, inss2
        		, m_sid_factory.createRetHolder("ret", AuxSType.getRetType(lambda.getType()))
        		, false);
        DefFun def_fun = new DefFun(loc, name, lin, paras, inss2);
        List<DefFun> fun_lst = new ArrayList<DefFun>();
        fun_lst.add(def_fun);
        
        // add to global definition
        Efunkind fun_knd = Efunkind.FK_fn;  // Unnamed lambda is non-recursive.
        DefFunGroup fun_group = new DefFunGroup(fun_knd, fun_lst, env_name, form_env);
        m_fun_map.put(name, fun_group);  // map name to definition
        m_defs.add(fun_group);

        // add to global declaration
        List<SId> name_lst = new ArrayList<SId>();
        name_lst.add(name);
        Edeckind dec_knd = Edeckind.fromEfunkind(fun_knd);
        DecFunGroup protos = new DecFunGroup(dec_knd, name_lst);
        m_decs.add(protos);

        // add instruction for move
        SIdUser ret = new SIdUser(name, false);
        
        if (null == holder) {
            return ret;
        } else {
            InsMove ins = new InsMove(ret, holder);
            inss.add(ins);
            
            ret = new SIdUser(holder, false);
            return ret;
        }
    }

    private SIdUser transform(D3Eifopt node0, Set<Cd3var> env,
            List<IStfplInstruction> inss, SId holder, boolean is_top) {
        if (null == holder) {
            holder = m_sid_factory.createLocalVar("cond", node0.m_test.m_node.getType());
        }
        
        IValPrim vp_cond = transform(node0.m_test, env, inss, null, false);
        
        List<IStfplInstruction> btrue = new ArrayList<IStfplInstruction>();
        List<IStfplInstruction> bfalse = new ArrayList<IStfplInstruction>();
        
        transform(node0.m_then, env, btrue, holder, false);
        if (null != node0.m_else) {
            transform(node0.m_else, env, bfalse, holder, false);
        }
        
        InsCond ins = new InsCond(holder, vp_cond, btrue, bfalse);
        inss.add(ins);
        
        SIdUser ret = new SIdUser(holder, false);
        return ret;   
    }

    private IValPrim transform(D3Eempty node0, Set<Cd3var> env,
            List<IStfplInstruction> inss, SId holder, boolean is_top) {
        
        IValPrim v = AtomValue.sEmpty;
        if (null != holder) {
            InsMove aIns = new InsMove(v, holder);
            inss.add(aIns);
            SIdUser ret = new SIdUser(holder, false);
            return ret;
        } else {
            return v;
        }
    }

    private SIdUser transform(D3Eapplst node0, Set<Cd3var> env,
            List<IStfplInstruction> inss, SId holder, boolean is_top) {
        SIdUser vpFun = (SIdUser)transform(node0.m_fun, env, inss, null, false);

        ListIterator<D3EXPARGdyn> iter_args = node0.m_args.listIterator();
        D3EXPARGdyn args = iter_args.next();
        
        ListIterator<ISType> iter_types = node0.m_inner_stype.listIterator();
        
        while (true) {
            List<IValPrim> vpArgs = new ArrayList<IValPrim>();
            for (Cd3exp arg: args.m_d3expLst) {
                IValPrim vpArg = transform(arg, env, inss, null, false);
                vpArgs.add(vpArg);
            }

            if (iter_args.hasNext()) {
                SId id = m_sid_factory.createLocalVar("temp", iter_types.next());
                formInsCall(id, vpFun, vpArgs, inss);

                vpFun = new SIdUser(id, false);
                args = iter_args.next();
                
            } else {
                if (null == holder) {
                    SId id = m_sid_factory.createLocalVar("temp", iter_types.next());
                    formInsCall(id, vpFun, vpArgs, inss);
                    
                    SIdUser ret = new SIdUser(id, false);
                    return ret;
                } else {
                    formInsCall(holder, vpFun, vpArgs, inss);
                    SIdUser ret = new SIdUser(holder, false);
                    return ret;
                }
                
            }  
        }
    }
    
    private void formInsCall(SId holder, SIdUser fun, 
            List<IValPrim> args,
            List<IStfplInstruction> inss) {
        InsCall aIns = null;
        
        if (AuxSType.isClosure(fun.getType())) {
            // Call a closure
            aIns = new InsCall(holder, fun, args, ECallType.eCloObj);
        } else {
            // Call a function
            aIns = new InsCall(holder, fun, args, ECallType.eFun);
        }
        inss.add(aIns);
    }

    private SIdUser transform(D3Ecst node, Set<Cd3var> env,
            List<IStfplInstruction> inss, SId holder, boolean is_top) {
        SId v = m_sid_factory.mapFromCd3cst(node.m_d3cst, SIdCategory.eConstant);
        SIdUser vu = new SIdUser(v, false);
        if (null != holder) {
            InsMove aIns = new InsMove(vu, holder);
            inss.add(aIns);
            
            SIdUser ret = new SIdUser(holder, false);
            return ret;
        } else {
            return vu;
        }
    }

    private SIdUser transform(D3Esym node, Set<Cd3var> env,
            List<IStfplInstruction> inss, SId holder, boolean is_top) {
        SId v = m_sid_factory.createFromCd3sym(node.m_d3sym, SIdCategory.eConstant);
        if (null != holder) {
            throw new Error("Check this.");
        } else {
            SIdUser ret = new SIdUser(v, false);
            return ret;
        }
    }

    /*
     * Usage of variable
     * IValPrim can only be of SIdUser or SId.
     */
    private SIdUser transform(D3Evar node, Set<Cd3var> env,
            List<IStfplInstruction> inss, SId holder, boolean is_top) {
        SIdUser sid_user = null;
        if (env.contains(node.m_d3var)) {
            sid_user = m_sid_factory.createSIdUserByCd3var(node.m_d3var, true);
        } else {
            sid_user = m_sid_factory.createSIdUserByCd3var(node.m_d3var, false);
        }

        if (null != holder) {
            InsMove aIns = new InsMove(sid_user, holder);
            inss.add(aIns);
            SIdUser ret = new SIdUser(holder, false);
            return ret;
        } else {
            return sid_user;
        }
    }
    



}






