package jats.utfpl.tree;

import jats.utfpl.ccomp.CCompUtils;
import jats.utfpl.instruction.TID;
import jats.utfpl.patcsps.type.PATType;
import jats.utfpl.patcsps.type.PATTypeSingleton;
import jats.utfpl.utils.MapScope;

public class ExpId extends IExp {
    public String m_sid;
    public TID m_tid;

    // id can be null.
    public ExpId(Location loc, String id) {
        super(loc);
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
        m_tid = TID.createPara(m_sid, null);
        m.addValue(m_sid, m_tid);
    }
    
    public void updateForGlobalDef(MapScope<TID> m) {
        if (null == m_sid /*val () = xxx */|| m_sid.equals("_") /*val _ = xxx */) {
            m_tid = TID.ANONY;
        } else {
            m_tid = TID.createGloValue(m_sid);
            m.addValue(m_sid, m_tid);
        }
    }
    
    public void updateForGlobalVar(MapScope<TID> m, PATType ty) {
//        System.out.println("======== updateForGlobalVar, name is " + m_sid);
        m_tid = TID.createGloVar(m_sid, ty);
        m.addValue(m_sid, m_tid);

    }
    
    public void updateForUsage(MapScope<TID> m) {
        m_tid = m.getValue(m_sid);
        if (null == m_tid) {
            throw new Error("This shall not happen." + " And m_sid is " + m_sid);
        }
    }

	// public boolean isSysGvalCreate() {
	// return m_sid.equals(CCompUtils.cSysGvalCreate);
	// }

	public boolean isSysGvarCreate() {
		return m_sid.equals(CCompUtils.cSysGvarCreate);
	}

	public boolean isSysGvarUpdate() {
		return m_sid.equals(CCompUtils.cSysGvarUpdate);
	}
	
	public boolean isSysGvarGet() {
		return m_sid.equals(CCompUtils.cSysGvarGet);
	}
	
	/* ************ ************** */
	
    public boolean isSysGarrCreate() {
        return m_sid.equals(CCompUtils.cSysGarrCreate);
    }

    public boolean isSysGarrUpdate() {
        return m_sid.equals(CCompUtils.cSysGarrUpdate);
    }
    
    public boolean isSysGarrGet() {
        return m_sid.equals(CCompUtils.cSysGarrGet);
    }

    public boolean isSysMCGetInt() {
        return m_sid.equals(CCompUtils.cSysMCGetInt);
    }

	public boolean isSysThreadCreate() {
		return m_sid.equals(CCompUtils.cSysThreadCreate);
	}

    /* ************ ************** */
    
	@Override
	public Object accept(TreeVisitor visitor) {
		return visitor.visit(this);
	}
}
