package jats.utfpl.csps;

import jats.utfpl.tree.TID;

public class CTempID implements CTemp {
    private TID m_tid;
   
    private StackLocation m_loc;
    
    public CTempID(TID tid) {
        m_tid = tid;
        m_loc = null;
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

}
