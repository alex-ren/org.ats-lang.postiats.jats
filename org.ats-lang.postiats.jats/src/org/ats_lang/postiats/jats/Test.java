package org.ats_lang.postiats.jats;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.antlr.runtime.ANTLRFileStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.TokenStream;
import org.antlr.runtime.tree.CommonTree;
import org.antlr.runtime.tree.CommonTreeNodeStream;

import org.ats_lang.postiats.jats.parser.*;
import org.ats_lang.postiats.jats.tree.ATSNode;
import org.ats_lang.postiats.jats.tree.FuncNode;
import org.ats_lang.postiats.jats.type.ATSType;


public class Test {

    /**
     * @param args
     * @throws RecognitionException 
     * @throws IOException 
     */
    public static void main(String[] args) throws RecognitionException, IOException {
        String [] files = {"test/test01.txt", "test/f91_dats.c", "test/fact_dats.c", "test/fib_dats.c", "test/test_dats.c"};
        
        for (String file: files) {
            ANTLRFileStream fileStream = new ANTLRFileStream(file);
            ATSILLexer lexer = new ATSILLexer(fileStream);
            TokenStream tokenStream = new CommonTokenStream(lexer);
            ATSILParser parser = new ATSILParser(tokenStream);
            ATSILParser.rule_return parser_ret = parser.rule();
            
            CommonTree tree = (CommonTree)parser_ret.getTree();
            CommonTreeNodeStream nodes = new CommonTreeNodeStream(tree);
            
            ATSILInterpreter walker = new ATSILInterpreter(nodes);
            
            // get the returned node
            Map<String, ATSType> types = new HashMap<String, ATSType>();
            Map<String, FuncNode> funcs = new HashMap<String, FuncNode>();
            // todo
            // add types and functions to walker
            
            walker.setTypes(types);
            walker.setFuncs(funcs);
            
            ATSNode returned = walker.program();
            
            System.out.println(file + " is O.K.");
        }
    }

    
}

