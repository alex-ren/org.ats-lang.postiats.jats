package jats.utfpl.csps;

public class CILoad extends CInstruction {

    public CTempID m_localHost;
    public CTempID m_globalVar;

    public CILoad(CTempID globalVar, CTempID localHost, CBlock blk) {
        super(blk);
        m_localHost = localHost;
        m_globalVar = globalVar;

    }
    @Override
    public Object accept(CSPSVisitor visitor) {
        return visitor.visit(this);
    }
    
    @Override
    public int process(int offset) {
        offset = m_localHost.processStack(offset);
        return offset;
    }

}
