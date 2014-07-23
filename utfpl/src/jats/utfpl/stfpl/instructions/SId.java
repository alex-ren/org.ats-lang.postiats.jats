package jats.utfpl.stfpl.instructions;

import java.util.HashMap;
import java.util.Map;

import jats.utfpl.stfpl.dynexp3.Cd3cst;
import jats.utfpl.stfpl.dynexp3.Cd3sym;
import jats.utfpl.stfpl.dynexp3.Cd3var;
import jats.utfpl.stfpl.stype.ISType;

public class SId implements IValPrim{
    static public SId ANONY = new SId(VNameId.s_anony, Category.eOther);
    
    enum Category {/*eLibFun, *//*eGloVar, */eGloValue, ePara, eUserFun, eLocalVar, eRetHolder, eConstant, eOther};
    
    public IVarName m_name;
    public Category m_cat;
    
    static private Map<IVarName, SId> s_map = new HashMap<IVarName, SId>(); 

    public SId(IVarName name, Category cat) {
        m_name = name;
        m_cat = cat;
    }
    
    static public SId fromCd3cst(Cd3cst d3cst, Category cat) {
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
    
    static public SId fromCd3sym(Cd3sym d3sym, Category cat) {
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
    
    static public SId fromCd3var(Cd3var d3var, Category cat) {
        VNameVar name = VNameVar.fromCd3var(d3var);
        SId id = s_map.get(name);
        if (null == id) {
            id = new SId(name, cat);
            s_map.put(name, id);
            return id;
        } else {
            return id;
        }

    }
    
    static public SId fromCloCd3var(Cd3var d3var, Category cat) {
        VNameClosurePara name = VNameClosurePara.fromClosurePara(d3var);
        SId id = s_map.get(name);
        if (null == id) {
            id = new SId(name, cat);
            s_map.put(name, id);
            return id;
        } else {
            return id;
        }

    }

    public static SId createRetHolder(String name, ISType stype) {
        VNameId id = new VNameId(name, stype);
        SId ret = new SId(id, Category.eRetHolder);
        return ret;
    }
    
    public static SId createLocalVar(String name, ISType stype) {
        VNameId id = new VNameId(name, stype);
        SId ret = new SId(id, Category.eLocalVar);
        return ret;
    }

    @Override
    public ISType getType() {
        return m_name.getType();
    }

}
