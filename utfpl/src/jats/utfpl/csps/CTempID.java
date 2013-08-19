package jats.utfpl.csps;

import java.util.ArrayList;
import java.util.List;

import jats.utfpl.tree.TID;

public class CTempID implements CTemp {
    private TID m_tid;
   
    private StackLocation m_loc;
    
    private EntityLocation m_def;
    private List<EntityLocation> m_usageLst;
    
    public static class EntityLocation {
        public TID m_funLab;
        public CBlock m_grp;
        
        public EntityLocation(TID funLab, CBlock grp) {
            m_funLab = funLab;
            m_grp = grp;
        }
    }
    
    static public CTempID createDef(TID tid, TID funLab, CBlock grp) {
        return new CTempID(tid, new EntityLocation(funLab, grp));
    }
    
    private CTempID(TID tid, EntityLocation defLoc) {
        m_tid = tid;
        m_loc = null;
        m_def = defLoc;
        m_usageLst = new ArrayList<EntityLocation>();
    }
    
    
    public void addUsageLoc(TID funLab, CBlock grp) {
        m_usageLst.add(new EntityLocation(funLab, grp));
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
    
    public Object accept(CSPSVisitor visitor) {
        return visitor.visit(this);
    }
    

}
