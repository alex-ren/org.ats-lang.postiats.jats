package jats.utfpl.csps;

import jats.utfpl.ccomp.CCompUtils;
import jats.utfpl.instruction.InstructionClosureConverter;
import jats.utfpl.instruction.InstructionPrinter;
import jats.utfpl.instruction.InstructionProgramProcessor;
import jats.utfpl.instruction.InstructionTransformer;
import jats.utfpl.instruction.ProgramInstruction;
import jats.utfpl.instruction.TID;
import jats.utfpl.instruction.InstructionPrinter.Type;
import jats.utfpl.parser.NamingVisitor;
import jats.utfpl.tree.ProgramTree;
import jats.utfpl.tree.TreeFromUtfpl;
import jats.utfpl.tree.TreePrinter;
import jats.utfpl.utfpl.UtfplPrinter;
import jats.utfpl.utfpl.UtfplProgram;
import jats.utfpl.utfpl.UtfplProgramParserJson;
import jats.utfpl.utils.FilenameUtils;
import jats.utfpl.utils.MapScope;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.antlr.runtime.RecognitionException;

public class test02_UTFPL {
    public static void main(String[] args) throws IOException, RecognitionException {
        String [] filenames = {
                "test/json/test01_dats.json"
                , "test/json/test02_dats.json"
    
        };
    
        for (String filename: filenames) {
            System.out.println("==Processing file " + filename + "==========");
            File file = new File(filename);
            String classname = FilenameUtils.removeExtension(file.getName());
            FileReader fReader = new FileReader(file);
    
            UtfplProgramParserJson utfplParser = new UtfplProgramParserJson();
            UtfplProgram uProg = utfplParser.trans(fReader);
            
            System.out.println("======= Generating Utfpl from JSON stream is finished ========== ");
            
            UtfplPrinter uPrinter = new UtfplPrinter();
            String output = uPrinter.print(uProg);
            
            System.out.println(output);
            
            FileWriter fwUTFPL = new FileWriter("test/json/" + classname
                    + ".myutfpl");
            BufferedWriter bwUTFPL = new BufferedWriter(fwUTFPL);
            bwUTFPL.write(output);
            bwUTFPL.close();
            
            System.out.println("======= Printing Utfpl is finished ========== ");
            
            TreeFromUtfpl treeGen = new TreeFromUtfpl();
            
            ProgramTree prog = treeGen.trans(uProg);
            
            MapScope<TID> libScope = new MapScope<TID>();
            CCompUtils.populateAllFunctions(libScope);
            NamingVisitor nameV = new NamingVisitor(libScope);
            prog.accept(nameV);
            
            TreePrinter tp = new TreePrinter();
            output = tp.print(prog);
            
            System.out.println("==program is ==========================");
            System.out.println(output);
            
            
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
