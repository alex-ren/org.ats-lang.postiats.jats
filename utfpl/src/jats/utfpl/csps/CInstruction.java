package jats.utfpl.csps;

public interface CInstruction {

    public Object accept(CSPSVisitor visitor);
}
