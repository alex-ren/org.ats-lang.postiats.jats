package jats.utfpl.stfpl.dynexp3;

import jats.utfpl.stfpl.StfplProgramParserJson;
import jats.utfpl.stfpl.StfplTypeChecker;
import jats.utfpl.stfpl.dynexp.ProgramStfpl2;
import jats.utfpl.stfpl.dynexp.ProgramStfpl2Printer;
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
                "src/jats/utfpl/stfpl/test/test_helloworld.dats"

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
                    ProgramStfpl2 uProg = utfplParser.trans(fReader);
                    
                    StfplTypeChecker tyChecker = new StfplTypeChecker(uProg);
                    tyChecker.typecheck();

                    ProgramStfpl2Printer uPrinter = new ProgramStfpl2Printer();
                    String outputUTFPL = uPrinter.print(uProg);
                    
                    System.out.println("==utfpl's ast code after type checking (dynexp2) is ==========================");
                    
                    System.out.println(outputUTFPL);
                    
                    DynExp3Transformer exp3_transformer = new DynExp3Transformer(uProg.m_d2ecs);
                    List<Cd3ecl> d3ecs = exp3_transformer.transform();
                    
//                    UtfplProgramProcessor processor = new UtfplProgramProcessor();
//                    uProg = processor.removeProof(uProg);
//                    outputUTFPL = uPrinter.print(uProg);
//                    
//                    System.out.println("==utfpl's ast code (layer 02) is ==========================");
//                    
//                    System.out.println(outputUTFPL);
                    
                    FileWriter fwUTFPL = new FileWriter(FilenameUtils.changeExt(path, FilenameUtils.cUTFPL));
                    BufferedWriter bwUTFPL = new BufferedWriter(fwUTFPL);
                    bwUTFPL.write(outputUTFPL);
                    bwUTFPL.close();
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
