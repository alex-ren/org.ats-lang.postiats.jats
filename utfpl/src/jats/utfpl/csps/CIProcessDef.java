package jats.utfpl.csps;

import java.util.List;

import jats.utfpl.instruction.TID;

public class CIProcessDef implements CInstruction {
    public TID m_name;
    public List<CTempID> m_paras;
    public List<CBlock> m_body;
    public CBlock m_blk;
    
    public CIProcessDef(TID name, List<CTempID> paras, List<CBlock> body, CBlock blk) {
        m_name = name;
        m_paras = paras;
        m_body = body;
        m_blk = blk;
    }

    @Override
    public Object accept(CSPSVisitor visitor) {
        return visitor.visit(this);
    }

    @Override
    public CBlock getBlock() {
        return m_blk;
    }

    @Override
    public int process(int offset) {
        int newOffset = 0;
        for (CTempID para: m_paras) {
            newOffset = para.processFirstOccurrence(newOffset);
        }

        for (CBlock cb: m_body) {
            newOffset = cb.process(newOffset);
        }
        
        return offset;  // offset is not changed.
    }
    

}
