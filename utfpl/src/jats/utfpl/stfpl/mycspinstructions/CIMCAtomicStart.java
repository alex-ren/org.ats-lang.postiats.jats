package jats.utfpl.stfpl.mycspinstructions;

public class CIMCAtomicStart extends MyCspInstruction {

	public CIMCAtomicStart(MyCspGroup blk, boolean effect) {
	    super(blk, effect);
    }


    @Override
    public Object accept(IMyCspInsVisitor visitor) {
        return visitor.visit(this);
    }

    @Override
    public int process(int offset) {
        return offset;
    }

}
