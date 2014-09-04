package jats.utfpl.patcsps;

import jats.utfpl.ccomp.CCompUtils;
import jats.utfpl.csps.CSPSPrinter;
import jats.utfpl.csps.CSPSTransformer;
import jats.utfpl.csps.ProgramCSPS;
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
import jats.utfpl.stfpl.StfplProgramParserJson;
import jats.utfpl.stfpl.dynexp.ProgramStfpl2;
import jats.utfpl.stfpl.dynexp.ProgramStfpl2Printer;
import jats.utfpl.tree.ProgramTree;
import jats.utfpl.tree.TreeFromUtfpl;
import jats.utfpl.tree.TreePrinter;
import jats.utfpl.utils.FilenameUtils;
import jats.utfpl.utils.MapScope;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import org.antlr.runtime.ANTLRFileStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.TokenStream;
import org.antlr.runtime.tree.CommonTree;
import org.antlr.runtime.tree.CommonTreeNodeStream;

public class Test_01 {
    
    public static void main(String[] args) throws IOException, RecognitionException, InterruptedException {
        String [] paths = {
//        		"test/src_ats/01_global_variable.dats"
//        		, "test/src_ats/02_global_value.dats"
//        		, "test/src_ats/03_global_variable_update.dats"
//        		, "test/src_ats/04_proc_call_noarg.dats"
//        		, "test/src_ats/05_proc_call_args.dats"
//        		"test/src_ats/06_if_ins_notret.dats"
//        		"test/src_ats/07_if_ins_ret.dats"
//        		"test/src_ats/08_if_proc_notret.dats"
//        		"test/src_ats/09_if_proc_ret.dats"
//        		"test/src_utfpl/21_global_value_rep.mutfpl"
//        		"test/src_utfpl/10_thread_create.mutfpl"
//        		"test/src_ats/10_thread_create.dats"
//        		"test/src_ats/11_mutex_operation.dats"
//        		"test/src_ats/12_cond_operation.dats"
//        		"test/src_ats/13_sync_operation.dats"
//        		"test/src_utfpl/50_producer_consumer.mutfpl"
//        		"test/src_ats/50_producer_consumer.dats"
//        		"test/src_ats/14_if_branch.dats"
//                "test/src_ats/15_closure_global_value.dats"
//                "test/src_ats/16_list_opr.dats"
//                "test/src_mutfpl/17_extcode.mutfpl"
//                "test/src_ats/17_extcode.dats"
//                "test/src_ats/51_4_slots.dats"
//                "test/src_ats/18_global_array_opr.dats"
//                "test/src_ats/52_deadlock_init.dats"
//                "test/src_ats/53_demo_mc_dyn.dats"
//                "test/test_temp.utfpl"
//                "test/src_ats/51_2_4_slots.dats"
                "test/src_ats/54_peterson.dats"

        };

        for (String strPath: paths) {
        	
            System.out.println("==Processing file " + strPath + "==========");
            System.out.println("");
            
            File path = new File(strPath);
            ProgramTree prog = null;
            
            if (FilenameUtils.isATS(path)) {
            	path = FilenameUtils.toJson(path);
            	
            	String cmd = "patsopt -o " + path.getPath() + " --jsonize-2 -d " + strPath;
            	System.out.println("cmd is " + cmd);
            	Process child = Runtime.getRuntime().exec(cmd);
            	int returnCode = child.waitFor();
            	System.out.println("returnCode is " + returnCode);
            	if (0 == returnCode) {
                    FileReader fReader = new FileReader(path);

                    StfplProgramParserJson utfplParser = new StfplProgramParserJson();
                    ProgramStfpl2 uProg = utfplParser.trans(fReader);

                    ProgramStfpl2Printer uPrinter = new ProgramStfpl2Printer();
                    String outputUTFPL = uPrinter.print(uProg);
                    
                    System.out.println("==utfpl's ast code (layer 01) is ==========================");
                    
                    System.out.println(outputUTFPL);
                    
//x                    UtfplProgramProcessor processor = new UtfplProgramProcessor();
//x                    uProg = processor.removeProof(uProg);
                    outputUTFPL = uPrinter.print(uProg);
                    
                    System.out.println("==utfpl's ast code (layer 02) is ==========================");
                    
                    System.out.println(outputUTFPL);
                    
                    FileWriter fwUTFPL = new FileWriter(FilenameUtils.changeExt(path, FilenameUtils.cUTFPL));
                    BufferedWriter bwUTFPL = new BufferedWriter(fwUTFPL);
                    bwUTFPL.write(outputUTFPL);
                    bwUTFPL.close();
                    
                    TreeFromUtfpl treeV = new TreeFromUtfpl();
                    
                    prog = treeV.trans(uProg);
            	} else {
            		String line;
            		BufferedReader reader = new BufferedReader(new InputStreamReader(child.getInputStream()));
            		while ((line = reader.readLine()) != null) {
            			System.err.println(line);
            		}
            		return;            		
            	}

            } else {
                ANTLRFileStream fileStream = new ANTLRFileStream(path.getPath());
                
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

                prog = walker.rule();  // worker works
            }
     
            
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
            
            // ================== ====================== ====================
            
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
            
            // ================== ====================== ====================
            
            /* ***************** ****************** */
            // generate new program of instructions by processing
            InstructionClosureConverter insClosureConverter = new InstructionClosureConverter(programIns);
            ProgramInstruction programIns2 = insClosureConverter.convert();
            
            /* ***************** ****************** */
            // print instructions
            String outputINS2 = insPrinter.print(programIns2);  // worker works
            System.out.println("==instructions after closure conversion are ==========================");
            System.out.println(outputINS2);
            
            // ================== ====================== ==================== 
            
            /* ***************** ****************** */
            // generate new program of instructions by processing
            ProgramInstruction programIns3 = InstructionProgramProcessor.processProgram(programIns2);
            
            /* ***************** ****************** */
            // print instructions
            String outputINS3 = insPrinter.print(programIns3);  // worker works
            System.out.println("==instructions after if transformation are ==========================");
            System.out.println(outputINS3);
            
            // ================== ====================== ====================
            
            /* ***************** ****************** */
            // generating CSPS program
            CSPSTransformer cspsV = new CSPSTransformer();
            ProgramCSPS programCSPS = cspsV.trans(programIns3);
            
            /* ***************** ****************** */
            // print csps program
            CSPSPrinter cspsPrinter = new CSPSPrinter();
            String outputCSPS = cspsPrinter.printProgram(programCSPS);
            System.out.println("==My CSPS code is ==========================");
            System.out.println(outputCSPS);

            File pathCSPS = FilenameUtils.changeExt(path, FilenameUtils.cCSPS);
            FileWriter fwCSPS = new FileWriter(pathCSPS);
            BufferedWriter bwCSPS = new BufferedWriter(fwCSPS);
            bwCSPS.write(outputCSPS);
            bwCSPS.close();
            
            //  ================== ====================== ====================
            
            /* ***************** ****************** */
            // generating patcsps program
            PatCspsTransformer patcspsV = new PatCspsTransformer();
            PModel programPCSPS = patcspsV.transProg(programCSPS);
            
            /* ***************** ****************** */
            // print patcsps program
            PATCSPSPrinter patcspsPrinter = new PATCSPSPrinter();
            String outputPATCSPS = patcspsPrinter.print(programPCSPS);
            System.out.println("==PAT CSPS# code is ==========================");
            System.out.println(outputPATCSPS);

            File pathPATCSPS = FilenameUtils.changeExt(path, FilenameUtils.cPATCSPS);
            FileWriter fwPATCSPS = new FileWriter(pathPATCSPS);
            BufferedWriter bwPATCSPS = new BufferedWriter(fwPATCSPS);
            bwPATCSPS.write(outputPATCSPS);
            bwPATCSPS.close();
                        
            /* ******** ******** */
            
            //  ================== ====================== ====================

            System.out.println("\n" + "==" + strPath + " is O.K. " + " ==============================================================================\n");
        }

    }
}

