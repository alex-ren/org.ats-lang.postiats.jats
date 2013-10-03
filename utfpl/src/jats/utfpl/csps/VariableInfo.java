package jats.utfpl.csps;

import jats.utfpl.instruction.TID;
import jats.utfpl.patcsps.type.PATTypeSingleton;

import java.util.ArrayList;
import java.util.List;


public class VariableInfo {
    private TID m_tid;
    private EntityLocation m_defLoc;  // The position of the definition of this entity of TID.
    private StackPosition m_stackPos;  // The stack location of the definition.
    private List<EntityLocation> m_usageLst;
    private Boolean m_isEscaped;
    
    public static VariableInfo createGlobalVariable(TID tid) {
        // The definition of global variable doesn't have location.
        return new VariableInfo(tid, null);
    }
    
    public static VariableInfo create(TID tid, EntityLocation defLoc) {
        return new VariableInfo(tid, defLoc);
    }

    public VariableInfo(TID tid, EntityLocation defLoc) {
        m_tid = tid;
        m_defLoc = defLoc;
        m_stackPos = null;
        m_usageLst = new ArrayList<EntityLocation>();
        m_isEscaped = null;
    }
    
    public TID getTID() {
        return m_tid;
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
        return m_isEscaped;
    }
    
    
    public void addUsage(EntityLocation loc) {
        m_usageLst.add(loc);
    }
    
    public void updateEscaped() {
        if (m_tid.isPara()) {
            for (EntityLocation loc: m_usageLst) {
                if (loc.getLevel() != m_defLoc.getLevel()) {
                    m_isEscaped = true;
                    return;
                }
            }
            m_isEscaped = false;
            return;
        } else if (m_tid.isGlobal()) {
            m_isEscaped = false;
            return;
        } else if (m_tid.isLocal() || m_tid.isRet()) {
            for (EntityLocation loc: m_usageLst) {
                if (loc.getBlock() != m_defLoc.getBlock()) {
                    m_isEscaped = true;
                    return;
                }
            }
            m_isEscaped = false;
            return;
        } else if (m_tid == TID.ANONY){
            m_isEscaped = false;
            return;
        } else if (m_tid.getType() == PATTypeSingleton.cVoidType) {
            m_isEscaped = false;
         
        } else {
            throw new Error("check this");
//            m_isEscaped = false;
//            return;
        }
    }
    
    public boolean isOutofScope(EntityLocation curLoc) {
        if (m_tid.isPara()) {
            if (curLoc.getLevel() != m_defLoc.getLevel()) {
                return true;
            } else {
                return false;
            }
        } else if (m_tid.isGlobal()) {
            return false;
        } else if (m_tid.isFunc()) {
            return false;
        } else {  // local variable
            if (curLoc.getBlock() != m_defLoc.getBlock()) {
                return true;
            } else {
                return false;
            }
        }
    }
}

