package jats.utfpl.patcsps;

import java.util.List;

public class PStatProcCallPrelogue implements PStat {
    
    public List<PExp> m_args;
    public boolean m_isTailCall;
    
    public PStatProcCallPrelogue(List<PExp> args, boolean isTailCall) {
        m_args = args;
        m_isTailCall = isTailCall;
    }

    @Override
    public Object accept(PNodeVisitor visitor) {
        return visitor.visit(this);
    }

}
