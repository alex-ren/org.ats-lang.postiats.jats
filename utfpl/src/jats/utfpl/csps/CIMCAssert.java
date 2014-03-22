package jats.utfpl.csps;

public class CIMCAssert extends CInstruction {

    public CTemp m_localSrc;

    public CIMCAssert(CTemp localSrc, CBlock blk) {
        super(blk);
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
