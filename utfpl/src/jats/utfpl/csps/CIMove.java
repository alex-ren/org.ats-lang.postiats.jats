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
    
    @Override
    public Object accept(CSPSVisitor visitor) {
        return visitor.visit(this, m_blk);
    }

    @Override
    public CBlock getBlock() {
        return m_blk;
    }

    @Override
    public void process(int level) {
        m_holder.updateEscaped();
        if (m_holder.isEscaped()) {
            m_holder.toStack(level);
        }
        
        if (m_vp instanceof CTempID) {
            CTempID ctid = (CTempID)m_vp;
            m_vp = ctid.createForStack(level);  // create a new CTempID
        }
        
    }
}


