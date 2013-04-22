package org.ats_lang.postiats.jats.value;

import java.util.Map;

import org.ats_lang.postiats.jats.type.ATSType;
import org.ats_lang.postiats.jats.type.RefType;

public class Ptrk {
    private int m_ptrval;
    private Object m_content;  // either ArrayElement or not
    
    public Ptrk(ATSType ty) {
        m_ptrval = 1;
        m_content = ty.createDefault();
    }

    // return the element stored in the lvalue pointed to by this pointer
    public Object getValue() {
        if (m_content instanceof ArrayElement) {
            return ((ArrayElement)m_content).get(); 
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
        if (m_content instanceof ArrayElement) {
            ((ArrayElement)m_content).set(v); 
        } else {
            m_content = v;
        }
    }

    static public class ArrayElement {
        Object[] m_arr;
        int m_index;

        public ArrayElement(Object[] array, int index) {
            m_arr = array;
            m_index = index;
        }
        
        public Object get() {
            return m_arr[m_index];
        }
        
        public void set(Object obj) {
            m_arr[m_index] = obj;
        }

    }
    
    static public class StructMember {
        Map<String, Object> m_struct;
        String m_name;

        public StructMember( Map<String, Object> struct, String name) {
            m_struct = struct;
            m_name = name;
        }
        
        public Object get() {
            return m_struct.get(m_name);
        }
        
        public void set(Object obj) {
            m_struct.put(m_name, obj);
        }

    }
    
}
