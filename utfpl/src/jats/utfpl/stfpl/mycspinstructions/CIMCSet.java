package jats.utfpl.stfpl.mycspinstructions;

public class CIMCSet extends MyCspInstruction {

    public MyCspTempID m_globalVar;
    public IMyCspTemp m_localSrc;

    public CIMCSet(IMyCspTemp localSrc, MyCspTempID globalVar, MyCspGroup blk, boolean effect) {
        super(blk, effect);
        m_globalVar = globalVar;
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
