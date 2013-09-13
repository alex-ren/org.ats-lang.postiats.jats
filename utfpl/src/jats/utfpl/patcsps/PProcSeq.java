package jats.utfpl.patcsps;

public class PProcSeq implements PProc {
    public PProc m_procLeft;
    public PProc m_procRight;
    
    public PProcSeq(PProc left, PProc right) {
        m_procLeft = left;
        m_procRight = right;
    }

    @Override
    public Object accept(PNodeVisitor visitor) {
        return visitor.visit(this);
    }

}
