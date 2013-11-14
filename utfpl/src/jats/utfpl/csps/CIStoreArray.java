package jats.utfpl.csps;

public class CIStoreArray extends CInstruction {
    public CTemp m_localSrc;
    public CTempID m_globalVar;
    public CTemp m_localIndex;
    
    public CIStoreArray(CTemp localSrc, CTempID globalVar, CTemp localIndex, CBlock blk) {
        super(blk);
        m_localSrc = localSrc;
        m_globalVar = globalVar;
        m_localIndex = localIndex;
    }
    @Override
    public Object accept(CSPSVisitor visitor) {
        return visitor.visit(this);
    }
    
    @Override
    public int process(int offset) {

        if (m_localSrc instanceof CTempID) {
            ((CTempID)m_localSrc).updateForUsage();
        }
        
        if (m_localIndex instanceof CTempID) {
            ((CTempID)m_localIndex).updateForUsage();
        }
        
        return offset;
        
    }

}
