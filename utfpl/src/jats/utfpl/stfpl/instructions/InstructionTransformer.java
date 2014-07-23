package jats.utfpl.stfpl.instructions;

import jats.utfpl.stfpl.Cloc_t;
import jats.utfpl.stfpl.Cstamp;
import jats.utfpl.stfpl.dynexp.Cd2cst;
import jats.utfpl.stfpl.dynexp.Cd2ecl;
import jats.utfpl.stfpl.dynexp.Cd2exp;
import jats.utfpl.stfpl.dynexp.Cd2sym;
import jats.utfpl.stfpl.dynexp.Cd2var;
import jats.utfpl.stfpl.dynexp.Cf2undec;
import jats.utfpl.stfpl.dynexp.Ci2mpdec;
import jats.utfpl.stfpl.dynexp.Cp2at;
import jats.utfpl.stfpl.dynexp.Cv2aldec;
import jats.utfpl.stfpl.dynexp.D2Cdatdecs;
import jats.utfpl.stfpl.dynexp.D2Cdcstdecs;
import jats.utfpl.stfpl.dynexp.D2Cextcode;
import jats.utfpl.stfpl.dynexp.D2Cfundecs;
import jats.utfpl.stfpl.dynexp.D2Cignored;
import jats.utfpl.stfpl.dynexp.D2Cimpdec;
import jats.utfpl.stfpl.dynexp.D2Cstacsts;
import jats.utfpl.stfpl.dynexp.D2Cvaldecs;
import jats.utfpl.stfpl.dynexp.D2EXPARGdyn;
import jats.utfpl.stfpl.dynexp.D2EXPARGsta;
import jats.utfpl.stfpl.dynexp.D2EannFunclo;
import jats.utfpl.stfpl.dynexp.D2EannSeff;
import jats.utfpl.stfpl.dynexp.D2EannType;
import jats.utfpl.stfpl.dynexp.D2Eapplst;
import jats.utfpl.stfpl.dynexp.D2Ecst;
import jats.utfpl.stfpl.dynexp.D2Eempty;
import jats.utfpl.stfpl.dynexp.D2Eexp;
import jats.utfpl.stfpl.dynexp.D2Ef0loat;
import jats.utfpl.stfpl.dynexp.D2Ei0nt;
import jats.utfpl.stfpl.dynexp.D2Eifopt;
import jats.utfpl.stfpl.dynexp.D2Eignored;
import jats.utfpl.stfpl.dynexp.D2ElamDyn;
import jats.utfpl.stfpl.dynexp.D2ElamMet;
import jats.utfpl.stfpl.dynexp.D2ElamSta;
import jats.utfpl.stfpl.dynexp.D2Elet;
import jats.utfpl.stfpl.dynexp.D2Elist;
import jats.utfpl.stfpl.dynexp.D2Es0tring;
import jats.utfpl.stfpl.dynexp.D2Esym;
import jats.utfpl.stfpl.dynexp.D2Etup;
import jats.utfpl.stfpl.dynexp.D2Evar;
import jats.utfpl.stfpl.dynexp.Edcstkind;
import jats.utfpl.stfpl.dynexp.Efunkind;
import jats.utfpl.stfpl.dynexp.Evalkind;
import jats.utfpl.stfpl.dynexp.Id2ecl_node;
import jats.utfpl.stfpl.dynexp.Id2exp_node;
import jats.utfpl.stfpl.dynexp.Id2exparg;
import jats.utfpl.stfpl.dynexp.Ilabp2at;
import jats.utfpl.stfpl.dynexp.Ip2at_node;
import jats.utfpl.stfpl.dynexp.LABP2ATnorm;
import jats.utfpl.stfpl.dynexp.LABP2ATomit;
import jats.utfpl.stfpl.dynexp.P2Tann;
import jats.utfpl.stfpl.dynexp.P2Tany;
import jats.utfpl.stfpl.dynexp.P2Tcon;
import jats.utfpl.stfpl.dynexp.P2Tempty;
import jats.utfpl.stfpl.dynexp.P2Tignored;
import jats.utfpl.stfpl.dynexp.P2Tpat;
import jats.utfpl.stfpl.dynexp.P2Trec;
import jats.utfpl.stfpl.dynexp.P2Tvar;
import jats.utfpl.stfpl.dynexp3.Cd3cst;
import jats.utfpl.stfpl.dynexp3.Cd3ecl;
import jats.utfpl.stfpl.dynexp3.Cd3exp;
import jats.utfpl.stfpl.dynexp3.Cd3sym;
import jats.utfpl.stfpl.dynexp3.Cd3var;
import jats.utfpl.stfpl.dynexp3.Cf3undec;
import jats.utfpl.stfpl.dynexp3.Ci3mpdec;
import jats.utfpl.stfpl.dynexp3.Cp3at;
import jats.utfpl.stfpl.dynexp3.Cv3aldec;
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
import jats.utfpl.stfpl.dynexp3.P3Tempty;
import jats.utfpl.stfpl.dynexp3.P3Trec;
import jats.utfpl.stfpl.dynexp3.P3Tvar;
import jats.utfpl.stfpl.instructions.SId.Category;
import jats.utfpl.stfpl.staexp.FUNCLOclo;
import jats.utfpl.stfpl.staexp.FUNCLOfun;
import jats.utfpl.stfpl.staexp.Ifunclo; 
import jats.utfpl.stfpl.stype.FunType;
import jats.utfpl.stfpl.stype.ISType;
import jats.utfpl.stfpl.stype.PolyType;
import jats.utfpl.utils.Log;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;

