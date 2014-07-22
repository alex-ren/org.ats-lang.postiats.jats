package jats.utfpl.stfpl.instructions;

import jats.utfpl.stfpl.stype.ISType;
import jats.utfpl.stfpl.stype.VoidType;

public class VNameId implements IVarName {
    
    private static int s_stamp = 0;
    
    public static VNameId s_anony = new VNameId("__anony", VoidType.cInstance);
    
    public String m_name;
    public int m_stamp;
    public ISType m_stype;
    
    public VNameId(String name, ISType stype)  {
        m_name = name;
        m_stamp = s_stamp;
        ++s_stamp;
        m_stype = stype;
    }

    @Override
    public ISType getSType() {
        return m_stype;
    }


}
