package jats.utfpl.stfpl.mycspinstructions;

public class CILoadArray extends MyCspInstruction {
    public MyCspTempID m_localHolder;
    public MyCspTempID m_globalVar;
    public IMyCspTemp m_localIndex;
    
    public CILoadArray(MyCspTempID globalVar, IMyCspTemp localIndex, MyCspTempID localHolder, MyCspGroup blk) {
        super(blk);
        m_globalVar = globalVar;
        m_localIndex = localIndex;
        m_localHolder = localHolder;
        
    }
    @Override
    public Object accept(IMyCspVisitor visitor) {
        return visitor.visit(this);
    }

    @Override
    public int process(int offset) {
        offset = m_localHolder.processStack(offset);
        
        if (m_localIndex instanceof MyCspTempID) {
            ((MyCspTempID)m_localIndex).updateForUsage();
        }
        
        return offset;
    }

}
