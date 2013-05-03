package org.ats_lang.postiats.jats.value;

import java.util.Map;

import org.ats_lang.postiats.jats.type.ATSType;
import org.ats_lang.postiats.jats.type.StringType;
import org.ats_lang.postiats.jats.type.StructType;

public class Ptrk {
    private int m_ptrval;
    private Object m_content; // either ArrayElement or not

    public Ptrk(Object obj) {
        m_ptrval = 1;
        m_content = obj;
    }

    // return the element stored in the lvalue pointed to by this pointer
    public Object getValue() {
        if (m_content instanceof ArrayElement) {
            return ((ArrayElement) m_content).get();
        } else if (m_content instanceof StringElement) {
            return ((StringElement) m_content).get();
        } else if (m_content instanceof StructMember) {
            return ((StructMember) m_content).get();
        } else {
            return m_content;
        }
    }
    
    public String formString() {
        if (m_content instanceof StringElement) {
            return ((StringElement) m_content).toString();
        } else {
            throw new Error("type error");
        }
    }

    public Ptrk addByteSize(int len) {
        if (m_content instanceof ArrayElement) {
            return new Ptrk(((ArrayElement) m_content).addByteSize(len));
        } else if (m_content instanceof StringElement) {
            return new Ptrk(((StringElement) m_content).addByteSize(len));
        } else {
            throw new Error("not supported");
        }
    }

    // return the lvalue pointed to by the pointer
    public Object def() {
        return this;
    }

    // update the content of the lvalue pointed to by this pointer
    public void update(Object v) {
        if (m_content instanceof ArrayElement) {
            ((ArrayElement) m_content).set(v);
        } else if (m_content instanceof StringElement) {
            ((StringElement) m_content).set((char) (Character) v);
        } else if (m_content instanceof StructMember) {
            ((StructMember) m_content).set(v);
        } else {
            m_content = v;
        }
    }

    public int subIndex(Ptrk from) {
        if (this.m_content instanceof ArrayElement
                && from.m_content instanceof ArrayElement) {
            ArrayElement left = (ArrayElement) this.m_content;
            ArrayElement right = (ArrayElement) from.m_content;
            return left.subIndex(right);
        } else if (this.m_content instanceof StringElement
                && from.m_content instanceof StringElement) {
            StringElement left = (StringElement) this.m_content;
            StringElement right = (StringElement) from.m_content;
            return left.subIndex(right);
        } else {
            throw new Error("Type mismatch");
        }
    }

    static public class StructMember {
        Map<String, Object> m_struct;
        String m_name;
        StructType m_structty;

        public StructMember(Map<String, Object> struct, String name,
                StructType structty) {
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

        public ArrayElement addByteSize(int len) {
            int unit = m_elety.getSize();
            if (len % unit != 0) {
                throw new Error("margin error");
            } else {
                int steps = len / unit;
                return new ArrayElement(m_arr, m_index + steps, m_elety);
            }
        }

        public int getIndex() {
            return m_index;
        }

        public ATSType getElementType() {
            return m_elety;
        }
        
        public int subIndex(ArrayElement from) {
            int diff = m_index - from.m_index;
            return diff * m_elety.getSize();
        }

    }

    static public class StringElement {
        private char[] m_arr;
        private int m_index;

        public StringElement(char[] array, int index) {
            m_arr = array;
            m_index = index;
        }

        public char get() {
            return m_arr[m_index];
        }

        public void inc() {
            m_index++;
        }

        public void dec() {
            m_index--;
        }

        public void set(char obj) {
            m_arr[m_index] = obj;
        }

        public StringElement addByteSize(int len) {
            return new StringElement(m_arr, m_index + len);
        }

        public int getIndex() {
            return m_index;
        }
        
        public int subIndex(StringElement from) {
            int diff = m_index - from.m_index;
            return diff;
        }
        
        public String toString() {
            return StringType.toString(m_arr, m_index, m_arr.length - 1 - m_index);            
        }

    }
}
