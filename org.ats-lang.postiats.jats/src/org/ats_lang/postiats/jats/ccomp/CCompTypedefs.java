package org.ats_lang.postiats.jats.ccomp;

import org.ats_lang.postiats.jats.type.BoolType;
import org.ats_lang.postiats.jats.type.DoubleType;
import org.ats_lang.postiats.jats.type.IntType;
import org.ats_lang.postiats.jats.type.PtrType;
import org.ats_lang.postiats.jats.type.SizeType;
import org.ats_lang.postiats.jats.type.StructType;
import org.ats_lang.postiats.jats.type.ULIntType;
import org.ats_lang.postiats.jats.type.VoidType;

public class CCompTypedefs {

    public static PtrType atstype_ptrk;

    public static VoidType atsvoid_t0ype;
    
    public static IntType atstype_int;
    
    public static DoubleType atstype_double;

    public static BoolType atstype_bool;
    
    public static StructType atstype_arrpsz;
    
    public static PtrType atstype_arrptr;
    
    public static SizeType atstype_size;
    
    static {
        atstype_ptrk = new PtrType();
        
        atsvoid_t0ype = VoidType.cType;
        
        atstype_int = IntType.cType;
        
        atstype_double = DoubleType.cType;

        atstype_bool = BoolType.cType;
        
        atstype_arrptr = new PtrType();
        
        atstype_size = SizeType.cType;
        
        atstype_arrpsz = new StructType("atstype_arrpsz");
        atstype_arrpsz.addMember("ptr", atstype_arrptr);
        atstype_arrpsz.addMember("size", atstype_size);
        
    }

}
