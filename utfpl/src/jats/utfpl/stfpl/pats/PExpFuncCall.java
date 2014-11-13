package jats.utfpl.stfpl.pats;

import java.util.List;

import jats.utfpl.instruction.TID;

public class PExpFuncCall implements PExp {
    public TID m_funLab;
    public List<PExp> m_argLst;
    
    public PExpFuncCall(TID funLab, List<PExp> argLst) {
        m_funLab = funLab;
        m_argLst = argLst;
    }

    @Override
    public Object accept(PNodeVisitor visitor) {
        return visitor.visit(this);
    }

}
