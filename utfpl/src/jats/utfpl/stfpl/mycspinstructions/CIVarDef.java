package jats.utfpl.stfpl.mycspinstructions;

/*
 * The existence of "CICond" cause the usage of this class.
 * This instruction only appears inside a CBEvent.
 */
public class CIVarDef extends MyCspInstruction {
    public MyCspTempID m_id;
    
    public CIVarDef(MyCspTempID id, MyCspGroup blk) {
        super(blk);
        m_id = id;
    }

    @Override
    public Object accept(IMyCspVisitor visitor) {
        return visitor.visit(this);
    }

    @Override
    public int process(int offset) {
        offset = m_id.processStack(offset);
        return offset;
    }

}
