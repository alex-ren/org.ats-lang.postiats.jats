package jats.utfpl.stfpl.mycspinstructions;

public class CIStore extends MyCspInstruction {

    public MyCspTempID m_globalVar;
    public IMyCspTemp m_localSrc;

    public CIStore(IMyCspTemp localSrc, MyCspTempID globalVar, MyCspGroup blk) {
        super(blk);
        m_globalVar = globalVar;
        m_localSrc = localSrc;

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
        
        return offset;
        
    }

}
