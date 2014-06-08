package jats.utfpl.utfpl.stype;

import java.util.List;

public class AppType extends BoxedType {
    private String m_con;
    private List<ISType> m_tys;
    
    public AppType(String con, List<ISType> tys) {
        if (tys == null) {
            throw new Error("Not allowed");
        }
        m_con = con;
        m_tys = tys;
    }
}
