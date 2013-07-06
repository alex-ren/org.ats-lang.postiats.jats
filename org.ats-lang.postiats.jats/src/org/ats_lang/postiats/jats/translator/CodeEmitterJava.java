package org.ats_lang.postiats.jats.translator;

import java.util.List;
import java.util.Map;

import org.ats_lang.postiats.jats.interpreter.FuncDef;
import org.ats_lang.postiats.jats.interpreter.Program;
import org.ats_lang.postiats.jats.tree.ATSNode;
import org.ats_lang.postiats.jats.tree.ATSTreeVisitor;
import org.ats_lang.postiats.jats.tree.UserFunc;
import org.ats_lang.postiats.jats.type.ATSTypeVisitor;
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
        
        

        ST stProg = group.getInstanceOf("program_t");
        stProg.add("filename", "Test01");

        
        List<StructType> userTypes = m_prog.getUserTypes();
        if (userTypes.size() > 0) {
            System.out.println("+++++++++++++++++++++++++++++user-defined types");
        }
        ATSTypeVisitor typeVisitor = new TypeEmitterJavaVisitor(group);
        emitUserTypes(typeVisitor, userTypes, stProg);
        
        
        List<ATSNode> gstats = m_prog.getGstats();
        if (gstats.size() > 0) {
            System.out.println("+++++++++++++++++++++++++++++gloval variables");
        }
        ATSTreeVisitor codeVisitor = new CodeEmitterJavaVisitor(group);
        emitGStats(codeVisitor, gstats, stProg);

        
        
        List<UserFunc> userFuncs = m_prog.getUserFuncs();
        
        
        String result = stProg.render();
        return result;
    }
    
    public ST emitFunc(FuncDef func) {
        return null;
    }
    
    // emit code for initializing global variable
    public void emitGStats(ATSTreeVisitor vistor, List<ATSNode> stats, ST st) {
        for (ATSNode stat: stats) {
            st.add("stats", stat.accept(vistor));
        }        
    }
    
    // emit code for initializing static variables for types
    public void emitUserTypes(ATSTypeVisitor vistor, List<StructType> utypes, ST st) {
        for (StructType utype: utypes) {
            st.add("stats", utype.accept(vistor));
        }        
    }
    
    
    
}

















