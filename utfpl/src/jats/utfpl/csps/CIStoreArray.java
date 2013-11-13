package jats.utfpl.csps;

public class CIStoreArray extends CInstruction {
    public CTemp m_localValue;
    public CTempID m_globalVar;
    public CTemp m_localIndex;
    
    public CIStoreArray(CTemp localValue, CTempID globalVar, CTemp localIndex, CBlock blk) {
        super(blk);
        m_localValue = localValue;
        m_globalVar = globalVar;
        m_localIndex = localIndex;
    }
    @Override
    public Object accept(CSPSVisitor visitor) {
        return visitor.visit(this);
    }
    
    @Override
    public int process(int offset) {

        if (m_localValue instanceof CTempID) {
            ((CTempID)m_localValue).updateForUsage();
        }
        
        if (m_localIndex instanceof CTempID) {
            ((CTempID)m_localIndex).updateForUsage();
        }
        
        return offset;
        
    }

}