package jats.utfpl.stfpl.pats;

import jats.utfpl.instruction.TID;

import java.util.List;

public class PGDecProc implements PGDec {
    public TID m_name;
    public List<TID> m_paraLst;
    public PProc m_body;
    
    public PGDecProc(TID name, List<TID> paraLst, PProc body) {
        m_name = name;
        m_paraLst = paraLst;
        m_body = body;
    }

    @Override
    public Object accept(PNodeVisitor visitor) {
        return visitor.visit(this);
    }

}
