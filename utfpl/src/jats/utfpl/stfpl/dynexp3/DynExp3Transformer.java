package jats.utfpl.stfpl.dynexp3;

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
import jats.utfpl.stfpl.staexp.FUNCLOclo;
import jats.utfpl.stfpl.staexp.FUNCLOfun;
import jats.utfpl.stfpl.staexp.Ifunclo;
import jats.utfpl.stfpl.stype.FunType; 
import jats.utfpl.stfpl.stype.ISType;
import jats.utfpl.stfpl.stype.PolyType;
import jats.utfpl.utils.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;

public class DynExp3Transformer {
    
    private Map<Cstamp, Cd3cst> m_cstMap;
    private Map<Cstamp, Cd3var> m_varMap;
    
    public DynExp3Transformer() {
        m_cstMap = new HashMap<Cstamp, Cd3cst>();
        m_varMap = new HashMap<Cstamp, Cd3var>();
    }
    
    
    /*
     * Purpose: 
     * Remove proof
     * 1. prval (...) = ...
     * 2. val (pf | ...) = foo ()
     * 
     * Handle closure
     * 
     * scope will be modified.
     * cur_scope:
     * needed: Symbols needed but not in cur_scope
     */
    public List<Cd3ecl> transform(List<Cd2ecl> d2ecs, Set<Cd3var> scope, Set<Cd3var> needed) {

        List<Cd3ecl> d3ecs = new ArrayList<Cd3ecl>();
        for (Cd2ecl d2ec: d2ecs) {
            Cd3ecl d3ec = transform(d2ec, scope, needed);
            if (null != d3ec) {
                d3ecs.add(d3ec);
            }
        }
        
        return d3ecs;
    }

