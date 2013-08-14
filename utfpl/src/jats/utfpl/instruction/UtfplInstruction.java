package jats.utfpl.instruction;

public interface UtfplInstruction {
    Object accept(InsVisitor visitor);
    boolean hasSideEffect();
}
