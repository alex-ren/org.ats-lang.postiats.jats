package jats.utfpl.csps;

public class CIMove implements CInstruction {
    public CTempID m_holder;
    public CTemp m_vp;
    
    private CBlock m_blk;

    public CIMove(CTempID holder, CTemp vp, CBlock blk) {
        m_holder = holder;
        m_vp = vp;
        m_blk = blk;
    }
    
    public boolean isRet() {
        return m_holder.isRet();
    }
    public boolean needStack() {
        return m_holder.isEscaped();
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
        offset = m_holder.processFirstOccurrence(offset);
        
        if (m_vp instanceof CTempID) {
            ((CTempID)m_vp).updateForUsage(m_blk.getLevel());  // create a new CTempID
        }
        
        return offset;
        
    }
}


