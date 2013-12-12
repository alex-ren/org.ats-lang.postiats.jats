package jats.utfpl.patcsps;

public class PStatFrameNew implements PStat {

    @Override
    public Object accept(PNodeVisitor visitor) {
        return visitor.visit(this);
    }

}
