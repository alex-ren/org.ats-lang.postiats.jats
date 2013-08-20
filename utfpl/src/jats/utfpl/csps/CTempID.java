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
   
    private StackLocation m_loc;
    
    private EntityLocation m_def;
    private List<EntityLocation> m_usageLst;
    
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
    
    static public CTempID createDef(TID tid, TID funLab, CBlock grp, int level) {
        return new CTempID(tid, new EntityLocation(funLab, grp, level));
    }
    
    private CTempID(TID tid, EntityLocation defLoc) {
        m_tid = tid;
        m_loc = null;
        m_def = defLoc;
        m_usageLst = new ArrayList<EntityLocation>();
    }
    
    
    public void addUsageLoc(TID funLab, CBlock grp, int level) {
        m_usageLst.add(new EntityLocation(funLab, grp, level));
    }

    public boolean isEscaped() {
        // todo
        // Currently always return true.
        return true;
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
