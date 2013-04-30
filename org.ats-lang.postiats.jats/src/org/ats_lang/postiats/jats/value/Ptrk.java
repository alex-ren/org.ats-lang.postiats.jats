package org.ats_lang.postiats.jats.value;

import java.util.Map;

import org.ats_lang.postiats.jats.type.StructType;
import org.ats_lang.postiats.jats.value.ArrPtr.ArrayElement;

public class Ptrk {
    private int m_ptrval;
    private Object m_content;  // either ArrayElement or not    
    
    public Ptrk(Object obj) {
    	m_ptrval = 1;
    	m_content = obj;
    }

    // return the element stored in the lvalue pointed to by this pointer
    public Object getValue() {
        if (m_content instanceof ArrPtr.ArrayElement) {
            return ((ArrPtr.ArrayElement)m_content).get(); 
        } else if (m_content instanceof StructMember){
            return ((StructMember) m_content).get();
        } else {
            return m_content;
        }
    }

    
    // return the lvalue pointed to by the pointer
    public Object def() {
        return this;
    }
    
    // update the content of the lvalue pointed to by this pointer
    public void update(Object v) {
        if (m_content instanceof ArrPtr.ArrayElement) {
            ((ArrPtr.ArrayElement)m_content).set(v); 
        } else if (m_content instanceof StructMember) {
            ((StructMember) m_content).set(v);
        } else {
            m_content = v;
        }
    }
    
    public int subIndex(Ptrk from) {
        if (this.m_content instanceof ArrayElement && from.m_content instanceof ArrayElement) {
            ArrayElement left = (ArrayElement) this.m_content;
            ArrayElement right = (ArrayElement) from.m_content;
            int sub = left.getIndex() - right.getIndex();
            return sub * (left.getElementType().getSize());
        } else {
            throw new Error("Type mismatch");
        }
    }

    
    static public class StructMember {
        Map<String, Object> m_struct;
        String m_name;
        StructType m_structty;

        public StructMember(Map<String, Object> struct, String name, StructType structty) {
            m_struct = struct;
            m_name = name;
            m_structty = structty;
            if (m_structty == null) {
                throw new Error("Type info incomplete");
            }
            
            if (struct.get(name) instanceof Map<?, ?>) {
                throw new Error("not supported");
                // to support this, we need to change m_name to a list of names.
            }
        }
        
        public Object get() {
            return m_struct.get(m_name);
        }
        
        public void set(Object obj) {
            m_struct.put(m_name, obj);
        }

    }
    
}
