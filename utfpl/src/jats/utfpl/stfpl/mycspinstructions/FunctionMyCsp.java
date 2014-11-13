package jats.utfpl.stfpl.mycspinstructions;

import java.util.List;

public class FunctionMyCsp {
    public MyCspTempID m_name;
    public List<MyCspTempID> m_paras;
    public MyCspTempID m_envname;
    public List<MyCspGroup> m_body;
    
    public FunctionMyCsp(MyCspTempID name, List<MyCspTempID> paras, MyCspTempID envname, List<MyCspGroup> body) {
        m_name = name;
        m_paras = paras;
        m_envname = envname;
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
