package jats.utfpl.tree;

import jats.utfpl.instruction.ValPrim;

public class TID implements ValPrim {
    static private int s_cnt = 0;
    
    private String m_id;
    private int m_uid;
    private boolean m_literal;
    
    private boolean m_used;
    
    public boolean isVoid() {
        return m_id.equals("_");
    }
    
    public void setUsed() {
        m_used = true;
    }
    
    public boolean getUsed() {
        return !isVoid() && m_used;
    }
    
    public boolean isLiteral() {
        return m_literal;
    }
    
    public String getID() {
        return m_id;
    }
    
    public String toString() {
        if (m_literal) {
            return m_id;
        } else {
            return m_id + "_" + m_uid;
        }
    }
    
    private TID(String id, boolean isLiteral) {
        m_id = id;
        
        s_cnt++;
        m_uid = s_cnt;
        m_literal = isLiteral;
        
        m_used = false;
    }
    
    public static TID create(String id) {
        TID tid = new TID(id, false);
        return tid;
    }
    
    public static TID createUnique(String id) {
        TID tid = new TID(id, true);
        return tid;
    }
}
