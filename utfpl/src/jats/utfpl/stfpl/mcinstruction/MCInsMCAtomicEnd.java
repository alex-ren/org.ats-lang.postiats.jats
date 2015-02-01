package jats.utfpl.stfpl.mcinstruction;

public class MCInsMCAtomicEnd implements IMCInstruction {

    public MCInsMCAtomicEnd() {}
    
    @Override
    public Object accept(IMCInsVisitor visitor) {
        return visitor.visit(this);
    }

    @Override
    public Boolean hasSideEffect() {
        return false;
    }

}
