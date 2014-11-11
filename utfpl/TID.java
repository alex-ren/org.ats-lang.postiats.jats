package jats.utfpl.instruction;

import jats.utfpl.patcsps.Aux;
import jats.utfpl.patcsps.type.PATType;
import jats.utfpl.patcsps.type.PATTypeBool;
import jats.utfpl.patcsps.type.PATTypeChannel;
import jats.utfpl.patcsps.type.PATTypeFunc;
import jats.utfpl.patcsps.type.PATTypeInt;
import jats.utfpl.patcsps.type.PATTypeSingleton;

import java.util.Map;


public class TID implements ValPrim {
    static private int s_cnt = 0;
    static public TID ANONY = new TID("__anony", Category.other, PATTypeSingleton.cVoidType, "_");
    
    enum Category {eLibFun, eGloVar, eGloValue, ePara, eUserFun, eLocalVar, eRetHolder, other};
    
    // members
    private String m_id;
    private Integer m_uid;
    private Category m_cat;
    private PATType m_type;
//    private boolean m_isSys;  // Whether this TID is for system name, which has no use of m_uid.
    public String m_trueName;  // name given by user
    private Aux.Address m_addr;
    // end of members
    
    private TID(String id, Category cat, PATType ty, String trueName) {
        m_id = id;
        
        s_cnt++;
        m_uid = s_cnt;
        m_cat = cat;
        m_type = ty;
        
        m_trueName = trueName;
        m_addr = null;

    }
    
    public boolean hasAddress() {
    	return null != m_addr;
    }
    
    public boolean isAnony() {
        return ANONY == this;
    }
    
    public TID dup() {
    	if (this == ANONY) {
    		return this;
    	} else {
            return new TID(m_id, m_cat, m_type, m_trueName);
    	}
    }
    
    public Aux.Address getAddr() {
        return m_addr;
    }
    
    public void updateAddr(Aux.Address addr) {
        m_addr = addr;
    }

    
    public PATType getType() {
        return m_type;
    }
    
    public PATType getFunReturnType() {
        return ((PATTypeFunc)m_type).getRetType();
    }
    
    public void updateType(PATType ty) {
        m_type = ty;
    }
    
    public boolean isBool() {
        return m_type instanceof PATTypeBool;
    }
    
    public boolean isInt() {
        return m_type instanceof PATTypeInt;
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
        return Category.eGloVar == m_cat || Category.eGloValue == m_cat;
    }
    
    public boolean isGlobalVariable() {
        return Category.eGloVar == m_cat;
    }
    
    public boolean isGlobalValue() {
        return Category.eGloValue == m_cat;
    }
    
    public boolean isFunc() {
        return m_type instanceof PATTypeFunc;
    }
//    
//    public boolean isVoid() {
//        return this == TID.ANONY;
//    }
    
    public boolean hasEffect() {
        if (m_type instanceof PATTypeFunc) {
            return ((PATTypeFunc)m_type).hasEffect();
        } else {
            throw new Error("Check this.");
        }
    }
//    
    public String getID() {
        return m_id;
    }
    
    public String toString() {
        if (null != m_trueName) {
            return m_trueName;
        } else {
            return m_id + "_" + m_uid;
        }
    }
    
    public static TID createLocalVar(String id, PATType ty) {
        TID tid = new TID(id, Category.eLocalVar, ty, null);
        return tid;
    }
    
    public static TID createUserFun(String id, String trueName) {
        TID tid = new TID(id, Category.eUserFun, new PATTypeFunc(), trueName);
        return tid;
    }
    
//    public static TID createUserFun(String id, PATTypeFunc ty) {
//        TID tid = new TID(id, Category.eUserFun, ty, false);
//        return tid;
//    }
    
    public static TID createLibFun(String id, PATTypeFunc ty) {
        TID tid = new TID(id, Category.eLibFun, ty, id);
        return tid;
    }
    
    public static TID createGloVar(String id, PATType ty) {
        TID tid = new TID(id, Category.eGloVar, ty, id);
        return tid;
    }
    
    public static TID createGloValue(String id) {
        TID tid = new TID(id, Category.eGloValue, PATTypeSingleton.cUnknownType, id);
        return tid;
    }
    
    public static TID createPara(String id, String trueName) {
        TID tid = new TID(id, Category.ePara, PATTypeSingleton.cUnknownType, trueName);
        return tid;
    }
    
    public static TID createRetHolder(String id) {
        TID tid = new TID(id, Category.eRetHolder, PATTypeSingleton.cUnknownType, null);
        return tid;
    }
    
    public static TID createChannel(String id, String trueName) {
        TID tid = new TID(id, Category.other, new PATTypeChannel(), trueName);
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
