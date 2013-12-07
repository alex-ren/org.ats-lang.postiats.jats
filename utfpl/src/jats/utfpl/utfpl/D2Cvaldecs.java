package jats.utfpl.utfpl;

import java.util.List;

public class D2Cvaldecs implements Id2ecl_node {
    public Evalkind m_knd;
    public List<Cv2aldec> m_v2ds;
    
    public D2Cvaldecs(Evalkind knd, List<Cv2aldec> v2ds) {
        m_knd = knd;
        m_v2ds = v2ds;
    }

}
