package jats.utfpl.csps;

/*
 * The existence of "CICond" cause the usage of this class.
 */
public class CIVarDef extends CInstruction {
    public CTempID m_id;
    
    public CIVarDef(CTempID id, CBlock blk) {
        super(blk);
        m_id = id;
    }

    @Override
    public Object accept(CSPSVisitor visitor) {
        return visitor.visit(this);
    }

    @Override
    public int process(int offset) {
        offset = m_id.processStack(offset);
        return offset;
    }

}
