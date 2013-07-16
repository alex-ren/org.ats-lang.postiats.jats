package org.ats_lang.postiats.jats.translator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.antlr.runtime.ANTLRFileStream;
import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.TokenRewriteStream;
import org.antlr.runtime.TokenStream;
import org.antlr.runtime.tree.CommonTree;
import org.antlr.runtime.tree.CommonTreeNodeStream;
import org.antlr.stringtemplate.StringTemplate;
import org.antlr.stringtemplate.StringTemplateGroup;
import org.ats_lang.postiats.jats.ccomp.CCompUtils;
import org.ats_lang.postiats.jats.interpreter.FuncDef;
import org.ats_lang.postiats.jats.interpreter.Program;
import org.ats_lang.postiats.jats.parser.ATSILInterpreter;
import org.ats_lang.postiats.jats.parser.ATSILLexer;
import org.ats_lang.postiats.jats.parser.ATSILParser;
import org.ats_lang.postiats.jats.parser.ATSILPrepocessorLexer;
import org.ats_lang.postiats.jats.parser.ATSILPrepocessorParser;
import org.ats_lang.postiats.jats.type.ATSType;
import org.ats_lang.postiats.jats.utils.ATSScope;
import org.ats_lang.postiats.jats.utils.FilenameUtils;
import org.ats_lang.postiats.jats.utils.MapScope;

public class Test {

