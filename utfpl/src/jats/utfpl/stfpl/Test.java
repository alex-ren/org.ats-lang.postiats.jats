package jats.utfpl.stfpl;

import jats.utfpl.csps.CSPSPrinter;
import jats.utfpl.stfpl.csharpins.CSInstructionTransformer;
import jats.utfpl.stfpl.csharpins.CSharpPrinter;
import jats.utfpl.stfpl.dynexp.ProgramStfpl2;
import jats.utfpl.stfpl.dynexp.ProgramStfpl2Printer;
import jats.utfpl.stfpl.dynexp3.Cd3ecl;
import jats.utfpl.stfpl.dynexp3.DynExp3Transformer;
import jats.utfpl.stfpl.dynexp3.ProgramStfpl3;
import jats.utfpl.stfpl.dynexp3.ProgramStfpl3Printer;
import jats.utfpl.stfpl.instructions.InstructionTransformer;
import jats.utfpl.utils.FilenameUtils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

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
                "test/src_ats/53_demo_mc_dyn.dats"
//                "src/jats/utfpl/stfpl/test/test08.dats"

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

                    StfplProgramParserJson utfplParser = new StfplProgramParserJson();
                    ProgramStfpl2 prog2 = utfplParser.trans(fReader);
                    
                    StfplTypeChecker tyChecker = new StfplTypeChecker(prog2);
                    tyChecker.typecheck();

                    ProgramStfpl2Printer uPrinter2 = new ProgramStfpl2Printer();
                    String outputUTFPL = uPrinter2.print(prog2);
                    
                    System.out.println("==stfpl's ast code (layer 02) is ==========================");
                    System.out.println(outputUTFPL);
                    FileWriter fwUTFPL = new FileWriter(FilenameUtils.changeExt(path, FilenameUtils.cUTFPL));
                    BufferedWriter bwUTFPL = new BufferedWriter(fwUTFPL);
                    bwUTFPL.write(outputUTFPL);
                    bwUTFPL.close();
                    
                    /* ************* ************** */
                    
//                    DynExp3Transformer d3transformer = new DynExp3Transformer(prog2.m_d2ecs);
//                    ProgramStfpl3 prog3 = d3transformer.transform();
//
//                    ProgramStfpl3Printer uPrinter3 = new ProgramStfpl3Printer();
//                    String outputUTFPL3 = uPrinter3.print(prog3);
//                    System.out.println("==stfpl's ast code (layer 03) is ==========================");
//                    System.out.println(outputUTFPL3);

                    
//                    InstructionTransformer ins_cvt = new InstructionTransformer();
//                    ins_cvt.transform_global(d3ecs);
//                    
//                    CSInstructionTransformer csins_cvt = new CSInstructionTransformer();
//                    csins_cvt.transformProgram(ins_cvt.getDecs(), 
//					                    		ins_cvt.getExts(),
//					                    		ins_cvt.getDefs(), 
//					                    		ins_cvt.getMainInss());
//                    
//                    CSharpPrinter csprinter = new CSharpPrinter(
//                                              csins_cvt.getDecs(),
//                                              csins_cvt.getExts(),
//                                              csins_cvt.getDefs(),
//                                              csins_cvt.getMainInss(),
//                                              csins_cvt.getMainName(),
//                                              csins_cvt.getTrack());
//                    
//                    String cs_output = csprinter.printCSharp();
//                    System.out.println("==csharp code is ==========================");
//                    System.out.println(cs_output);
//                    FileWriter cs_filew = new FileWriter(FilenameUtils.changeExt(path, FilenameUtils.cCSHARP));
//                    BufferedWriter cs_bw = new BufferedWriter(cs_filew);
//                    cs_bw.write(cs_output);
//                    cs_bw.close();
                    
//                    UtfplProgramProcessor processor = new UtfplProgramProcessor();
//                    uProg = processor.removeProof(uProg);
//                    outputUTFPL = uPrinter.print(uProg);
//                    
//                    System.out.println("==utfpl's ast code (layer 02) is ==========================");
//                    
//                    System.out.println(outputUTFPL);
                    

            	} else {
            		String line;
            		BufferedReader reader = new BufferedReader(new InputStreamReader(child.getErrorStream()));
            		while ((line = reader.readLine()) != null) {
            			System.err.println(line);
            		}
            		return;            		
            	}

            }

//            System.out.println("\n" + "==" + strPath + " is O.K. " + " ==============================================================================\n");
        }

    }

}
