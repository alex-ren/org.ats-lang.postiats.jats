package jats.utfpl.patcsps;

import jats.utfpl.instruction.TID;

import java.util.List;

public class PGDecProc implements PGDec {
    public TID m_name;
    public List<TID> m_paraLst;
    public List<TID> m_escParaLst;
    public PProc m_body;
    
    public PGDecProc(TID name, List<TID> paraLst, List<TID> escParaLst, PProc body) {
        m_name = name;
        m_paraLst = paraLst;
        m_escParaLst = escParaLst;
        m_body = body;
    }

    @Override
    public Object accept(PNodeVisitor visitor) {
        return visitor.visit(this);
    }

}
