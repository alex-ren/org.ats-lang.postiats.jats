package org.ats_lang.postiats.jats.ccomp;

import java.util.Random;

import org.ats_lang.postiats.jats.type.ATSType;
import org.ats_lang.postiats.jats.type.BoolType;
import org.ats_lang.postiats.jats.type.DoubleType;
import org.ats_lang.postiats.jats.type.FuncType;
import org.ats_lang.postiats.jats.type.IntType;
import org.ats_lang.postiats.jats.type.SizeType;
import org.ats_lang.postiats.jats.type.VoidType;
import org.ats_lang.postiats.jats.utils.ATSScope;

public class CCompStdlib {
    

    public static Double atslib_drand48() {
        return new Random().nextDouble();
    }

    
    
    static public void populateFuncType(ATSScope<ATSType> typscope) {
        FuncType intFunc = new FuncType(IntType.cType, null);
        FuncType sizeFunc = new FuncType(SizeType.cType, null);
        FuncType boolFunc = new FuncType(BoolType.cType, null);
        FuncType voidFunc = new FuncType(VoidType.cType, null);
        FuncType doubleFunc = new FuncType(DoubleType.cType, null);

        typscope.addValue("atslib_drand48", doubleFunc);
        
       
    }
    
    static public void populateGlobalValueType(ATSScope<ATSType> tyscope) {
    }
}

