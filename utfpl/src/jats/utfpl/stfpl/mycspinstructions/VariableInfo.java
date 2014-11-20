package jats.utfpl.stfpl.mycspinstructions;


import jats.utfpl.stfpl.instructions.SId;
import jats.utfpl.stfpl.mcinstruction.MCSId;
import jats.utfpl.stfpl.stype.AuxSType;

import java.util.ArrayList;
import java.util.List;


public class VariableInfo {
    private MCSId m_mid;
    private EntityLocation m_defLoc;  // The position of the definition of this entity of MCSId.
    private StackPosition m_stackPos;  // The stack location of the definition.
    private List<EntityLocation> m_usageLst;
    private Boolean m_isEscaped;
    
    public static VariableInfo createGlobalValue(MCSId mid) {
        // The definition of global variable doesn't have location.
        return new VariableInfo(mid, null);
    }
    
    public static VariableInfo create(MCSId mid, EntityLocation defLoc) {
        return new VariableInfo(mid, defLoc);
    }

    private VariableInfo(MCSId mid, EntityLocation defLoc) {
        m_mid = mid;
        m_defLoc = defLoc;
        m_stackPos = null;
        m_usageLst = new ArrayList<EntityLocation>();

        if (mid.getSId().isFunName()) {
        	m_isEscaped = false;
        } else if (mid.getSId().isGlobalValue()) {
        	m_isEscaped = false;
        } else if (mid.getSId().isConstant()) {
        	m_isEscaped = false;
        } else {
        	m_isEscaped = null;
        }
        
    }
    
    public MCSId getMCSId() {
        return m_mid;
    }
    
    public EntityLocation getDefLoc() {
        return m_defLoc;
    }
    
    public void setStackPos(StackPosition stackPos) {
        m_stackPos = stackPos;
    }
    
    public StackPosition getStackPos() {
        return m_stackPos;
    }
    
    public boolean getEscaped() {
    	if (null == m_isEscaped) {
    		throw new Error("This should not happen. id is " + m_mid.toStringMCIns());
    	}
        return m_isEscaped;
    }
    
    public void addUsage(EntityLocation loc) {
        m_usageLst.add(loc);
    }

    public void updateEscaped() {
//    	if (m_mid.getSId().toStringWithStamp().equals("foo2_env4_id")) {
//    		throw new Error("ERRRRRRRRRRRRRR");
//    	}
    	
    	SId sid = m_mid.getSId();
        if (sid.isGlobalValue()) {
            return;
        } else if (sid.isConstant()) {
        	throw new Error("Check this. This should not happen. updateEscaped is only called on definition.");
        } else if (sid.isPara() || sid.isLocal() || sid.isRetHolder()) {
            for (EntityLocation loc: m_usageLst) {
                if (!loc.equals(m_defLoc)) {
                    m_isEscaped = true;
                    return;
                }
            }
            m_isEscaped = false;
            return;
//        } else if (m_mid == TID.ANONY){  todo
//            m_isEscaped = false;
//            return;
        } else if (AuxSType.isVoid(m_mid.getType())) {
//            m_isEscaped = false;
            throw new Error("should not happen: mid is " + m_mid);
        } else {
        	System.out.println("============ mid is " + m_mid);
            throw new Error("check this");
//            m_isEscaped = false;
//            return;
        }
    }
    
    // Check whether the curLoc is with the same group as where the 
    // id is defined.
    public boolean isOutofScope(EntityLocation curLoc) {

        if (m_mid.getSId().isGlobalValue()) {
            return false;
        }
        
        if (m_defLoc.equals(curLoc)) {
            return false;
        } else if (m_mid.getSId().isFunName()) {
            return false;
        } else { // do nothing
            return true;
        }
    }
}

