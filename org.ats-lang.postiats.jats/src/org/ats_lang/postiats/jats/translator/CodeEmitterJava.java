package org.ats_lang.postiats.jats.translator;

import java.util.List;

import org.ats_lang.postiats.jats.interpreter.Program;
import org.ats_lang.postiats.jats.interpreter.Program.MainFunc;
import org.ats_lang.postiats.jats.tree.ATSNode;

import org.ats_lang.postiats.jats.tree.UserFunc;

import org.ats_lang.postiats.jats.type.StructType;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupFile;

public class CodeEmitterJava {
    Program m_prog;

    public CodeEmitterJava(Program prog) {
        m_prog = prog;
    }

    public String emit() {


        STGroup group = new STGroupFile("src/org/ats_lang/postiats/jats/translator/ats_il_java.stg");
        System.out.println("==group template file loaded==============");
        
        

        ST stProg = group.getInstanceOf("program_st");
        stProg.add("filename", m_prog.getFileName());

        
        // emit user-defined types
        List<StructType> userTypes = m_prog.getUserTypes();
        if (userTypes.size() > 0) {
            System.out.println("+++++++++++++++++++++++++++++user-defined types");
        }
        TypeJavaInitVisitor typeVisitor = new TypeJavaInitVisitor(group);
        emitUserTypes(typeVisitor, userTypes, stProg);
        
        // emit initialization of global variables
        List<ATSNode> gstats = m_prog.getGstats();
        if (gstats.size() > 0) {
            System.out.println("+++++++++++++++++++++++++++++gloval variables");
        }
        CodeEmitterJavaVisitor codeVisitor = new CodeEmitterJavaVisitor(group, m_prog.getFuncs());
        emitGStats(codeVisitor, gstats, stProg);

        // emit user-defined functions
        List<UserFunc> userFuncs = m_prog.getUserFuncs();
        if (userFuncs.size() > 0) {
            System.out.println("+++++++++++++++++++++++++++++user-defined functions");
        }
        emitFuncs(codeVisitor, userFuncs, stProg);
        
        // emit main function
        emitMain(codeVisitor, group, stProg, m_prog.getMain());
        
        
        
        String result = stProg.render();
        return result;
    }

    
    // emit code for initializing global variable
    public void emitGStats(CodeEmitterJavaVisitor visitor, List<ATSNode> stats, ST st) {
        for (ATSNode stat: stats) {
            st.add("stats", stat.accept(visitor));
        }        
    }
    
    // emit code for initializing static variables for types
    public void emitUserTypes(TypeJavaInitVisitor visitor, List<StructType> utypes, ST st) {
        for (StructType utype: utypes) {
            st.add("stats", utype.accept(visitor));
        }        
    }
    
    // emit code for functions
    public void emitFuncs(CodeEmitterJavaVisitor visitor, List<UserFunc> funcs, ST st) {
        for (UserFunc func: funcs) {
            st.add("stats", func.accept(visitor));
        }        
    }
    
    // emit the wrapper for the real main function
    public void emitMain(CodeEmitterJavaVisitor visitor, STGroup stg, ST stProg, MainFunc mainfunc) {
        ST stMain = null;
        
        if (mainfunc.m_mainName == "ATSmainats_void_0") {
            stMain = stg.getInstanceOf("mainats_void_0_st");
        } else if (mainfunc.m_mainName == "ATSmainats_argc_argv_0") {
            stMain = stg.getInstanceOf("mainats_argc_argv_0_st");
        } else if (mainfunc.m_mainName == "ATSmainats_argc_argv_envp_0") {
            stMain = stg.getInstanceOf("mainats_argc_argv_envp_0_st");
        } else if (mainfunc.m_mainName == "ATSmainats_void_int") {
            stMain = stg.getInstanceOf("mainats_void_int_st");
        } else if (mainfunc.m_mainName == "ATSmainats_argc_argv_int") {
            stMain = stg.getInstanceOf("mainats_argc_argv_int_st");
        } else if (mainfunc.m_mainName == "ATSmainats_argc_argv_envp_int") {
            stMain = stg.getInstanceOf("mainats_argc_argv_envp_int_st");
        } else {
            throw new Error("no support");
        }
        
        stMain.add("init_func", mainfunc.m_initFunc.accept(visitor));
        stProg.add("stats", stMain);
    }
    
    
}

















