package jats.utfpl.stfpl;

import jats.utfpl.stfpl.dynexp.ProgramStfpl2;
import jats.utfpl.stfpl.dynexp.ProgramStfpl2Printer;
import jats.utfpl.stfpl.dynexp3.DynExp3Transformer;
import jats.utfpl.stfpl.dynexp3.ProgramStfpl3;
import jats.utfpl.stfpl.dynexp3.ProgramStfpl3Printer;
import jats.utfpl.stfpl.instructions.InstructionPrinter;
import jats.utfpl.stfpl.instructions.InstructionTransformer;
import jats.utfpl.stfpl.instructions.ProgramIns;
import jats.utfpl.stfpl.instructions.SIdFactory;
import jats.utfpl.stfpl.mcinstruction.MCInstructionPrinter;
import jats.utfpl.stfpl.mcinstruction.MCInstructionTransformer;
import jats.utfpl.stfpl.mcinstruction.MCSIdFactory;
import jats.utfpl.stfpl.mcinstruction.ProgramMCIns;
import jats.utfpl.stfpl.mcinstruction.AuxMCIns.AddressAllocator;
import jats.utfpl.stfpl.mycspinstructions.MyCspInsPrinter;
import jats.utfpl.stfpl.mycspinstructions.MyCspInsTransformer;
import jats.utfpl.stfpl.mycspinstructions.ProgramMyCspIns;
import jats.utfpl.stfpl.pats.PATCSPSPrinter;
import jats.utfpl.stfpl.pats.PModel;
import jats.utfpl.stfpl.pats.PatCspsTransformer;
import jats.utfpl.utils.FilenameUtils;
import jats.utfpl.utils.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class ModelGenerater {
	private String m_inputpath;
	private String m_outputpath;
	private String m_patpath;
	
	private String m_dyn;
	private String m_dyn3;
	private String m_inss;
	private String m_mcinss;
    private String m_mycspinss;
    private String m_patsinss;
	
	
	
	public ModelGenerater(String inputpath, String outputpath, String patpath) {
		m_inputpath = inputpath;
        m_outputpath = outputpath;
        m_patpath = patpath;
    	m_dyn = null;
    	m_dyn3 = null;
    	m_inss = null;
	}
	
	public String getPATModel() {
		return m_patsinss;
	}
	
	static String getProcessCommand(ProcessBuilder pb) {
		List<String> coms = pb.command();
		String command = "";
		for (String com: coms) {
			command += com + " ";
		}
		
		return command;
	}
	
	public int generate(int level) throws IOException, InterruptedException {

        System.out.println("==Processing file " + m_inputpath + "==========");
        System.out.println("");
        
        File finput = new File(m_inputpath);
        
        if (FilenameUtils.isATS(finput)) {
        	// Create temporary directory.
        	File tempDir = new File(System.getProperty("java.io.tmpdir", null), "conats" + Long.toString(System.nanoTime()));
            if (!tempDir.exists() && !tempDir.mkdir())
                throw new IOException("Failed to create temporary directory " + tempDir);
            Log.log4j.info("tempDir is " + tempDir.getAbsolutePath());
            
            // Generate list for remote files.
            File list_file = new File(tempDir.getAbsolutePath(), "pdgreloc.json");
        	ProcessBuilder pb = new ProcessBuilder("patsopt", 
        			"--pkgreloc", "-DATS", "ATSPKGRELOCROOT=\"" + tempDir.getAbsolutePath() + "\"",
        			"--output-w", list_file.getAbsolutePath(),
        			"-d", finput.getAbsolutePath());
        	
        	System.out.println("cmd is " + getProcessCommand(pb));
        	pb.redirectErrorStream(true);
        	Process child = pb.start();
        	int returnCode = child.waitFor();
        	System.out.println("returnCode is " + returnCode);
        	if (0 != returnCode) {
        		String line;
        		BufferedReader reader = new BufferedReader(new InputStreamReader(child.getInputStream()));
        		while ((line = reader.readLine()) != null) {
        			System.err.println(line);
        		}
        		return returnCode;
        	}
        	
        	// Download remote files.
        	pb = new ProcessBuilder("atspkgreloc_curl", list_file.getAbsolutePath());
        	System.out.println("cmd is " + getProcessCommand(pb));
        	pb.redirectErrorStream(true);
        	child = pb.start();
        	returnCode = child.waitFor();
        	System.out.println("returnCode is " + returnCode);
        	if (0 != returnCode) {
        		String line;
        		BufferedReader reader = new BufferedReader(new InputStreamReader(child.getInputStream()));
        		while ((line = reader.readLine()) != null) {
        			System.err.println(line);
        		}
        		return returnCode;
        	}
        	
        	// ...
        	
        	File path_json = FilenameUtils.toJson(finput);
        	pb = new ProcessBuilder("patsopt", "-o", path_json.getAbsolutePath(),
                    "--jsonize-2", "-d", finput.getAbsolutePath());
        	pb.redirectErrorStream(true);
        	child = pb.start();
        	
        	String cmd = "patsopt -o " + path_json.getAbsolutePath() + " --jsonize-2 -d " + finput.getAbsolutePath();
        	System.out.println("cmd is " + cmd);
//        	Process child = Runtime.getRuntime().exec(cmd);
        	returnCode = child.waitFor();
        	System.out.println("returnCode is " + returnCode);
        	if (0 == returnCode) {
                FileReader fReader = new FileReader(path_json);

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
                FileWriter fwSTFPL2 = new FileWriter(FilenameUtils.changeExt(path_json, FilenameUtils.cSTFPL2));
                BufferedWriter bwSTFPL2 = new BufferedWriter(fwSTFPL2);
                bwSTFPL2.write(outputSTFPL2);
                bwSTFPL2.close();
                
                m_dyn = outputSTFPL2;
                if (level <= 2) {
                	System.out.println("\n" + "==" + m_inputpath + " is O.K. " + " ==============================================================================\n");
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
                	System.out.println("\n" + "==" + m_inputpath + " is O.K. " + " ==============================================================================\n");
                	return 0;
                }

                /* ************* ************** */
                
                System.out.println("== Generating instruction start ==========================");
                
                SIdFactory sid_factory = new SIdFactory();
                InstructionTransformer ins_cvt = new InstructionTransformer(sid_factory, prog3);
                ProgramIns prog_in = ins_cvt.transform();
                
                System.out.println("== Generating instruction end ==========================");
                
                InstructionPrinter insPrinter = new InstructionPrinter();
                String outputIns = insPrinter.print(prog_in);
                System.out.println("==stfpl's code (layer IStfplInstruction) is ==========================");
                System.out.println(outputIns);
                
                m_inss = outputIns;
                if (level <= 4) {
                    System.out.println("\n" + "==" + m_inputpath + " is O.K. " + " ==============================================================================\n");
                    return 0;
                }

                /* ************* ************** */
                
                System.out.println("== Generating mcinstruction start ==========================");
                AddressAllocator addr_alloc = new AddressAllocator();
                MCSIdFactory mcsid_factory = new MCSIdFactory(sid_factory, addr_alloc);
                MCInstructionTransformer mcins_cvt = new MCInstructionTransformer(
                                         mcsid_factory
                                       , ins_cvt.getFunMap()
                                       , prog_in);

                ProgramMCIns prog_mcins = mcins_cvt.transform();
                
                System.out.println("== Generating mcinstruction end ==========================");
                
                MCInstructionPrinter mcinsPrinter = new MCInstructionPrinter();
                String outputMCIns = mcinsPrinter.print(prog_mcins);
                System.out.println("==stfpl's code (layer IMCInstruction) is ==========================");
                System.out.println(outputMCIns);

                m_mcinss = outputMCIns;
                if (level <= 5) {
                    System.out.println("\n" + "==" + m_inputpath + " is O.K. " + " ==============================================================================\n");
                    return 0;
                }
                
                /* ************* ************** */
                
                System.out.println("== Generating mycspinstruction start ==========================");

                MyCspInsTransformer mycspins_cvt = new MyCspInsTransformer(prog_mcins, mcsid_factory);

                ProgramMyCspIns prog_mycspins = mycspins_cvt.transform();
                
                System.out.println("== Generating mycspinstruction end ==========================");
                
                MyCspInsPrinter mycspinsPrinter = new MyCspInsPrinter();
                String outputMyCspIns = mycspinsPrinter.print(prog_mycspins);
                System.out.println("==stfpl's code (layer MyCspInstruction) is ==========================");
                System.out.println(outputMyCspIns);

                m_mycspinss = outputMyCspIns;
                if (level <= 6) {
                    System.out.println("\n" + "==" + m_inputpath + " is O.K. " + " ==============================================================================\n");
                    return 0;
                }
                
                /* ************* ************** */
                
                System.out.println("== Generating patsinstruction start ==========================");

                PatCspsTransformer patsins_cvt = new PatCspsTransformer(prog_mycspins);

                PModel prog_patsins = patsins_cvt.transform();
                
                System.out.println("== Generating patsinstruction end ==========================");
                
                PATCSPSPrinter patsinsPrinter = new PATCSPSPrinter();
                String outputPatsIns = patsinsPrinter.print(prog_patsins);
                
                System.out.println("==stfpl's code (layer PatsInstructions) is ==========================");
//                System.out.println(outputPatsIns);
                
                FileWriter fwPats = new FileWriter(FilenameUtils.changeExt(path_json, FilenameUtils.cPATCSPS));
                BufferedWriter bwPats = new BufferedWriter(fwPats);
                bwPats.write(outputPatsIns);
                bwPats.close();

                m_patsinss = outputPatsIns;
                if (level <= 7) {
                    System.out.println("\n" + "==" + m_inputpath + " is O.K. " + " ==============================================================================\n");
                    return 0;
                }
                
                /* ************* ************** */
                
                File path_csp = FilenameUtils.changeExt(path_json, FilenameUtils.cPATCSPS);
                File path_result = null;
                
                if (null == m_outputpath) {
                	path_result = FilenameUtils.changeExt(path_json, FilenameUtils.cTxt);
                } else {
                	path_result = new File(m_outputpath);
                }

            	ProcessBuilder pbpat3 = new ProcessBuilder("mono", m_patpath, "-csp", path_csp.getAbsolutePath(), path_result.getAbsolutePath());
            	pbpat3.redirectErrorStream(true);
            	Process childpat = pbpat3.start();
            	
                String cmdpat = "mono " + m_patpath + " -csp " + 
            			path_csp.getAbsolutePath() + " " + path_result.getAbsolutePath();
            	System.out.println("cmdpat is " + cmdpat);

            	int returnCodePat = childpat.waitFor();
            	System.out.println("returnCode is " + returnCodePat);
            	if (0 == returnCodePat) {
                    System.out.println("== Model Checking succeeded.");
            		String line;
            		BufferedReader reader = new BufferedReader(new InputStreamReader(childpat.getInputStream()));
            		while ((line = reader.readLine()) != null) {
            			System.out.println(line);
            		}
            	} else {
            		String line;
            		BufferedReader reader = new BufferedReader(new InputStreamReader(childpat.getErrorStream()));
            		while ((line = reader.readLine()) != null) {
            			System.err.println("Model Checking failed.");
            			System.err.println(line);
            		}
            		return returnCode;            		
            	}
                    
            	
                if (level <= 8) {
                    System.out.println("\n" + "==" + m_inputpath + " is O.K. " + " ==============================================================================\n");
                    return 0;
                }
                
                /* ************* ************** */
                throw new Error("level " + level + " is not supported.");

        	} else {
        		String line;
        		BufferedReader reader = new BufferedReader(new InputStreamReader(child.getInputStream()));
        		System.err.println("\"patsopt --jsonize-2\" failed.");
        		while ((line = reader.readLine()) != null) {
        			System.err.println(line);
        		}
        		return returnCode;            		
        	}

        } else {
        	throw new Error("non-ats file is not supported.");
        }

    }
	

}
