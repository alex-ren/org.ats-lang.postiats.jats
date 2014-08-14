package jats.utfpl.stfpl.instructions;

import jats.utfpl.stfpl.Cloc_t;

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
import jats.utfpl.stfpl.instructions.SId.Category;
import jats.utfpl.stfpl.staexp.FUNCLOfun; 
import jats.utfpl.stfpl.stype.Aux;
import jats.utfpl.stfpl.stype.ISType;
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
    
    private List<DefFunGroup> m_defs;
    
    private List<IStfplInstruction> m_main_inss;  // global instructions
    
    public InstructionTransformer() {
        m_decs = new ArrayList<DecGroup>();
        m_exts = new ArrayList<D3Cextcode>();
        m_defs = new ArrayList<DefFunGroup>();
        m_main_inss = new ArrayList<IStfplInstruction>();
        
    }
    
    public List<DecGroup> getDecs() {
    	return m_decs;
    }
    
    public List<D3Cextcode> getExts() {
    	return m_exts;
    }
    
    public List<DefFunGroup> getDefs() {
    	return m_defs;
    }
    
    public List<IStfplInstruction> getMainInss() {
    	return m_main_inss;
    }
    
    
    public void transform_global(List<Cd3ecl> d3ecs) {
        transform(d3ecs, new HashSet<Cd3var>(), m_main_inss);
    }
    
    private void transform(List<Cd3ecl> d3ecs, Set<Cd3var> env,
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
            transform(d3ec.m_loc, (D3Cimpdec)node0, env, inss);
        } else if (node0 instanceof D3Cstacsts) {
            Log.log4j.warn("D3Cstacsts encountered in generating instruction.");
        } else if (node0 instanceof D3Cvaldecs) {
            transform(d3ec.m_loc, (D3Cvaldecs)node0, env, inss);
        } else if (node0 instanceof D3Cdatdecs) {
            throw new Error("D3Cdatdecs not supported yet");
        } else {
            throw new Error(node0 + " is not supported.");
        }
    }

    private void transform(Cloc_t m_loc, D3Cvaldecs node0, Set<Cd3var> env,
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
        } else {
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

    private void transform(Cloc_t m_loc, D3Cimpdec node0, Set<Cd3var> env,
            List<IStfplInstruction> inss) {
        List<DefFun> funs = new ArrayList<DefFun>();
        DefFun fundef = transform(node0, env, inss);  
        funs.add(fundef);
        
        DefFunGroup fungrp = new DefFunGroup(Efunkind.fromInt(node0.m_knd), funs);
        m_defs.add(fungrp);
    }

    private DefFun transform(D3Cimpdec node0, Set<Cd3var> env,
            List<IStfplInstruction> inss) {
        Cd3exp fun_exp = node0.m_i3mpdec.i2mpdec_def;
        
        D3ElamDyn lambda = (D3ElamDyn)(fun_exp.m_node);
        Cloc_t loc = fun_exp.m_loc;
        SId name = SId.fromCd3cst(node0.m_i3mpdec.i2mpdec_cst, Category.eUserFun);
        int lin = lambda.m_lin; 
        List<Cp3at> p3ts = lambda.m_p3ts;
        Set<Cd3var> env2 = lambda.m_env;
        
        // transform the body of the function
        List<IStfplInstruction> inss2 = new ArrayList<IStfplInstruction>();
        transform(lambda.m_d3exp, env2, inss2, SId.createRetHolder("ret", Aux.getRetType(lambda.getType())));
        DefFun def_fun = new DefFun(loc, name, lin, p3ts, inss2, env2);

        // create the closure if necessary
        ISType clo_type = lambda.getType();
        if (Aux.getClosureInfo(clo_type) != FUNCLOfun.cInstance) {  // closure
            if (inss != m_main_inss) {
                throw new Error("Implementing closure is only allowed on the first level.");
            }
            Set<SId> form_env = new HashSet<SId>();
            
            for (Cd3var d3var: env2) {
                if (env.contains(d3var)) {
                    SId clo_id = SId.fromCloCd3var(d3var, Category.eOther);
                    form_env.add(clo_id);
                } else {
                    form_env.add(SId.fromCd3var(d3var, Category.eOther));
                }
            }
            InsClosure ins_clo = new InsClosure(name, form_env);
            inss.add(ins_clo);
        }
        
        return def_fun;
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

    private void transform(Cloc_t loc, D3Cfundecs node0, Set<Cd3var> env, 
            List<IStfplInstruction> inss) {
        List<SId> names = new ArrayList<SId>();
        List<DefFun> funs = new ArrayList<DefFun>();
        for (Cf3undec f3undec: node0.m_f3ds) {
            SId name = SId.fromCd3var(f3undec.m_var, SId.Category.eUserFun);
            names.add(name);
            DefFun fundef = transform(f3undec, env, inss);
            funs.add(fundef);
        }
        
        Edeckind knd = Edeckind.fromEfunkind(node0.m_knd);
        DecGroup protos = new DecGroup(knd, names);
        m_decs.add(protos);
        
        DefFunGroup fungrp = new DefFunGroup(node0.m_knd, funs);
        m_defs.add(fungrp);
        
    }

    /*
     * create function definition and, closure if necessary
     */
    private DefFun transform(Cf3undec f3undec, Set<Cd3var> env, List<IStfplInstruction> inss) {
        D3ElamDyn lambda = (D3ElamDyn)f3undec.m_def.m_node;
        
        Cloc_t loc = f3undec.m_loc;
        SId name = SId.fromCd3var(f3undec.m_var, Category.eUserFun);
        int lin = lambda.m_lin; 
        List<Cp3at> p3ts = lambda.m_p3ts;
        Set<Cd3var> env2 = lambda.m_env;
        
        // transform the body of the function
        List<IStfplInstruction> inss2 = new ArrayList<IStfplInstruction>();
        transform(lambda.m_d3exp, env2, inss2, SId.createRetHolder("ret", Aux.getRetType(lambda.getType())));
        DefFun def_fun = new DefFun(loc, name, lin, p3ts, inss2, env2);

        // create the closure if necessary
        ISType clo_type = lambda.getType();
        if (Aux.getClosureInfo(clo_type) != FUNCLOfun.cInstance) {  // closure
            Set<SId> form_env = new HashSet<SId>();
            
            for (Cd3var d3var: env2) {
                if (env.contains(d3var)) {
                    SId clo_id = SId.fromCloCd3var(d3var, Category.eOther);
                    form_env.add(clo_id);
                } else {
                    form_env.add(SId.fromCd3var(d3var, Category.eOther));
                }
            }
            InsClosure ins_clo = new InsClosure(name, form_env);
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
            return holder;
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
            return holder;
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
            return holder;
        } else {
            return v;
        }
    }

    private IValPrim transform(D3Etup node0, Set<Cd3var> env,
            List<IStfplInstruction> inss, SId holder) {
        List<IValPrim> elements = new ArrayList<IValPrim>();
        for (Cd3exp d3exp: node0.m_d2es) {
            IValPrim element = transform(d3exp, env, inss, null);
            elements.add(element);
        }
        
        if (null == holder) {
            holder = SId.createLocalVar("tup", node0.getType());
        }
        
        InsTuple ins = new InsTuple(elements, holder);
        inss.add(ins);
        
        return holder;
    }

    private IValPrim transform(D3Elet node0, Set<Cd3var> env,
            List<IStfplInstruction> inss, SId holder, Cloc_t loc) {
        transform(node0.m_d3cs, env, inss);
        return transform(node0.m_body, env, inss, holder);     
    }

    private IValPrim transform(D3ElamDyn node0, Set<Cd3var> env,
            List<IStfplInstruction> inss, SId holder, Cloc_t loc) {
        D3ElamDyn lambda = node0;
        
        // unnamed lambda
        SId name = SId.createUserFunction("lam", lambda.getType());
        int lin = lambda.m_lin; 
        List<Cp3at> p3ts = lambda.m_p3ts;
        Set<Cd3var> env2 = lambda.m_env;
        
        // transform the body of the function
        List<IStfplInstruction> inss2 = new ArrayList<IStfplInstruction>();
        transform(lambda.m_d3exp, env2, inss2, SId.createRetHolder("ret", Aux.getRetType(lambda.getType())));
        DefFun def_fun = new DefFun(loc, name, lin, p3ts, inss2, env2);
        List<DefFun> fun_lst = new ArrayList<DefFun>();
        fun_lst.add(def_fun);
        
        // add to global definition
        Efunkind fun_knd = Efunkind.FK_fn;  // Unnamed lambda is non-recursive.
        DefFunGroup fun_group = new DefFunGroup(fun_knd, fun_lst);
        m_defs.add(fun_group);

        // add to global declaration
        List<SId> name_lst = new ArrayList<SId>();
        name_lst.add(name);
        Edeckind dec_knd = Edeckind.fromEfunkind(fun_knd);
        DecGroup protos = new DecGroup(dec_knd, name_lst);
        m_decs.add(protos);

        // create the closure if necessary
        ISType clo_type = lambda.getType();
        // if no annotation about closure, then treat it as closure
        if (Aux.getClosureInfo(clo_type) != FUNCLOfun.cInstance) {  // closure
            Set<SId> form_env = new HashSet<SId>();
            
            for (Cd3var d3var: env2) {
                if (env.contains(d3var)) {
                    SId clo_id = SId.fromCloCd3var(d3var, Category.eOther);
                    form_env.add(clo_id);
                } else {
                    form_env.add(SId.fromCd3var(d3var, Category.eOther));
                }
            }
            InsClosure ins_clo = new InsClosure(name, form_env);
            inss.add(ins_clo);
        }
        
        // add instruction for move
        if (null == holder) {
            return name;
        } else {
            InsMove ins = new InsMove(name, holder);
            inss.add(ins);
            return holder;
        }
    }

    private IValPrim transform(D3Eifopt node0, Set<Cd3var> env,
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
        return holder;   
    }

    private IValPrim transform(D3Eempty node0, Set<Cd3var> env,
            List<IStfplInstruction> inss, SId holder) {
        
        IValPrim v = AtomValue.sEmpty;
        if (null != holder) {
            InsMove aIns = new InsMove(v, holder);
            inss.add(aIns);
            return holder;
        } else {
            return v;
        }
    }

    private IValPrim transform(D3Eapplst node0, Set<Cd3var> env,
            List<IStfplInstruction> inss, SId holder) {
        IValPrim vpFun = transform(node0.m_fun, env, inss, null);

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
                InsCall aIns = new InsCall(id, vpFun, vpArgs);
                inss.add(aIns);
                
                vpFun = id;
                args = iter_args.next();
                
            } else {
                if (null == holder) {
                    SId id = SId.createLocalVar("temp", iter_types.next());
                    InsCall aIns = new InsCall(id, vpFun, vpArgs);
                    inss.add(aIns);
                    return id;
                } else {
                    InsCall aIns = new InsCall(holder, vpFun, vpArgs);
                    inss.add(aIns);
                    return holder;
                }
                
            }  
        }
    }

    private IValPrim transform(D3Ecst node, Set<Cd3var> env,
            List<IStfplInstruction> inss, SId holder) {
        SId v = SId.fromCd3cst(node.m_d3cst, SId.Category.eConstant);
        if (null != holder) {
            InsMove aIns = new InsMove(v, holder);
            inss.add(aIns);
            return holder;
        } else {
            return v;
        }
    }

    private IValPrim transform(D3Esym node, Set<Cd3var> env,
            List<IStfplInstruction> inss, SId holder) {
        SId v = SId.fromCd3sym(node.m_d3sym, SId.Category.eConstant);
        if (null != holder) {
            throw new Error("Check this.");
        } else {
            return v;
        }
    }

    private IValPrim transform(D3Evar node, Set<Cd3var> env,
            List<IStfplInstruction> inss, SId holder) {
        IValPrim v = null;
        if (env.contains(node)) {
            v = SId.fromCloCd3var(node.m_d3var, Category.eOther);
        } else {
            v = SId.fromCd3var(node.m_d3var, SId.Category.eLocalVar);
        }

        if (null != holder) {
            InsMove aIns = new InsMove(v, holder);
            inss.add(aIns);
            return holder;
        } else {
            return v;
        }
    }
    



}






