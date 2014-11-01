package jats.utfpl.stfpl.staexp;

import jats.utfpl.stfpl.stype.Abstype;
import jats.utfpl.stfpl.stype.BoolType;
import jats.utfpl.stfpl.stype.DataType;
import jats.utfpl.stfpl.stype.DefaultAppTypeStore;
import jats.utfpl.stfpl.stype.ESort;
import jats.utfpl.stfpl.stype.FunType;
import jats.utfpl.stfpl.stype.ILabPat;
import jats.utfpl.stfpl.stype.ISType;
import jats.utfpl.stfpl.stype.IntType;
import jats.utfpl.stfpl.stype.LabPatNorm;
import jats.utfpl.stfpl.stype.PolyParaType;
import jats.utfpl.stfpl.stype.PolyType;
import jats.utfpl.stfpl.stype.PropType;
import jats.utfpl.stfpl.stype.RecType;
import jats.utfpl.stfpl.stype.VoidType;

import java.util.ArrayList;
import java.util.List;

public class SExpTypeExtractor {

    // This function may return null if s2e is not of
    // sort type or t@ype.
    static public ISType extractType(Cs2exp s2e) {
        Is2exp_node node = s2e.s2exp_node;
        if (node instanceof S2Eapp) {
            return extractType((S2Eapp)node);
        } else if (node instanceof S2Ecst) {
            return extractType((S2Ecst)node);
        } else if (node instanceof S2Eeqeq) {
            return null;
        } else if (node instanceof S2Eerr) {
            throw new Error("S2Eerr should not be seen.");
        } else if (node instanceof S2Eexi) {
            return extractType((S2Eexi)node);
        } else if (node instanceof S2Eextkind) {
            throw new Error("S2Eextkind should not be seen.");
        } else if (node instanceof S2Efun) {
            return extractType((S2Efun)node);
        } else if (node instanceof S2Eignored) {
            throw new Error("S2Eignored should not be seen.");
        } else if (node instanceof S2Eint) {
            throw new Error("S2Eint encountered.");
        } else if (node instanceof S2Eintinf) {
            return null;
        } else if (node instanceof S2Esizeof) {
            return null;
        } else if (node instanceof S2Euni) {
            return extractType((S2Euni)node);
        } else if (node instanceof S2Evar) {
            return extractType((S2Evar)node);
        } else if (node instanceof S2Etyrec) {
            return extractType((S2Etyrec)node);
        } else {
            throw new Error(node + " is not supported");
        }
        
    }

    private static ISType extractType(S2Ecst node) {
        String name = node.getName();

        if (name.equals(DefaultAppTypeStore.ats_void_t0ype)) {
            return VoidType.cInstance;
        } else {
            Is2rt srt = node.m_s2cst.m_srt;
            ESort sort = srt.simplify();
            switch (sort)
            {
            case prop:
            {
                return PropType.cInstance;
            }
            case type:
            case t0ype:
                return new Abstype(node.m_s2cst, sort);
            default:
                throw new Error("srt " + srt + " is not supported");
            }
        }
    }

    private static RecType extractType(S2Etyrec node) {

        int knd = node.m_knd.getId();
        
        List<ILabPat> labPatLst = new ArrayList<ILabPat>();    
        for (Clabs2exp lexp: node.m_ls2es) {
            ISType ty = extractType(lexp.m_s2exp);

            LabPatNorm labexp = new LabPatNorm(lexp.m_label, ty);
            labPatLst.add(labexp);
        }
        RecType ret = new RecType(labPatLst, node.m_npf, knd);
        return ret;
    }

    public static PolyParaType extractType(S2Evar node) {
        return extractType(node.m_s2var);
    }
    
    public static PolyParaType extractType(Cs2var node) {
        if (node.isType()) {
            PolyParaType ret = new PolyParaType(node);
            return ret;
        } else {
            return null;
        }
    }

    public static ISType extractType(S2Euni node) {
        List<Cs2var> s2vs = node.m_s2vs;
        List<PolyParaType> paras = new ArrayList<PolyParaType>();
        
        for (Cs2var s2var: s2vs) {
            PolyParaType para = extractType(s2var);
            
            if (null != para) {
                paras.add(para);
            }
        }
        
        ISType body = extractType(node.m_s2e_body);
        
        if (paras.isEmpty()) {
            return body;
        } else {
            return new PolyType(paras, body);
        }

    }

    public static FunType extractType(S2Efun node) {
        int npf = node.m_npf;
        List<ISType> args = extractTypeList(node.m_arg);
        ISType res = extractType(node.m_res);
        
        return new FunType(npf, args, res, FunCloNA.cInstance, /*todo always has effect*/1);
    }

    public static ISType extractType(S2Eexi node) {
        return extractType(node.m_s2e_body);
    }

    public static ISType extractType(S2Eapp node) {
        // Currently, we get the type information from the function in the statics.
        if (node.m_fun.s2exp_node instanceof S2Ecst) {
            S2Ecst snode = (S2Ecst)node.m_fun.s2exp_node;
            S2RTfun sort =  (S2RTfun)node.m_fun.s2exp_srt;
            String con = snode.getName();

            if (con.equals(DefaultAppTypeStore.con_g0int_t0ype)) {
                return IntType.cInstance;
            } else if (con.equals(DefaultAppTypeStore.con_g1int_int_t0ype)) {
                return IntType.cInstance;
            } else if (con.equals(DefaultAppTypeStore.con_bool_bool_t0ype)) {
                return BoolType.cInstance;
                
            } else if (con.equals(DefaultAppTypeStore.con_mc_sid_t0ype)) {
                return IntType.cInstance;
            } else {
                switch (sort.m_res.simplify())
                {
                    case type:
                    case t0ype:
                    {
                        List<ISType> tys = extractTypeList(node.m_arglst);
                        DataType ret = new DataType(snode.m_s2cst, tys);
                        return ret;
                    }
                    case prop:
                        return PropType.cInstance;
                    default:
//                        return null;
                        throw new Error("Not supported");
                }
            }

        } else {
            throw new Error("check this.");
        }
    }
    
    public static List<ISType> extractTypeList(List<Cs2exp> s2exps) {
        List<ISType> ret = new ArrayList<ISType>();
        for (Cs2exp exp: s2exps) {
            ISType ty = extractType(exp);
            if (ty != null) {
                ret.add(ty);
            }
        }
        
        return ret;
    }

}
