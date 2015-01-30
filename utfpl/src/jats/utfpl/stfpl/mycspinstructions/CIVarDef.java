package jats.utfpl.stfpl.mycspinstructions;

/*
 * The existence of "CICond" cause the usage of this class.
 * This instruction only appears inside a CBEvent.
 * var x;
 * if ... {
 *   x = 1;
 * } else {
 *   x = 2;
 * }
 */
public class CIVarDef extends MyCspInstruction {
    public MyCspTempID m_id;
    
    public CIVarDef(MyCspTempID id, MyCspGroup blk) {
        super(blk, false);
        m_id = id;
    }

    @Override
    public Object accept(IMyCspInsVisitor visitor) {
        return visitor.visit(this);
    }

    @Override
    public int process(int offset) {
        offset = m_id.processStack(offset);
        return offset;
    }

}
