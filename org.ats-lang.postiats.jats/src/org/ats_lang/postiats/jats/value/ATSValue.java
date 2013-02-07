package org.ats_lang.postiats.jats.value;

import org.ats_lang.postiats.jats.type.ATSType;
import org.ats_lang.postiats.jats.type.IntType;
import org.ats_lang.postiats.jats.type.PtrType;


/*
 * This is actually a holder instead of just a value.
 */
public class ATSValue {
    
    private Object m_mem;
    private ATSType m_type;
    
    public static final ATSValue NULL = new ATSValue();
    public static final ATSValue VOID = new ATSValue();
    
    public ATSValue() {}
    
    public ATSValue deRef() {
        if (m_type == PtrType.cType) {
            return (ATSValue)m_mem;
        } else {
            throw new Error("Cannot deref a non-pointer.");
        }
    }
    
    public ATSValue(ATSType type, Object mem) {
        m_type = type;
        m_mem = mem;
    }
    
    public ATSValue(int v) {
        this(IntType.cType, new Integer(v));
    }
    
    // for pointer
    public ATSValue(ATSValue v) {
        this(PtrType.cType, v);
    }
    
    public void update(ATSValue v) {
        m_type = v.m_type;
        m_mem = v.m_mem;
    }
}
