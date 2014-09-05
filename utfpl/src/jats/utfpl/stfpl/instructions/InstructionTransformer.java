package jats.utfpl.stfpl.instructions;

import jats.utfpl.stfpl.Cloc_t;
import jats.utfpl.stfpl.Csymbol;
import jats.utfpl.stfpl.LABsym;

import jats.utfpl.stfpl.dynexp.Edcstkind;
import jats.utfpl.stfpl.dynexp.Efunkind;
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
import jats.utfpl.stfpl.instructions.InsCall.ECallType;
import jats.utfpl.stfpl.instructions.SId.Category;
import jats.utfpl.stfpl.staexp.FUNCLOfun; 
import jats.utfpl.stfpl.stype.Aux;
import jats.utfpl.stfpl.stype.FunType;
import jats.utfpl.stfpl.stype.ILabPat;
import jats.utfpl.stfpl.stype.ISType;
import jats.utfpl.stfpl.stype.LabPatNorm;
import jats.utfpl.stfpl.stype.RecType;
import jats.utfpl.utils.Log;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;

/*
 * Handle closure.
 */
public class InstructionTransformer {
    
    private List<DecGroup> m_decs;  // global declaration
    
    private List<D3Cextcode> m_exts;
    
    private List<IFunDef> m_defs;
    
    private List<IStfplInstruction> m_main_inss;  // global instructions
    
    public InstructionTransformer() {
        m_decs = new ArrayList<DecGroup>();
        m_exts = new ArrayList<D3Cextcode>();
        m_defs = new ArrayList<IFunDef>();
        m_main_inss = new ArrayList<IStfplInstruction>();
        
    }
    
