package jats.utfpl.stfpl.mycspinstructions;


public class GrpMCAtomicEnd extends MyCspGroup {
  
  public GrpMCAtomicEnd() {}
  
  @Override
  public Object accept(IMyCspInsVisitor visitor) {
      return visitor.visit(this);
  }

  @Override
  public int process(int offset) {

      return offset;
  }
}

