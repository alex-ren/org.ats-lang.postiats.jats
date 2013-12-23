package jats.utfpl.csps;

public class CICondAlloc extends CInstruction {
    public CTempID m_holder;
    
    public CICondAlloc(CTempID holder, CBlock blk) {
        super(blk);
        m_holder = holder;
    }

    @Override
    public Object accept(CSPSVisitor visitor) {
        return visitor.visit(this);
    }

    @Override
    public int process(int offset) {
        offset = m_holder.processStack(offset);
        return offset;
    }
}

