package jats.utfpl.patcsps;

import java.util.ArrayList;
import java.util.List;

public class PProcParallel implements PProc {
    
    public List<PProc> m_procLst;
    
    public PProcParallel(PProc proc1, PProc proc2) {
        m_procLst = new ArrayList<PProc>();
        m_procLst.add(proc1);
        m_procLst.add(proc2);        
    }

    @Override
    public Object accept(PNodeVisitor visitor) {
        return visitor.visit(this);
    }

}
