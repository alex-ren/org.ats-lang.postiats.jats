package jats.utfpl.tree;

import jats.utfpl.instruction.ValPrim;

public class TID implements ValPrim {
    static private int s_cnt = 0;
    static public TID ANONY = new TID("()", Type.other);
    
    enum Type {eLibFun, eGloVar, ePara, eUserFun, eLocalVar, eRetHolder, other};
    
    private String m_id;
    private int m_uid;
    private Type m_type;
    
    public boolean isLocal() {
        return Type.other == m_type;
    }
    
    public boolean isPara() {
        return Type.ePara == m_type;
    }
    
    public boolean isLibFun() {
        return Type.eLibFun == m_type;
    }
    
    public boolean isGlobal() {
        return Type.eGloVar == m_type;
    }
    
    public boolean isVoid() {
        return this == TID.ANONY;
    }
    
    public String getID() {
        return m_id;
    }
    
    public String toString() {
        if (Type.eLibFun == m_type) {
            return m_id;
        } else {
            return m_id + "_" + m_uid;
        }
    }
    
    private TID(String id, Type type) {
        m_id = id;
        
        s_cnt++;
        m_uid = s_cnt;
        m_type = type;

    }
    
    public static TID createLocalVar(String id) {
        TID tid = new TID(id, Type.eLocalVar);
        return tid;
    }
    
    public static TID createUserFun(String id) {
        TID tid = new TID(id, Type.eUserFun);
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
    
    public static TID createPara(String id) {
        TID tid = new TID(id, Type.ePara);
        return tid;
    }
    
    public static TID createRetHolder(String id) {
        TID tid = new TID(id, Type.eRetHolder);
        return tid;
    }
    
    
}
