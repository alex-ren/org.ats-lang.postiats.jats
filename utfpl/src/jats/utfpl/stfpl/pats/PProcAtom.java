package jats.utfpl.stfpl.pats;

public class PProcAtom implements PProcNode {
    
    private PProcAtom() {}
    
    public static PProcAtom SKIP = new PProcAtom();

    @Override
    public Object accept(PNodeVisitor visitor) {
        return visitor.visit(this);
    }
    

}
