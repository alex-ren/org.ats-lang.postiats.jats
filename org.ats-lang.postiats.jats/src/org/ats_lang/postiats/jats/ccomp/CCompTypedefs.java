/*
 * This class corresponds to the file pats_ccomp_typedefs.h.
 */
package org.ats_lang.postiats.jats.ccomp;

import org.ats_lang.postiats.jats.type.BoolType;
import org.ats_lang.postiats.jats.type.DoubleType;
import org.ats_lang.postiats.jats.type.IntType;
import org.ats_lang.postiats.jats.type.PtrType;
import org.ats_lang.postiats.jats.type.SizeType;
import org.ats_lang.postiats.jats.type.StructType;
import org.ats_lang.postiats.jats.type.ULIntType;
import org.ats_lang.postiats.jats.type.VoidType;
import org.ats_lang.postiats.jats.value.IntValue;
import org.ats_lang.postiats.jats.value.PtrValue;
import org.ats_lang.postiats.jats.value.SizeValue;

public class CCompTypedefs {


    public static PtrType m_atstype_ptrk = PtrType.cType;

    public static VoidType m_atsvoid_t0ype = VoidType.cType;

    public static IntType m_atstype_int = IntType.cType;
    
    public static ULIntType m_atstype_ulint = ULIntType.cType;

    public static DoubleType m_atstype_double = DoubleType.cType;

    public static BoolType m_atstype_bool = BoolType.cType;

    public static PtrType m_atstype_arrptr = PtrType.cType;

    public static SizeType m_atstype_size = SizeType.cType;
    
    public static StructType m_atstype_arrpsz;
    
    public static StructType m_demo;
    

    static {

        // name should include the class name "CCompTypedefs"
        m_atstype_arrpsz = new StructType("CCompTypedefs.atstype_arrpsz");
        m_atstype_arrpsz.addMember("ptr", m_atstype_arrptr);
        m_atstype_arrpsz.addMember("size", m_atstype_size);
        

        m_demo = new StructType("CCompTypedefs.demo");
        m_demo.addMember("dd", m_atstype_int);
        m_demo.addMember("ss", m_atstype_arrpsz);

    }

    public static class atstype_arrpsz implements CCompCompositeValue {
        // size info
        public final static int m_size = PtrValue.m_type.getSize()
                + SizeValue.m_type.getSize();

        // type info
        public static class m_type {
            public static int getSize() {
                return m_size;
            }

            public static atstype_arrpsz createDefault() {
                atstype_arrpsz obj = new atstype_arrpsz();
                obj.ptr = CPtrValue.m_type.createDefault();
                obj.size = SizeValue.m_type.createDefault();

                return obj;
            }
        }

        // member info
        public CPtrValue ptr;
        public SizeValue size;

        // constructor
        private atstype_arrpsz() {
        }

        @Override
        public void copyfrom(CCompCompositeValue v) {
            if (v instanceof atstype_arrpsz) {
                atstype_arrpsz from = (atstype_arrpsz) v;
                this.ptr.copyfrom(from.ptr);
                this.size.copyfrom(from.size);
            } else {
                throw new Error("Wrong type");
            }

        }

        @Override
        public atstype_arrpsz deepcopy() {
            atstype_arrpsz obj = new atstype_arrpsz();
            obj.ptr = ptr.deepcopy();
            obj.size = size.deepcopy();
            return obj;
        }

    }

    static class demo implements CCompCompositeValue {
        // size info
        public final static int m_size = IntValue.m_type.getSize()
                + atstype_arrpsz.m_type.getSize();

        // type info
        public final static atstype_arrpsz cType = new atstype_arrpsz();

        public static class m_type {
            public static int getSize() {
                return m_size;
            }

            public static demo createDefault() {
                demo obj = new demo();
                obj.dd = IntValue.m_type.createDefault();
                obj.ss = atstype_arrpsz.m_type.createDefault();

                return obj;
            }
        }

        // member info
        public IntValue dd;
        public atstype_arrpsz ss;

        // constructor
        public demo() {
        }

        @Override
        public void copyfrom(CCompCompositeValue v) {
            if (v instanceof demo) {
                demo from = (demo) v;
                this.dd.copyfrom(from.dd);
                this.ss.copyfrom(from.ss);
            } else {
                throw new Error("Wrong type");
            }

        }

        @Override
        public demo deepcopy() {
            demo obj = new demo();
            obj.dd = dd.deepcopy();
            obj.ss = ss.deepcopy();
            return obj;
        }

    }


    public static class CPtrValue implements CCompCompositeValue {
        // size info
        public final static int m_size = 4;

        // type info
        public static class m_type {
            public static int getSize() {
                return m_size;
            }

            public static CPtrValue createDefault() {
                CPtrValue obj = new CPtrValue();
                return obj;
            }
        }

        // member info
        public Object   m_mem;
        // It's possible that the pointer points to an element in an array.
        public Object[] m_arr;

        public int m_ind;
        
        public int m_elesz;

        static public CPtrValue create(Object v, int elesz) {
            return new CPtrValue(v, elesz);
        }
        
        // constructor
        public CPtrValue() {
            m_mem = null;
            m_arr = null;
            m_ind = -1;
            m_elesz = 0;
        }
        public CPtrValue(Object mem, int elesz) {
            m_mem = mem;
            m_arr = null;
            m_ind = -1;
            m_elesz = elesz;
        }
        
        public CPtrValue(Object[] arr, int elesz) {
            m_mem = arr[0];
            m_arr = arr;
            m_ind = 0;
            m_elesz = elesz;
        }

        public Object deRef() {
            return m_mem;
        }
        
        public void incIndex() {
            m_ind++;
            m_mem = m_arr[m_ind];
        }
        
        public void addByteSize(int sz) {
            if (sz % m_elesz != 0) {
                throw new Error("PtrValue::addByteSize, ptr boundry error");
            }
            
            m_ind += sz / m_elesz;
            m_mem = m_arr[m_ind];
        }
        

        @Override
        public void copyfrom(CCompCompositeValue v) {
            if (v instanceof CPtrValue) {
                CPtrValue from = (CPtrValue) v;
                m_mem = from.m_mem;
                m_arr = from.m_arr;
                m_ind = from.m_ind;
                m_elesz = from.m_elesz;
            } else {
                throw new Error("Wrong type");
            }

        }

        @Override
        public CPtrValue deepcopy() {
            CPtrValue obj = new CPtrValue();
            obj.m_mem = m_mem;
            obj.m_arr = m_arr;
            obj.m_ind = m_ind;
            obj.m_elesz = m_elesz;
            return obj;
        }

    }

}
