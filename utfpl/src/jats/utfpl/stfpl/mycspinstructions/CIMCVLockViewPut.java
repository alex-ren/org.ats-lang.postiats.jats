package jats.utfpl.stfpl.mycspinstructions;


public class CIMCVLockViewPut extends MyCspInstruction {

    public MyCspTempID m_v;

    public CIMCVLockViewPut(MyCspTempID v, MyCspGroup blk) {
        super(blk);
        m_v = v;

    }
    @Override
    public Object accept(IMyCspInsVisitor visitor) {
        return visitor.visit(this);
    }
     
    @Override
    public int process(int offset) {
    	m_v.updateForUsage();
        
        return offset;
        
    }

}
