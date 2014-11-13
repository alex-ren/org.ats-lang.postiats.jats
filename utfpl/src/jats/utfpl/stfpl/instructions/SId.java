package jats.utfpl.stfpl.instructions;

import jats.utfpl.stfpl.stype.Aux;
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
public class SId implements IIdPrim{
    
    public static enum SIdCategory {eGloValue  // global entity but not of function format
                                  , ePara      // parameters
                                  , eUserFun   // function declaration, implementation, definition, lambda expression
                                  , eLocalVar
                                  , eRetHolder
                                  , eConstant  // symbols from the internal of compiler (d2sym, d2cst)
                                  };


    public IVarName m_name;
    private SIdCategory m_cat;

    // Used by factory.
    public SId(IVarName name, SIdCategory cat) {
        m_name = name;
        m_cat = cat;
        
//    	if (toStringWithStamp().equals("foo2_env4_id")) {
//    		throw new Error("ERRRRRRRRRRRRRR");
//    	}
    }
    
    // Used by factory.
    public SIdCategory getCategory() {
        return m_cat;
    }
    
    public void updateCat(SIdCategory cat) {
        m_cat = cat;
    }

    @Override
    public ISType getType() {
        return m_name.getType();
    }

    public boolean isRetHolder() {
        return SIdCategory.eRetHolder == m_cat;
    }
    
    public boolean isConstant() {
        return SIdCategory.eConstant == m_cat;
    }
    
    public boolean isLocal() {
        return SIdCategory.eLocalVar == m_cat;
    }
    
    public boolean isPara() {
        return SIdCategory.ePara == m_cat;
    }
    
    // functions inherent to the ATS compiler
    public boolean isLibFun() {
        if (Aux.getFunctionType(m_name.getType()) == null) {
            return false;
        } else {
            return SIdCategory.eConstant == m_cat;
        }
    }
    
    public boolean isUserFun() {
        return SIdCategory.eUserFun == m_cat;
    }
    
    public boolean isFunName() {
        if (Aux.getFunctionType(m_name.getType()) == null) {
            return false;
        } else {
            if (SIdCategory.eUserFun == m_cat || SIdCategory.eConstant == m_cat) {
                return true;
            } else {
            	return false;
//                throw new Error("Check this. Should not happen. id is " + toStringWithStamp() + " and cat is " + m_cat);
            }
        }
    }
    
    
    public boolean isGlobalValue() {
        return m_cat == SIdCategory.eGloValue;
    }
    
    /* *************** ****************** */
    
    public String toStringCS() {
        return m_name.toStringCS();
    }
    
    public String toStringIns() {
        return m_name.toStringIns();
    }
    
    public String toStringNoStamp() {
    	return m_name.toStringNoStamp();
    }
    
    public String toStringWithStamp() {
    	return m_name.toStringWithStamp();
    }

    
}













