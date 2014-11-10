package jats.utfpl.stfpl.mcinstruction;

public class MCInsMCAtomicStart implements IMCInstruction {

    public MCInsMCAtomicStart() {}
    
    @Override
    public Object accept(IMCInsVisitor visitor) {
        return visitor.visit(this);
    }

    @Override
    public Boolean hasSideEffect() {
        return true;  // It's difficult to say this ins has effect.
                      // I may change my mind in the future.
    }

}
