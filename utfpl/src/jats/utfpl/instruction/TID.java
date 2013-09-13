package jats.utfpl.instruction;

import java.util.Map;


public class TID implements ValPrim {
    static private int s_cnt = 0;
    static public TID ANONY = new TID("()", Category.other, Type.eVoid);
    static public TID MAIN = new TID("_main_", Category.other, Type.eUnknown);
    
    enum Category {eLibFun, eGloVar, ePara, eUserFun, eLocalVar, eRetHolder, other};
    enum Type {eBool, eInt, eVoid, eUnknown};
    
    private String m_id;
    private int m_uid;
    private Category m_cat;
    private Type m_type;
    
    public void setType(Type ty) {
        m_type = ty;
    }
    
    public boolean isBool() {
        return Type.eBool == m_type;
    }
    
    public boolean equals(String name) {
        return toString().equals(name);
    }
    
    public boolean isRet() {
        return Category.eRetHolder == m_cat;
    }
    
    public boolean isLocal() {
        return Category.eLocalVar == m_cat;
    }
    
    public boolean isPara() {
        return Category.ePara == m_cat;
    }
    
    public boolean isLibFun() {
        return Category.eLibFun == m_cat;
    }
    
    public boolean isGlobal() {
        return Category.eGloVar == m_cat;
    }
    
    public boolean isVoid() {
        return this == TID.ANONY;
    }
    
    public String getID() {
        return m_id;
    }
    
    public String toString() {
        if (Category.eLibFun == m_cat) {
            return m_id;
        } else {
            return m_id + "_" + m_uid;
        }
    }
    
    private TID(String id, Category cat, Type ty) {
        m_id = id;
        
        s_cnt++;
        m_uid = s_cnt;
        m_cat = cat;
        m_type = ty;

    }
    
    public static TID createLocalVar(String id, Type ty) {
        TID tid = new TID(id, Category.eLocalVar, ty);
        return tid;
    }
    
    public static TID createUserFun(String id) {
        TID tid = new TID(id, Category.eUserFun, Type.eUnknown);
        return tid;
    }
    
    public static TID createLibFun(String id) {
        TID tid = new TID(id, Category.eLibFun, Type.eUnknown);
        return tid;
    }
    
    public static TID createGloVar(String id) {
        TID tid = new TID(id, Category.eGloVar, Type.eUnknown);
        return tid;
    }
    
    public static TID createPara(String id) {
        TID tid = new TID(id, Category.ePara, Type.eUnknown);
        return tid;
    }
    
    public static TID createRetHolder(String id) {
        TID tid = new TID(id, Category.eRetHolder, Type.eUnknown);
        return tid;
    }
    
    static public TID subsTID(TID tid, Map<TID, TID> subMap) {
        TID newTID = subMap.get(tid);
        if (null == newTID) {
            return tid;
        } else {
            return newTID;
        }
    }

    
}
