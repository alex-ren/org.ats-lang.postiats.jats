package jats.utfpl.tree;

import jats.utfpl.ccomp.CCompUtils;
import jats.utfpl.parser.UtfplLexer;
import jats.utfpl.parser.UtfplParser;
import jats.utfpl.parser.Utfpl_tree;
import jats.utfpl.utils.MapScope;

import java.io.IOException;

import org.antlr.runtime.ANTLRFileStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.TokenStream;
import org.antlr.runtime.tree.CommonTree;
import org.antlr.runtime.tree.CommonTreeNodeStream;


public class Test_02_naming {

    /**
     * @param args
     * @throws IOException 
     * @throws RecognitionException 
     */
    public static void main(String[] args) throws IOException, RecognitionException {
        String [] filenames = {
                "test/test01_tuple.utfpl"
                ,"test/test02_fact.utfpl"
        
        };

        for (String filename: filenames) {
            System.out.println("==Processing file " + filename + "==========");
            
            ANTLRFileStream fileStream = new ANTLRFileStream(filename);
            
            /* ******** ******** */
            // lexing
            UtfplLexer lexer = new UtfplLexer(fileStream);
            TokenStream tokenStream = new CommonTokenStream(lexer);
            // System.out.println(tokenStream.toString());
            
            /* ******** ******** */
            // parsing
            UtfplParser parser = new UtfplParser(tokenStream);
            UtfplParser.rule_return parser_ret = parser.rule();
            CommonTree tree = (CommonTree)parser_ret.getTree();
            CommonTreeNodeStream nodes = new CommonTreeNodeStream(tree);
            
            /* ******** ******** */
            // tree parsing
            Utfpl_tree walker = new Utfpl_tree(nodes);

//            // populate types and functions
//            Map<String, ATSType> types = CCompUtils.getLibTypes();
//            
//            Map<String, FuncDef> funcs = new HashMap<String, FuncDef>();
//            CCompUtils.populateAllFuncs(funcs);
//            
//            // initialize all the global variables, and put them into global scope
//            ATSScope<Object> gvscope = new MapScope<Object>();
//            CCompUtils.populateAllGlobalValues(gvscope);
//            
//            ATSScope<ATSType> tyscope = new MapScope<ATSType>();
//            CCompUtils.populateAllFuncTypes(tyscope);
//            CCompUtils.populateAllGlobalValueTypes(tyscope);
            
            // collect the definition of all the functions
            Program prog = walker.rule();
            
            MapScope<TID> libScope = new MapScope<TID>();
            CCompUtils.populateAllFunctions(libScope);
            NamingVisitor nameV = new NamingVisitor(libScope);
            prog.accept(nameV);
            
            TreePrinter tp = new TreePrinter();
            String output = tp.print(prog);
            
            System.out.println("==program is ==========================");
            System.out.println(output);
//            prog.run(gvscope, new String[] {filename, "3.3432"});
            
            /* ******** ******** */

            
            System.out.println("\n" + "==" + filename + " is O.K. " + "\n==============================================================================\n");
        }

    }

}
