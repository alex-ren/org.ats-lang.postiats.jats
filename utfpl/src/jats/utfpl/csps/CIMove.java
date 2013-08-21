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
    public int process(int offset) {
        m_holder.updateEscaped();
        if (m_holder.isEscaped()) {
            offset = m_holder.updateStackLocation(offset);
        }
        
        if (m_vp instanceof CTempID) {
            CTempID ctid = (CTempID)m_vp;
            if (ctid.isEscaped()) {
                m_vp = ctid.createForStack(m_blk.getLevel());  // create a new CTempID
            }
        }
        
        return offset;
        
    }
}


