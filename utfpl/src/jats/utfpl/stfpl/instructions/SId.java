package jats.utfpl.stfpl.instructions;

import java.util.HashMap;
import java.util.Map;

import jats.utfpl.stfpl.dynexp3.Cd3cst;
import jats.utfpl.stfpl.dynexp3.Cd3sym;
import jats.utfpl.stfpl.dynexp3.Cd3var;
import jats.utfpl.stfpl.stype.ISType;

/*
 * SId can be viewed as left value, which serves as a holder.
 * Due to the existence of branching in the program, the same SId
 * can appear in multiple places.
 * E.g.
 * int x;
 * if (..) {
 *     x = 1;
 * } else {
 *     x = 2;
 * }
 */

/*
 * one to one correspondence between SId and IVarName
 */
public class SId implements IValPrim{
    
    public static enum SIdCategory {/*eLibFun, */eGloVar, eGloValue, ePara, eUserFun, eLocalVar, eRetHolder, eConstant, eOther};
    
    static public SId ANONY = new SId(VNameId.s_anony, SIdCategory.eOther);

    public IVarName m_name;
    public SIdCategory m_cat;

    static private Map<IVarName, SId> s_map = new HashMap<IVarName, SId>(); 

    public SId(IVarName name, SIdCategory cat) {
        m_name = name;
        m_cat = cat;
    }
    
    static public SId fromCd3cst(Cd3cst d3cst, SIdCategory cat) {
        IVarName name = VNameCst.fromCd3cst(d3cst);
        SId id = s_map.get(name);
        if (null == id) {
            id = new SId(name, cat);
            s_map.put(name, id);
            return id;
        } else {
            return id;
        }

    }
    
    // called by D3Esym
    static public SId fromCd3sym(Cd3sym d3sym, SIdCategory cat) {
        VNameSym name = new VNameSym(d3sym);
        SId id = s_map.get(name);
        if (null == id) {
            id = new SId(name, cat);
            s_map.put(name, id);
            return id;
        } else {
            return id;
        }
    }
    
    static public SId fromCd3var(Cd3var d3var, SIdCategory cat) {
        VNameVar name = VNameVar.fromCd3var(d3var);
        SId id = s_map.get(name);
        if (null == id) {
            id = new SId(name, cat);
            s_map.put(name, id);
            return id;
        } else {
            throw new Error("Should not happen.");
        }
    }
    
    // This function should be called when we are sure SId has already
    // been created.
    static public SId fromCd3var(Cd3var d3var) {
        VNameVar name = VNameVar.fromCd3var(d3var);
        SId id = s_map.get(name);
        if (null == id) {
            throw new Error("Should not happen.");
        } else {
            return id;
        }
    }
    
    static public SIdUser createSIdUserByCd3var(Cd3var d3var, boolean from_env) {
        VNameVar name = VNameVar.fromCd3var(d3var);
        SId sid = s_map.get(name);
        if (null == sid) {
            throw new Error("should not happen");
        }
        SIdUser su = new SIdUser(sid, from_env);
        return su;
    }

    // Give RetHolder a name.
    public static SId createRetHolder(String name, ISType stype) {
        VNameId id = new VNameId(name, stype);
        SId ret = new SId(id, SIdCategory.eRetHolder);
        return ret;
    }
    
    // Give name to a local temporary value without a name
    public static SId createLocalVar(String name, ISType stype) {
        VNameId id = new VNameId(name, stype);
        SId ret = new SId(id, SIdCategory.eLocalVar);
        return ret;
    }
    
    // Give lambda expression a name
    public static SId createLambdaFunction(String name, ISType stype) {
        VNameId id = new VNameId(name, stype);
        SId ret = new SId(id, SIdCategory.eUserFun);
        return ret;
    }
    
    public static SId createEnvForclosure(String name, ISType stype) {
        VNameId id = new VNameId(name, stype);
        SId ret = new SId(id, SIdCategory.eLocalVar);
        return ret;
    }

    @Override
    public ISType getType() {
        return m_name.getType();
    }

    public boolean isRetHolder() {
        return SIdCategory.eRetHolder == m_cat;
    }
    
    public boolean isUserFun() {
        return SIdCategory.eUserFun == m_cat;
    }
    
    public boolean isConstant() {
        return SIdCategory.eConstant == m_cat;
    }

    public String toStringCS() {
        return m_name.toStringCS();
    }
    
    public String toStringIns() {
        return m_name.toStringIns();
    }
    
}













