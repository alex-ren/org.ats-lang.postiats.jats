package jats.utfpl.stfpl.mycspinstructions;

public class CIMCGet extends MyCspInstruction {

    public MyCspTempID m_localHolder;
    public MyCspTempID m_globalVar;

    public CIMCGet(MyCspTempID globalVar, MyCspTempID localHolder, MyCspGroup blk, boolean effect) {
        super(blk, effect);
        m_localHolder = localHolder;
        m_globalVar = globalVar;

    }
    @Override
    public Object accept(IMyCspInsVisitor visitor) {
        return visitor.visit(this);
    }
    
    @Override
    public int process(int offset) {
        offset = m_localHolder.processStack(offset);
        return offset;
    }

}
