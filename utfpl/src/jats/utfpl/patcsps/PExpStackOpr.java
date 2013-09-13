package jats.utfpl.patcsps;

public class PExpStackOpr implements PExp {
    
    public int m_frame;
    public int m_pos;
    
    public PExpStackOpr(int frame, int pos) {
        m_frame = frame;
        m_pos = pos;
    }

    @Override
    public Object accept(PNodeVisitor visitor) {
        return visitor.visit(this);
    }

}
