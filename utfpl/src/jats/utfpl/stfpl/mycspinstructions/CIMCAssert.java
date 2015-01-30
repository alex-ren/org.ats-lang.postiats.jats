package jats.utfpl.stfpl.mycspinstructions;

public class CIMCAssert extends MyCspInstruction {

    public IMyCspTemp m_localSrc;

    public CIMCAssert(IMyCspTemp localSrc, MyCspGroup blk, boolean effect) {
        super(blk, effect);
        m_localSrc = localSrc;

    }
    @Override
    public Object accept(IMyCspInsVisitor visitor) {
        return visitor.visit(this);
    }
     
    @Override
    public int process(int offset) {
        if (m_localSrc instanceof MyCspTempID) {
            ((MyCspTempID)m_localSrc).updateForUsage();
        }
        
        return offset;
        
    }

}
