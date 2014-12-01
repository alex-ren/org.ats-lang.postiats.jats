package jats.utfpl.stfpl.pats;


import jats.utfpl.stfpl.mcinstruction.MCSId;

public class PProcCall implements PProcNode {
    public MCSId m_name;
    public boolean m_isTailCall;
//    public List<PExp> m_paraLst;
//    public boolean m_reuseStack;
    
//    public PProcCall(TID name, List<PExp> paraLst, boolean reuseStack) {
//        m_name = name;
//        m_paraLst = paraLst;
//        m_reuseStack = reuseStack;
//
//    }

    public PProcCall(MCSId name, boolean isTailCall) {
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
