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
    
    // For parameter and global value, the grp is actually null.
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
        if (this == o) {
            return true;
        } else if (!(o instanceof EntityLocation)) {
            return false;
        } else {
            EntityLocation obj = (EntityLocation)o;
            if (m_funLab == obj.m_funLab && m_grp == obj.m_grp) {
                return true;
            } else {
                return false;
            }
        }
    }
}