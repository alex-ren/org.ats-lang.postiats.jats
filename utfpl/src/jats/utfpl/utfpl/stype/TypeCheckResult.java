package jats.utfpl.utfpl.stype;

public class TypeCheckResult {
    
    private boolean m_ret;
    private String m_msg; 
    
    public TypeCheckResult() {
        m_ret = true;
        m_msg = null;
    }
    
    public TypeCheckResult(String msg) {
        m_ret = false;
        m_msg = msg;
    }
    
    public boolean isGood() {
        return m_ret;
    }
    
    public String getMsg() {
        return m_msg;
    }

}
