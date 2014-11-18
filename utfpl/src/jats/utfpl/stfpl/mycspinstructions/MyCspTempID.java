package jats.utfpl.stfpl.mycspinstructions;

import jats.utfpl.stfpl.instructions.SId;
import jats.utfpl.stfpl.mcinstruction.MCSId;
import jats.utfpl.stfpl.stype.ISType;

/*
 * If m_tid is global variable or function parameter, 
 * then the m_grp inside the m_cur. can be null since
 * it's not defined in any CBlock. 
 */
public class MyCspTempID implements IMyCspTemp {

    private StackPosition m_stackPos;  // The position of TID in the stack.
    // If m_stackPos == null, then we don't need to get this CTempID via stack.
    
    private Boolean m_isDef;  // indicating whether this CTempID is definition or usage.
    // parameter is also a definition.
    
    private VariableInfo m_vi;
    private EntityLocation m_curLoc;  // The position of the current location of this entity of TID.
    
    
    public static MyCspTempID createAsDef(VariableInfo vi, EntityLocation curLoc) {
        SId sid = vi.getMCSId().getSId();
        if (!(sid.isPara() || sid.isLocal() || sid.isRetHolder() || sid.isFunName())) {
            throw new Error("Wrong usage. sid is " + sid.toStringNoStamp());
        }
        
        MyCspTempID ret = new MyCspTempID(true, vi, curLoc);
        return ret;
    }
    
    public static MyCspTempID createAsUsage(VariableInfo vi, EntityLocation curLoc) {
        MyCspTempID ret = new MyCspTempID(false, vi, curLoc);
        return ret;
    }
    
    public boolean isFunName() {
        return m_vi.getMCSId().getSId().isFunName();
    }
    
    private MyCspTempID(boolean isDef, VariableInfo vi, EntityLocation curLoc) {
        m_stackPos = null;
        m_isDef = isDef;
        m_vi = vi;
        m_curLoc = curLoc;
    }
    
    // update the CTempID indicating the usage of a value.
    public void updateForUsage() {
        if (isEscaped()) {  // This CTempID can be a parameter.
            if (isOutofScope()) {
                m_stackPos = m_vi.getStackPos();
            } else {
                m_stackPos = null; // still in the valid scope
            }
        } else {
            // nothing
        }

    }   

    private int updateForDef(int offset) {
        if (!m_isDef) {
            throw new Error("Should only call on the definition." + this);
        }
        m_stackPos = StackPosition.createDef(offset);
        m_vi.setStackPos(m_stackPos);  // set the stack position
        return offset + 1;
    }
    
    public boolean isRet() {
        return m_vi.getMCSId().getSId().isRetHolder();
    }
    
    public StackPosition getStackInfo() {
        return m_stackPos;
    }
    
    public boolean isPara() {
        return m_vi.getMCSId().getSId().isPara();
    }
    
    public int processStackPrelogue(int offset) {
        if (isPara()) {
        	if (m_isDef) {
                updateEscaped();
//                if (isEscaped()) { 
                  // It's possible that isEscaped() is false, 
                  // but parameter is already put onto stack by the caller.
                    offset =  updateForDef(offset);
                    return offset;
//                }
        	} else {
        		throw new Error("parameter is also definition");
        	}
        } else {
        	throw new Error("has to be parameter id is " + m_vi.getMCSId().getSId().toStringWithStamp());
        }
    }
    
    /*
     * This function is called when an object of CTempID allocated at first time.
     */
    public int processStack(int offset) {
    	
    	if (m_vi.getMCSId().toStringMCIns().equals("temp6_id")) {
    		
    	}
    	if (m_isDef) {
            updateEscaped();
            if (isEscaped()) {
                offset =  updateForDef(offset);
            }
    	}

        return offset;
    }
    
    /*
     * The current id is a holder for a function call.
     */
    public int processStackProcCallEpilogue(int offset) {    	
    	updateEscaped();
    	
        if (isEscaped()) {
            offset =  updateForDef(offset);  // allocate from stack
        }

        return offset;
    }
    
    
    public boolean isDefinition() {
        return m_isDef;
    }
    
    private void updateEscaped() {
        m_vi.updateEscaped();
    }
    
    public boolean isEscaped() {
        return m_vi.getEscaped();
    }
    
//    public String getID() {
//        return m_vi.getMCSId().getID();
//    }
    
    public String toString() {
        return m_vi.getMCSId().toString();
    }
    
    public MCSId getMCSId() {
        return m_vi.getMCSId();
    }
    
    public boolean isOutofScope() {
        return m_vi.isOutofScope(m_curLoc);

    }

	@Override
    public Object accept(IMyCspInsVisitor visitor) {
		return visitor.visit(this);
    }

	@Override
	public ISType getType() {
		return m_vi.getMCSId().getType();
	}

}





