package org.ats_lang.postiats.jats.value;

public class ArrPtr {
    private ArrayElement m_content;
    
    public ArrPtr(ArrayElement obj) {
        m_content = obj;
    }
    
    public void inc() {
        m_content.inc();
    }
    
    public void dec() {
        m_content.dec();
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
        
        public void inc() {
            m_index++;
        }
        
        public void dec() {
            m_index--;
        }
        
        public void set(Object obj) {
            m_arr[m_index] = obj;
        }

    }
}
