package org.ats_lang.postiats.jats.value;

import org.ats_lang.postiats.jats.type.ATSType;

public class ArrPtr {
    private ArrayElement m_content;
    
    
    private ArrPtr(Object[] arr, ATSType elety) {
        m_content = new ArrayElement(arr, 0, elety);
    }
    
    public ArrPtr(ArrPsz arrpsz) {
        this(arrpsz.getPtr(), arrpsz.getElementType());
    }
    
    public ArrPtr(ArrayElement obj) {
        m_content = obj;
    }
    
    public void inc() {
        m_content.inc();
    }
    
    public void dec() {
        m_content.dec();
    }
    
    public void addByteSize(int len) {
        m_content.addByteSize(len);
    }

    static public class ArrayElement {
        private Object[] m_arr;
        private int m_index;
        private ATSType m_elety;

        public ArrayElement(Object[] array, int index, ATSType elety) {
            m_arr = array;
            m_index = index;
            m_elety = elety;
            if (elety == null) {
                throw new Error("Type info incomplete");
            }
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
        
        public void addByteSize(int len) {
            int unit = m_elety.getSize();
            if (len % unit != 0) {
                throw new Error("margin error");
            } else {
                int steps = len / unit;
                m_index += steps;
            }
        }
        
        public int getIndex() {
            return m_index;
        }
        
        public ATSType getElementType() {
            return m_elety;
        }

    }
}
