package jats.utfpl.csps;

import jats.utfpl.ccomp.CCompUtils;
import jats.utfpl.instruction.InsTransformer;
import jats.utfpl.instruction.InstructionPrinter;
import jats.utfpl.instruction.InstructionPrinter.Type;
import jats.utfpl.instruction.InstructionProcessor;
import jats.utfpl.instruction.UtfplInstruction;
import jats.utfpl.parser.UtfplLexer;
import jats.utfpl.parser.UtfplParser;
import jats.utfpl.parser.Utfpl_tree;
import jats.utfpl.tree.NamingVisitor;
import jats.utfpl.tree.Program;
import jats.utfpl.tree.TID;
import jats.utfpl.tree.TreePrinter;
import jats.utfpl.utils.FilenameUtils;
import jats.utfpl.utils.MapScope;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.antlr.runtime.ANTLRFileStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.TokenStream;
import org.antlr.runtime.tree.CommonTree;
import org.antlr.runtime.tree.CommonTreeNodeStream;

public class Test_01 {
    
    public static void main(String[] args) throws IOException, RecognitionException {
        String [] filenames = {
                "test/test20_csps_trans_.utfpl"
        
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
            MapScope<TID> libScope = new MapScope<TID>();
            CCompUtils.populateAllFunctions(libScope);
            NamingVisitor nameV = new NamingVisitor(libScope);
            prog.accept(nameV);
            
            TreePrinter tp = new TreePrinter();
            String output1 = tp.print(prog);
            
            System.out.println("==program is ==========================");
            System.out.println(output1);
            
            InsTransformer insV = new InsTransformer();
            @SuppressWarnings("unchecked")
            List<UtfplInstruction> inslst = (List<UtfplInstruction>)prog.accept(insV);
            
            Map<TID, TID> subMapTT = new HashMap<TID, TID>();
            
            TID mainFunLab = TID.createUserFun("main");
            List<UtfplInstruction> inslst2 = InstructionProcessor.InsLstProcess(inslst, subMapTT, mainFunLab, TID.ANONY);
            
            Map<TID, CTempID> subMapTCT = new HashMap<TID, CTempID>();
            List<CProcess> procLst = new ArrayList<CProcess>();
            List<CBlock> cbLst = CSPSTransformer.InsLst2CGroupLst(inslst2, subMapTCT, mainFunLab, procLst);
            
            /* ***************** ****************** */
            InstructionPrinter insPrinter = new InstructionPrinter(Type.INS);
            String outputINS = insPrinter.print(inslst);
            System.out.println("==instructions are ==========================");
            System.out.println(outputINS);
            
            String outputINS2 = insPrinter.print(inslst2);
            System.out.println("==instructions after processing are ==========================");
            System.out.println(outputINS2);
            
            CSPSPrinter cspsPrinter = new CSPSPrinter();
            System.out.println("==CSP# code is ==========================");
//            String outputCSPS = cspsPrinter.print(cbLst);
            
            
            
//            FileWriter fwINS = new FileWriter("test/" + classname
//                    + ".ins");
//            BufferedWriter bwINS = new BufferedWriter(fwINS);
//            bwINS.write(outputINS);
//            bwINS.close();
                        
            /* ******** ******** */

            
            System.out.println("\n" + "==" + filename + " is O.K. " + "\n==============================================================================\n");
        }

    }
}
