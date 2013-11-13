package jats.utfpl.csps;

public class CIStore extends CInstruction {

    public CTempID m_globalVar;
    public CTemp m_localSrc;

    public CIStore(CTemp localSrc, CTempID globalVar, CBlock blk) {
        super(blk);
        m_globalVar = globalVar;
        m_localSrc = localSrc;

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
        
        return offset;
        
    }

}
