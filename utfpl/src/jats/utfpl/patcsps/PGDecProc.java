package jats.utfpl.patcsps;

import jats.utfpl.instruction.TID;

import java.util.List;

public class PGDecProc implements PGDec {
    public TID m_name;
    public List<TID> m_paraLst;
    public List<TID> m_escParaLst;  // m_escParaLst is actually part 
                                    // of m_paraLst. Element in m_escParaLst
                                    // should be put onto stack.
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
