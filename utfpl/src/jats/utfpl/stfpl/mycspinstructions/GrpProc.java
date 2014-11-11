package jats.utfpl.stfpl.mycspinstructions;

import jats.utfpl.stfpl.mcinstruction.MCSId;

import java.util.List;

public class GrpProc extends MyCspGroup {
    public MCSId m_funlab;  // Currently we assume this is function name.
//    public List<CTemp> m_args;
//    public CTempID m_ret;  // The holder for the return value of the function call.
    public boolean m_isTail;
    
    public GrpProc(MCSId funlab, List<IMyCspTemp> args, MyCspTempID ret, boolean isTail) {
        m_funlab = funlab;
//        m_args = args;
//        m_ret = ret;
        m_isTail = isTail;

    }
    
    public GrpProc(MCSId funlab, boolean isTail) {
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
    public Object accept(IMyCspInsVisitor visitor) {
        return visitor.visit(this);
    }

    @Override
    public int process(int offset) {

        return offset;
    }
}
