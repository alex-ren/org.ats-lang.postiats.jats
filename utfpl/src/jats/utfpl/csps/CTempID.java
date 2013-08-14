package jats.utfpl.csps;

import jats.utfpl.tree.TID;

public class CTempID {
    static private enum Type {Def, Use};
    
    private TID m_tid;
    private Type m_type;
    private int m_stackLoc;

}
