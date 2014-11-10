package jats.utfpl.stfpl.mycspinstructions;

public class CIStoreArray extends MyCspInstruction {
    public IMyCspTemp m_localSrc;
    public MyCspTempID m_globalVar;
    public IMyCspTemp m_localIndex;
    
    public CIStoreArray(IMyCspTemp localSrc, MyCspTempID globalVar, IMyCspTemp localIndex, MyCspGroup blk) {
        super(blk);
        m_localSrc = localSrc;
        m_globalVar = globalVar;
        m_localIndex = localIndex;
    }
    @Override
    public Object accept(IMyCspVisitor visitor) {
        return visitor.visit(this);
    }
    
    @Override
    public int process(int offset) {

        if (m_localSrc instanceof MyCspTempID) {
            ((MyCspTempID)m_localSrc).updateForUsage();
        }
        
        if (m_localIndex instanceof MyCspTempID) {
            ((MyCspTempID)m_localIndex).updateForUsage();
        }
        
        return offset;
        
    }

}
