package jats.utfpl.tree;

import jats.utfpl.instruction.ValPrim;

public class TID implements ValPrim {
    static private int s_cnt = 0;
    static public TID ANONY = new TID("()", Type.other);
    
    enum Type {eLibFun, eGloVar, other};
    
    private String m_id;
    private int m_uid;
    private Type m_type;
    private boolean m_declared;  // indicating that the TID has been declared 
    
    private boolean m_used;
    
    public boolean isVoid() {
        return this == TID.ANONY;
    }
    
    public void setUsed() {
        m_used = true;
    }
    
    public void setDeclared() {
    	m_declared = true;
    }
    
    public boolean getUsed() {
        return !isVoid() && m_used;
    }
    
//    public boolean isLiteral() {
//        return m_literal;
//    }
    
    public String getID() {
        return m_id;
    }
    
    public String toString() {
        if (Type.eLibFun == m_type) {
            return m_id;
        } else {
            return m_id + "_gv" + m_uid;
        }
    }
    
    private TID(String id, Type type) {
        m_id = id;
        
        s_cnt++;
        m_uid = s_cnt;
        m_type = type;
        
        m_used = false;
        m_declared = false;
    }
    
    public static TID create(String id) {
        TID tid = new TID(id, Type.other);
        return tid;
    }
    
    public static TID createLibFun(String id) {
        TID tid = new TID(id, Type.eLibFun);
        return tid;
    }
    
    public static TID createGloVar(String id) {
        TID tid = new TID(id, Type.eGloVar);
        return tid;
    }
    
//    public static TID createUnique(String id) {
//        TID tid = new TID(id, true);
//        return tid;
//    }
}
