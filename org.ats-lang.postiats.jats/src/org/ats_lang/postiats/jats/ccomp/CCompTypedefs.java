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


    public static PtrType atstype_ptrk = PtrType.cType;

    public static VoidType atsvoid_t0ype = VoidType.cType;

    public static IntType atstype_int = IntType.cType;
    
    public static ULIntType atstype_ulint = ULIntType.cType;

    public static DoubleType atstype_double = DoubleType.cType;

    public static BoolType atstype_bool = BoolType.cType;

    public static PtrType atstype_arrptr = PtrType.cType;

    public static SizeType atstype_size = SizeType.cType;
    
    public static StructType atstype_arrpsz;
    
    public static StructType demo;
    

    static {

        atstype_arrpsz = new StructType("atstype_arrpsz");
        atstype_arrpsz.addMember("ptr", atstype_arrptr);
        atstype_arrpsz.addMember("size", atstype_size);
        

        demo = new StructType("demo");
        demo.addMember("dd", atstype_int);
        demo.addMember("ss", atstype_arrpsz);

    }

    static class Catstype_arrpsz implements CCompCompositeValue {
        // size info
        public final static int m_size = PtrValue.m_type.getSize()
                + SizeValue.m_type.getSize();

        // type info
        public static class m_type {
            public static int getSize() {
                return m_size;
            }

            public static Catstype_arrpsz createDefault() {
                Catstype_arrpsz obj = new Catstype_arrpsz();
                obj.ptr = PtrValue.m_type.createDefault();
                obj.size = SizeValue.m_type.createDefault();

                return obj;
            }
        }

        // member info
        public PtrValue ptr;
        public SizeValue size;

        // constructor
        private Catstype_arrpsz() {
        }

        @Override
        public void copyfrom(CCompCompositeValue v) {
            if (v instanceof Catstype_arrpsz) {
                Catstype_arrpsz from = (Catstype_arrpsz) v;
                this.ptr.copyfrom(from.ptr);
                this.size.copyfrom(from.size);
            } else {
                throw new Error("Wrong type");
            }

        }

        @Override
        public Catstype_arrpsz deepcopy() {
            Catstype_arrpsz obj = new Catstype_arrpsz();
            obj.ptr = ptr.deepcopy();
            obj.size = size.deepcopy();
            return obj;
        }

    }

    static class Cdemo implements CCompCompositeValue {
        // size info
        public final static int m_size = IntValue.m_type.getSize()
                + Catstype_arrpsz.m_type.getSize();

        // type info
        public final static Catstype_arrpsz cType = new Catstype_arrpsz();

        public static class m_type {
            public static int getSize() {
                return m_size;
            }

            public static Cdemo createDefault() {
                Cdemo obj = new Cdemo();
                obj.dd = IntValue.m_type.createDefault();
                obj.ss = Catstype_arrpsz.m_type.createDefault();

                return obj;
            }
        }

        // member info
        public IntValue dd;
        public Catstype_arrpsz ss;

        // constructor
        public Cdemo() {
        }

        @Override
        public void copyfrom(CCompCompositeValue v) {
            if (v instanceof Cdemo) {
                Cdemo from = (Cdemo) v;
                this.dd.copyfrom(from.dd);
                this.ss.copyfrom(from.ss);
            } else {
                throw new Error("Wrong type");
            }

        }

        @Override
        public Cdemo deepcopy() {
            Cdemo obj = new Cdemo();
            obj.dd = dd.deepcopy();
            obj.ss = ss.deepcopy();
            return obj;
        }

    }


}
