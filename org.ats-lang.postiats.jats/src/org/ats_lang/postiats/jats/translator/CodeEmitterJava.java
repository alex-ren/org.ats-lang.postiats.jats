package org.ats_lang.postiats.jats.translator;

import java.util.List;
import java.util.Map;

import org.ats_lang.postiats.jats.interpreter.FuncDef;
import org.ats_lang.postiats.jats.interpreter.Program;
import org.ats_lang.postiats.jats.tree.ATSNode;
import org.ats_lang.postiats.jats.tree.ATSTreeVisitor;
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
        ATSTreeVisitor vistor = new EmitterJavaVisitor(group);

        ST stProg = group.getInstanceOf("program_t");
        stProg.add("filename", "Test01");

        List<ATSNode> stats = m_prog.getStats();
        if (stats.size() > 0) {
            System.out.println("+++++++++++++++++++++++++++++gloval variables");
        }
        emitStats(vistor, stats, stProg);
        
        
        Map<String, FuncDef> funcs = m_prog.getFuncs();
        
        
        String result = stProg.render();
        return result;
    }
    
    public ST emitFunc(FuncDef func) {
        return null;
    }
    
    public void emitStats(ATSTreeVisitor vistor, List<ATSNode> stats, ST st) {
        for (ATSNode stat: stats) {
            st.add("stats", stat.accept(vistor));
        }        
    }
    
    
    
    
    
}

















