package jats.utfpl.stfpl.mycspinstructions;


public class GrpMCAtomicStart extends MyCspGroup {
  
  public GrpMCAtomicStart() {}
  
  @Override
  public Object accept(IMyCspInsVisitor visitor) {
      return visitor.visit(this);
  }

  @Override
  public int process(int offset) {

      return offset;
  }
}

