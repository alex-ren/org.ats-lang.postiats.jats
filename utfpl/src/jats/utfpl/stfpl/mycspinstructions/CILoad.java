package jats.utfpl.stfpl.mycspinstructions;

public class CILoad extends MyCspInstruction {

    public MyCspTempID m_localHolder;
    public MyCspTempID m_globalVar;

    public CILoad(MyCspTempID globalVar, MyCspTempID localHolder, MyCspGroup blk) {
        super(blk);
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
