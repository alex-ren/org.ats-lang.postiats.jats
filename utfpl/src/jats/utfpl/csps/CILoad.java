package jats.utfpl.csps;

public class CILoad extends CInstruction {

    public CTempID m_localHolder;
    public CTempID m_globalVar;

    public CILoad(CTempID globalVar, CTempID localHolder, CBlock blk) {
        super(blk);
        m_localHolder = localHolder;
        m_globalVar = globalVar;

    }
    @Override
    public Object accept(CSPSVisitor visitor) {
        return visitor.visit(this);
    }
    
    @Override
    public int process(int offset) {
        offset = m_localHolder.processStack(offset);
        return offset;
    }

}