    public List<DecGroup> getDecs() {
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
    
    
    public void transform_global(List<Cd3ecl> d3ecs) {
        transform(d3ecs, new HashSet<Cd3var>(), m_main_inss);
    }
    
    private void transform(List<Cd3ecl> d3ecs, Set<Cd3var> env,  // names from outside
            List<IStfplInstruction> inss) {
        for (Cd3ecl d3ec: d3ecs) {
            transform(d3ec, env, inss);
        }
    }

    private void transform(Cd3ecl d3ec, Set<Cd3var> env, 
            List<IStfplInstruction> inss) {
        Id3ecl_node node0 = d3ec.m_node;
        if (node0 instanceof D3Cdcstdecs) {
            transform((D3Cdcstdecs)node0, env, inss);
        } else if (node0 instanceof D3Cextcode) {
            m_exts.add((D3Cextcode)node0);
        } else if (node0 instanceof D3Cfundecs) {
            transform(d3ec.m_loc, (D3Cfundecs)node0, env, inss);
        } else if (node0 instanceof D3Cimpdec) {
            transform((D3Cimpdec)node0, d3ec.m_loc, env, inss);
        } else if (node0 instanceof D3Cstacsts) {
            Log.log4j.warn("D3Cstacsts encountered in generating instruction.");
        } else if (node0 instanceof D3Cvaldecs) {
            transform((D3Cvaldecs)node0, d3ec.m_loc, env, inss);
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
            List<IStfplInstruction> inss) {
        for (Cv3aldec valdec: node0.m_v3ds) {
            transfrom(valdec, env, inss);
        }
        
    }

    private void transfrom(Cv3aldec valdec, Set<Cd3var> env,
            List<IStfplInstruction> inss) {
        
        Ip3at_node node0 = valdec.m_pat.m_node;
        if (node0 instanceof P3Tany
            || node0 instanceof P3Tempty
            || node0 instanceof P3Tvar) {
            SId holder = null;
            if (node0 instanceof P3Tvar) {
                holder = SId.fromCd3var(((P3Tvar)node0).m_var, Category.eLocalVar);
            }
//            System.out.println("P3Tvar is " + ((P3Tvar)node0).m_var.toString() + " holder is " + holder);
            transform(valdec.m_def, env, inss, holder);
            return;
        } else {  // P3Tcon, P3Trec
            SId holder = null;
            IValPrim vp = transform(valdec.m_def, env, inss, holder);
            transform(valdec.m_pat, vp, inss);
            return;
        }
    }

    private void transform(Cp3at pat, IValPrim vp,
            List<IStfplInstruction> inss) {
        Ip3at_node node0 = pat.m_node;
        if (node0 instanceof P3Tany) {
            throw new Error("Should not happen.");
        } else if (node0 instanceof P3Tvar) {
            throw new Error("Should not happen.");
        } else if (node0 instanceof P3Tempty) {
            throw new Error("Should not happen.");
        } else if (node0 instanceof P3Trec) {
            transform((P3Trec)node0, vp, inss);
        } else if (node0 instanceof P3Tcon) {
            throw new Error("P3Tcon is not supported yet.");
        } else {
            throw new Error(node0 + " is not supported");
        }
        
    }

    private void transform(P3Trec node, IValPrim vp,
            List<IStfplInstruction> inss) {
        for (LABP3ATnorm lab_pat : node.m_labpats) {
            Ip3at_node node0 = lab_pat.m_pat.m_node;
            if (node0 instanceof P3Tany 
                || node0 instanceof P3Tempty
                || node0 instanceof P3Tvar) {
                SId holder = null;
                if (node0 instanceof P3Tvar) {
                    holder = SId.fromCd3var(((P3Tvar) node0).m_var,
                            Category.eLocalVar);
                    InsPatLabDecompose ins = new InsPatLabDecompose(lab_pat.m_lab,
                            holder, vp);
                    inss.add(ins);
                    return;
                }

            } else {
                SId holder = SId.createLocalVar("pat",
                        lab_pat.m_pat.m_node.getType());
                InsPatLabDecompose ins = new InsPatLabDecompose(lab_pat.m_lab,
                        holder, vp);
                inss.add(ins);
                transform(lab_pat.m_pat, holder, inss);
                return;
            }
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
                SId para = SId.fromCd3var(((P3Tvar) node0).m_var, Category.ePara);
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
            List<IStfplInstruction> inss) {
        Cd3exp fun_exp = node0.m_i3mpdec.i3mpdec_def;
        D3ElamDyn lambda = (D3ElamDyn)(fun_exp.m_node);
        
        SId name = SId.fromCd3cst(node0.m_i3mpdec.i3mpdec_cst, Category.eUserFun);
        int lin = lambda.m_lin;
        
        List<Cp3at> p3ts = lambda.m_p3ts;
        List<SId> paras = collectPatVarFunPara(p3ts);
        
        Set<Cd3var> env2 = lambda.m_env;
        
        // transform the body of the function
        List<IStfplInstruction> inss2 = new ArrayList<IStfplInstruction>();
        transform(lambda.m_d3exp, env2, inss2, SId.createRetHolder("ret", Aux.getRetType(lambda.getType())));
  
        SId env_name = null;
        Set<SIdUser> form_env = null;
        
        // create the closure if necessary
        ISType clo_type = lambda.getType();
        if (Aux.getClosureInfo(clo_type) != FUNCLOfun.cInstance) {  // closure
            if (inss != m_main_inss) {
                throw new Error("Implementing closure is only allowed on the first level.");
            }
            
            form_env = new HashSet<SIdUser>();
            
            for (Cd3var d3var: env2) {
                if (env.contains(d3var)) {
                    SIdUser env_member = SId.createSIdUserByCd3var(d3var, true);
                    form_env.add(env_member);
                } else {
                    SIdUser env_member = SId.createSIdUserByCd3var(d3var, false);
                    form_env.add(env_member);
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
            env_name = SId.createEnvId("env", env_type);
            InsFormEnv ins_env = new InsFormEnv(env_name, form_env);
            inss.add(ins_env);
            
            SIdUser env_name_user = new SIdUser(env_name, false);
            InsClosure ins_clo = new InsClosure(name, env_name_user);
            inss.add(ins_clo);
        }
        
        ImplFun ret = new ImplFun(loc, name, lin, paras, inss2, env_name, form_env);
        
        m_defs.add(ret);
        return ret;
    }

    private void transform(D3Cdcstdecs node0, Set<Cd3var> env,  
            List<IStfplInstruction> inss) {
        List<SId> names = new ArrayList<SId>();
        for (Cd3cst cst: node0.m_d3cst) {
        	SId.Category cat = null;
        	if (Edcstkind.DCK_val == node0.m_dck) {
        		cat = SId.Category.eGloValue;
        	} else {
        		cat = SId.Category.eUserFun;
        	}
            names.add(SId.fromCd3cst(cst, cat));
        }
        
        Edeckind knd = Edeckind.fromEcstkind(node0.m_dck);
        m_decs.add(new DecGroup(knd, names));
    }

    private void transform(Cloc_t loc, D3Cfundecs node0, Set<Cd3var> env, // names from outside
            List<IStfplInstruction> inss) {

        Set<SIdUser> form_env = null;
        SId env_name = null;
        
        // form environment if necessary
        if (node0.m_is_clo) {
            form_env = new HashSet<SIdUser>();
            
            for (Cd3var d3var: node0.m_env) {
                if (env.contains(d3var)) {
                    SIdUser env_member = SId.createSIdUserByCd3var(d3var, true);
                    form_env.add(env_member);
                } else {
                    SIdUser env_member = SId.createSIdUserByCd3var(d3var, false);
                    form_env.add(env_member);
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
            env_name = SId.createEnvId("env", env_type);
            InsFormEnv ins_env = new InsFormEnv(env_name, form_env);
            inss.add(ins_env);
            
        }
        
        List<SId> names = new ArrayList<SId>();
        List<DefFun> funs = new ArrayList<DefFun>();
        

        for (Cf3undec f3undec: node0.m_f3ds) {
            // populate the map
            SId name = SId.fromCd3var(f3undec.m_var, SId.Category.eUserFun);
            names.add(name);
        }
        
        for (Cf3undec f3undec: node0.m_f3ds) {
            
            DefFun fundef = transform(f3undec,
                    node0.m_env/*the is the env for the function*/, 
                    env_name,
                    inss);
            funs.add(fundef);
        }
        
        Edeckind knd = Edeckind.fromEfunkind(node0.m_knd);
        DecGroup protos = new DecGroup(knd, names);
        m_decs.add(protos);
        
        DefFunGroup fungrp = new DefFunGroup(node0.m_knd, funs, env_name, form_env);
        m_defs.add(fungrp);
        
    }

    /*
     * create function definition and, closure if necessary
     * We are inside the function body.
     * 
     * env: the environment for the function
     * env_names: including env_name
     * env_name: current env_name if not null
     */
    private DefFun transform(Cf3undec f3undec, Set<Cd3var> env, SId env_name,
            List<IStfplInstruction> inss) {
        
        SId name = SId.fromCd3var(f3undec.m_var);
        
        List<Cp3at> p3ts = f3undec.m_p3ts;
        List<SId> paras = collectPatVarFunPara(p3ts);
        
        Cd3exp body = f3undec.m_body;
        
        Cloc_t loc = f3undec.m_loc;
        
        int lin = f3undec.m_lin; 
        

        // transform the body of the function
        List<IStfplInstruction> inss2 = new ArrayList<IStfplInstruction>();
        transform(body, env, inss2, SId.createRetHolder("ret", Aux.getRetType(f3undec.m_type)));
        DefFun def_fun = new DefFun(loc, name, lin, paras, inss2);

        // create the closure if necessary
        ISType clo_type = f3undec.m_type;
        if (Aux.getClosureInfo(clo_type) != FUNCLOfun.cInstance) {  // closure
            SIdUser env_name_user = new SIdUser(env_name, false);
            InsClosure ins_clo = new InsClosure(name, env_name_user);
            inss.add(ins_clo);
        }

        return def_fun;
    }

    private IValPrim transform(Cd3exp d3exp, Set<Cd3var> env, 
            List<IStfplInstruction> inss, SId holder) {
       Cloc_t loc = d3exp.m_loc;
       Id3exp_node node0 = d3exp.m_node;
       
       if (node0 instanceof D3Eapplst) {
           return transform((D3Eapplst)node0, env, inss, holder);
       } else if (node0 instanceof D3Ecst) {
           return transform((D3Ecst)node0, env, inss, holder);
       } else if (node0 instanceof D3Eempty) {
           return transform((D3Eempty)node0, env, inss, holder);
       } else if (node0 instanceof D3Ef0loat) {
           return transform((D3Ef0loat)node0, env, inss, holder, loc);
       } else if (node0 instanceof D3Ei0nt) {
           return transform((D3Ei0nt)node0, env, inss, holder, loc);
       } else if (node0 instanceof D3Eifopt) {
           return transform((D3Eifopt)node0, env, inss, holder);
       } else if (node0 instanceof D3ElamDyn) {
           return transform((D3ElamDyn)node0, env, inss, holder, loc);
       } else if (node0 instanceof D3Elet) {
           return transform((D3Elet)node0, env, inss, holder, loc);
       } else if (node0 instanceof D3Es0tring) {
           return transform((D3Es0tring)node0, env, inss, holder, loc);
       } else if (node0 instanceof D3Esym) {
           return transform((D3Esym)node0, env, inss, holder);
       } else if (node0 instanceof D3Etup) {
           return transform((D3Etup)node0, env, inss, holder);           
       } else if (node0 instanceof D3Evar) {
           return transform((D3Evar)node0, env, inss, holder);
       } else {
           throw new Error(node0 + " is not supported.");
       }
    }

    private IValPrim transform(D3Ef0loat node0, Set<Cd3var> env,
            List<IStfplInstruction> inss, SId holder, Cloc_t loc) {
        AtomValue v = new AtomValue(node0.m_ty, node0.m_f0loat);
        
        if (null != holder && holder.isRet()) {
            InsMove aIns = new InsMove(v, holder);
            inss.add(aIns);
            SIdUser ret = new SIdUser(holder, false);
            return ret;
        } else {
            return v;
        }
    }
    
    private IValPrim transform(D3Ei0nt node0, Set<Cd3var> env,
            List<IStfplInstruction> inss, SId holder, Cloc_t loc) {
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
            List<IStfplInstruction> inss, SId holder, Cloc_t loc) {
        AtomValue v = new AtomValue(node0.m_ty, node0.m_s0tring);
        
        if (null != holder && holder.isRet()) {
            InsMove aIns = new InsMove(v, holder);
            inss.add(aIns);
            SIdUser ret = new SIdUser(holder, false);
            return ret;
        } else {
            return v;
        }
    }

    private SIdUser transform(D3Etup node0, Set<Cd3var> env,
            List<IStfplInstruction> inss, SId holder) {
        List<IValPrim> elements = new ArrayList<IValPrim>();
        for (Cd3exp d3exp: node0.m_d3es) {
            IValPrim element = transform(d3exp, env, inss, null);
            elements.add(element);
        }
        
        if (null == holder) {
            holder = SId.createLocalVar("tup", node0.getType());
        }
        
        InsTuple ins = new InsTuple(elements, holder);
        inss.add(ins);
        
        SIdUser ret = new SIdUser(holder, false);
        
        return ret;
    }

    private IValPrim transform(D3Elet node0, Set<Cd3var> env,
            List<IStfplInstruction> inss, SId holder, Cloc_t loc) {
        transform(node0.m_d3cs, env, inss);
        return transform(node0.m_body, env, inss, holder);     
    }

    private SIdUser transform(D3ElamDyn node0, Set<Cd3var> env,
            List<IStfplInstruction> inss, SId holder, Cloc_t loc) {
        D3ElamDyn lambda = node0;

        int lin = lambda.m_lin; 
        
        List<Cp3at> p3ts = lambda.m_p3ts;
        List<SId> paras = collectPatVarFunPara(p3ts);

        Set<Cd3var> env2 = lambda.m_env;

        // unnamed lambda
        SId name = SId.createUserFunction("lam", lambda.getType());
        
        SId env_name = null;
        Set<SIdUser> form_env = null;
        
        // create the closure if necessary
        ISType clo_type = lambda.getType();
        if (Aux.getClosureInfo(clo_type) != FUNCLOfun.cInstance) {  // closure
            form_env = new HashSet<SIdUser>();
            
            for (Cd3var d3var: env2) {
                if (env.contains(d3var)) {
                    SIdUser env_member = SId.createSIdUserByCd3var(d3var, true);
                    form_env.add(env_member);
                } else {
                    SIdUser env_member = SId.createSIdUserByCd3var(d3var, false);
                    form_env.add(env_member);
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
            env_name = SId.createEnvId("env", env_type);
            
            InsFormEnv ins_env = new InsFormEnv(env_name, form_env);
            inss.add(ins_env);
            
            SIdUser env_name_user = new SIdUser(env_name, false);
            InsClosure ins_clo = new InsClosure(name, env_name_user);
            inss.add(ins_clo);
        }

        // transform the body of the function
        List<IStfplInstruction> inss2 = new ArrayList<IStfplInstruction>();
        transform(lambda.m_d3exp, node0.m_env, inss2, SId.createRetHolder("ret", Aux.getRetType(lambda.getType())));
        DefFun def_fun = new DefFun(loc, name, lin, paras, inss2);
        List<DefFun> fun_lst = new ArrayList<DefFun>();
        fun_lst.add(def_fun);
        
        // add to global definition
        Efunkind fun_knd = Efunkind.FK_fn;  // Unnamed lambda is non-recursive.
        DefFunGroup fun_group = new DefFunGroup(fun_knd, fun_lst, env_name, form_env);
        m_defs.add(fun_group);

        // add to global declaration
        List<SId> name_lst = new ArrayList<SId>();
        name_lst.add(name);
        Edeckind dec_knd = Edeckind.fromEfunkind(fun_knd);
        DecGroup protos = new DecGroup(dec_knd, name_lst);
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
            List<IStfplInstruction> inss, SId holder) {
        if (null == holder) {
            holder = SId.createLocalVar("cond", node0.m_test.m_node.getType());
        }
        
        IValPrim vp_cond = transform(node0.m_test, env, inss, null);
        
        List<IStfplInstruction> btrue = new ArrayList<IStfplInstruction>();
        List<IStfplInstruction> bfalse = new ArrayList<IStfplInstruction>();
        
        transform(node0.m_then, env, btrue, holder);
        if (null != node0.m_else) {
            transform(node0.m_else, env, bfalse, holder);
        }
        
        InsCond ins = new InsCond(holder, vp_cond, btrue, bfalse);
        inss.add(ins);
        
        SIdUser ret = new SIdUser(holder, false);
        return ret;   
    }

    private IValPrim transform(D3Eempty node0, Set<Cd3var> env,
            List<IStfplInstruction> inss, SId holder) {
        
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
            List<IStfplInstruction> inss, SId holder) {
        SIdUser vpFun = (SIdUser)transform(node0.m_fun, env, inss, null);

        ListIterator<D3EXPARGdyn> iter_args = node0.m_args.listIterator();
        D3EXPARGdyn args = iter_args.next();
        
        ListIterator<ISType> iter_types = node0.m_inner_stype.listIterator();
        
        while (true) {
            List<IValPrim> vpArgs = new ArrayList<IValPrim>();
            for (Cd3exp arg: args.m_d3expLst) {
                IValPrim vpArg = transform(arg, env, inss, null);
                vpArgs.add(vpArg);
            }

            if (iter_args.hasNext()) {
                SId id = SId.createLocalVar("temp", iter_types.next());
                formInsCall(id, vpFun, vpArgs, inss);

                vpFun = new SIdUser(id, false);
                args = iter_args.next();
                
            } else {
                if (null == holder) {
                    SId id = SId.createLocalVar("temp", iter_types.next());
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
        
        if (((FunType)fun.getType()).getFunClo() != FUNCLOfun.cInstance) {
            // Call a closure
            aIns = new InsCall(holder, fun, args, ECallType.eCloObj);
        } else {
            // Call a function
            aIns = new InsCall(holder, fun, args, ECallType.eFun);
        }
        inss.add(aIns);
    }

    private SIdUser transform(D3Ecst node, Set<Cd3var> env,
            List<IStfplInstruction> inss, SId holder) {
        SId v = SId.fromCd3cst(node.m_d3cst, SId.Category.eConstant);
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
            List<IStfplInstruction> inss, SId holder) {
        SId v = SId.fromCd3sym(node.m_d3sym, SId.Category.eConstant);
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
            List<IStfplInstruction> inss, SId holder) {
        SIdUser sid_user = null;
        if (env.contains(node.m_d3var)) {
            sid_user = SId.createSIdUserByCd3var(node.m_d3var, true);
        } else {
            sid_user = SId.createSIdUserByCd3var(node.m_d3var, false);
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






