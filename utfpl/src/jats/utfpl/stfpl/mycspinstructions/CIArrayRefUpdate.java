package jats.utfpl.stfpl.mycspinstructions;

public class CIArrayRefUpdate extends MyCspInstruction {

    public MyCspTempID m_ref;
    public IMyCspTemp m_pos;
    public IMyCspTemp m_v;

    public CIArrayRefUpdate(MyCspTempID ref, IMyCspTemp pos, IMyCspTemp v, MyCspGroup blk) {
        super(blk);
        m_ref = ref;
        m_pos = pos;
        m_v = v;

    }
    @Override
    public Object accept(IMyCspInsVisitor visitor) {
        return visitor.visit(this);
    }
    
    @Override
    public int process(int offset) {
    	m_ref.updateForUsage();
    	
        if (m_pos instanceof MyCspTempID) {
            ((MyCspTempID)m_pos).updateForUsage();
        }
        
        if (m_v instanceof MyCspTempID) {
            ((MyCspTempID)m_v).updateForUsage();
        }
        
        return offset;
        
    }

}
