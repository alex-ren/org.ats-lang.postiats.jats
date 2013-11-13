package jats.utfpl.csps;

public class CIMutexAlloc extends CInstruction {
    CTempID m_holder;
    
    public CIMutexAlloc(CTempID holder, CBlock blk) {
        super(blk);
        m_holder = holder;
    }

    @Override
    public Object accept(CSPSVisitor visitor) {
        return visitor.visit(this);
    }

    @Override
    public int process(int offset) {
        offset = m_holder.processFirstOccurrence(offset);
        return offset;
    }

}
