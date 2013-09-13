package jats.utfpl.patcsps;

import java.util.List;

import jats.utfpl.instruction.TID;

public class PProcCall implements PProc {
    public TID m_name;
    public List<PExp> m_paraLst;
    
    public PProcCall(TID name, List<PExp> paraLst) {
        m_name = name;
        m_paraLst = paraLst;
    }

    @Override
    public Object accept(PNodeVisitor visitor) {
        return visitor.visit(this);
    }

}
