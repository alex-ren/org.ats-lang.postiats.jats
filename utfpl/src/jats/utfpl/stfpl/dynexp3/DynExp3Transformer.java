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
import jats.utfpl.stfpl.dynexp.D2Cinclude;
import jats.utfpl.stfpl.dynexp.D2Clocal;
import jats.utfpl.stfpl.dynexp.D2Cnone;
import jats.utfpl.stfpl.dynexp.D2Cstacsts;
import jats.utfpl.stfpl.dynexp.D2Cstaload;
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
import jats.utfpl.stfpl.stype.AuxSType;
import jats.utfpl.stfpl.stype.FunType; 
import jats.utfpl.stfpl.stype.ISType;
import jats.utfpl.stfpl.stype.PolyParaType;
import jats.utfpl.stfpl.stype.PolyType;
import jats.utfpl.stfpl.stype.PropType;
import jats.utfpl.stfpl.stype.RecType;
import jats.utfpl.stfpl.stype.TypeCheckResult;
import jats.utfpl.stfpl.stype.VoidType;
import jats.utfpl.utils.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;

import org.stringtemplate.v4.STGroup;

/*
 * Some explanation about needed and scope.
 * scope contains the current variables within scope.
 * needed is used to accumulate all the variables needed
 * but not in the current scope. Such variables are expected
 * to come from the environment.
 * 
 */

public class DynExp3Transformer {
    
    private Map<Cstamp, Cd3cst> m_cstMap;
    private Map<Cstamp, Cd3var> m_varMap;
    private List<Cd2ecl> m_d2ecs;
    
    public DynExp3Transformer(List<Cd2ecl> d2ecs) {
        m_cstMap = new HashMap<Cstamp, Cd3cst>();
        m_varMap = new HashMap<Cstamp, Cd3var>();
        m_d2ecs = d2ecs;

    }
    
    /*
     * Remove proof.
     */
    public ProgramStfpl3 transform() {

        Set<Cd3var> scope = new HashSet<Cd3var>();
        Set<Cd3var> needed = new HashSet<Cd3var>();
        
        List<Cd3ecl> decs = transform(m_d2ecs, scope, needed);
        return new ProgramStfpl3(decs);
    }
    
