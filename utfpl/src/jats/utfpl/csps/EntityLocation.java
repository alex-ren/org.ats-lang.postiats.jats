package jats.utfpl.csps;

import jats.utfpl.instruction.TID;


/*
 * Use EntityLocation is functional data structure, which means
 * it's immutable.
 */
public class EntityLocation {
    private TID m_funLab;
    private CBlock m_grp;
    
    static EntityLocation create(TID funLab, CBlock grp) {
        return new EntityLocation(funLab, grp);
    }
    
    // Though, CBlock contains the information of level,
    // for parameter and global value, the grp is actually null.
    // Therefore, we need the information of level.
    private EntityLocation(TID funLab, CBlock grp) {
        m_funLab = funLab;
        m_grp = grp;
    }
    
    public TID getFunLab() {
        return m_funLab;
    }

    public  CBlock getBlock() {
        return m_grp;
    }
    
    @Override
    public boolean equals(Object o) {
        throw new Error("not supported");
    }
}