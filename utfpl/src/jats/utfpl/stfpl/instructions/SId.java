package jats.utfpl.stfpl.instructions;

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
    
    public static enum SIdCategory {/*eLibFun, */eGloVar/*obsolete*/, eGloValue, ePara, eUserFun, eLocalVar, eRetHolder, eConstant, eOther};


    public IVarName m_name;
    private SIdCategory m_cat;
    
    public void updateCat(SIdCategory cat) {
        m_cat = cat;
    }
    
    public SIdCategory getCat() {
    	return m_cat;
    }

    // Used by factory.
    public SId(IVarName name, SIdCategory cat) {
        m_name = name;
        m_cat = cat;
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
    
    public String toStringNoStamp() {
    	return m_name.toStringNoStamp();
    }
    
    public String toStringWithStamp() {
    	return m_name.toStringWithStamp();
    }
    
    public boolean isGlobal() {
    	return m_cat == SIdCategory.eGloVar || m_cat == SIdCategory.eGloValue;
    }
    
}













