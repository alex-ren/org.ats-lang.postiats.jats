package jats.utfpl.csps;

import java.util.List;

import jats.utfpl.instruction.TID;

public class FunctionCSPS {
    public TID m_name;
    public List<CTempID> m_paras;
    public List<CBlock> m_body;
    
    public FunctionCSPS(TID name, List<CTempID> paras, List<CBlock> body) {
        m_name = name;
        m_paras = paras;
        m_body = body;
    }
//
//    @Override
//    public int process(int offset) {
//        int newOffset = 0;
//        for (CTempID para: m_paras) {
//            newOffset = para.processFirstOccurrence(newOffset);
//        }
//
//        for (CBlock cb: m_body) {
//            newOffset = cb.process(newOffset);
//        }
//        
//        return offset;  // offset is not changed.
//    }
//    

}
