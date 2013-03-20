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
import org.ats_lang.postiats.jats.tree.ATSNode;
import org.ats_lang.postiats.jats.type.ATSType;


public class Test {

    /**
     * @param args
     * @throws RecognitionException 
     * @throws IOException 
     */
    public static void main(String[] args) throws RecognitionException, IOException {
        String [] files = {"test/test01.txt", "test/f91_dats.c" , "test/fact_dats.c", "test/fib_dats.c", "test/test_dats.c", "test/atof_dats.c" };
        

        for (String file: files) {
            System.out.println("Processing file " + file);
            
            ANTLRFileStream fileStream = new ANTLRFileStream(file);
            
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

            // populate types and funcstions
            Map<String, ATSType> types = CCompUtils.getLibTypes();
            
            Map<String, FuncDef> funcs = new HashMap<String, FuncDef>();
            CCompUtils.populateAllFuncs(funcs);
            
            // collect the definition of all the functions
            ATSNode prog = walker.program(types, funcs);
            
            /* ******** ******** */
            // initialize all the global variables
            ValueScope globalScope = new ValueScope();
            prog.evaluate(types, funcs, globalScope);
            
            // "main" or "mainats"
            FuncDef fun = funcs.get("mainats");
            if (null == fun) {
                fun = funcs.get("main");
                if (null == fun) {
                    System.out.println("No main function is provided.");
                    return;
                }
            }
            
            ValueScope scope = globalScope.newScope();
            ((UserFunc)fun).evaluate(types, funcs, scope, null);
            
            
            System.out.println("\n" + file + " is O.K.\n");
        }
    }

    
}

