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
    
    private List<InsDecGroup> m_decs;
    
    private List<D3Cextcode> m_exts;
    
    private List<InsFunDefGroup> m_defs;
    
    private List<IStfplInstruction> m_inss;
    
    public InstructionTransformer() {
        m_decs = new ArrayList<InsDecGroup>();
        m_exts = new ArrayList<D3Cextcode>();
        m_defs = new ArrayList<InsFunDefGroup>();
        m_inss = new ArrayList<IStfplInstruction>();
        
    }
    
    public void transform_global(List<Cd3ecl> d3ecs) {
        for (Cd3ecl d3ec: d3ecs) {
            transform_global(d3ec);
        }
    }

    private void transform_global(Cd3ecl d3ec) {
        Id3ecl_node node0 = d3ec.m_node;
        if (node0 instanceof D3Cdcstdecs) {
            m_decs.add(transform((D3Cdcstdecs)node0));
        } else if (node0 instanceof D3Cextcode) {
            m_exts.add((D3Cextcode)node0);
        } else if (node0 instanceof D3Cfundecs) {
            transform_global(d3ec.m_loc, (D3Cfundecs)node0);
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

    private InsDecGroup transform(D3Cdcstdecs node0) {
        List<IVarName> names = new ArrayList<IVarName>();
        for (Cd3cst cst: node0.m_d3cst) {
            names.add(new VNameCst(cst));
        }
        
        Edeckind knd = Edeckind.fromEcstkind(node0.m_dck);
        return new InsDecGroup(knd, names);
    }

    private void transform_global(Cloc_t loc, D3Cfundecs node0) {
        List<IVarName> names = new ArrayList<IVarName>();
        List<InsFunDef> funs = new ArrayList<InsFunDef>();
        for (Cf3undec f3undec: node0.m_f3ds) {
            VNameVar name = new VNameVar(f3undec.m_var);
            names.add(name);
            InsFunDef fundef = transform_global(f3undec);
            funs.add(fundef);
        }
        
        Edeckind knd = Edeckind.fromEfunkind(node0.m_knd);
        InsDecGroup protos = new InsDecGroup(knd, names);
        m_decs.add(protos);
        
        InsFunDefGroup fungrp = new InsFunDefGroup(node0.m_knd, funs);
        m_defs.add(fungrp);
        
    }


    private InsFunDef transform_global(Cf3undec f3undec) {
        D3ElamDyn lambda = (D3ElamDyn)f3undec.m_def.m_node;
        
        Cloc_t loc = f3undec.m_loc;
        IVarName name = new VNameVar(f3undec.m_var);
        int lin = lambda.m_lin; 
        List<Cp3at> p3ts = lambda.m_p3ts;
        Ifunclo funclo = lambda.m_funclo; 
        
        Set<Cd3var> env = lambda.m_env;
        
        List<IStfplInstruction> inss = new ArrayList<IStfplInstruction>();
        IValPrim vp = transform(lambda.m_d3exp, env, inss, SId.createRetHolder("ret", f3undec.getRetType()));
        
        InsFunDef aIns = new InsFunDef(loc, name, lin, p3ts, funclo, ins, env);
        return aIns;
         
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
           
       } else if (node0 instanceof D3Ei0nt) {
           
       } else if (node0 instanceof D3Eifopt) {
           
       } else if (node0 instanceof D3ElamDyn) {
           
       } else if (node0 instanceof D3Elet) {
           
       } else if (node0 instanceof D3Es0tring) {
           
       } else if (node0 instanceof D3Esym) {
           
       } else if (node0 instanceof D3Etup) {
           
       } else if (node0 instanceof D3Evar) {
           
       } else {
           
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

        ListIterator<D3EXPARGdyn> iter = node0.m_args.listIterator();
        
        D3EXPARGdyn args = iter.next();
        
        while (true) {
            List<IValPrim> vpArgs = new ArrayList<IValPrim>();
            for (Cd3exp arg: args.m_d3expLst) {
                IValPrim vpArg = transform(arg, env, inss, holder);
            }
            
            ISType ty = vpFun.getSType();
            FunType funTy = (FunType)ty;
            
            
            if (iter.hasNext()) {
                SId id = SId.createLocalVar("temp", stype)
            }
        }
        

        
        if 
        
    }
    

    private IValPrim transform(D3Ecst node, Set<Cd3var> env,
            List<IStfplInstruction> inss, SId holder) {
        SId v = new SId(node.m_d3cst, SId.Category.eConstant);
        if (null != holder) {
            InsMove aIns = new InsMove(v, holder);
            inss.add(aIns);
            return holder;
        } else {
            return v;
        }
    }



}






