package jats.utfpl.csps;

import jats.utfpl.instruction.TID;

public class EntityLocation {
    private TID m_funLab;
    private int m_level;
    private CBlock m_grp;
    
    static EntityLocation create(TID funLab, CBlock grp, int level) {
        return new EntityLocation(funLab, grp, level);
    }
    
    // Though, CBlock contains the information of level,
    // for parameter and global value, the grp is actually null.
    // Therefore, we need the information of level.
    private EntityLocation(TID funLab, CBlock grp, int level) {
        m_funLab = funLab;
        m_level = level;
        m_grp = grp;
    }
    
    public TID getFunLab() {
        return m_funLab;
    }
    
    public int getLevel() {
        return m_level;
    }
    
    public  CBlock getBlock() {
        return m_grp;
    }
    
    @Override
    public boolean equals(Object o) {
        throw new Error("not supported");
    }
}