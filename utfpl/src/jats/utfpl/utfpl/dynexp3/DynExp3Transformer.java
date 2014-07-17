package jats.utfpl.utfpl.dynexp3;

import jats.utfpl.utfpl.Cloc_t;
import jats.utfpl.utfpl.Cstamp;
import jats.utfpl.utfpl.dynexp.Cd2cst;
import jats.utfpl.utfpl.dynexp.Cd2ecl;
import jats.utfpl.utfpl.dynexp.Cd2exp;
import jats.utfpl.utfpl.dynexp.Cd2var;
import jats.utfpl.utfpl.dynexp.Cf2undec;
import jats.utfpl.utfpl.dynexp.Cp2at;
import jats.utfpl.utfpl.dynexp.D2Cdatdecs;
import jats.utfpl.utfpl.dynexp.D2Cdcstdecs;
import jats.utfpl.utfpl.dynexp.D2Cextcode;
import jats.utfpl.utfpl.dynexp.D2Cfundecs;
import jats.utfpl.utfpl.dynexp.D2Cignored;
import jats.utfpl.utfpl.dynexp.D2Cimpdec;
import jats.utfpl.utfpl.dynexp.D2Cstacsts;
import jats.utfpl.utfpl.dynexp.D2Cvaldecs;
import jats.utfpl.utfpl.dynexp.D2EannFunclo;
import jats.utfpl.utfpl.dynexp.D2EannSeff;
import jats.utfpl.utfpl.dynexp.D2EannType;
import jats.utfpl.utfpl.dynexp.D2Eapplst;
import jats.utfpl.utfpl.dynexp.D2Ecst;
import jats.utfpl.utfpl.dynexp.D2Eempty;
import jats.utfpl.utfpl.dynexp.D2Eexp;
import jats.utfpl.utfpl.dynexp.D2Ef0loat;
import jats.utfpl.utfpl.dynexp.D2Ei0nt;
import jats.utfpl.utfpl.dynexp.D2Eifopt;
import jats.utfpl.utfpl.dynexp.D2Eignored;
import jats.utfpl.utfpl.dynexp.D2ElamDyn;
import jats.utfpl.utfpl.dynexp.D2ElamMet;
import jats.utfpl.utfpl.dynexp.D2ElamSta;
import jats.utfpl.utfpl.dynexp.D2Elet;
import jats.utfpl.utfpl.dynexp.D2Elist;
import jats.utfpl.utfpl.dynexp.D2Es0tring;
import jats.utfpl.utfpl.dynexp.D2Esym;
import jats.utfpl.utfpl.dynexp.D2Etup;
import jats.utfpl.utfpl.dynexp.D2Evar;
import jats.utfpl.utfpl.dynexp.Id2ecl_node;
import jats.utfpl.utfpl.dynexp.Id2exp_node;
import jats.utfpl.utfpl.dynexp.Ip2at_node;
import jats.utfpl.utfpl.dynexp.P2Tann;
import jats.utfpl.utfpl.dynexp.P2Tany;
import jats.utfpl.utfpl.dynexp.P2Tcon;
import jats.utfpl.utfpl.dynexp.P2Tempty;
import jats.utfpl.utfpl.dynexp.P2Tignored;
import jats.utfpl.utfpl.dynexp.P2Tpat;
import jats.utfpl.utfpl.dynexp.P2Trec;
import jats.utfpl.utfpl.dynexp.P2Tvar;
import jats.utfpl.utfpl.stype.FloatType;
import jats.utfpl.utfpl.stype.IntType;
import jats.utfpl.utfpl.stype.StringType;
import jats.utfpl.utfpl.stype.VarType;
import jats.utfpl.utfpl.stype.VoidType;
import jats.utfpl.utils.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
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
     * cur_scope:
     * needed: Symbols needed but not in cur_scope
     */
    public List<Cd3ecl> transform(List<Cd2ecl> d2ecs, Set<Cstamp> scope, Set<Cstamp> needed) {

        List<Cd3ecl> d3ecs = new ArrayList<Cd3ecl>();
        for (Cd2ecl d2ec: d2ecs) {
            Cd3ecl d3ec = transform(d2ec, scope, needed);
            if (null != d3ec) {
                d3ecs.add(d3ec);
            }
        }
        
        return d3ecs;
    }

    private Cd3ecl transform(Cd2ecl d2ec, Set<Cstamp> scope, Set<Cstamp> needed) {
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

    /*
     * scope: will be updated
     */
    private Cd3ecl transform(Cloc_t loc, D2Cfundecs node0, Set<Cstamp> scope, Set<Cstamp> needed) {
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
                scope.add(f3un.m_var.m_stamp);
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


    private Cf3undec transform(Cf2undec f2un, Set<Cstamp> needed) {
        Cd3var d3var = transform(f2un.f2undec_loc, f2un.f2undec_var);
        Cd3exp d3exp = transform(f2un.f2undec_def, null, needed);
        if (isClosure(d3exp)) {
            d3var.m_stype.addclosureinfo
        }
        
        return new Cf3undec(f2un.f2undec_loc, d3var, d3exp);
    }


    /*
     * scope: names can be used. This cannot be changed.
     */
    private Cd3exp transform(Cd2exp d2exp, Set<Cstamp> scope, Set<Cstamp> needed) {
        Id2exp_node node = d2exp.d2exp_node;
        Cloc_t loc = d2exp.d2exp_loc;
        if (node instanceof D2EannFunclo) {
            return transform((D2EannFunclo)node, loc);
        } else if (node instanceof D2EannSeff) {
            return transform((D2EannSeff)node, loc);
        } else if (node instanceof D2EannType) {
            return transform((D2EannType)node, loc);
        } else if (node instanceof D2Eapplst) {
            return transform((D2Eapplst)node, loc);
        } else if (node instanceof D2Ecst) {
            return transform((D2Ecst)node, loc);
        } else if (node instanceof D2Eempty) {
            return VoidType.cInstance;
        } else if (node instanceof D2Eexp) {
            return oftype(((D2Eexp)node).m_d2exp);
        } else if (node instanceof D2Ef0loat) {
            return FloatType.cInstance;
        } else if (node instanceof D2Ei0nt) {
            return IntType.cInstance;
        } else if (node instanceof D2Eifopt) {
            return oftype((D2Eifopt)node, loc);
        } else if (node instanceof D2Eignored) {
            throw new Error("Check this");
        } else if (node instanceof D2ElamDyn) {
            return transform((D2ElamDyn)node, loc, needed);
        } else if (node instanceof D2ElamMet) {
            return transform((D2ElamMet)node, loc, needed);
        } else if (node instanceof D2ElamSta) {
            return transform((D2ElamSta)node, loc, needed);
        } else if (node instanceof D2Elet) {
            return oftype((D2Elet)node, loc);
        } else if (node instanceof D2Es0tring) {
            return StringType.cInstance;
        } else if (node instanceof D2Esym) {
            return oftype((D2Esym)node, loc);
        } else if (node instanceof D2Etup) {
            return oftype((D2Etup)node, loc);
        } else if (node instanceof D2Elist) {
            return oftype((D2Elist)node, loc);            
        } else if (node instanceof D2Evar) {
            return oftype((D2Evar)node, loc);
        } else {
            throw new Error(d2exp + " is not supported");
        }
    }


    private Cd3exp transform(D2ElamDyn node0, Cloc_t loc, Set<Cstamp> needed) {
        List<Cp3at> p3ats = transform(node0.m_p2ts);
        
    }


    /*
     * pattern may contain constant.
     * E.g. val cons (x (* x is defined previously *), 1) = v
     */
    private List<Cp3at> transform(List<Cp2at> p2ts) {
        List<Cp3at> p3ats = new ArrayList<Cp3at>();
        for (Cp2at p2at: p2ts) {
            Cp3at p3at = transform(p2at);
        }
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
            return VoidType.cInstance;
        } else if (node0 instanceof P2Tignored) {
            throw new Error("Check this.");
        } else if (node0 instanceof P2Tpat) {
            return oftype(((P2Tpat)node0).m_p2at);
        } else if (node0 instanceof P2Trec) {
            return oftype((P2Trec)node0);
        } else if (node0 instanceof P2Tvar) {
            return oftype((P2Tvar)node0);
        } else {
            throw new Error(node0 + " is not supported.");
        }
    }

    private Cp3at transform(P2Tany node0, Cloc_t loc) {
        return new Cp3at(loc, P3Tany.cInstance);
    }


    private Cp3at transform(P2Tann node0) {
        return transform(node0.m_p2t);
    }


    private Cd3exp transform(D2ElamMet node, Cloc_t loc, Set<Cstamp> needed) {
        return transform(node.m_d2exp, null, needed);
    }


    private Cd3exp transform(D2ElamSta node, Cloc_t loc, Set<Cstamp> needed) {
        return transform(node.m_d2exp, null, needed);
    }


    private Cd3var transform(Cloc_t f2undec_loc, Cd2var d2var) {
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


    private Cd3ecl transform(Cloc_t loc, D2Cdcstdecs node0, Set<Cstamp> scope) {
        switch (node0.m_dck) {
        case DCK_fun:  // extern fun
        case DCK_val:  // extern val
        {
            List<Cd3cst> d3csts = new ArrayList<Cd3cst>();
            for (Cd2cst d2cst: node0.m_d2cst) {
                Cd3cst d3cst = transform(loc, d2cst);
                d3csts.add(d3cst);
                scope.add(d3cst.m_stamp);
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


    private Cd3cst transform(Cloc_t loc, Cd2cst d2cst) {
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