    /**
     * @param args
     * @throws RecognitionException
     * @throws IOException
     */
    public static void main(String[] args) throws RecognitionException,
            IOException {

        String[] fileNames = { 
                "test/printtest_dats.c"
                 ,"test/basic_dats.c"
                 ,"test/f91_dats.c"
                 ,"test/fact_dats.c"
                 ,"test/fib_dats.c"
                 ,"test/gfib_dats.c"
                 ,"test/atof_dats.c"
                 ,"test/test01_dats.c"
                 ,"test/test02_dats.c"
                 ,"test/test03_dats.c"
                 ,"test/test04_dats.c"
                 ,"test/test05_dats.c"
                 ,"test/test06_dats.c"
                 ,"test/test07_dats.c"
                 ,"test/test08_dats.c"
                 ,"test/test09_dats.c"
                 ,"test/test10_dats.c"
                 ,"test/test11_dats.c"
                 ,"test/test12_dats.c"
                 ,"test/test13_dats.c"
                 ,"test/test14_dats.c" // array
                 ,"test/test15_dats.c" // array
                 ,"test/test16_dats.c"
                 ,"test/test17_dats.c"
                 ,"test/test18_dats.c"
                 ,"test/test19_dats.c"
                 ,"test/test20_dats.c"
                 ,"test/test21_dats.c"
                 ,"test/test22_string_dats.c"
                 ,"test/test23_array_dats.c" // array
                , "test/test24_dats.c"

        };

        for (String fileName : fileNames) {
            System.out.println("==Processing file " + fileName + "==========");

            ANTLRFileStream fileStream = new ANTLRFileStream(fileName);
            File file = new File(fileName);
            String classname = FilenameUtils.removeExtension(file.getName());
            // preprocessing
            ATSILPrepocessorLexer lexer0 = new ATSILPrepocessorLexer(fileStream);
            TokenStream tokens = new TokenRewriteStream(lexer0);
            ATSILPrepocessorParser preparser = new ATSILPrepocessorParser(
                    tokens);
            preparser.rule();

            // System.out.println("==print out processed==========================");
            // System.out.println(tokens.toString());
            System.out
                    .println("==preprocessing finished==========================");

            /* ******** ******** */
            // lexing
            ANTLRStringStream stream = new ANTLRStringStream(tokens.toString());

            ATSILLexer lexer = new ATSILLexer(stream);
            TokenStream tokenStream = new CommonTokenStream(lexer);
            // System.out.println(tokenStream.toString());

            /* ******** ******** */
            // parsing
            ATSILParser parser = new ATSILParser(tokenStream);
            ATSILParser.rule_return parser_ret = parser.rule();
            CommonTree tree = (CommonTree) parser_ret.getTree();
            CommonTreeNodeStream nodes = new CommonTreeNodeStream(tree);

            /* ******** ******** */
            // tree parsing
            ATSILInterpreter walker = new ATSILInterpreter(nodes);

            // populate types and functions
            Map<String, ATSType> types = CCompUtils.getLibTypes();

            Map<String, FuncDef> funcs = new HashMap<String, FuncDef>();
            CCompUtils.populateAllFuncs(funcs);

            // initialize all the global variables, and put them into global
            // scope
            ATSScope<Object> gvscope = new MapScope<Object>();
            CCompUtils.populateAllGlobalValues(gvscope);

            ATSScope<ATSType> tyscope = new MapScope<ATSType>();
            CCompUtils.populateAllFuncTypes(tyscope);
            CCompUtils.populateAllGlobalValueTypes(tyscope);

            // collect the definition of all the functions
            Program prog = walker.program(types, funcs, tyscope);
            prog.setFileName(classname);

            CodeEmitterJava emiter = new CodeEmitterJava(prog);
            
            System.out.println("\n" + "==" + file + " starts here. " + "\n==============================================================================\n");
            
            String content = emiter.emit();
//            System.out.println(content);
            
            /* ******** ******** */

            
            System.out.println("\n" + "==" + file + " ends here. " + "\n==============================================================================\n");
            
            /* ******** ******** */
            // System.out.println(output.toString()); // render full template
            FileWriter fw = new FileWriter("test/postiats/" + classname
                    + ".java");
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(content);
            bw.close();

            System.out.println(file + " is O.K.\n\n");
            
            try {
                Class<?> c = Class.forName("postiats." + classname);
                try {
                    Method meth = c.getMethod("main", String[].class);
                    String[] params = null; // init params accordingly
                    params = new String[2];
                    params[0] = classname;
                    params[1] = "4.999";
                    try {
                        meth.invoke(null, (Object) params);
                    } catch (IllegalArgumentException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } // static method doesn't have an instance
                } catch (SecurityException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (NoSuchMethodException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            } catch (ClassNotFoundException e) {
                System.out.println("Generating " + classname + " failed.");
                e.printStackTrace();
            }

            System.out.println("\n======================" + file
                    + " execution is O.K.\n\n");

        }
        
////        // little try using StringTemplate V4
////        STGroup group = new STGroupFile("org/ats_lang/postiats/jats/translator/ats_il_java.stg");
////        
////        ST st = group.getInstanceOf("classes_st");
////        String [] arr = {"xjsiejfwef;", "feifjeijfw;"};
////        st.add("classes", arr);
////        String result = st.render(); // yields "int x = 0;"
////        System.out.println(result);
//        
//        // load the group file ats_il_java.stg, put in templates
//        FileReader groupFileR = new FileReader(
//                "src/org/ats_lang/postiats/jats/translator/ats_il_java.stg");
//        StringTemplateGroup templates = new StringTemplateGroup(groupFileR);
//        groupFileR.close();
//        System.out.println("==group template file loaded==============");
//
//        String[] files = { "test/test01.txt" , "test/f91_dats.c",
//                                                 "test/fact_dats.c",
//                                                 "test/fib_dats.c",
//                                                 "test/test_dats.c"};  // ,
//                                             //    "test/atof_dats.c"};
//
////        // populate types and funcstions
////        Map<String, ATSType> types = CCompTypes.getLibTypes();
////
////        Map<String, FuncDef> funcs = new HashMap<String, FuncDef>();
////        CCompUtils.populateAllFuncs(funcs);
//
//        for (String fileName : files) {
//            System.out.println("Processing file " + fileName);
//
//            ANTLRFileStream fileStream = new ANTLRFileStream(fileName);
//            
//            File file = new File(fileName);
//            String classname = FilenameUtils.removeExtension(file.getName());
//            // preprocessing
//            ATSILPrepocessorLexer lexer0 = new ATSILPrepocessorLexer(fileStream);
//            TokenStream tokens = new TokenRewriteStream(lexer0);
//            ATSILPrepocessorParser preparser = new ATSILPrepocessorParser(tokens);
//            preparser.rule();
//            
//            System.out.println("==preprocessing finished==========================");
//            
//            /* ******** ******** */
//            // lexing
//            ANTLRStringStream stream = new ANTLRStringStream(tokens.toString());
//            ATSILLexer lexer = new ATSILLexer(stream);
//            TokenStream tokenStream = new CommonTokenStream(lexer);
//
//            /* ******** ******** */
//            // parsing
//            ATSILParser parser = new ATSILParser(tokenStream);
//            ATSILParser.rule_return parser_ret = parser.rule();
//            CommonTree tree = (CommonTree) parser_ret.getTree();
//            CommonTreeNodeStream nodes = new CommonTreeNodeStream(tree);
//
//            /* ******** ******** */
//            // tree parsing
//            ATSIL2JavaPass1 walker = new ATSIL2JavaPass1(nodes);
//            walker.setTemplateLib(templates);
//            
//            // populate types and funcstions
//            Map<String, ATSType> types = CCompUtils.getLibTypes();
//            
//            Map<String, String> libfuncs = new HashMap<String, String>();
//            CCompUtils.populateAllFuncNames(libfuncs);
//            
//            // collect the definition of all the structures
//            ATSIL2JavaPass1.program_return ret = walker.program(types, libfuncs, classname);
//            // ATSNode prog = walker.program(types, funcs);
//
//            /* ******** ******** */
//            StringTemplate output = ret.st;
//
//            /* ******** ******** */
//            // System.out.println(output.toString()); // render full template
//            FileWriter fw = new FileWriter("test/postiats/" + classname + ".java");
//            BufferedWriter bw = new BufferedWriter(fw);
//            bw.write(output.toString());
//            bw.close();
//
//            System.out.println(file + " is O.K.\n\n");
//            
//            try {
//                Class<?> c = Class.forName("postiats." + classname);
//                try {
//                    Method meth = c.getMethod("main", String[].class);
//                    String[] params = null; // init params accordingly
//                    try {
//                        meth.invoke(null, (Object) params);
//                    } catch (IllegalArgumentException e) {
//                        // TODO Auto-generated catch block
//                        e.printStackTrace();
//                    } catch (IllegalAccessException e) {
//                        // TODO Auto-generated catch block
//                        e.printStackTrace();
//                    } catch (InvocationTargetException e) {
//                        // TODO Auto-generated catch block
//                        e.printStackTrace();
//                    } // static method doesn't have an instance
//                } catch (SecurityException e) {
//                    // TODO Auto-generated catch block
//                    e.printStackTrace();
//                } catch (NoSuchMethodException e) {
//                    // TODO Auto-generated catch block
//                    e.printStackTrace();
//                }
//                
//            } catch (ClassNotFoundException e) {
//                System.out.println("Generating " + classname + " failed.");
//                e.printStackTrace();
//            }
//            
//            System.out.println("\n======================" + file + " execution is O.K.\n\n");
//        }
    }

}
