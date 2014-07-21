package jats.utfpl.stfpl.instructions;

import jats.utfpl.stfpl.dynexp3.Cd3cst;
import jats.utfpl.stfpl.dynexp3.Cd3var;
import jats.utfpl.stfpl.stype.ISType;

public class SId implements IValPrim{
    static public SId ANONY = new SId(VNameId.s_anony, Category.other);
    
    enum Category {/*eLibFun, *//*eGloVar, */eGloValue, ePara, eUserFun, eLocalVar, eRetHolder, eConstant, other};
    
    public IVarName m_name;
    public Category m_cat;
    
    public SId(IVarName name, Category cat) {
        m_name = name;
        m_cat = cat;
    }
    
    public SId(Cd3cst d3cst, Category cat) {
        m_name = new VNameCst(d3cst);
        m_cat = cat;
    }
    
    public SId(Cd3var d3var, Category cat) {
        m_name = new VNameVar(d3var);
        m_cat = cat;
    }
    
    public static SId createRetHolder(String name, ISType stype) {
        VNameId id = new VNameId(name, stype);
        SId ret = new SId(id, Category.eRetHolder);
        return ret;
    }
    

}
