package jats.utfpl.csps;

public class CIMove extends CInstruction {
    public CTempID m_holder;
    public CTemp m_vp;


    public CIMove(CTemp vp, CTempID holder, CBlock blk) {
        super(blk);
        m_holder = holder;
        m_vp = vp;
    }
    
    public boolean needStack() {
        return m_holder.isEscaped();
    }
    
    @Override
    public Object accept(CSPSVisitor visitor) {
        return visitor.visit(this);
    }
    
    @Override
    public int process(int offset) {
        offset = m_holder.processStack(offset);
        
        if (m_vp instanceof CTempID) {
            ((CTempID)m_vp).updateForUsage();
        }
        
        return offset;
        
    }
}