    /*
     * scope will be modified.
     */
    private Cd3ecl transform(Cd2ecl d2ec, Set<Cd3var> scope, Set<Cd3var> needed) {
        Id2ecl_node node0 = d2ec.d2ecl_node;
        if (node0 instanceof D2Cdcstdecs) {
            return transform(d2ec.d2ecl_loc, (D2Cdcstdecs)node0, scope);
        } else if (node0 instanceof D2Cextcode) {
            return transform(d2ec.d2ecl_loc, (D2Cextcode)node0);
        } else if (node0 instanceof D2Cfundecs) {
            return transform(d2ec.d2ecl_loc, (D2Cfundecs)node0, scope, needed);
        } else if (node0 instanceof D2Cignored) {
            Log.log4j.warn("D2Cignored encountered in generating dynexp3.");
            return null;
        } else if (node0 instanceof D2Cimpdec) {
            return transform(d2ec.d2ecl_loc, (D2Cimpdec)node0, scope, needed);
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

    private Cd3ecl transform(Cloc_t loc, D2Cvaldecs node0,
            Set<Cd3var> scope, Set<Cd3var> needed) {
        if (Evalkind.VK_prval == node0.m_knd) {
            return null;
        } else {
            D3Cvaldecs node = transform(node0, loc, scope, needed);
            Cd3ecl d3ecl = new Cd3ecl(loc, node);
            return d3ecl;
        }

    }


    private D3Cvaldecs transform(D2Cvaldecs node0, Cloc_t loc,
            Set<Cd3var> scope, Set<Cd3var> needed) {
        List<Cv3aldec> v3ds = new ArrayList<Cv3aldec>();
        
        for (Cv2aldec v2d: node0.m_v2ds) {
            Cv3aldec v3d = transform(v2d, scope, needed);
            v3ds.add(v3d);
        }
        
        D3Cvaldecs node = new D3Cvaldecs(node0.m_knd, v3ds);
        return node;
    }


    private Cv3aldec transform(Cv2aldec v2d, Set<Cd3var> scope,
            Set<Cd3var> needed) {
        Cp3at p3at = transform(v2d.v2aldec_pat);
        Cd3exp d3exp = transform(v2d.v2aldec_def, scope, needed);
        
        Cv3aldec v3d = new Cv3aldec(v2d.v2aldec_loc, p3at, d3exp);
        return v3d;
    }


    private Cd3ecl transform(Cloc_t loc, D2Cstacsts node0) {
        D3Cstacsts node = new D3Cstacsts(node0.m_s2csts);
        Cd3ecl d3ecl = new Cd3ecl(loc, node);
        return d3ecl;
    }


    private Cd3ecl transform(Cloc_t loc, D2Cimpdec node0,
            Set<Cd3var> scope, Set<Cd3var> needed) {
        Ci3mpdec i3mpdec = transform(node0.m_i2mpdec, scope, needed);
        D3Cimpdec node = new D3Cimpdec(node0.m_knd, i3mpdec);
        
        Cd3ecl d3ecl = new Cd3ecl(loc, node);
        return d3ecl;
    }


    private Ci3mpdec transform(Ci2mpdec i2mpdec, Set<Cd3var> scope,
            Set<Cd3var> needed) {
        Cd3cst d3cst = transform(i2mpdec.i2mpdec_cst, i2mpdec.i2mpdec_locid);
        Cd3exp d3exp = transform(i2mpdec.i2mpdec_def, scope, needed);
        
        Ci3mpdec i3mpdec = new Ci3mpdec(i2mpdec.i2mpdec_loc, i2mpdec.i2mpdec_locid, d3cst, d3exp);
        return i3mpdec;        
    }


    /*
     * scope: will be updated
     */
    private Cd3ecl transform(Cloc_t loc, D2Cfundecs node0, Set<Cd3var> scope, Set<Cd3var> needed) {
        switch (node0.m_knd) {
        case FK_fn: // non-recursive function 
        case FK_fnx: // tail-recursive 
        case FK_fun: // recursive
        {
            List<Cf3undec> f3uns = new ArrayList<Cf3undec>();
            for (Cf2undec f2un: node0.m_f2ds) {
                Cf3undec f3un = transform(f2un, needed);
                f3uns.add(f3un);
            }
            
            for (Cf3undec f3un: f3uns) {
                scope.add(f3un.m_var);
                needed.remove(f3un.m_var.m_stamp);
            }
            D3Cfundecs node = new D3Cfundecs(node0.m_knd, f3uns);
            
            return new Cd3ecl(loc, node);
        }
        
        case FK_prfn:
        case FK_prfun:
        case FK_praxi:
        case FK_castfn:
            return null;
        default:
            throw new Error(node0.m_knd + " is not supported.");
        }
    }


    private Cf3undec transform(Cf2undec f2un, Set<Cd3var> needed) {
        Cd3var d3var = transform(f2un.f2undec_var, f2un.f2undec_loc);
        Cd3exp d3exp = transform(f2un.f2undec_def, null, needed);
        D3ElamDyn d3elam = (D3ElamDyn)(d3exp.m_node);
        
        update_closure_info(d3var.m_stype, d3elam.m_funclo, f2un.f2undec_loc);

        return new Cf3undec(f2un.f2undec_loc, d3var, d3exp);
    }

    /*
     * Update the closure information in stype
     */
    private void update_closure_info(ISType stype, Ifunclo funclo, Cloc_t loc) {
        if (stype instanceof FunType) {
            FunType fun_ty = (FunType)stype;
            if (null != fun_ty.m_funclo) {
                Log.log4j.warn("closure information has been set");
            } else {
                fun_ty.m_funclo = funclo;
            }
        } else if (stype instanceof PolyType) {
            update_closure_info(((PolyType) stype).m_body, funclo, loc);
        } else {
            throw new Error("Not expecting " + stype + " at " + loc);
        }
    }


    /*
     * scope: names can be used. This cannot be changed since expression doesn't
     * provide new names.
     */
    private Cd3exp transform(Cd2exp d2exp, Set<Cd3var> scope, Set<Cd3var> needed) {
        Id2exp_node node = d2exp.d2exp_node;
        Cloc_t loc = d2exp.d2exp_loc;
        if (node instanceof D2EannFunclo) {
            return transform((D2EannFunclo)node, needed, loc);
        } else if (node instanceof D2EannSeff) {
            return transform(((D2EannSeff)node).m_d2exp, scope, needed);
        } else if (node instanceof D2EannType) {
            return transform(((D2EannType)node).m_d2exp, scope, needed);
        } else if (node instanceof D2Eapplst) {
            return transform((D2Eapplst)node, scope, needed, loc);
        } else if (node instanceof D2Ecst) {
            return transform((D2Ecst)node, loc);
        } else if (node instanceof D2Eempty) {
            return new Cd3exp(loc, D3Eempty.cInstance);
        } else if (node instanceof D2Eexp) {
            return transform(((D2Eexp)node).m_d2exp, scope, needed);
        } else if (node instanceof D2Ef0loat) {
            return new Cd3exp(loc, new D3Ef0loat(((D2Ef0loat)node).m_f0loat));
        } else if (node instanceof D2Ei0nt) {
            return new Cd3exp(loc, new D3Ei0nt(((D2Ei0nt)node).m_i0nt));
        } else if (node instanceof D2Eifopt) {
            return transform((D2Eifopt)node, loc, scope, needed);
        } else if (node instanceof D2Eignored) {
            throw new Error("Check this");
        } else if (node instanceof D2ElamDyn) {
            return transform((D2ElamDyn)node, loc, needed);
        } else if (node instanceof D2ElamMet) {
            return transform((D2ElamMet)node, loc, needed);
        } else if (node instanceof D2ElamSta) {
            return transform((D2ElamSta)node, loc, needed);
        } else if (node instanceof D2Elet) {
            return transform((D2Elet)node, loc, scope, needed);
        } else if (node instanceof D2Es0tring) {
            return new Cd3exp(loc, new D3Es0tring(((D2Es0tring)node).m_s0tring));
        } else if (node instanceof D2Esym) {
            return transform((D2Esym)node, loc);
        } else if (node instanceof D2Etup) {
            return transform((D2Etup)node, loc, scope, needed);
        } else if (node instanceof D2Elist) {
            return transform((D2Elist)node, loc, scope, needed);            
        } else if (node instanceof D2Evar) {
            return transform((D2Evar)node, loc, scope, needed);
        } else {
            throw new Error(d2exp + " is not supported");
        }
    }

    private Cd3exp transform(D2Evar node0, Cloc_t loc, Set<Cd3var> scope,
            Set<Cd3var> needed) {
        Cd3var d3var = transform(node0.m_d2var, loc);
        if (!scope.contains(d3var)) {
            needed.add(d3var);
        }
        D3Evar node = new D3Evar(d3var);
        Cd3exp d3exp = new Cd3exp(loc, node);
        return d3exp;
    }


    private Cd3exp transform(D2Elist node0, Cloc_t loc, Set<Cd3var> scope,
            Set<Cd3var> needed) {
        List<Cd3exp> d3es = new ArrayList<Cd3exp>();
        int i = node0.m_npf;
        if (i < 0) {
            i = 0;
        }
        ListIterator<Cd2exp> iter = node0.m_d2es.listIterator(i);
        while (iter.hasNext()) {
            Cd3exp d3e = transform(iter.next(), scope, needed);
            d3es.add(d3e);
        }
        
        D3Etup node = new D3Etup(1, d3es);
        
        Cd3exp d3exp = new Cd3exp(loc, node);
        return d3exp;
    }


    private Cd3exp transform(D2Etup node0, Cloc_t loc, Set<Cd3var> scope,
            Set<Cd3var> needed) {
        List<Cd3exp> d3es = new ArrayList<Cd3exp>();
        if (node0.m_npf >= 0) {
            throw new Error("Check this scenario at " + loc);
        }
        int i = node0.m_npf;
        if (i < 0) {
            i = 0;
        }
        ListIterator<Cd2exp> iter = node0.m_d2es.listIterator(i);
        while (iter.hasNext()) {
            Cd3exp d3e = transform(iter.next(), scope, needed);
            d3es.add(d3e);
        }
        
        D3Etup node = new D3Etup(node0.m_knd, d3es);
        
        Cd3exp d3exp = new Cd3exp(loc, node);
        return d3exp;
    }


    private Cd3exp transform(D2Esym node0, Cloc_t loc) {
        Cd3sym d3sym = transform(node0.m_d2sym, loc);
        D3Esym node = new D3Esym(d3sym);
        
        Cd3exp d3exp = new Cd3exp(loc, node);
        return d3exp;
    }

    private Cd3sym transform(Cd2sym d2sym, Cloc_t loc) {
        return new Cd3sym(d2sym.m_d2sym_name, d2sym.m_stype);
    }


    private Cd3exp transform(D2Elet node0, Cloc_t loc, Set<Cd3var> scope,
            Set<Cd3var> needed) {
        Set<Cd3var> nscope = new HashSet<Cd3var>(scope);
        
        List<Cd3ecl> d3cs = transform(node0.m_d2cs, nscope, needed);
        Cd3exp body = transform(node0.m_d2e_body, nscope, needed);
        
        D3Elet node = new D3Elet(d3cs, body);
        Cd3exp d3exp = new Cd3exp(loc, node);
        return d3exp;
    }


    private Cd3exp transform(D2Eifopt node0, Cloc_t loc, Set<Cd3var> scope,
            Set<Cd3var> needed) {
        Cd3exp test = transform(node0.m_test, scope, needed);
        Cd3exp athen = transform(node0.m_then, scope, needed);
        Cd3exp aelse = null;
        if (null != node0.m_else) {
            aelse = transform(node0.m_else, scope, needed);
        }
        
        D3Eifopt node = new D3Eifopt(test, athen, aelse);
        Cd3exp d3exp = new Cd3exp(loc, node);
        return d3exp;
    }


    private Cd3exp transform(D2Ecst node0, Cloc_t loc) {
        Cd3cst d3cst = transform(node0.m_d2cst, loc);
        D3Ecst node = new D3Ecst(d3cst);
        
        Cd3exp d3exp = new Cd3exp(loc, node);
        return d3exp;
    }


    private Cd3exp transform(D2Eapplst node0, Set<Cd3var> scope,
            Set<Cd3var> needed, Cloc_t loc) {
        Cd3exp fun = transform(node0.m_d2e_fun, scope, needed);
        
        List<D3EXPARGdyn> argslst = new ArrayList<D3EXPARGdyn>();
        
        for (Id2exparg iargs: node0.m_d2as_arg) {
            if (iargs instanceof D2EXPARGsta) {
                // do nothing
            } else if (iargs instanceof D2EXPARGdyn) {
                D2EXPARGdyn a2rgs = (D2EXPARGdyn)iargs;
                D3EXPARGdyn a3rgs = transform(a2rgs, scope, needed);
                argslst.add(a3rgs);
                
            } else {
                throw new Error(iargs + " is not supported.");
            }
        }
        
        D3Eapplst node = new D3Eapplst(fun, argslst);
        return new Cd3exp(loc, node);
    }


    private D3EXPARGdyn transform(D2EXPARGdyn node0, Set<Cd3var> scope,
            Set<Cd3var> needed) {
        List<Cd3exp> d3es = new ArrayList<Cd3exp>();
        
        int i = 0;
        if (node0.m_i < 0) {
            i = 0;
        } else {
            i = node0.m_i;
        }
        ListIterator<Cd2exp> iter = node0.m_d2expLst.listIterator(i);
        
        while (iter.hasNext()) {
            Cd3exp d3e = transform(iter.next(), scope, needed);
            d3es.add(d3e);    
        }
        
        D3EXPARGdyn dyn = new D3EXPARGdyn(node0.m_loc, d3es);
        
        return dyn;
    }


    private Cd3exp transform(D2EannFunclo node0, Set<Cd3var> needed, Cloc_t loc) {
        Cd3exp d3exp = transform(node0.m_d2exp, null, needed);
        D3ElamDyn d3elam = (D3ElamDyn)(d3exp.m_node);
        if (null != node0.m_funclo) {
            Log.log4j.warn("Use closure information from annoatation @" + loc);
            d3elam.m_funclo = node0.m_funclo;
        } else {
            Log.log4j.warn("no annotation for closure information");
        }
        
        
        return d3exp;
    }


    private Cd3exp transform(D2ElamDyn node0, Cloc_t loc, Set<Cd3var> needed) {
        List<Cp3at> p3ats = transform(node0.m_p2ts);
        Set<Cd3var> paras = collect_stamps(p3ats);
        
        Set<Cd3var> cur_needed = new HashSet<Cd3var>();
        
        Cd3exp body = transform(node0.m_d2exp, paras, cur_needed);

        Ifunclo funclo = null;
        if (cur_needed.isEmpty()) {
            funclo = FUNCLOfun.cInstance;
        } else {
            funclo = new FUNCLOclo(-1);
        }
        
        needed.addAll(cur_needed);
        
        D3ElamDyn node = new D3ElamDyn(node0.m_lin, p3ats, body, funclo, cur_needed);
        
        Cd3exp ret = new Cd3exp(loc, node);
        return ret;
        
    }

    private Set<Cd3var> collect_stamps(List<Cp3at> p3ats) {
        Set<Cd3var> stamps = new HashSet<Cd3var>();
        for (Cp3at p3at: p3ats) {
            collect_stamps(p3at, stamps);
        }
        return stamps;
    }

    private void collect_stamps(Cp3at p3at, Set<Cd3var> stamps) {
        Ip3at_node node0 = p3at.m_node;
        
        if (node0 instanceof P3Tany) {
            return;
        } else if (node0 instanceof P3Tempty) {
            return;
        } else if (node0 instanceof P3Trec) {
            collect_stamps((P3Trec)node0, stamps);
            return;
        } else if (node0 instanceof P3Tvar) {
            stamps.add(((P3Tvar)node0).m_var);
        } else {
            throw new Error(node0 + " is not supported.");
        }
    }


    private void collect_stamps(P3Trec node0, Set<Cd3var> stamps) {
        for (LABP3ATnorm patitem: node0.m_labpats) {
            collect_stamps(patitem.m_pat, stamps);
        }
    }


    /*
     * pattern may contain constant.
     * E.g. val cons (x (* x is defined previously *), 1) = v
     */
    private List<Cp3at> transform(List<Cp2at> p2ts) {
        List<Cp3at> p3ats = new ArrayList<Cp3at>();
        for (Cp2at p2at: p2ts) {
            Cp3at p3at = transform(p2at);
            p3ats.add(p3at);
        }
        return p3ats;
    }


    private Cp3at transform(Cp2at p2at) {
        Cloc_t loc = p2at.p2at_loc;
        Ip2at_node node0 = p2at.p2at_node;
        
        if (node0 instanceof P2Tann) {
            return transform((P2Tann)node0);
        } else if (node0 instanceof P2Tany) {
            return transform((P2Tany)node0, loc);
        } else if (node0 instanceof P2Tcon) {
            throw new Error("not supported yet");
        } else if (node0 instanceof P2Tempty) {
            return transform((P2Tempty)node0, loc);
        } else if (node0 instanceof P2Tignored) {
            throw new Error("Check this.");
        } else if (node0 instanceof P2Tpat) {
            return transform(((P2Tpat)node0).m_p2at);
        } else if (node0 instanceof P2Trec) {
            return transform(((P2Trec)node0), loc);
        } else if (node0 instanceof P2Tvar) {
            return transform((P2Tvar)node0, loc);
        } else {
            throw new Error(node0 + " is not supported.");
        }
    }

    private Cp3at transform(P2Trec node0, Cloc_t loc) {
        int i = 0;
        if (node0.m_npf > 0) {
            i = node0.m_npf;            
        }
        
        List<LABP3ATnorm> labitems = new ArrayList<LABP3ATnorm>();
        ListIterator<Ilabp2at> iter = node0.m_labpats.listIterator(i);
        while (iter.hasNext()) {
            // todo: check the type to see whether it's a proof
            LABP3ATnorm labitem = transform(iter.next());
            labitems.add(labitem);
        }
        
        P3Trec node = new P3Trec(labitems, node0.m_knd);
        return new Cp3at(loc, node);        
        
    }


    private LABP3ATnorm transform(Ilabp2at node0) {
        if (node0 instanceof LABP2ATnorm) {
            LABP2ATnorm p2atnorm = (LABP2ATnorm) node0;
            Cp3at p3at = transform(p2atnorm.m_pat);
            LABP3ATnorm p3atnorm = new LABP3ATnorm(p2atnorm.m_lab, p3at);
            return p3atnorm;
        } else if (node0 instanceof LABP2ATomit) {
            throw new Error(node0 + " is not supported.");
        } else {
            throw new Error(node0 + " is not supported.");
        }
    }


    private Cp3at transform(P2Tvar node0, Cloc_t loc) {
        Cd3var d3var = transform(node0.m_var, loc);
        P3Tvar node = new P3Tvar(d3var);
        return new Cp3at(loc, node);
    }


    private Cp3at transform(P2Tempty node0, Cloc_t loc) {
        return new Cp3at(loc, P3Tempty.cInstance);
    }


    private Cp3at transform(P2Tany node0, Cloc_t loc) {
        return new Cp3at(loc, P3Tany.cInstance);
    }


    private Cp3at transform(P2Tann node0) {
        return transform(node0.m_p2t);
    }


    private Cd3exp transform(D2ElamMet node, Cloc_t loc, Set<Cd3var> needed) {
        return transform(node.m_d2exp, null, needed);
    }


    private Cd3exp transform(D2ElamSta node, Cloc_t loc, Set<Cd3var> needed) {
        return transform(node.m_d2exp, null, needed);
    }


    private Cd3var transform(Cd2var d2var, Cloc_t loc) {
        Cd3var d3var = m_varMap.get(d2var.m_stamp);
        if (null != d3var) {
            return d3var;
        } else {
            d3var = new Cd3var(d2var.m_sym, d2var.m_stamp, d2var.getSType());
            m_varMap.put(d2var.m_stamp, d3var);
            return d3var;
        }
    }


    private Cd3ecl transform(Cloc_t loc, D2Cextcode node0) {
        D3Cextcode node = new D3Cextcode(node0.m_code);
        return new Cd3ecl(loc, node);
    }


    private Cd3ecl transform(Cloc_t loc, D2Cdcstdecs node0, Set<Cd3var> scope) {
        switch (node0.m_dck) {
        case DCK_fun:  // extern fun
        case DCK_val:  // extern val
        {
            List<Cd3cst> d3csts = new ArrayList<Cd3cst>();
            for (Cd2cst d2cst: node0.m_d2cst) {
                Cd3cst d3cst = transform(d2cst, loc);
                d3csts.add(d3cst);
                // scope.add(d3cst.m_stamp);  07/21/2014 Do not take into consideration of cst.
            }
            
            D3Cdcstdecs node = new D3Cdcstdecs(node0.m_knd, node0.m_dck, d3csts);
            
            return new Cd3ecl(loc, node);
        }

        case DCK_praxi:  // praxi
        case DCK_prfun: // extern prfun
        case DCK_prval:  // extern prval
        case DCK_castfn:  // extern castfn
            return null;
        default:
            throw new Error(node0.m_dck + " is not supported.");
        }
    }


    private Cd3cst transform(Cd2cst d2cst, Cloc_t loc) {
        Cd3cst d3cst = m_cstMap.get(d2cst.m_stamp);
        if (null != d3cst) {
            return d3cst;
        } else {
            d3cst = new Cd3cst(d2cst.m_stamp, d2cst.m_symbol, d2cst.m_stype);
            m_cstMap.put(d2cst.m_stamp, d3cst);
            return d3cst;
        }
    }


}