package jats.utfpl.stfpl.mcinstruction;


public interface IMCInstruction {
    Object accept(IMCInsVisitor visitor);
    Boolean hasSideEffect();
}
