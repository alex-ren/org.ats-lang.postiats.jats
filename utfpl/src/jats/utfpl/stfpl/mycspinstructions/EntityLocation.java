package jats.utfpl.stfpl.mycspinstructions;

import jats.utfpl.stfpl.mcinstruction.MCSId;

/*
 * Use EntityLocation as functional data structure, which means
 * it's immutable.
 */
public class EntityLocation {
    private MCSId m_funname;  // name of the function
    private MyCspGroup m_grp;
    
    static EntityLocation create(MCSId mid, MyCspGroup grp) {
        return new EntityLocation(mid, grp);
    }
    
    // For parameter and global value, the grp is actually null.
    private EntityLocation(MCSId mid, MyCspGroup grp) {
        m_funname = mid;
        m_grp = grp;
    }
    
    public MCSId getFunname() {
    	return m_funname;
    }

    public  MyCspGroup getBlock() {
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
            if (m_funname == obj.m_funname && m_grp == obj.m_grp) {
                return true;
            } else {
                return false;
            }
        }
    }
}