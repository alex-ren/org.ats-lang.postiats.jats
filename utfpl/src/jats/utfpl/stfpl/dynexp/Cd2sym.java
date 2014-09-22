package jats.utfpl.stfpl.dynexp;

import jats.utfpl.stfpl.Csymbol;
import jats.utfpl.stfpl.staexp.FUNCLOfun;
import jats.utfpl.stfpl.stype.FunType;
import jats.utfpl.stfpl.stype.ISType;

public class Cd2sym {
    public Csymbol m_d2sym_name;
    
    private ISType m_stype;
    
    // used by StringTemplate
    public Csymbol getSymbol() {
        return m_d2sym_name;
    }
    
    public Cd2sym(Csymbol sym_name) {
        m_d2sym_name = sym_name;
        m_stype = null;
    }
    
    public void updateSType(ISType stype) {
        if (null != m_stype) {
            m_stype.match(stype);
        } else {
            m_stype = stype;
        }
    }
    
    public ISType getSType() {
        return m_stype;
    }
    
    public void normalizeType() {
        m_stype = m_stype.normalize();
        if (m_stype instanceof FunType) {
            FunType fun_ty = (FunType)m_stype;
            if (null == fun_ty.getFunClo()) {
                throw new Error("Check this.");
//                // symbol is treated as function
//                fun_ty.updateFunClo(FUNCLOfun.cInstance);
            }
        }
    }
}
