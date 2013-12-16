package jats.utfpl.csps;

public class CIReturn extends CInstruction {
    
    public CTemp m_v;

    public CIReturn(CTemp v, CBlock blk) {
        super(blk);
        m_v = v;
    }

    @Override
    public Object accept(CSPSVisitor visitor) {
        return visitor.visit(this);
    }

    @Override
    public int process(int offset) {
        if (m_v instanceof CTempID) {
            ((CTempID)m_v).updateForUsage();
        }

        // The return value would be put onto the stack.
        return offset + 1;
        
    }

}
