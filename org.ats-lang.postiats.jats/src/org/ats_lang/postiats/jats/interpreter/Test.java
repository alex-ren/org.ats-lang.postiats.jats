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



public class Test {

    /**
     * @param args
     * @throws RecognitionException 
     * @throws IOException 
     */
    public static void main(String[] args) throws RecognitionException, IOException {
        String [] filenames = {"test/f91_dats.c", "test/atof_dats.c"};  // /*"test/test01.txt", "test/f91_dats.c", "test/fact_dats.c", "test/fib_dats.c", "test/test_dats.c", */"test/atof_dats.c" };
        // ATStmpdec_void(tmp92, atsvoid_t0ype) ;ATSINSmove_void(tmp92, atspre_print_string(ATSPMVstring("atof(\""))) ;

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

            // populate types and funcstions
            Map<String, ATSType> types = CCompUtils.getLibTypes();
            
            Map<String, FuncDef> funcs = new HashMap<String, FuncDef>();
            CCompUtils.populateAllFuncs(funcs);
            
            // collect the definition of all the functions
            Program prog = walker.program(types, funcs);
            
            System.out.println("==fun the program==========================");
            prog.run(new String[] {filename, "3"});
            
            /* ******** ******** */

            
            System.out.println("\n" + "==" + filename + " is O.K. " + "==========");
        }
    }

    
}

