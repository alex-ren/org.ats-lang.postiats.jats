package org.ats_lang.postiats.jats.interpreter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.ats_lang.postiats.jats.tree.ATSNode;
import org.ats_lang.postiats.jats.tree.FuncCallNode;
import org.ats_lang.postiats.jats.tree.UserFunc;
import org.ats_lang.postiats.jats.type.ATSType;
import org.ats_lang.postiats.jats.type.ArrayType;
import org.ats_lang.postiats.jats.type.PtrkType;
import org.ats_lang.postiats.jats.type.StringType;
import org.ats_lang.postiats.jats.type.StructType;
import org.ats_lang.postiats.jats.utils.ATSScope;
import org.ats_lang.postiats.jats.value.Ptrk;
import org.ats_lang.postiats.jats.value.SingletonValue;


public class Program {
    private Map<String, ATSType> m_types;  // type definition including both library and user-defined types
    private List<StructType> m_userTypes;  // list of user-defined types
    private Map<String, FuncDef> m_funcs;  // function definition including both library and user-defined functions
    private List<UserFunc> m_userFuncs;    // list of user-defined function definitions
    private List<ATSNode> m_gstats;  // statements for global variables in the file
    private MainFunc m_main;
    private String m_fileName;
    
    public void setFileName(String fileName) {
        m_fileName = fileName;
    }
    
    public String getFileName() {
        return m_fileName;
    }
    
    public Map<String, FuncDef> getFuncs() {
        return m_funcs;
    }

    public Program(Map<String, ATSType> types, Map<String, FuncDef> funcs) {
        m_types = types;
        m_userTypes = new ArrayList<StructType>();
        m_funcs = funcs;
        m_userFuncs = new ArrayList<UserFunc>();
        m_gstats = new ArrayList<ATSNode>();
        m_main = null;
	}
    
    public List<StructType> getUserTypes() {
        return m_userTypes;
    }
    
    public List<UserFunc> getUserFuncs() {
        return m_userFuncs;
    }

    public MainFunc getMain() {
        return m_main;
    }
    
    public void defineType(String id, StructType type) {
        m_types.put(id, type);
        m_userTypes.add(type);
    }
    
    public ATSType getType(String id) {
        return m_types.get(id);
    }
    
    public void defineFunc(UserFunc func) {
        m_funcs.put(func.getName(), func);
        m_userFuncs.add(func);
    }

    
    public void addStat(ATSNode stat) {
        m_gstats.add(stat);
    }

    public void setMain(String errname, FuncCallNode initFunc, String mainName) {
        m_main = new MainFunc(errname, initFunc, mainName);
    }

    public List<ATSNode> getGstats() {
        return m_gstats;
    }
//    
//    public Map<String, FuncDef> getFuncs() {
//        return m_funcs;
//    }

    public class MainFunc {
        public String m_errname;
        public FuncCallNode m_initFunc;
        public String m_mainName;
        
        public MainFunc(String errname, FuncCallNode initFunc, String mainName) {
            m_errname = errname;
            m_initFunc = initFunc;
            m_mainName = mainName;
        }

    }
    
    // gvscope: global value definition including global variables (in library) and functions
    public void run(ATSScope<Object> gvscope, String [] argv) {

        EvaluateVisitor gvisitor = new EvaluateVisitor(m_types, m_funcs, gvscope);
        // executing global statement
        for (ATSNode state : m_gstats) {
            state.accept(gvisitor);
        }
        
        // =======================
        // new scope for the main
        ATSScope<Object> mainScope = gvscope.newScope();
        // todo add the error to the current scope
        // something like mainScope.addValue(errname, LValue(int))
        // =======================
                
        // initialization function
        EvaluateVisitor initVisitor = new EvaluateVisitor(m_types, m_funcs, mainScope);
        m_main.m_initFunc.accept(initVisitor);
        
        
        // ==transform arguments=====================
        Integer mainArgc = argv.length;
        
        Ptrk[] arrArgv = new Ptrk[argv.length];
        for (int i = 0; i < argv.length; ++i) {
            arrArgv[i] = StringType.fromString(argv[i]);
        }
        
        ArrayType argvTy = new ArrayType(PtrkType.cType, argv.length);

        Ptrk mainArgv = Ptrk.createPtrk(argvTy, arrArgv);
        
        Object mainEnvp = SingletonValue.NULL;  // no envp at all
        
        // todo
        // I should put appropriate arguments (including error) into the list based
        // what main function is actually used.
        List<Object> mainArgs = new ArrayList<Object>();
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
        
        ATSScope<Object> scope = gvscope.newScope();
        ((UserFunc)fun).evaluate(m_types, m_funcs, scope, mainArgs);
        
        return;
    }

}
