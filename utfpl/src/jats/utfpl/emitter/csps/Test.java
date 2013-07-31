package jats.utfpl.emitter.csps;


import jats.utfpl.instruction.InsTransformer;
import jats.utfpl.instruction.UtfplInstruction;
import jats.utfpl.parser.UtfplLexer;
import jats.utfpl.parser.UtfplParser;
import jats.utfpl.parser.Utfpl_tree;
import jats.utfpl.tree.NamingVisitor;
import jats.utfpl.tree.Program;
import jats.utfpl.tree.TreePrinter;
import jats.utfpl.utils.FilenameUtils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.antlr.runtime.ANTLRFileStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.TokenStream;
import org.antlr.runtime.tree.CommonTree;
import org.antlr.runtime.tree.CommonTreeNodeStream;


public class Test {

    /**
     * @param args
     * @throws IOException 
     * @throws RecognitionException 
     */
    public static void main(String[] args) throws IOException, RecognitionException {
        String [] filenames = {
                "test/dp.utfpl"
        
        };

        for (String filename: filenames) {
            System.out.println("==Processing file " + filename + "==========");
            ANTLRFileStream fileStream = new ANTLRFileStream(filename);
            
            File file = new File(filename);
            String classname = FilenameUtils.removeExtension(file.getName());
            
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

            Program prog = walker.rule();
            NamingVisitor nameV = new NamingVisitor();
            prog.accept(nameV);
            
            TreePrinter tp = new TreePrinter();
            String output1 = tp.print(prog);
            
            System.out.println("==program is ==========================");
            System.out.println(output1);
            
            InsTransformer insV = new InsTransformer();
            @SuppressWarnings("unchecked")
            List<UtfplInstruction> inslst = (List<UtfplInstruction>)prog.accept(insV);
            
            /* ***************** ****************** */
            CspsVisitor insPrinter = new CspsVisitor();
            String outputCSPS = insPrinter.print(inslst);
            System.out.println("==instructions are ==========================");
            System.out.println(outputCSPS);
            
            FileWriter fwCSPS = new FileWriter("test/" + classname
                    + ".csps");
            BufferedWriter bwCSPS = new BufferedWriter(fwCSPS);
            bwCSPS.write(outputCSPS);
            bwCSPS.close();
            
            /* ******** ******** */

            
            System.out.println("\n" + "==" + filename + " is O.K. " + "\n==============================================================================\n");
        }

    }

}
