package jats.utfpl.stfpl.pats;

public class PProcEvent implements PProc {
    public PNodeEvent m_evt;
    public PProc m_proc;
    
    public PProcEvent(PNodeEvent evt, PProc proc) {
        m_evt = evt;
        m_proc = proc;
    }

    @Override
    public Object accept(PNodeVisitor visitor) {
        return visitor.visit(this);
    }

}
