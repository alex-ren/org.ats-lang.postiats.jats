package jats.utfpl.patcsps;


import jats.utfpl.instruction.TID;

public class PProcCall implements PProc {
    public TID m_name;
    public boolean m_isTailCall;
//    public List<PExp> m_paraLst;
//    public boolean m_reuseStack;
    
//    public PProcCall(TID name, List<PExp> paraLst, boolean reuseStack) {
//        m_name = name;
//        m_paraLst = paraLst;
//        m_reuseStack = reuseStack;
//
//    }

    public PProcCall(TID name, boolean isTailCall) {
        m_name = name;
        m_isTailCall = isTailCall;
    }
    
    @Override
    public Object accept(PNodeVisitor visitor) {
        return visitor.visit(this);
    }
//    
//    public boolean isReuseStack() {
//        return m_reuseStack;
//    }

}
