package jats.utfpl.csps;
import jats.utfpl.instruction.TID;

/*
 * If m_tid is global variable or function parameter, 
 * then the m_grp inside the m_cur. can be null since
 * it's not defined in any CBlock. 
 */
public class CTempID implements CTemp {

    private StackPosition m_stackPos;  // The position of TID in the stack.
    private Boolean m_isDef;  // indicating whether this CTempID is definition or usage.
    private VariableInfo m_vi;
    private EntityLocation m_curLoc;  // The position of the current location of this entity of TID.
    
    public static CTempID createAsDef(VariableInfo vi, EntityLocation curLoc) {
        if (vi.getTID().isGlobal()) {
            throw new Error("Wrong usage. Only parameter and local varaible.");
        }
        
        CTempID ret = new CTempID(true, vi, curLoc);
        return ret;
    }
    
    public static CTempID createAsUsage(VariableInfo vi, EntityLocation curLoc) {
        CTempID ret = new CTempID(false, vi, curLoc);
        return ret;
    }

    private CTempID(boolean isDef, VariableInfo vi, EntityLocation curLoc) {
        m_stackPos = null;
        m_isDef = isDef;
        m_vi = vi;
        m_curLoc = curLoc;
    }
    
    // update the CTempID indicating the usage of a value.
    public void updateForUsage(int level) {
        if (level != m_curLoc.getLevel()) {
            throw new Error("Check this");
        }
        if (isEscaped()) {
            // for closure, m_def.m_level - level may be less than 0
            m_stackPos = StackPosition.createUsage(m_vi.getDefLoc().getLevel() - m_curLoc.getLevel(), m_vi.getStackPos().getOffset());
        } else {
            // nothing
        }
    }   

    private int updateForDef(int offset) {
        if (!m_isDef) {
            throw new Error("Should only call on the definition.");
        }
        m_stackPos = StackPosition.createDef(offset);
        m_vi.setStackPos(m_stackPos);  // set the stack position
        return offset + 1;
    }
    
    
    public boolean isRet() {
        return m_vi.getTID().isRet();
    }
    
    public boolean equals(String name) {
        return m_vi.getTID().equals(name);
    }
    
    public StackPosition getStackInfo() {
        return m_stackPos;
    }
    
    public boolean isPara() {
        return m_vi.getTID().isPara();
    }
    
    /*
     * This function is called when an object of CTempID is assigned at the first time.
     */
    public int processFirstOccurrence(int offset) {
        updateEscaped();
        if (isEscaped()) {
            offset =  updateForDef(offset);
        }
        return offset;
    }
    
    /*
     * m_tid won't be global, which is guaranteed by InstructionProcessor.GlobalVarInsProcessor.addInsForGlobalVar
     * This function is called when an object of CTempID is assigned at the first time.
     */
    public int processFirstOccurrenceProcCall(int offset) {
        updateEscaped();
        offset = updateForDef(offset);  // no matter what, we increase 
                           // the offset because proc would put the return value on the stack. 
        return offset;
    }
    
    
    public boolean isDefinition() {
        return m_isDef;
    }
//    
//    private boolean isDefinedInside(CBlock grp) {
//        if (m_vi.getDefLoc().getBlock() == grp) {
//            return true;
//        } else {
//            // parameter in the same level (not in the inner function)
//            if (m_vi.getTID().isPara() && m_vi.getDefLoc().getLevel() == grp.getLevel()) {
//                return true;
//            } else {
//                return false;
//            }
//        }
//    }
    
    private void updateEscaped() {
        m_vi.updateEscaped();
    }
    
    public boolean isEscaped() {
        return m_vi.getEscaped();
    }
    
    public String getID() {
        return m_vi.getTID().getID();
    }
    
    public String toString() {
        return m_vi.getTID().toString();
    }
    
    public Object accept(CSPSVisitor visitor) {
        return visitor.visit(this);
    }
    
    public TID getTID() {
        return m_vi.getTID();
    }
    
    public boolean isOutofScope() {
        return m_vi.isOutofScope(m_curLoc);

    }

}





