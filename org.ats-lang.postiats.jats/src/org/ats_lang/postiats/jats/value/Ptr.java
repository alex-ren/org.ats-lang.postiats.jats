package org.ats_lang.postiats.jats.value;

import org.ats_lang.postiats.jats.type.ATSType;

public class Ptr {
    private LValue m_value;
    
    public LValue deRef(ATSType type) {
        return m_value;
    }
//
//    public Object derefNow() {
//        if (m_value instanceof Object[]) {
//            Object [] arr = (Object []) m_value;
//            return arr[m_index];            
//        } else {
//            return m_value;
//        }
//    }
//    
//    public Derefed derefLazy() {
//        return new Derefed(this);
//    }
//    
//    public static class Derefed {
//        private ATSPtr m_ptr;
//        
//        private Derefed(ATSPtr p) {
//            m_ptr = p;
//        }
//        
//        public ATSPtr getLValue() {
//            return m_ptr;
//        }
//        
//        public Object getRValue() {
//            return m_ptr.derefNow();
//        }
//        
//    }

}
