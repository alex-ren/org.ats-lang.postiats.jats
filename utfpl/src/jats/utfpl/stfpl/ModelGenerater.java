package jats.utfpl.stfpl;

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

public class ModelGenerater {
	private String m_path;
	
	private String m_dyn;
	private String m_dyn3;
	private String m_inss;
	
	
	public ModelGenerater(String strPath) {
        m_path = strPath;
    	m_dyn = null;
    	m_dyn3 = null;
    	m_inss = null;
	}
	
	public int generate(int level) throws IOException, InterruptedException {

        System.out.println("==Processing file " + m_path + "==========");
        System.out.println("");
        
        File path = new File(m_path);
//        ProgramTree prog = null;
        
        if (FilenameUtils.isATS(path)) {
        	path = FilenameUtils.toJson(path);
        	
        	String cmd = "patsopt -o " + path.getPath() + " --jsonize-2 -d " + m_path + " 2>&1";
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
                
                m_dyn = outputSTFPL2;
                if (level <= 2) {
                	System.out.println("\n" + "==" + m_path + " is O.K. " + " ==============================================================================\n");
                	return 0;
                }

                /* ************* ************** */
                
                System.out.println("== Generating dynexp3 start ==========================");
                DynExp3Transformer d3transformer = new DynExp3Transformer(prog2.m_d2ecs);
                ProgramStfpl3 prog3 = d3transformer.transform();
                System.out.println("== Generating dynexp3 end ==========================");

                ProgramStfpl3Printer sPrinter3 = new ProgramStfpl3Printer();
                String outputSTFPL3 = sPrinter3.print(prog3);
                System.out.println("==stfpl's ast code (layer 03) is ==========================");
                System.out.println(outputSTFPL3);
                
                m_dyn3 = outputSTFPL3;
                if (level <= 3) {
                	System.out.println("\n" + "==" + m_path + " is O.K. " + " ==============================================================================\n");
                	return 0;
                }

                /* ************* ************** */
                throw new Error("level " + level + " is not supported.");

        	} else {
        		String line;
        		BufferedReader reader = new BufferedReader(new InputStreamReader(child.getErrorStream()));
        		while ((line = reader.readLine()) != null) {
        			System.err.println("Invalid ATS file.");
        			System.err.println(line);
        		}
        		return returnCode;            		
        	}

        } else {
        	throw new Error("non-ats file is not supported.");
        }

    }
	

}
