package jats.utfpl.stfpl.pats;

import java.util.List;

public class PEvent implements PNode {
    
    public List<PStat> m_statLst;
    
    public PEvent(List<PStat> statLst) {
        m_statLst = statLst;
    }
    
    @Override
    public Object accept(PNodeVisitor visitor) {
        return visitor.visit(this);
    }

}
