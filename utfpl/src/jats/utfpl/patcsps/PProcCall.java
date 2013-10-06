package jats.utfpl.patcsps;

import java.util.List;

import jats.utfpl.instruction.TID;

public class PProcCall implements PProc {
    public TID m_name;
    public List<PExp> m_paraLst;
    public boolean m_isTailCall;
    
    public PProcCall(TID name, List<PExp> paraLst, boolean isTailCall) {
        m_name = name;
        m_paraLst = paraLst;
        m_isTailCall = isTailCall;
    }

    @Override
    public Object accept(PNodeVisitor visitor) {
        return visitor.visit(this);
    }
    
    public boolean isTailCall() {
        return m_isTailCall;
    }

}
