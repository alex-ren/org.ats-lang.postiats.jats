package jats.utfpl.stfpl.mycspinstructions;

public class CIAtomRefUpdate extends MyCspInstruction {

    public MyCspTempID m_ref;
    public IMyCspTemp m_localSrc;

    public CIAtomRefUpdate(IMyCspTemp localSrc, MyCspTempID ref, MyCspGroup blk) {
        super(blk);
        m_ref = ref;
        m_localSrc = localSrc;

    }
    @Override
    public Object accept(IMyCspInsVisitor visitor) {
        return visitor.visit(this);
    }
    
    @Override
    public int process(int offset) {
    	m_ref.updateForUsage();
        if (m_localSrc instanceof MyCspTempID) {
            ((MyCspTempID)m_localSrc).updateForUsage();
        }
        
        return offset;
        
    }

}
