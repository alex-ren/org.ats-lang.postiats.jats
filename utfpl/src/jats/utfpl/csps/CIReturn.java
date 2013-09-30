package jats.utfpl.csps;

public class CIReturn implements CInstruction {
    
    public CTempID m_id;
    
    private CBlock m_blk;
    
    public CIReturn(CTempID id, CBlock blk) {
        m_id = id;
        m_blk = blk;
    }

    @Override
    public Object accept(CSPSVisitor visitor) {
        return visitor.visit(this);
    }

    @Override
    public CBlock getBlock() {
        return m_blk;
    }

    @Override
    public int process(int offset) {
        m_id.updateForUsage(m_blk.getLevel());
        return offset;
        
    }

}
