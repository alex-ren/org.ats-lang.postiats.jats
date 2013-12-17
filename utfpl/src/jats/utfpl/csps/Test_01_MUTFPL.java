package jats.utfpl.csps;

import jats.utfpl.ccomp.CCompUtils;
import jats.utfpl.instruction.InstructionClosureConverter;
import jats.utfpl.instruction.InstructionProgramProcessor;
import jats.utfpl.instruction.InstructionTransformer;
import jats.utfpl.instruction.InstructionPrinter;
import jats.utfpl.instruction.ProgramInstruction;
import jats.utfpl.instruction.TID;
import jats.utfpl.instruction.InstructionPrinter.Type;
import jats.utfpl.parser.NamingVisitor;
import jats.utfpl.parser.UtfplLexer;
import jats.utfpl.parser.UtfplParser;
import jats.utfpl.parser.Utfpl_tree;
import jats.utfpl.tree.ProgramTree;
import jats.utfpl.tree.TreePrinter;
import jats.utfpl.utils.FilenameUtils;
import jats.utfpl.utils.MapScope;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.antlr.runtime.ANTLRFileStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.TokenStream;
import org.antlr.runtime.tree.CommonTree;
import org.antlr.runtime.tree.CommonTreeNodeStream;

public class Test_01_MUTFPL {
    
    public static void main(String[] args) throws IOException, RecognitionException {
        String [] filenames = {
//                "test/test23_csp_trans_fact.utfpl"
//                , "test/test24_csp_trans_tail_proc_call.utfpl"
//                , "test/test25_csp_closure.utfpl"
                "test/test09_all.utfpl"
        
        };

        for (String filename: filenames) {
            System.out.println("==Processing file " + filename + "==========");
            System.out.println("");
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
            UtfplParser parser = new UtfplParser(tokenStream);  // create worker
            UtfplParser.rule_return parser_ret = parser.rule();  // worker works
            CommonTree tree = (CommonTree)parser_ret.getTree();
            CommonTreeNodeStream nodes = new CommonTreeNodeStream(tree);
            
            /* ******** ******** */
            // tree parsing
            Utfpl_tree walker = new Utfpl_tree(nodes);  // create worker

            ProgramTree prog = walker.rule();  // worker works
            
            /* ***************** ****************** */
            // naming construction
            MapScope<TID> libScope = new MapScope<TID>();
            CCompUtils.populateAllFunctions(libScope);
            NamingVisitor nameV = new NamingVisitor(libScope);
            prog.accept(nameV);
            
            /* ***************** ****************** */
            // print tree
            TreePrinter tp = new TreePrinter();  // create worker
            String output1 = tp.print(prog);  // worker works
            
            System.out.println("==program is ==========================");
            System.out.println(output1);
            
            
            /* ***************** ****************** */
            // generate program of instructions
            InstructionTransformer insV = new InstructionTransformer();  // create worker
            ProgramInstruction programIns = insV.trans(prog);  // worker works
            
            /* ***************** ****************** */
            // print instructions
            InstructionPrinter insPrinter = new InstructionPrinter(Type.INS);  // create worker
            String outputINS = insPrinter.print(programIns);  // worker works
            System.out.println("==instructions are ==========================");
            System.out.println(outputINS);
            
            /* ***************** ****************** */
            // generate new program of instructions by processing
            ProgramInstruction programIns2 = InstructionClosureConverter.convert(programIns);
            
            /* ***************** ****************** */
            // print instructions
            String outputINS2 = insPrinter.print(programIns2);  // worker works
            System.out.println("==instructions after closure conversion are ==========================");
            System.out.println(outputINS2);
            
            /* ***************** ****************** */
            // generate new program of instructions by processing
            ProgramInstruction programIns3 = InstructionProgramProcessor.processProgram(programIns2);
            
            /* ***************** ****************** */
            // print instructions
            String outputINS3 = insPrinter.print(programIns3);  // worker works
            System.out.println("==instructions after if transformation are ==========================");
            System.out.println(outputINS3);
            
            /* ***************** ****************** */
            // generating CSPS program
            CSPSTransformer cspsV = new CSPSTransformer();
            ProgramCSPS programCSPS = cspsV.trans(programIns3);
            
            /* ***************** ****************** */
            // print csps program
            CSPSPrinter cspsPrinter = new CSPSPrinter();
            String outputCSPS = cspsPrinter.printProgram(programCSPS);
            System.out.println("==CSPS code is ==========================");
            System.out.println(outputCSPS);

            FileWriter fwCSP = new FileWriter("test/" + classname
                    + ".mycsps");
            BufferedWriter bwCSP = new BufferedWriter(fwCSP);
            bwCSP.write(outputCSPS);
            bwCSP.close();
                        
            /* ******** ******** */

            
            System.out.println("\n" + "==" + filename + " is O.K. " + "\n==============================================================================\n");
        }

    }
}
