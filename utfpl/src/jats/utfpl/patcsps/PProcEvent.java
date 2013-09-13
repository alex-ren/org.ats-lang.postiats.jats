package jats.utfpl.patcsps;

public class PProcEvent implements PProc {
    public PEvent m_evt;
    public PProc m_proc;
    
    public PProcEvent(PEvent evt, PProc proc) {
        m_evt = evt;
        m_proc = proc;
    }

    @Override
    public Object accept(PNodeVisitor visitor) {
        return visitor.visit(this);
    }

}
