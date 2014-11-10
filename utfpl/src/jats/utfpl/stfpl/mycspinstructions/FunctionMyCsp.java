package jats.utfpl.stfpl.mycspinstructions;

import java.util.List;

import jats.utfpl.instruction.TID;

public class FunctionMyCsp {
    public TID m_name;
    public List<MyCspTempID> m_paras;
    public List<MyCspGroup> m_body;
    
    public FunctionMyCsp(TID name, List<MyCspTempID> paras, List<MyCspGroup> body) {
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
