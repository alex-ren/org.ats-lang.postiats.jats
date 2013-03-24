package org.ats_lang.postiats.jats.interpreter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.ats_lang.postiats.jats.tree.ATSNode;
import org.ats_lang.postiats.jats.tree.DefinitionNode;
import org.ats_lang.postiats.jats.tree.FuncCallNode;
import org.ats_lang.postiats.jats.type.ATSType;
import org.ats_lang.postiats.jats.value.ATSValue;
import org.ats_lang.postiats.jats.value.IntValue;
import org.ats_lang.postiats.jats.value.PtrValue;
import org.ats_lang.postiats.jats.value.StringValue;

public class Program {
    private Map<String, ATSType> m_types;
    private Map<String, FuncDef> m_funcs;
    
    private List<ATSNode> m_statements;
    
    private MainFunc m_main;

    public Program(Map<String, ATSType> types, Map<String, FuncDef> funcs) {
        m_types = types;
        m_funcs = funcs;
        
		m_statements = new ArrayList<ATSNode>();
		m_main = null;
	}

    
    public void defineType(String id, ATSType type) {
        m_types.put(id, type);
    }
    
    public void defineFunc(UserFunc func) {
        m_funcs.put(func.getName(), func);
    }
    
    public Map<String, ATSType> getTypes() {
        return m_types;
    }
    
    public void addStat(ATSNode stat) {
        m_statements.add(stat);
    }

    public void setMain(DefinitionNode err, FuncCallNode initFunc, String mainName) {
        m_main = new MainFunc(err, initFunc, mainName);
    }

    public List<ATSNode> getStat() {
        return m_statements;
    }

    private class MainFunc {
        public DefinitionNode m_err;
        public FuncCallNode m_initFunc;
        public String m_mainName;
        
        public MainFunc(DefinitionNode err, FuncCallNode initFunc, String mainName) {
            m_err = err;
            m_initFunc = initFunc;
            m_mainName = mainName;
        }

    }
    
    public void run(String [] argv) {
        // initialize all the global variables, and put them into global scope
        ValueScope globalScope = new ValueScope();
        
        globalScope.addValue("atspre_FILE_stdout", PtrValue.c_stdout);
        globalScope.addValue("atspre_FILE_stderr", PtrValue.c_stderr);
        
        for (ATSNode state : m_statements) {
            state.evaluate(m_types, m_funcs, globalScope);
        }
        
        // =======================
        // new scope for the main
        ValueScope mainScope = globalScope.newScope();
//        no use now
//        // The variable for error
//        ATSValue err =  m_main.m_err.evaluate(m_types, m_funcs, mainScope);
//        
        // =======================
                
        // initialization function
        m_main.m_initFunc.evaluate(m_types, m_funcs, mainScope);
        
        // ==transform arguments=====================
        IntValue mainArgc = new IntValue(argv.length);
        
        StringValue [] arrArgv = new StringValue[argv.length];
        for (int i = 0; i < argv.length; ++i) {
            arrArgv[i] = new StringValue(argv[i]);
        }

        PtrValue mainArgv = new PtrValue(arrArgv);
        
        PtrValue mainEnvp = new PtrValue();  // no envp at all
        
        List<ATSValue> mainArgs = new ArrayList<ATSValue>();
        mainArgs.add(mainArgc);
        mainArgs.add(mainArgv);
        mainArgs.add(mainEnvp);
        
        // ==transform name for the main function=====================
        String mainName;
//        pats_ccomp_basics.h
//        #define ATSmainats_void_0(err) mainats_void_0()
//        #define ATSmainats_argc_argv_0(argc, argv, err) mainats_argc_argv_0(argc, argv)
//        #define ATSmainats_argc_argv_envp_0(argc, argv, envp, err) mainats_argc_argv_envp_0(argc, argv, envp)
//
//        #define ATSmainats_void_int(err) err = mainats_void_int()
//        #define ATSmainats_argc_argv_int(argc, argv, err) err = mainats_argc_argv_int(argc, argv)
//        #define ATSmainats_argc_argv_envp_int(argc, argv, envp, err) err = mainats_argc_argv_envp_int(argc, argv, envp)
        if (m_main.m_mainName == "ATSmainats_void_0") {
            mainName = "mainats_void_0";
        } else if (m_main.m_mainName == "ATSmainats_argc_argv_0") {
            mainName = "mainats_argc_argv_0";
        } else if (m_main.m_mainName == "ATSmainats_argc_argv_envp_0") {
            mainName = "mainats_argc_argv_envp_0";
        } else if (m_main.m_mainName == "ATSmainats_void_int") {
            mainName = "mainats_void_int";
        } else if (m_main.m_mainName == "ATSmainats_argc_argv_int") {
            mainName = "mainats_argc_argv_int";
        } else if (m_main.m_mainName == "ATSmainats_argc_argv_envp_int") {
            mainName = "mainats_argc_argv_envp_int";
        } else {
            throw new Error("no support");
        }

        // The actual main function.
        FuncDef fun = m_funcs.get(mainName);
        if (null == fun) {
            System.out.println("No main function.");
            return;
        }
        
        ValueScope scope = globalScope.newScope();
        ((UserFunc)fun).evaluate(m_types, m_funcs, scope, mainArgs);
        
        return;
    }

}
