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

}
