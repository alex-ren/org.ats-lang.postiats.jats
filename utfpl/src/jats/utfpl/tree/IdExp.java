package jats.utfpl.tree;

import jats.utfpl.instruction.TID;
import jats.utfpl.patcsps.type.PATTypeSingleton;
import jats.utfpl.utils.MapScope;

public class IdExp implements Exp {
    public String m_sid;
    public TID m_tid;

    // id can be null.
    public IdExp(String id) {
        m_sid = id;
        m_tid = null;
    }

    public void updateForLocalDef(MapScope<TID> m) {
        if (null == m_sid || m_sid.equals("_")) {
            m_tid = TID.ANONY;
        } else {
            m_tid = TID.createLocalVar(m_sid, PATTypeSingleton.cUnknownType);
            m.addValue(m_sid, m_tid);
        }
    }

    public void updateForPara(MapScope<TID> m) {
        m_tid = TID.createPara(m_sid, false);
        m.addValue(m_sid, m_tid);
    }
    
    public void updateForGlobalDef(MapScope<TID> m) {
        if (null == m_sid /*val () = xxx */|| m_sid.equals("_") /*val _ = xxx */) {
            m_tid = TID.ANONY;
        } else {
            m_tid = TID.createGloValue(m_sid, false);
            m.addValue(m_sid, m_tid);
        }
    }
    
    public void updateForGlobalVar(MapScope<TID> m) {
        m_tid = TID.createGloVar(m_sid, false);
        m.addValue(m_sid, m_tid);

    }
    
    public void updateForUsage(MapScope<TID> m) {
        m_tid = m.getValue(m_sid);
        if (null == m_tid) {
            throw new Error("This shall not happen." + " And m_sid is " + m_sid);
        }
    }


    @Override
    public Object accept(TreeVisitor visitor) {
        return visitor.visit(this);
    }
}
