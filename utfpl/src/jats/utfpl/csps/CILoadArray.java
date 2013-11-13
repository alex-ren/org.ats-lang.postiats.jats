package jats.utfpl.csps;

public class CILoadArray extends CInstruction {
    public CTempID m_localHolder;
    public CTempID m_globalVar;
    public CTemp m_localIndex;
    
    public CILoadArray(CTempID globalVar, CTemp localIndex, CTempID localHolder, CBlock blk) {
        super(blk);
        m_globalVar = globalVar;
        m_localIndex = localIndex;
        m_localHolder = localHolder;
        
    }
    @Override
    public Object accept(CSPSVisitor visitor) {
        return visitor.visit(this);
    }

    @Override
    public int process(int offset) {
        offset = m_localHolder.processStack(offset);
        
        if (m_localIndex instanceof CTempID) {
            ((CTempID)m_localIndex).updateForUsage();
        }
        
        return offset;
    }

}
