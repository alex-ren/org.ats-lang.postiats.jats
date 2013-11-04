package jats.utfpl.instruction;

public interface GlobalEntity {
    Object accept(InsVisitor visitor);
}
