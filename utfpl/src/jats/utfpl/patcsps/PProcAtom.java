package jats.utfpl.patcsps;

public class PProcAtom implements PProc {
    
    private PProcAtom() {}
    
    public static PProcAtom SKIP = new PProcAtom();

    @Override
    public Object accept(PNodeVisitor visitor) {
        return visitor.visit(this);
    }
    

}