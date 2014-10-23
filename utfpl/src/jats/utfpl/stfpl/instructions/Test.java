package jats.utfpl.stfpl.instructions;


import jats.utfpl.stfpl.StfplProgramParserJson;
import jats.utfpl.stfpl.StfplTypeChecker;
import jats.utfpl.stfpl.dynexp.ProgramStfpl2;
import jats.utfpl.stfpl.dynexp.ProgramStfpl2Printer;
import jats.utfpl.stfpl.dynexp3.DynExp3Transformer;
import jats.utfpl.stfpl.dynexp3.ProgramStfpl3;
import jats.utfpl.stfpl.dynexp3.ProgramStfpl3Printer;
import jats.utfpl.utils.FilenameUtils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import org.antlr.runtime.RecognitionException;


public class Test {


    /**
     * @param args
     * @throws IOException 
     * @throws InterruptedException 
     * @throws RecognitionException 
     */
    public static void main(String[] args) throws IOException, InterruptedException, RecognitionException {
        String[] paths = { 
//                "test/ats2utfpl/test01.dats"
//                , "test/ats2utfpl/test02.dats"
//                "test/src_ats/51_2_4_slots.dats"
//                "test/src_ats/54_peterson.dats"
//                "src/jats/utfpl/utfpl/test/test01.dats",
//                "src/jats/utfpl/utfpl/test/test02.dats",
//                "src/jats/utfpl/utfpl/test/test03.dats"
//                "src/jats/utfpl/utfpl/test/test04.dats"
//                "src/jats/utfpl/stfpl/test/test05.dats"
//                "src/jats/utfpl/stfpl/test/test06.dats"
//                "src/jats/utfpl/stfpl/test/test07.dats"
//                "src/jats/utfpl/stfpl/test/test_helloworld.dats",
//                "src/jats/utfpl/stfpl/csharpins/test/01_tuple_op.dats"
//                "src/jats/utfpl/stfpl/csharpins/test/02_if_branch.dats"
//                "src/jats/utfpl/stfpl/csharpins/test/03_closure.dats"
//                "src/jats/utfpl/stfpl/csharpins/test/04_polymorphism.dats"
//                "test/src_ats/53_demo_mc_dyn.dats"
                "src/jats/utfpl/stfpl/test/test08.dats"
        	  , "src/jats/utfpl/stfpl/test/test_helloworld.dats"

        };

        for (String strPath: paths) {
        	
            System.out.println("==Processing file " + strPath + "==========");
            System.out.println("");
            
            File path = new File(strPath);
//            ProgramTree prog = null;
            
            if (FilenameUtils.isATS(path)) {
            	path = FilenameUtils.toJson(path);
            	
            	String cmd = "patsopt -o " + path.getPath() + " --jsonize-2 -d " + strPath + " 2>&1";
            	System.out.println("cmd is " + cmd);
            	Process child = Runtime.getRuntime().exec(cmd);
            	int returnCode = child.waitFor();
            	System.out.println("returnCode is " + returnCode);
            	if (0 == returnCode) {
                    FileReader fReader = new FileReader(path);

                    System.out.println("== Parsing JSON start ==========================");
                    StfplProgramParserJson stfplParser = new StfplProgramParserJson();
                    ProgramStfpl2 prog2 = stfplParser.trans(fReader);
                    System.out.println("== Parsing JSON end   ==========================");
                    
                    System.out.println("== Type Checking start ==========================");
                    StfplTypeChecker tyChecker = new StfplTypeChecker(prog2);
                    tyChecker.typecheck();
                    System.out.println("== Type Checking end   ==========================");

                    ProgramStfpl2Printer sPrinter2 = new ProgramStfpl2Printer();
                    String outputSTFPL2 = sPrinter2.print(prog2);
                    
                    System.out.println("==stfpl's ast code (layer 02) is ==========================");
                    System.out.println(outputSTFPL2);
                    FileWriter fwSTFPL2 = new FileWriter(FilenameUtils.changeExt(path, FilenameUtils.cSTFPL2));
                    BufferedWriter bwSTFPL2 = new BufferedWriter(fwSTFPL2);
                    bwSTFPL2.write(outputSTFPL2);
                    bwSTFPL2.close();

                    /* ************* ************** */
                    
                    System.out.println("== Generating dynexp3 start ==========================");
                    DynExp3Transformer d3transformer = new DynExp3Transformer(prog2.m_d2ecs);
                    ProgramStfpl3 prog3 = d3transformer.transform();
                    System.out.println("== Generating dynexp3 end ==========================");

                    ProgramStfpl3Printer sPrinter3 = new ProgramStfpl3Printer();
                    String outputSTFPL3 = sPrinter3.print(prog3);
                    System.out.println("==stfpl's ast code (layer 03) is ==========================");
                    System.out.println(outputSTFPL3);
                    
                    System.out.println("== Generating instruction start ==========================");
                    InstructionTransformer ins_cvt = new InstructionTransformer();
                    ins_cvt.transform_global(prog3);
                    
                    System.out.println("== Generating instruction end ==========================");
                    
                    InstructionPrinter insPrinter = new InstructionPrinter();
                    String outputIns = insPrinter.print(
                            ins_cvt.getDecs(), 
                            ins_cvt.getExts(), 
                            ins_cvt.getDefs(),
                            ins_cvt.getMainInss());
                    System.out.println("==stfpl's code (layer IStfplInstruction) is ==========================");
                    System.out.println(outputIns);

            	} else {
            		String line;
            		BufferedReader reader = new BufferedReader(new InputStreamReader(child.getErrorStream()));
            		while ((line = reader.readLine()) != null) {
            			System.err.println("Invalid ATS file.");
            			System.err.println(line);
            		}
            		return;            		
            	}

            }

            System.out.println("\n" + "==" + strPath + " is O.K. " + " ==============================================================================\n");
        }

    }

}

