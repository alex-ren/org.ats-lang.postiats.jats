package org.ats_lang.postiats.jats.ccomp;

import org.ats_lang.postiats.jats.type.ATSType;
import org.ats_lang.postiats.jats.type.BoolType;
import org.ats_lang.postiats.jats.type.FuncType;
import org.ats_lang.postiats.jats.type.IntType;
import org.ats_lang.postiats.jats.type.PtrkType;
import org.ats_lang.postiats.jats.type.SizeType;
import org.ats_lang.postiats.jats.type.VoidType;
import org.ats_lang.postiats.jats.utils.ATSScope;
import org.ats_lang.postiats.jats.value.Ptrk;

public class CCompFileBas {

    public static Ptrk atspre_FILE_stdin = Ptrk.c_stdin;
    public static Ptrk atspre_FILE_stdout = Ptrk.c_stdout;
    public static Ptrk atspre_FILE_stderr = Ptrk.c_stderr;
    
    static public void populateFuncType(ATSScope<ATSType> typscope) {
        FuncType intFunc = new FuncType(IntType.cType, null);
        FuncType sizeFunc = new FuncType(SizeType.cType, null);
        FuncType boolFunc = new FuncType(BoolType.cType, null);
        FuncType voidFunc = new FuncType(VoidType.cType, null);
    }
    
    static public void populateGlobalValueType(ATSScope<ATSType> tyscope) {
        tyscope.addValueWithModule("atspre_FILE_stdin", PtrkType.cType, CCompFileBas.class.getSimpleName());
        tyscope.addValueWithModule("atspre_FILE_stdout", PtrkType.cType, CCompFileBas.class.getSimpleName());
        tyscope.addValueWithModule("atspre_FILE_stderr", PtrkType.cType, CCompFileBas.class.getSimpleName());
    }
}
