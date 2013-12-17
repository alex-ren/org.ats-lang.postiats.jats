package jats.utfpl.csps;

import java.util.List;

import jats.utfpl.instruction.TID;

public class CBProc extends CBlock {
    public TID m_funlab;  // Don't support function pointer.
//    public List<CTemp> m_args;
//    public CTempID m_ret;  // The holder for the return value of the function call.
    public boolean m_isTail;
    
    public CBProc(TID funlab, List<CTemp> args, CTempID ret, boolean isTail) {
        m_funlab = funlab;
//        m_args = args;
//        m_ret = ret;
        m_isTail = isTail;

    }
    
    public CBProc(TID funlab, boolean isTail) {
        m_funlab = funlab;
//        m_args = null;
//        m_ret = null;
        m_isTail = isTail;

    }
    
//    public void reset(List<CTemp> args, CTempID ret) {
////        m_args = args;
////        m_ret = ret;
//        if (m_isTail && !m_ret.isRet()) {
//            throw new Error("conflict");
//        }
//    }
    
    public boolean isTailCall() {
        return m_isTail;
    }
    
    @Override
    public Object accept(CSPSVisitor visitor) {
        return visitor.visit(this);
    }

    @Override
    public int process(int offset) {

        return offset;
    }
}
