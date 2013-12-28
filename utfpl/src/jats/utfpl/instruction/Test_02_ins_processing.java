package jats.utfpl.instruction;

import jats.utfpl.ccomp.CCompUtils;
import jats.utfpl.instruction.InstructionPrinter.Type;
import jats.utfpl.parser.NamingVisitor;
import jats.utfpl.parser.UtfplLexer;
import jats.utfpl.parser.UtfplParser;
import jats.utfpl.parser.Utfpl_tree;
import jats.utfpl.tree.ProgramTree;
import jats.utfpl.tree.TreeFromUtfpl;
import jats.utfpl.tree.TreePrinter;
import jats.utfpl.utfpl.ProgramUtfpl;
import jats.utfpl.utfpl.UtfplPrinter;
import jats.utfpl.utfpl.UtfplProgramParserJson;
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

public class Test_02_ins_processing {
    
    public static void main(String[] args) throws IOException, RecognitionException, InterruptedException {
        String [] paths = {
//                "test/test02_fact.utfpl"
//                , "test/test03_var.utfpl"
//                , "test/test04_if.utfpl"
//                , "test/test05_func_def.utfpl"
//                , "test/test06_func_call.utfpl"
//                , "test/c01_single_main.utfpl"                
//                , "test/test20_csps_trans_.utfpl"
//                , "test/test35_mutual_closure.utfpl"
//                "test/test09_all.utfpl"
        		"test/src_utfpl/10_thread_create.mutfpl"
        
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

                    UtfplProgramParserJson utfplParser = new UtfplProgramParserJson();
                    ProgramUtfpl uProg = utfplParser.trans(fReader);

                    UtfplPrinter uPrinter = new UtfplPrinter();
                    String outputUTFPL = uPrinter.print(uProg);
                    
                    System.out.println("==utfpl's ast code is ==========================");
                    
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
                        
            //  ================== ====================== ====================

            System.out.println("\n" + "==" + strPath + " is O.K. " + " ==============================================================================\n");
        }

    }
}