public class InstructionTransformer {
    
    private List<DecGroup> m_decs;
    
    private List<D3Cextcode> m_exts;
    
    private List<DefFunGroup> m_defs;
    
    private List<IStfplInstruction> m_main_inss;  // global instructions
    
    public InstructionTransformer() {
        m_decs = new ArrayList<DecGroup>();
        m_exts = new ArrayList<D3Cextcode>();
        m_defs = new ArrayList<DefFunGroup>();
        m_main_inss = new ArrayList<IStfplInstruction>();
        
    }
    
    public void transform_global(List<Cd3ecl> d3ecs) {
        for (Cd3ecl d3ec: d3ecs) {
            transform(d3ec, new HashSet<Cd3var>(), m_main_inss);
        }
    }

    private void transform(Cd3ecl d3ec, Set<Cd3var> env, 
            List<IStfplInstruction> inss) {
        Id3ecl_node node0 = d3ec.m_node;
        if (node0 instanceof D3Cdcstdecs) {
            transform((D3Cdcstdecs)node0, env, inss);
            return;
        } else if (node0 instanceof D3Cextcode) {
            m_exts.add((D3Cextcode)node0);
            return;
        } else if (node0 instanceof D3Cfundecs) {
            transform(d3ec.m_loc, (D3Cfundecs)node0, env, inss);
            return;
        } else if (node0 instanceof D2Cimpdec) {
            return transform_global(d2ec.d2ecl_loc, (D2Cimpdec)node0, scope, needed);
        } else if (node0 instanceof D2Cstacsts) {
            return transform(d2ec.d2ecl_loc, (D2Cstacsts)node0);
        } else if (node0 instanceof D2Cvaldecs) {
            return transform(d2ec.d2ecl_loc, (D2Cvaldecs)node0, scope, needed);    
        } else if (node0 instanceof D2Cdatdecs) {
            Log.log4j.warn("D2Cdatdecs encountered in generating dynexp3.");
            return null;
        } else {
            throw new Error(node0 + " is not supported.");
        }
    }

    private void transform(D3Cdcstdecs node0, Set<Cd3var> env, 
            List<IStfplInstruction> inss) {
        List<IVarName> names = new ArrayList<IVarName>();
        for (Cd3cst cst: node0.m_d3cst) {
            names.add(VNameCst.fromCd3cst(cst));
        }
        
        Edeckind knd = Edeckind.fromEcstkind(node0.m_dck);
        m_decs.add(new DecGroup(knd, names));
    }

    private void transform(Cloc_t loc, D3Cfundecs node0, Set<Cd3var> env, 
            List<IStfplInstruction> inss) {
        List<IVarName> names = new ArrayList<IVarName>();
        List<DefFun> funs = new ArrayList<DefFun>();
        for (Cf3undec f3undec: node0.m_f3ds) {
            VNameVar name = VNameVar.fromCd3var(f3undec.m_var);
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
        Ifunclo funclo = lambda.m_funclo; 
        
        Set<Cd3var> env2 = lambda.m_env;
        
        List<IStfplInstruction> inss2 = new ArrayList<IStfplInstruction>();
        transform(lambda.m_d3exp, env2, inss2, SId.createRetHolder("ret", f3undec.getRetType()));
        DefFun def_fun = new DefFun(loc, name, lin, p3ts, funclo, inss2, env2);

        if (funclo != FUNCLOfun.cInstance) {  // closure
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
           D3Ef0loat node = (D3Ef0loat)node0;
           return new AtomValue(node.m_ty, node.m_f0loat);
       } else if (node0 instanceof D3Ei0nt) {
           D3Ei0nt node = (D3Ei0nt)node0;
           return new AtomValue(node.m_ty, node.m_i0nt);
       } else if (node0 instanceof D3Eifopt) {
           return transform((D3Eifopt)node0, env, inss, holder);
       } else if (node0 instanceof D3ElamDyn) {
           
       } else if (node0 instanceof D3Elet) {
           
       } else if (node0 instanceof D3Es0tring) {
           D3Es0tring node = (D3Es0tring)node0;
           return new AtomValue(node.m_ty, node.m_s0tring);
       } else if (node0 instanceof D3Esym) {
           return transform((D3Esym)node0, env, inss, holder);
       } else if (node0 instanceof D3Etup) {
           
       } else if (node0 instanceof D3Evar) {
           return transform((D3Evar)node0, env, inss, holder);
       } else {
           
       }
    }

    private IValPrim transform(D3Eifopt node0, Set<Cd3var> env,
            List<IStfplInstruction> inss, SId holder) {
        if (null == holder) {
            SId vp = SId.createLocalVar("cond", node0.m_test.m_node.m)
        }
        IValPrim vp_cond = transform(node0.m_test, env, inss, null);
        
        List<IStfplInstruction> btrue = new ArrayList<IStfplInstruction>();
        List<IStfplInstruction> bfalse = new ArrayList<IStfplInstruction>();
        
        transform(node0.m_then, env, btrue, holder);
        if (null != node0.m_else) {
            transform(node0.m_else, env, btrue, holder);
        }
        
        
        
        
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
                IValPrim vpArg = transform(arg, env, inss, holder);
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