    /*
     * Purpose: 
     * 1. Remove proof
     * 1) prval (...) = ...
     * 2) val (pf | ...) = foo ()
     * 
     * 2. Handle closure
     * scope will be modified.
     * cur_scope:
     * needed: Symbols needed but not in cur_scope
     * 
     * 3. Remove polymorphism, simplify AST.
     * There is no lamSta any more. But PolyType still exists.
     * 
     */
    private List<Cd3ecl> transform(List<Cd2ecl> d2ecs, Set<Cd3var> scope, Set<Cd3var> needed) {
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
            return transform((D2Cfundecs)node0, d2ec.d2ecl_loc, scope, needed);
        } else if (node0 instanceof D2Cignored) {
            Log.log4j.warn("D2Cignored encountered in generating dynexp3.");
            return null;
        } else if (node0 instanceof D2Cimpdec) {
            return transform((D2Cimpdec)node0, d2ec.d2ecl_loc, scope, needed);
        } else if (node0 instanceof D2Cstacsts) {
            return transform((D2Cstacsts)node0, d2ec.d2ecl_loc);
        } else if (node0 instanceof D2Cvaldecs) {
            return transform((D2Cvaldecs)node0, d2ec.d2ecl_loc, scope, needed);    
        } else if (node0 instanceof D2Clocal) {
            return transform((D2Clocal)node0, d2ec.d2ecl_loc, scope, needed);                
        } else if (node0 instanceof D2Cdatdecs) {
            Log.log4j.warn("D2Cdatdecs encountered in generating dynexp3.");
            return null;
        } else if (node0 instanceof D2Cinclude) {
            Log.log4j.warn("D2Cinclude encountered in generating dynexp3.");
            return null;
        } else if (node0 instanceof D2Cstaload) {
            Log.log4j.warn("D2Cstaload encountered in generating dynexp3.");
            return null;
        } else if (node0 instanceof D2Cnone) {
            Log.log4j.warn("D2Cnone encountered in generating dynexp3.");
            return null;            
        } else {
            throw new Error(node0 + " is not supported.");
        }
    }

    private Cd3ecl transform(D2Clocal node0, Cloc_t loc,
			Set<Cd3var> scope, Set<Cd3var> needed) {
        List<Cd3ecl> d3cs = transform(node0.m_d2cs, scope, needed);
        
        D3Clocal node = new D3Clocal(d3cs);
        Cd3ecl d3ecl = new Cd3ecl(loc, node);
        
        return d3ecl;
        
	}

	private Cd3ecl transform(D2Cvaldecs node0, Cloc_t loc, 
            Set<Cd3var> scope, Set<Cd3var> needed) {
        if (Evalkind.VK_prval == node0.m_knd) {
            D3Cvaldecs node = transform_prval(node0, loc, scope, needed);
            if (null != node) {
                Cd3ecl d3ecl = new Cd3ecl(loc, node);
                return d3ecl;
            } else {
                return null;
            }
        } else {
            D3Cvaldecs node = transform_val(node0, loc, scope, needed);
            Cd3ecl d3ecl = new Cd3ecl(loc, node);
            return d3ecl;
        }

    }
    
    private D3Cvaldecs transform_prval(D2Cvaldecs node0, Cloc_t loc,
            Set<Cd3var> scope, Set<Cd3var> needed) {
        List<Cv3aldec> v3ds = new ArrayList<Cv3aldec>();
        
        for (Cv2aldec v2d: node0.m_v2ds) {
            Cv3aldec v3d = transform_prval(v2d, scope, needed);
            if (null != v3d) {
            	v3ds.add(v3d);
            }
        }
        
        if (v3ds.size() > 0) {
        	D3Cvaldecs node = new D3Cvaldecs(node0.m_knd, v3ds);
    		return node;
        } else {
        	return null;
        }
	
    }
    
    private Cv3aldec transform_prval(Cv2aldec v2d, Set<Cd3var> scope,
            Set<Cd3var> needed) {
    	Cp2at p2at = v2d.v2aldec_pat;
    	Cd2exp d2e = v2d.v2aldec_def;
    	Cloc_t loc = v2d.v2aldec_loc;
    	
        Cv3aldec v3d = transform_prval_pat_exp(0, loc, p2at, d2e, scope, needed);
        return v3d;
    }
    

    
    private Cv3aldec transform_prval_pat_exp(int level, Cloc_t loc, Cp2at p2at, Cd2exp d2exp, Set<Cd3var> scope, Set<Cd3var> needed) {
        Ip2at_node pnode = p2at.p2at_node;
        Cloc_t loc_pat = p2at.p2at_loc;
        if (pnode instanceof P2Tann) {
            return transform_prval_pat_exp(level, loc, ((P2Tann) pnode).m_p2t, d2exp, scope, needed);
        } else if (pnode instanceof P2Tany) { // prval _ = mc_set
            // Nobody is gonna use the return value. 
            // We keep this only if RHS is an app of mc-related function.
            if (isMCApp(d2exp)) {
                Cd3exp d3exp = transform(d2exp, scope, needed, 
                        new ArrayList<List<PolyParaType>>());
                Cp3at p3at = transform(level, p2at, scope);
                Cv3aldec v3d = new Cv3aldec(loc, p3at, d3exp);
                return v3d;
            } else {
                // prval _ = gen_proof () will be erased. This is good.
                // prval _ = (x | mc_xx ()) will be erased. This is not so good. But programmers'd
                // better not write program like this.
                return null;
            }
        } else if (pnode instanceof P2Tcon) {
            throw new Error("not supported yet");
        } else if (pnode instanceof P2Tempty) {
            // Nobody is gonna use the return value. 
            // We keep this only if RHS is an app of mc-related function.
            if (isMCApp(d2exp)) {
                Cd3exp d3exp = transform(d2exp, scope, needed, 
                        new ArrayList<List<PolyParaType>>());
                Cp3at p3at = transform(level, p2at, scope);
                Cv3aldec v3d = new Cv3aldec(loc, p3at, d3exp);
                return v3d;
            } else {
                throw new Error("Check this. I think this should not happen. loc is " + loc);
            }
        } else if (pnode instanceof P2Tignored) {
            throw new Error("Check this.");
        } else if (pnode instanceof P2Tpat) {
            return transform_prval_pat_exp(level, loc , ((P2Tpat)pnode).m_p2at, d2exp, scope, needed);
        } else if (pnode instanceof P2Trec) {
            return transform_prval_pat_exp(level, loc, loc_pat, (P2Trec)pnode, d2exp, scope, needed);
        } else if (pnode instanceof P2Tvar) {
            P2Tvar p2t = (P2Tvar)pnode;

            if (p2t.m_var.getSType() instanceof PropType) {
                // prval x = gen_proof () will be erased.
                return null;
            } else if (VoidType.cInstance == p2t.m_var.getSType()) {
                if (isMCApp(d2exp)) {
                    // prval x:void = mc_xx () is kept.
                    Cp3at p3at = transform(level, p2at, scope);
                    
                    Cd3exp d3exp = transform(d2exp, scope, needed, 
                            new ArrayList<List<PolyParaType>>());
                    
                    Cv3aldec v3d = new Cv3aldec(loc, p3at, d3exp);
                    return v3d;
                } else {
                    return null;
                }
            } else {  // prval x = 3 will be kept.
                Cp3at p3at = transform(level, p2at, scope);
                
                Cd3exp d3exp = transform(d2exp, scope, needed, 
                        new ArrayList<List<PolyParaType>>());
                
                Cv3aldec v3d = new Cv3aldec(loc, p3at, d3exp);
                return v3d;                
                
            }
        } else {
            throw new Error(pnode + " is not supported.");
        }
    }
    
    private Cv3aldec transform_prval_pat_exp(
            int level, 
            Cloc_t loc, 
            Cloc_t loc_pat,
            P2Trec pnode, 
            Cd2exp d2exp, 
            Set<Cd3var> scope, 
            Set<Cd3var> needed) {
        int i = 0;
        if (pnode.m_npf > 0) {
            if (level > 0) {
                throw new Error("This should not happen.");
            }
            i = pnode.m_npf;            
        }
        
        List<LABP3ATnorm> labitems = new ArrayList<LABP3ATnorm>();
        // skip all the proofs
        ListIterator<Ilabp2at> iter = pnode.m_labpats.listIterator(i);
        while (iter.hasNext()) {
            LABP3ATnorm labitem = transform(1, iter.next(), scope);
            labitems.add(labitem);
        }
        
        if (labitems.isEmpty()) {
            P3Tempty node = new P3Tempty(VoidType.cInstance);
            Cp3at p3at = new Cp3at(loc_pat, node);        
            Cd3exp d3exp = transform(d2exp, scope, needed, new ArrayList<List<PolyParaType>>());
            
            Cv3aldec v3d = new Cv3aldec(loc, p3at, d3exp);
            return v3d;
        } if (labitems.size() == 1 && !pnode.isBoxed() && pnode.isTuple()) {
            // flat tuple with one element
            // degrade into the element
            Cp3at p3at = labitems.get(0).m_pat;
            Cd3exp d3exp = transform(d2exp, scope, needed, new ArrayList<List<PolyParaType>>());
            Cv3aldec v3d = new Cv3aldec(loc, p3at, d3exp);
            return v3d;
            
        } else {
            P3Trec node = new P3Trec(labitems, pnode.m_knd);
            Cp3at p3at = new Cp3at(loc_pat, node);        
            Cd3exp d3exp = transform(d2exp, scope, needed, new ArrayList<List<PolyParaType>>());
            
            Cv3aldec v3d = new Cv3aldec(loc, p3at, d3exp);
            return v3d;
        }

    }

    
    private boolean isMCApp(Cd2exp d2exp) {
        Id2exp_node node = d2exp.d2exp_node;
        if (node instanceof D2Eapplst) {
            Cd2exp fun_exp = ((D2Eapplst)node).m_d2e_fun;
            Id2exp_node fun_node = fun_exp.d2exp_node;
            if (fun_node instanceof D2Ecst) {
                Cd2cst d2cst = ((D2Ecst)fun_node).m_d2cst;
                return d2cst.m_symbol.isMC();
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
	private D3Cvaldecs transform_val(D2Cvaldecs node0, Cloc_t loc,
            Set<Cd3var> scope, Set<Cd3var> needed) {
        List<Cv3aldec> v3ds = new ArrayList<Cv3aldec>();
        
        for (Cv2aldec v2d: node0.m_v2ds) {
            Cv3aldec v3d = transform_val(v2d, scope, needed);
            v3ds.add(v3d);
        }
		D3Cvaldecs node = new D3Cvaldecs(node0.m_knd, v3ds);
		return node;
    }

    private Cv3aldec transform_val(Cv2aldec v2d, Set<Cd3var> scope,
            Set<Cd3var> needed) {
        Cp3at p3at = transform(0, v2d.v2aldec_pat, scope);
        Cd3exp d3exp = transform(v2d.v2aldec_def, scope, needed, new ArrayList<List<PolyParaType>>());
        
        Cv3aldec v3d = new Cv3aldec(v2d.v2aldec_loc, p3at, d3exp);
        return v3d;
    }


    private Cp3at transform(int level, Cp2at p2at, Set<Cd3var> scope) {
        Cloc_t loc = p2at.p2at_loc;
        Ip2at_node node0 = p2at.p2at_node;
        
        if (node0 instanceof P2Tann) {
            return transform(level, (P2Tann)node0, scope);
        } else if (node0 instanceof P2Tany) {
            return transform((P2Tany)node0, loc);
        } else if (node0 instanceof P2Tcon) {
            throw new Error("not supported yet");
        } else if (node0 instanceof P2Tempty) {
            return transform((P2Tempty)node0, loc);
        } else if (node0 instanceof P2Tignored) {
            throw new Error("Check this.");
        } else if (node0 instanceof P2Tpat) {
            return transform(level, ((P2Tpat)node0).m_p2at, scope);
        } else if (node0 instanceof P2Trec) {
            return transform(level, ((P2Trec)node0), loc, scope);
        } else if (node0 instanceof P2Tvar) {
            return transform((P2Tvar)node0, loc, scope);
        } else {
            throw new Error(node0 + " is not supported.");
        }
    }
    

    private Cp3at transform(P2Tvar node0, Cloc_t loc, Set<Cd3var> scope) {
    	if (node0.m_var.getSType() instanceof PropType) {
    		return null;
    	}
        Cd3var d3var = transform(node0.m_var, loc);
        scope.add(d3var);
        P3Tvar node = new P3Tvar(d3var);
        return new Cp3at(loc, node);
    }


    private Cp3at transform(P2Tempty node0, Cloc_t loc) {
        return new Cp3at(loc, new P3Tempty(node0.getSType()));
    }


    private Cp3at transform(P2Tany node0, Cloc_t loc) {
        return new Cp3at(loc, new P3Tany(node0.getSType()));
    }


    private Cp3at transform(int level, P2Tann node0, Set<Cd3var> scope) {
        return transform(level, node0.m_p2t, scope);
    }

    private Cp3at transform(int level, P2Trec node0, Cloc_t loc, Set<Cd3var> scope) {
        int i = 0;
        if (node0.m_npf > 0) {
            if (level > 0) {
                throw new Error("This should not happen.");
            }
            i = node0.m_npf;            
        }
        
        List<LABP3ATnorm> labitems = new ArrayList<LABP3ATnorm>();
        // skip all the proofs
        ListIterator<Ilabp2at> iter = node0.m_labpats.listIterator(i);
        while (iter.hasNext()) {
            LABP3ATnorm labitem = transform(1, iter.next(), scope);
            labitems.add(labitem);
        }
        
        if (labitems.isEmpty()) {
            P3Tempty node = new P3Tempty(VoidType.cInstance);
            Cp3at p3at = new Cp3at(loc, node);
            return p3at;
        } if (labitems.size() == 1 && !node0.isBoxed()) {
            return labitems.get(0).m_pat;
        } else {
            P3Trec node = new P3Trec(labitems, node0.m_knd);
            Cp3at p3at = new Cp3at(loc, node);
            return p3at;
        }
    }


    private LABP3ATnorm transform(int level, Ilabp2at node0, Set<Cd3var> scope) {
        if (node0 instanceof LABP2ATnorm) {
            LABP2ATnorm p2atnorm = (LABP2ATnorm) node0;
            Cp3at p3at = transform(level, p2atnorm.m_pat, scope);
            LABP3ATnorm p3atnorm = new LABP3ATnorm(p2atnorm.m_lab, p3at);
            return p3atnorm;
        } else if (node0 instanceof LABP2ATomit) {
            throw new Error(node0 + " is not supported.");
        } else {
            throw new Error(node0 + " is not supported.");
        }
    }
    
    private Cd3ecl transform(D2Cstacsts node0, Cloc_t loc) {
        D3Cstacsts node = new D3Cstacsts(node0.m_s2csts);
        Cd3ecl d3ecl = new Cd3ecl(loc, node);
        return d3ecl;
    }


    private Cd3ecl transform(D2Cimpdec node0, Cloc_t loc, 
            Set<Cd3var> scope, Set<Cd3var> needed) {
        Ci3mpdec i3mpdec = transform(node0.m_i2mpdec, scope, needed);
        D3Cimpdec node = new D3Cimpdec(node0.m_knd, i3mpdec);
        
        Cd3ecl d3ecl = new Cd3ecl(loc, node);
        return d3ecl;
    }


    private Ci3mpdec transform(Ci2mpdec i2mpdec, Set<Cd3var> scope,
            Set<Cd3var> needed) {
        Cd3cst d3cst = transform(i2mpdec.i2mpdec_cst, i2mpdec.i2mpdec_locid);
        String env_name = d3cst.toString() + "env";
        
        Set<Cd3var> total_needed = new HashSet<Cd3var>();
        
        Cd3exp d3exp = transform(i2mpdec.i2mpdec_def, scope, total_needed, new ArrayList<List<PolyParaType>>());
        
        Ci3mpdec i3mpdec = new Ci3mpdec(i2mpdec.i2mpdec_loc, i2mpdec.i2mpdec_locid, d3cst, d3exp, env_name);
        return i3mpdec;        
    }


    /*
     * scope: will be updated
     */
    private Cd3ecl transform(D2Cfundecs node0, Cloc_t loc, Set<Cd3var> scope, Set<Cd3var> needed) {
        switch (node0.m_knd) {
        case FK_prfn:
        case FK_prfun:
        case FK_fn: // non-recursive function 
        case FK_fnx: // tail-recursive 
        case FK_fun: // recursive
        {

            Cf2undec f2undec0 = node0.m_f2ds.get(0);
            boolean isClosure = AuxSType.isClosure(f2undec0.f2undec_var.getSType());

            for (Cf2undec f2undec: node0.m_f2ds) {
            	if (AuxSType.isClosure(f2undec.f2undec_var.getSType()) != isClosure) {
            		throw new Error("not allowed");
            	}
            }
            
            List<Cf3undec> f3uns = new ArrayList<Cf3undec>();
            Set<Cd3var> total_needed = new HashSet<Cd3var>();
            
            for (Cf2undec f2un: node0.m_f2ds) {
                if (   Efunkind.FK_fn == node0.m_knd 
                    || Efunkind.FK_fnx == node0.m_knd
                    || Efunkind.FK_fun == node0.m_knd 
                    || f2un.f2undec_var.m_sym.isMC()) {

//                    System.out.println("========= trans fundec " + f2un.f2undec_var);
                    
                    Cd3var name = transform(f2un.f2undec_var, f2un.f2undec_loc);
                    
                    Set<Cd3var> cur_scope = new HashSet<Cd3var>();
                    Set<Cd3var> cur_needed = new HashSet<Cd3var>();
                    
                    Cd3exp d3exp = transform(f2un.f2undec_def, cur_scope, cur_needed, new ArrayList<List<PolyParaType>>());
                    // Since cur_scope is empty, cur_needed is the env for the function.
    
                    D3ElamDyn d3elam = (D3ElamDyn)(d3exp.m_node);
                    total_needed.addAll(d3elam.m_env);
                    
                    // Just curious whether this would be triggered. 
                    TypeCheckResult res = name.m_stype.match(d3elam.getType());
                    if (!res.isGood()) {
                        STGroup stg = AuxSType.cStg;
                        System.out.println("left is " + name.m_stype.toSTStfpl3(stg).render());
                        System.out.println("right is " + d3elam.getType().toSTStfpl3(stg).render());

                        throw new Error(res.getMsg());
                    }
                    
                    Cf3undec f3un = new Cf3undec(f2un.f2undec_loc, name, 
                    		d3elam.m_lin, d3elam.m_p3ts, d3elam.m_d3exp, d3elam.getType());
                    f3uns.add(f3un);
                }
            }
            

            for (Cf3undec f3un: f3uns) {
                scope.add(f3un.m_var);
                total_needed.remove(f3un.m_var);  // due to mutually recursive, we
                                                    // may have put sibling closures into "needed".
            }
            
            Set<Cd3var> temp_needed = new HashSet<Cd3var>(total_needed);
            temp_needed.removeAll(scope);
            needed.addAll(temp_needed);
            
            if (f3uns.isEmpty()) {
                return null;
            } else {
                D3Cfundecs node = new D3Cfundecs(node0.m_knd, f3uns, total_needed, isClosure);
                return new Cd3ecl(loc, node);
            }

        }
        

        case FK_praxi:
        case FK_castfn:
            return null;
        default:
            throw new Error(node0.m_knd + " is not supported.");
        }
    }
    
    /*
     * scope: names can be used. This cannot be changed since expression doesn't
     * provide new names.
     * 
     * accu: This parameter is for assigning polymorphic type to D3ElamDyn.
     */
    private Cd3exp transform(Cd2exp d2exp, Set<Cd3var> scope, Set<Cd3var> needed, List<List<PolyParaType>> accu) {
        Id2exp_node node0 = d2exp.d2exp_node;
        Cloc_t loc = d2exp.d2exp_loc;
        if (node0 instanceof D2EannFunclo) {
            if (accu.size() != 0) {
                throw new Error("Check this.");
            }
            return transform(((D2EannFunclo)node0).m_d2exp, scope, needed, accu);
        } else if (node0 instanceof D2EannSeff) {
            if (accu.size() != 0) {
                throw new Error("Check this.");
            }
            return transform(((D2EannSeff)node0).m_d2exp, scope, needed, accu);
        } else if (node0 instanceof D2EannType) {
            if (accu.size() != 0) {
                throw new Error("Check this.");
            }
            return transform(((D2EannType)node0).m_d2exp, scope, needed, accu);
        } else if (node0 instanceof D2Eapplst) {
            if (accu.size() != 0) {
                throw new Error("Check this.");
            }
            return transform((D2Eapplst)node0, scope, needed, loc);
        } else if (node0 instanceof D2Ecst) {
            if (accu.size() != 0) {
                throw new Error("Check this.");
            }
            return transform((D2Ecst)node0, loc);
        } else if (node0 instanceof D2Eempty) {
            if (accu.size() != 0) {
                throw new Error("Check this.");
            }
            return new Cd3exp(loc, D3Eempty.cInstance);
        } else if (node0 instanceof D2Eexp) {
            if (accu.size() != 0) {
                throw new Error("Check this.");
            }
            return transform(((D2Eexp)node0).m_d2exp, scope, needed, accu);
        } else if (node0 instanceof D2Ef0loat) {
            if (accu.size() != 0) {
                throw new Error("Check this.");
            }
            D2Ef0loat node = (D2Ef0loat)node0;
            return new Cd3exp(loc, new D3Ef0loat(node.m_f0loat, node.getSType()));
        } else if (node0 instanceof D2Ei0nt) {
            if (accu.size() != 0) {
                throw new Error("Check this.");
            }
            D2Ei0nt node = (D2Ei0nt)node0;
            return new Cd3exp(loc, new D3Ei0nt(node.m_i0nt, node.getSType()));
        } else if (node0 instanceof D2Eifopt) {
            if (accu.size() != 0) {
                throw new Error("Check this.");
            }
            return transform((D2Eifopt)node0, loc, scope, needed);
        } else if (node0 instanceof D2Eignored) {
            throw new Error("Check this");
        } else if (node0 instanceof D2ElamDyn) {
            return transform((D2ElamDyn)node0, loc, scope, needed, accu);
        } else if (node0 instanceof D2ElamMet) {
            return transform((D2ElamMet)node0, loc, needed, accu);
        } else if (node0 instanceof D2ElamSta) {
            if (null == accu) {
                accu = new ArrayList<List<PolyParaType>>();
            }
            PolyType poly_type = (PolyType)((D2ElamSta)node0).getSType();
            accu.add(poly_type.m_paras);
            return transform((D2ElamSta)node0, loc, needed, accu);
        } else if (node0 instanceof D2Elet) {
            return transform((D2Elet)node0, loc, scope, needed);
        } else if (node0 instanceof D2Es0tring) {
            if (accu.size() != 0) {
                throw new Error("Check this.");
            }
            D2Es0tring node = (D2Es0tring)node0;
            return new Cd3exp(loc, new D3Es0tring(node.m_s0tring, node.getSType()));
        } else if (node0 instanceof D2Esym) {
            if (accu.size() != 0) {
                throw new Error("Check this.");
            }
            return transform((D2Esym)node0, loc);
        } else if (node0 instanceof D2Etup) {
            if (accu.size() != 0) {
                throw new Error("Check this.");
            }
            return transform((D2Etup)node0, loc, scope, needed);
        } else if (node0 instanceof D2Elist) {
            if (accu.size() != 0) {
                throw new Error("Check this.");
            }
            return transform((D2Elist)node0, loc, scope, needed);            
        } else if (node0 instanceof D2Evar) {
            if (accu.size() != 0) {
                throw new Error("Check this.");
            }
            return transform((D2Evar)node0, loc, scope, needed);
        } else {
            throw new Error(d2exp + " is not supported");
        }
    }

    private Cd3exp transform(D2Evar node0, Cloc_t loc, Set<Cd3var> scope,
            Set<Cd3var> needed) {
    	if (node0.m_d2var.getSType() instanceof PropType) {
    		return null;
    	}
        Cd3var d3var = transform(node0.m_d2var, loc);
        
        // not a normal function // treat no annotation as closure
        ISType type = node0.m_d2var.getSType();
        
        if (!scope.contains(d3var)) {
            if (type instanceof FunType || type instanceof PolyType) {
                if (AuxSType.isClosure(node0.m_d2var.getSType())) {
                    // global function is always accessible
                    needed.add(d3var);
                }
            } else {
                needed.add(d3var);
            }
        }


        D3Evar node = new D3Evar(d3var);
        Cd3exp d3exp = new Cd3exp(loc, node);
        return d3exp;
    }


    /*
     * Currently I treat D2Elist as a tuple.
     */
    private Cd3exp transform(D2Elist node0, Cloc_t loc, Set<Cd3var> scope,
            Set<Cd3var> needed) {
//        throw new Error("D2Elist should be handled previously.");
        List<Cd3exp> d3es = new ArrayList<Cd3exp>();
        int i = node0.m_npf;
        if (i < 0) {
            i = 0;
        }
        ListIterator<Cd2exp> iter = node0.m_d2es.listIterator(i);
        while (iter.hasNext()) {
            Cd3exp d3e = transform(iter.next(), scope, needed, new ArrayList<List<PolyParaType>>());
            d3es.add(d3e);
        }
        
        if (d3es.size() == 1 && !node0.getSType().isBoxed()) {
            return d3es.get(0);
        } else {
            ISType recType = node0.getSType().removeProof();
            if (!(recType instanceof RecType)) {
                throw new Error("Check this.");
            }

            D3Etup node = new D3Etup(node0.getSType().getKind(), d3es, (RecType)recType);
            
            Cd3exp d3exp = new Cd3exp(loc, node);
            return d3exp;
        }
    }


    private Cd3exp transform(D2Etup node0, Cloc_t loc, Set<Cd3var> scope,
            Set<Cd3var> needed) {
        List<Cd3exp> d3es = new ArrayList<Cd3exp>();
        int i = node0.m_npf;
        if (i < 0) {
            i = 0;
        }
        ListIterator<Cd2exp> iter = node0.m_d2es.listIterator(i);
        while (iter.hasNext()) {
            Cd3exp d3e = transform(iter.next(), scope, needed, new ArrayList<List<PolyParaType>>());
            d3es.add(d3e);
        }
        
        // flat tuple with one element
        if (d3es.size() == 1 && !node0.isBoxed()) {
            return d3es.get(0);
        } else {
            ISType recType = node0.getSType().removeProof();
            if (!(recType instanceof RecType)) {
                throw new Error("Should not happen.");
            }

            D3Etup node = new D3Etup(node0.m_knd, d3es, (RecType)recType);
            
            Cd3exp d3exp = new Cd3exp(loc, node);
            return d3exp;
        }

    }


    private Cd3exp transform(D2Esym node0, Cloc_t loc) {
        Cd3sym d3sym = transform(node0.m_d2sym, loc);
        D3Esym node = new D3Esym(d3sym);
        
        Cd3exp d3exp = new Cd3exp(loc, node);
        return d3exp;
    }

    private Cd3sym transform(Cd2sym d2sym, Cloc_t loc) {
//    	System.out.println("sym is " + d2sym.m_d2sym_name.toString() + " addr is " + d2sym);
        ISType type = d2sym.getSType().removeProof();
        return new Cd3sym(d2sym.m_d2sym_name, type);
    }


    private Cd3exp transform(D2Elet node0, Cloc_t loc, Set<Cd3var> scope,
            Set<Cd3var> needed) {
        Set<Cd3var> nscope = new HashSet<Cd3var>(scope);
        
        List<Cd3ecl> d3cs = transform(node0.m_d2cs, nscope, needed);
        Cd3exp body = transform(node0.m_d2e_body, nscope, needed, new ArrayList<List<PolyParaType>>());
        
        D3Elet node = new D3Elet(d3cs, body);
        Cd3exp d3exp = new Cd3exp(loc, node);
        return d3exp;
    }


    private Cd3exp transform(D2Eifopt node0, Cloc_t loc, Set<Cd3var> scope,
            Set<Cd3var> needed) {
        Cd3exp test = transform(node0.m_test, scope, needed, new ArrayList<List<PolyParaType>>());
        Cd3exp athen = transform(node0.m_then, scope, needed, new ArrayList<List<PolyParaType>>());
        Cd3exp aelse = null;
        if (null != node0.m_else) {
            aelse = transform(node0.m_else, scope, needed, new ArrayList<List<PolyParaType>>());
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
        Cd3exp fun = transform(node0.m_d2e_fun, scope, needed, new ArrayList<List<PolyParaType>>());
        
        List<D3EXPARGdyn> argslst = new ArrayList<D3EXPARGdyn>();
        List<ISType> inner_types = new ArrayList<ISType>();
        
        ListIterator<ISType> iter = node0.getInnerSType().listIterator();
        for (Id2exparg iargs: node0.m_d2as_arg) {
            if (iargs instanceof D2EXPARGsta) {
                iter.next();  // skip this one
            } else if (iargs instanceof D2EXPARGdyn) {
                D2EXPARGdyn a2rgs = (D2EXPARGdyn)iargs;
                D3EXPARGdyn a3rgs = transform(a2rgs, scope, needed);
                argslst.add(a3rgs);
                inner_types.add(iter.next().removeProof());
            } else {
                throw new Error(iargs + " is not supported.");
            }
        }
        
        D3Eapplst node = new D3Eapplst(fun, argslst, inner_types);
        return new Cd3exp(loc, node);
    }


    private D3EXPARGdyn transform(D2EXPARGdyn node0, Set<Cd3var> scope,
            Set<Cd3var> needed) {
        List<Cd3exp> d3es = new ArrayList<Cd3exp>();
        
        int i = 0;
        if (node0.m_npf < 0) {
            i = 0;
        } else {
            i = node0.m_npf;
        }
        ListIterator<Cd2exp> iter = node0.m_d2expLst.listIterator(i);
        
        while (iter.hasNext()) {
            Cd3exp d3e = transform(iter.next(), scope, needed, new ArrayList<List<PolyParaType>>());
            if (null != d3e) {
            	d3es.add(d3e);   
            }
             
        }
        
        D3EXPARGdyn dyn = new D3EXPARGdyn(node0.m_loc, d3es);
        
        return dyn;
    }

    private Cd3exp transform(D2ElamDyn node0, Cloc_t loc, 
            Set<Cd3var> scope, Set<Cd3var> needed, List<List<PolyParaType>> accu) {
        Set<Cd3var> cur_scope = new HashSet<Cd3var>();
        int npf = 0;
        if (node0.m_npf > 0) {
            npf = node0.m_npf;
        }
        
        
        // remove proof parameters
        List<Cp3at> p3ats = transform(node0.m_p2ts.subList(npf, node0.m_p2ts.size()), cur_scope);
//        Set<Cd3var> paras = collect_stamps(p3ats);
        
        Set<Cd3var> cur_needed = new HashSet<Cd3var>();
        
        Cd3exp body = transform(node0.m_d2exp, cur_scope, cur_needed, new ArrayList<List<PolyParaType>>());
        // cur_needed now contains environment to form the closure. 
        
//        Ifunclo funclo = null;
//        if (cur_needed.isEmpty()) {
//            funclo = FUNCLOfun.cInstance;
//        } else {
//            funclo = new FUNCLOclo(-1);
//        }
        
        
        Set<Cd3var> actually_needed = new HashSet<Cd3var>(cur_needed);
        actually_needed.removeAll(scope);
        needed.addAll(cur_needed);
        
        FunType fun_type = (FunType)node0.getSType().removeProof();
//        if (null != fun_type.getFunClo()) {
//            Log.log4j.warn("funclo has been set already.");
//        } else {
//            fun_type.updateFunClo(funclo);
//        }
        
        ISType ty = fun_type;
        for (List<PolyParaType> para_types: accu) {
            if (!para_types.isEmpty()) {
                ty = new PolyType(para_types, ty);
            }
        }
        
        D3ElamDyn node = new D3ElamDyn(node0.m_lin, p3ats, body, ty, cur_needed);
        
        Cd3exp ret = new Cd3exp(loc, node);
        return ret;
        
    }

//    private Set<Cd3var> collect_stamps(List<Cp3at> p3ats) {
//        Set<Cd3var> stamps = new HashSet<Cd3var>();
//        for (Cp3at p3at: p3ats) {
//            collect_stamps(p3at, stamps);
//        }
//        return stamps;
//    }
//
//    private void collect_stamps(Cp3at p3at, Set<Cd3var> stamps) {
//        Ip3at_node node0 = p3at.m_node;
//        
//        if (node0 instanceof P3Tany) {
//            return;
//        } else if (node0 instanceof P3Tempty) {
//            return;
//        } else if (node0 instanceof P3Trec) {
//            collect_stamps((P3Trec)node0, stamps);
//            return;
//        } else if (node0 instanceof P3Tvar) {
//            stamps.add(((P3Tvar)node0).m_var);
//        } else {
//            throw new Error(node0 + " is not supported.");
//        }
//    }
//
//
//    private void collect_stamps(P3Trec node0, Set<Cd3var> stamps) {
//        for (LABP3ATnorm patitem: node0.m_labpats) {
//            collect_stamps(patitem.m_pat, stamps);
//        }
//    }


    /*
     * pattern may contain constant.
     * E.g. val cons (x (* x is defined previously *), 1) = v
     */
    private List<Cp3at> transform(List<Cp2at> p2ts, Set<Cd3var> scope) {
        List<Cp3at> p3ats = new ArrayList<Cp3at>();
        for (Cp2at p2at: p2ts) {
            Cp3at p3at = transform(0, p2at, scope);
            if (null != p3at) {
            	p3ats.add(p3at);
            }
            
        }
        return p3ats;
    }



    private Cd3exp transform(D2ElamMet node, Cloc_t loc, Set<Cd3var> needed, List<List<PolyParaType>> accu) {
        return transform(node.m_d2exp, new HashSet<Cd3var>(), needed, accu);
    }


    private Cd3exp transform(D2ElamSta node, Cloc_t loc, Set<Cd3var> needed, List<List<PolyParaType>> accu) {
        return transform(node.m_d2exp, new HashSet<Cd3var>(), needed, accu);
    }

    private Cd3var transform(Cd2var d2var, Cloc_t loc) {
        Cd3var d3var = m_varMap.get(d2var.m_stamp);
        if (null != d3var) {
            return d3var;
        } else {
        	Log.log4j.info("var is " + d2var.toString() + " " + d2var.getSType().toSTStfpl3(AuxSType.cStg).render());
            d3var = new Cd3var(d2var.m_sym, d2var.m_stamp, d2var.getSType().removeProof());
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
        case DCK_prfun: // extern prfun
        case DCK_prval: // extern prval
        case DCK_fun:  // extern fun
        case DCK_val:  // extern val
        {
            List<Cd3cst> d3csts = new ArrayList<Cd3cst>();
            for (Cd2cst d2cst: node0.m_d2cst) {
                if (node0.m_dck == Edcstkind.DCK_prfun || node0.m_dck == Edcstkind.DCK_prval) {
                    if (d2cst.m_symbol.isMC()) {  // mc_xx is kept.
                        Cd3cst d3cst = transform(d2cst, loc);
                        d3csts.add(d3cst);
                    }
                } else {
                    Cd3cst d3cst = transform(d2cst, loc);
                    d3csts.add(d3cst);
                }

                // scope.add(d3cst.m_stamp);  07/21/2014 Do not take into consideration of cst.
            }
            
            if (d3csts.isEmpty()) {
                return null;
            } else {
                D3Cdcstdecs node = new D3Cdcstdecs(node0.m_knd, node0.m_dck, d3csts);
                return new Cd3ecl(loc, node);
            }
        }

        case DCK_praxi:  // praxi
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
            d3cst = new Cd3cst(d2cst.m_stamp, d2cst.m_symbol, d2cst.getSType().removeProof());
            m_cstMap.put(d2cst.m_stamp, d3cst);
            return d3cst;
        }
    }

}
