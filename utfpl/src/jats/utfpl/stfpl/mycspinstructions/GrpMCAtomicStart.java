package jats.utfpl.stfpl.mycspinstructions;

import jats.utfpl.stfpl.mcinstruction.MCSId;

import java.util.List;

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

