package jats.utfpl.instruction;

import java.util.List;

public class FunctionInstruction {
    private TID m_name;
    private List<TID> m_paraLst;
    private List<TID> m_escParaLst;
    private List<UtfplInstruction> m_body;
//    private TID m_ret;
    
    public FunctionInstruction(TID name, 
            List<TID> paraLst, 
            List<TID> escParaLst,
            List<UtfplInstruction> body
            /*TID ret*/) {
        m_name = name;
        m_paraLst = paraLst;
        m_escParaLst = escParaLst;
        m_body = body;
//        m_ret = ret;
    }
    
    public TID getName() {
        return m_name;
    }

    public List<TID> getParaLst() {
        return m_paraLst;
    }
    
    public List<TID> getEscParaLst() {
        return m_escParaLst;
    }
    
    public List<UtfplInstruction> getBody() {
        return m_body;
    }
    
//    public TID getRet() {
//        return m_ret;
//    }
}
