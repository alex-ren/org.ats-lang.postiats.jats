package jats.utfpl.stfpl.instructions;

import jats.utfpl.stfpl.stype.ISType;

public class VNameId implements IVarName {
    
    
    public String m_name;
    public int m_stamp;
    public ISType m_stype;
    
    // Should be used inside factory
    public VNameId(String name, ISType stype, int stamp)  {
        m_name = name;
        m_stamp = stamp;
        m_stype = stype;
    }

    @Override
    public ISType getType() {
        return m_stype;
    }

    @Override
    public String toStringCS() {
        return toString();
    }

    @Override
    public String toStringIns() {
        return toString();
    }
    
    @Override
    public String toString() {
        return m_name + m_stamp + "_id";
    }


}
