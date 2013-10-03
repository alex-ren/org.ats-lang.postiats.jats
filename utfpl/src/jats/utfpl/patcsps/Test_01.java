package jats.utfpl.patcsps;

import jats.utfpl.ccomp.CCompUtils;
import jats.utfpl.csps.CSPSPrinter;
import jats.utfpl.csps.CSPSTransformer;
import jats.utfpl.csps.ProgramCSPS;
import jats.utfpl.instruction.InsTransformer;
import jats.utfpl.instruction.InstructionPrinter;
import jats.utfpl.instruction.ProgramIns;
import jats.utfpl.instruction.TID;
import jats.utfpl.instruction.InstructionPrinter.Type;
import jats.utfpl.instruction.InstructionProcessor;
import jats.utfpl.parser.NamingVisitor;
import jats.utfpl.parser.UtfplLexer;
import jats.utfpl.parser.UtfplParser;
import jats.utfpl.parser.Utfpl_tree;
import jats.utfpl.tree.Program;
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

public class Test_01 {
    
    public static void main(String[] args) throws IOException, RecognitionException {
        String [] filenames = {
//                "test/test20_csps_trans_.utfpl"
//"test/test21_csps_trans_ret_proc_call.utfpl"
//                , "test/test22_csp_trans_2if.utfpl"
//                "test/test23_csp_trans_fact.utfpl"
                "test/test30_csp_multithread.utfpl"
                
        
        };

        for (String filename: filenames) {
            System.out.println("==Processing file " + filename + "==========");
            System.out.println("============================================");
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

            Program prog = walker.rule();  // worker works
            
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
            InsTransformer insV = new InsTransformer();  // create worker
            ProgramIns programIns = insV.trans(prog);  // worker works
            
            /* ***************** ****************** */
            // print instructions
            InstructionPrinter insPrinter = new InstructionPrinter(Type.INS);  // create worker
            String outputINS = insPrinter.print(programIns);  // worker works
            System.out.println("==instructions are ==========================");
            System.out.println(outputINS);
            
            /* ***************** ****************** */
            // generate new program of instructions by processing
            InstructionProcessor insProcessor = new InstructionProcessor();  // create worker
            ProgramIns programIns2 = insProcessor.process(programIns);  // worker works
            
            /* ***************** ****************** */
            // print instructions
            String outputINS2 = insPrinter.print(programIns2);  // worker works
            System.out.println("==instructions after processing are ==========================");
            System.out.println(outputINS2);
            
            /* ***************** ****************** */
            // generating CSPS program
            CSPSTransformer cspsV = new CSPSTransformer();
            ProgramCSPS programCSPS = cspsV.trans(programIns2);
            
            /* ***************** ****************** */
            // print csps program
            CSPSPrinter cspsPrinter = new CSPSPrinter();
            String outputCSPS = cspsPrinter.print(programCSPS);
            System.out.println("==CSPS code is ==========================");
            System.out.println(outputCSPS);
            
            /* ***************** ****************** */
            // generating patcsps program
            PatCspsTransformer patcspsV = new PatCspsTransformer();
            PModel programPCSPS = patcspsV.trans(programCSPS);
            
            /* ***************** ****************** */
            // print patcsps program
            PATCSPSPrinter patcspsPrinter = new PATCSPSPrinter();
            String outputPATCSPS = patcspsPrinter.print(programPCSPS);
            System.out.println("==PAT CSP# code is ==========================");
            System.out.println(outputPATCSPS);

            FileWriter fwINS = new FileWriter("test/" + classname
                    + ".csps");
            BufferedWriter bwINS = new BufferedWriter(fwINS);
            bwINS.write(outputPATCSPS);
            bwINS.close();
                        
            /* ******** ******** */

            
            System.out.println("\n" + "==" + filename + " is O.K. " + "\n==============================================================================\n");
        }

    }
}
















