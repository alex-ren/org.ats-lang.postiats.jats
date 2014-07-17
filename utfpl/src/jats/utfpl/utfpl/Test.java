package jats.utfpl.utfpl;

import jats.utfpl.utfpl.dynexp.ProgramUtfpl;
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
                "src/jats/utfpl/utfpl/test/test05.dats"

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

                    UtfplProgramParserJson utfplParser = new UtfplProgramParserJson();
                    ProgramUtfpl uProg = utfplParser.trans(fReader);
                    
                    UtfplTypeChecker tyChecker = new UtfplTypeChecker(uProg);
                    tyChecker.typecheck();

                    UtfplPrinter uPrinter = new UtfplPrinter();
                    String outputUTFPL = uPrinter.print(uProg);
                    
                    System.out.println("==utfpl's ast code (layer 01) is ==========================");
                    
                    System.out.println(outputUTFPL);
                    
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
