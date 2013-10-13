package jats.utfpl.patcsps;

import java.util.List;

import jats.utfpl.instruction.TID;

public class PProcCall implements PProc {
    public TID m_name;
    public List<PExp> m_paraLst;
    public boolean m_reuseStack;
    
    public PProcCall(TID name, List<PExp> paraLst, boolean reuseStack) {
        m_name = name;
        m_paraLst = paraLst;
        m_reuseStack = reuseStack;

    }

    @Override
    public Object accept(PNodeVisitor visitor) {
        return visitor.visit(this);
    }
    
    public boolean isReuseStack() {
        return m_reuseStack;
    }

}
