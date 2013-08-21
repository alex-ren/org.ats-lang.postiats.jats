package jats.utfpl.csps;

import java.util.ArrayList;
import java.util.List;

import jats.utfpl.instruction.TID;

/*
 * If m_tid is global variable or function parameter, 
 * then the m_grp inside the m_loc can be null since
 * it's not defined in any CBlock. 
 */
public class CTempID implements CTemp {
    private TID m_tid;
   
    private StackLocation m_loc;  // The position of TID in the stack.
    
    private EntityLocation m_def;  // The position of the definition of this TID.
    private List<EntityLocation> m_usageLst;
    Boolean m_isEscaped;
    Boolean m_isDef;  // indicating whether this CTempID is definition or usage.
    
    public static class EntityLocation {
        public TID m_funLab;
        public int m_level;
        public CBlock m_grp;
        
        public EntityLocation(TID funLab, CBlock grp, int level) {
            m_funLab = funLab;
            m_level = level;
            m_grp = grp;
        }
    }
    
    public boolean isRet() {
        return m_tid.isRet();
    }
    
    public boolean equals(String name) {
        return m_tid.equals(name);
    }
    
    public StackLocation getStackInfo() {
        return m_loc;
    }
    
    public boolean isPara() {
        return m_tid.isPara();
    }
    
    /*
     * This function is called when an object of CTempID is seen at the first time.
     * 
     */
    public int processFirstOccurrence(int offset) {
        updateEscaped();
        setDefinition();  // indicating that this is a definition.
        if (isEscaped()) {
            offset =  updateStackLocation(offset);
        }
        return offset;
    }
    
    
    public boolean isDefinition() {
        return m_isDef;
    }
    
    public boolean isDefinedInside(CBlock grp) {
        if (m_def.m_grp == grp) {
            return true;
        } else {
            if (m_tid.isPara() && m_def.m_level == grp.getLevel()) {
                return true;
            } else {
                return false;
            }
        }
    }
    
    public CTempID(TID tid,
    		StackLocation loc,
    		EntityLocation def,
    		List<EntityLocation> usageLst,
    		Boolean isEscaped,
    		Boolean isDef)
    {
    	m_tid = tid;
    	m_loc = loc;
    	m_def = def;
    	m_usageLst = usageLst;
    	m_isEscaped = isEscaped;
    	m_isDef = isDef;
    }
    
    
    // Create a new CTempID indicating the usage of a value.
    public CTempID createForUsage(int level) {
        StackLocation loc = null;
        if (isEscaped()) {

            // for closure, m_def.m_level - level may be less than 0
            loc = new StackLocation(m_def.m_level - level, m_loc.m_offset);
        } else {
            loc = m_loc;
        }
    	CTempID ret = new CTempID(m_tid, loc, m_def, m_usageLst, m_isEscaped, false);
    	return ret;
    }
    
    private void setDefinition() {
        m_isDef = true;
    }
    
    private int updateStackLocation(int offset) {
    	m_loc = new StackLocation(m_def.m_level, offset);
    	return offset + 1;
    }
    
    private void updateEscaped() {
    	if (m_tid.isPara()) {
    		for (EntityLocation loc: m_usageLst) {
    			if (loc.m_level != m_def.m_level) {
    				m_isEscaped = true;
    				return;
    			}
    		}
    		m_isEscaped = false;
    		return;
    	} else if (m_tid.isGlobal()) {
    		m_isEscaped = false;
    		return;
    	} else if (m_tid.isLocal()) {
    		for (EntityLocation loc: m_usageLst) {
    			if (loc.m_grp != m_def.m_grp) {
    				m_isEscaped = true;
    				return;
    			}
    		}
    		m_isEscaped = false;
    		return;
    	} else {
    		m_isEscaped = false;
    		return;
    	}
    }
    
    static public CTempID createDef(TID tid, TID funLab, CBlock grp, int level) {
        return new CTempID(tid, new EntityLocation(funLab, grp, level));
    }
    
    private CTempID(TID tid, EntityLocation defLoc) {
        m_tid = tid;
        m_loc = null;
        m_def = defLoc;
        m_usageLst = new ArrayList<EntityLocation>();
        m_isEscaped = null;
        m_isDef = null;
    }
    
    
    public void addUsageLoc(TID funLab, CBlock grp, int level) {
        m_usageLst.add(new EntityLocation(funLab, grp, level));
    }

    public boolean isEscaped() {
        return m_isEscaped;
    }
    
    public void setLoc(StackLocation loc) {
        if (m_tid.isLocal()) {
            m_loc = loc;
        } else {
            throw new Error("Setting location for non-local variable");
        }
    }
    
    public String getID() {
        return m_tid.getID();
    }
    
    public String toString() {
        return m_tid.toString();
    }
    
    public Object accept(CSPSVisitor visitor, CBlock curBlk) {
        return visitor.visit(this, curBlk);
    }
    
    public TID getTID() {
        return m_tid;
    }
    

}
