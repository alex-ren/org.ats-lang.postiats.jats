package org.ats_lang.postiats.jats.interpreter;

import java.io.IOException;
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

import org.ats_lang.postiats.jats.ccomp.CCompUtils;
import org.ats_lang.postiats.jats.parser.*;
import org.ats_lang.postiats.jats.type.ATSType;
import org.ats_lang.postiats.jats.utils.ATSScope;
import org.ats_lang.postiats.jats.utils.MapScope;
import org.ats_lang.postiats.jats.value.SingletonValue;



public class Test {

    /**
     * @param args
     * @throws RecognitionException 
     * @throws IOException 
     */
    public static void main(String[] args) throws RecognitionException, IOException {
        String [] filenames = {
        		"test/printtest_dats.c"
        		,"test/basic_dats.c"
        		,"test/f91_dats.c"
        		,"test/fact_dats.c"
        		,"test/fib_dats.c"
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
                ,"test/test14_dats.c"  // array
                ,"test/test15_dats.c"  // array
                ,"test/test16_dats.c"
                ,"test/test17_dats.c"
                ,"test/test18_dats.c"
                ,"test/test19_dats.c"
                ,"test/test20_dats.c"
                ,"test/test21_dats.c"
                ,"test/test22_string_dats.c"
                ,"test/test23_array_dats.c"  // array
        
        };

        for (String filename: filenames) {
            System.out.println("==Processing file " + filename + "==========");
            
            ANTLRFileStream fileStream = new ANTLRFileStream(filename);
            
            // preprocessing
            ATSILPrepocessorLexer lexer0 = new ATSILPrepocessorLexer(fileStream);
            TokenStream tokens = new TokenRewriteStream(lexer0);
            ATSILPrepocessorParser preparser = new ATSILPrepocessorParser(tokens);
            preparser.rule();
            
//            System.out.println("==print out processed==========================");
//            System.out.println(tokens.toString());
            System.out.println("==preprocessing finished==========================");
            
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
            CommonTree tree = (CommonTree)parser_ret.getTree();
            CommonTreeNodeStream nodes = new CommonTreeNodeStream(tree);
            
            /* ******** ******** */
            // tree parsing
            ATSILInterpreter walker = new ATSILInterpreter(nodes);

            // populate types and functions
            Map<String, ATSType> types = CCompUtils.getLibTypes();
            
            Map<String, FuncDef> funcs = new HashMap<String, FuncDef>();
            CCompUtils.populateAllFuncs(funcs);
            
            // initialize all the global variables, and put them into global scope
            ATSScope<Object> gvscope = new MapScope<Object>();
            CCompUtils.populateAllGlobalValues(gvscope);
            
            ATSScope<ATSType> tyscope = new MapScope<ATSType>();
            CCompUtils.populateAllFuncTypes(tyscope);
            CCompUtils.populateAllGlobalValueTypes(tyscope);
            
            // collect the definition of all the functions
            Program prog = walker.program(types, funcs, gvscope, tyscope);
            
            System.out.println("==fun the program==========================");
            prog.run(new String[] {filename, "3.3432"});
            
            /* ******** ******** */

            
            System.out.println("\n" + "==" + filename + " is O.K. " + "\n==============================================================================\n");
        }
    }

    
}

